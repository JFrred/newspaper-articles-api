DROP TABLE IF EXISTS article;

CREATE TABLE article
(
    id                INT PRIMARY KEY AUTO_INCREMENT,
    title             VARCHAR(100) NOT NULL,
    content           CLOB,
    publication_date  DATE,
    journal_name      VARCHAR(100),
    author_first_name VARCHAR(50),
    author_last_name  VARCHAR(50),
    created_at        TIMESTAMP
);