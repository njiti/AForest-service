CREATE DATABASE wildlife_tracker;

\c wildlife_tracker

CREATE TABLE animals
(
    id   SERIAL PRIMARY KEY,
    name varchar,
    type varchar,
    health varchar,
    age varchar
);

CREATE TABLE sightings
(
    id          SERIAL PRIMARY KEY,
    animalid   int,
    location    varchar,
    ranger_name varchar,
    type varchar,
    sightedat timestamp
);

CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;