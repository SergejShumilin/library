CREATE TABLE users (
    id INTEGER AUTO_INCREMENT NOT NULL,
    name VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
    role ENUM("admin", "librarian", "reader"),
    PRIMARY KEY (id)
);


CREATE TABLE books (
    id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    title VARCHAR(30) NOT NULL,
    author VARCHAR(30) NOT NULL,
    genre VARCHAR(30) NOT NULL,
    description VARCHAR(500) NOT NULL,
    number_instances VARCHAR(30) NOT NULL
);