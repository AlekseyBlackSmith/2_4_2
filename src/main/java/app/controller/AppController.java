package app.controller;

import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AppController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String toLogin1() {
        return "login";
    }

    @GetMapping("/login")
    public String toLogin2() {
        return "login";
    }



    @GetMapping(value = "/show")
    public String userPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", userService.getUserByNameWithRoles(authentication.getName()));
        model.addAttribute("roles", userService.getUserByNameWithRoles(authentication.getName()).getRoles());
        return "show";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserByIdWithRoles(id));
        model.addAttribute("roles", userService.getUserByIdWithRoles(id).getRoles());
        return "show";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/new")
    public ModelAndView newUser(ModelAndView mav) {
        mav.addObject("user", new User());
        mav.addObject("allRoles", roleService.getAllRoles());
        mav.setViewName("new");
        return mav;
    }

    @PostMapping("/new")
    public ModelAndView create(ModelAndView mav, @ModelAttribute("user") User user, @ModelAttribute("rolesSelected") Long[] rolesId) {
        for(Long roleId : rolesId) {
            user.setRole(roleService.getRoleById(roleId));
        }
        userService.addUser(user);
        mav.addObject("users", userService.getAllUsers());
        mav.setViewName("users");
        return mav;
    }


    @GetMapping("/{id}/update")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "update";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                        @RequestParam("rolesSelected") Long[] rolesId) {
        for(Long roleId : rolesId) {
            System.out.println(roleId);
            user.setRole(roleService.getRoleById(roleId));
        }
        userService.updateUser(user);
        return "redirect:/users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public String delite(@PathVariable("id") Long id) {
        userService.removeUserById(id);
        return "redirect:/users";
    }

}
