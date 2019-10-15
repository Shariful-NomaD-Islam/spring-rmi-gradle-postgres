CREATE TABLE person (
    id VARCHAR(50),
    name VARCHAR(100),
    address VARCHAR(100),

    PRIMARY KEY (id)
);
INSERT INTO person(id, name, address) VALUES ('p1', 'ami', 'dhaka');
INSERT INTO person(id, name, address) VALUES ('p2', 'tui', 'khulna');


CREATE TABLE book (
    id VARCHAR(50),
    name VARCHAR(100),
    author_name VARCHAR(100),

    PRIMARY KEY (id)
);
INSERT INTO book(id, name, author_name) VALUES ('b1', 'amar-boi', 'some-idiot');
INSERT INTO book(id, name, author_name) VALUES ('b2', 'arekta-boi', 'another-idiot');