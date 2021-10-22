DROP DATABASE IF EXISTS images_database;
CREATE DATABASE IF NOT EXISTS images_database;

DROP TABLE IF EXISTS images_database.images;
CREATE TABLE IF NOT EXISTS images_database.images (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    hashvalue BIGINT(64) UNSIGNED NOT NULL,
    upload_dir VARCHAR(100) NOT NULL,
    CONSTRAINT PRIMARY KEY (id),
    CONSTRAINT UNIQUE_nome UNIQUE KEY (nome)
    );

SELECT * FROM images_database.images;

DROP TABLE images_database.images;
DROP SCHEMA images_database;