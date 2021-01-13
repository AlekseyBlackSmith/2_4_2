create table users
(
    userId       int          not null auto_increment primary key,
    userName     varchar(45)  not null,
    userPassword varchar(255) not null
)
ENGINE = InnoDB;


create table roles
(
    roleId   int         not null auto_increment primary key,
    roleName varchar(45) not null
)
ENGINE = InnoDB;


create table users_roles
(
    id      int not null auto_increment primary key,
    user_id int not null,
    role_id int not null,

    foreign key (user_id) references users (userId) ,
    foreign key (role_id) references roles (roleId),

    unique (user_id, role_id)
)
ENGINE  = InnoDB;

Insert into users values (1, 'admin', 123);
Insert into users values (2, 'user', 321);

insert into roles values (1, 'ROLE_ADMIN');
insert into roles values (2, 'ROLE_USER');

insert into users_roles values (1, 1, 1);
insert into users_roles values (2, 2, 2);