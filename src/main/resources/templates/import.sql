CREATE TABLE my_db.user (
                            id int NOT NULL AUTO_INCREMENT,
                            name varchar(15),
                            password varchar(25),
                            PRIMARY KEY (id)
) ;
INSERT INTO my_db.user (name, password)
VALUES
('ADMIN', 'ADMIN'),
('KAT', 'KAT'),
('IVAN', 'IVAN');





CREATE TABLE role (
                      id int NOT NULL AUTO_INCREMENT,
                      role varchar(15),
                      PRIMARY KEY (id)
) ;
INSERT INTO my_db.role (role)
VALUES
('ROLE_ADMIN'),
('ROLE_USER');



CREATE TABLE my_db.user_role (
                                 user_id int,
                                 roles_id int,
                                 FOREIGN KEY (user_id) references user(id)
) ;
INSERT INTO my_db.user_role (user_id, roles_id)
VALUES
('1','1'),
('2', '2'),
('3','2');