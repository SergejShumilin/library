CREATE TABLE users (
    id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
    role ENUM(ADMIN, LIBRARIAN, READER) NOT NULL
);