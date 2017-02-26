drop table client_table;
drop table user_table;
CREATE TABLE client_table
(
    client_id VARCHAR(255) PRIMARY KEY NOT NULL,
    access_token_validity_seconds INTEGER,
    additional_information VARCHAR(255),
    authorities VARCHAR(255),
    authorized_grant_types VARCHAR(255),
    client_secret VARCHAR(255),
    refresh_token_validity_seconds INTEGER,
    registered_redirect_uri VARCHAR(255),
    resource_ids VARCHAR(255),
    scope VARCHAR(255),
    scoped BOOLEAN NOT NULL,
    secret_required BOOLEAN NOT NULL
);
CREATE TABLE user_table
(
    id BIGINT PRIMARY KEY NOT NULL,
    authorities VARCHAR(255),
    email VARCHAR(255),
    enabled BOOLEAN NOT NULL,
    password VARCHAR(255),
    username VARCHAR(255)
);