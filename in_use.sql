CREATE TABLE in_use (
    user_id INTEGER NOT NULL,
    book_id INTEGER  NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);