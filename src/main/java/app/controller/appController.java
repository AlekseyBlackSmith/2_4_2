package app.controller;

import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class appController {
    private final UserService userService;

    @Autowired
    public appController(UserService userService) {
        this.userService = userService;
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
        String login = authentication.getName();
        System.out.println(login);
        model.addAttribute("user", userService.getByName(login));
        return "show";
    }

}
