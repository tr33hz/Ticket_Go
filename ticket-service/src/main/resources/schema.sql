DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS transporters CASCADE;
DROP TABLE IF EXISTS paths CASCADE;
DROP TABLE IF EXISTS tickets CASCADE;

CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    login    VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    fio      VARCHAR(255)        NOT NULL
);

CREATE TABLE transporters
(
    id     SERIAL PRIMARY KEY,
    name   VARCHAR(255) NOT NULL,
    number VARCHAR(255) NOT NULL
);

CREATE TABLE paths
(
    id             SERIAL PRIMARY KEY,
    start          VARCHAR(255) NOT NULL,
    finish         VARCHAR(255) NOT NULL,
    transporter_id INTEGER      NOT NULL REFERENCES transporters (id),
    travel_time    INTEGER
);

CREATE TABLE tickets
(
    id          SERIAL PRIMARY KEY,
    path_id     INTEGER        NOT NULL REFERENCES paths (id),
    date_time   DATE,
    seat_number INTEGER,
    price       NUMERIC(19, 4),
    available   BOOLEAN
);

