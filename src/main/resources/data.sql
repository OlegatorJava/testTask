CREATE TABLE book (
                      id NUMBER NOT NULL,
                      title VARCHAR2(150) NOT NULL,
                      author VARCHAR2(150) NOT NULL,
                      description VARCHAR2(150),
                      CONSTRAINT book_pk PRIMARY KEY (id)
);

INSERT INTO book (id, title, author, description) VALUES (1, 'Crime and Punishment', 'F. Dostoevsky', null);
INSERT INTO book (id, title, author, description) VALUES (2, 'Anna Karenina', 'L. Tolstoy', null);
INSERT INTO book (id, title, author, description) VALUES (3, 'The Brothers Karamazov', 'F. Dostoevsky', null);
INSERT INTO book (id, title, author, description) VALUES (4, 'War and Peace', 'L. Tolstoy', null);
INSERT INTO book (id, title, author, description) VALUES (5, 'Dead Souls', 'N. Gogol', null);
