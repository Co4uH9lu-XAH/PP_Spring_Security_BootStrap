CREATE TABLE user (
  id int NOT NULL AUTO_INCREMENT,
  username varchar(15),
  surname varchar(25),
  age int,
  password varchar(254) not null,
  PRIMARY KEY (id)
) ;

CREATE TABLE role (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(15),
  PRIMARY KEY (id)
) ;

CREATE TABLE users_roles (
	user_id int NOT NULL,
    role_id int NOT NULL,
    primary key (user_id, role_id),
    constraint users_roles_user_fk foreign key (user_id) references user(id),
    constraint users_roles_role_fk foreign key (role_id) references role(id)
);