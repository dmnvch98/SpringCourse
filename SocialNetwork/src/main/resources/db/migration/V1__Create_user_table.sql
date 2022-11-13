create table users (
                       id bigserial not null,
                       created_at timestamp,
                       password varchar(255),
                       role varchar(255),
                       username varchar(255),
                       primary key (id)
);

INSERT INTO users (created_at, password, role, username)
VALUES ('2022-10-24 00:00:00.000000', '$2a$12$8Sr2wtfcp3zhZ4x44lMwCuS//WNVLYVRsSlOHQcOXoLNJWH/BSvvG','USER', 'masha');
INSERT INTO users (created_at, password, role, username)
VALUES ('2022-10-24 00:00:00.000000', '$2a$12$dJbfI8BzMhCOkzp/PluY4OupoBRpFjat8W6rbjmPg89X1j1rnrr8y','USER', 'pasha');
INSERT INTO users (created_at, password, role, username)
VALUES ('2022-10-24 00:00:00.000000', '$2a$12$MQpT6LyFH36ISF5XyEGyWeKsQVlQh70MYpogqm7ZbKRKti/wDwXzm','USER', 'admin');
