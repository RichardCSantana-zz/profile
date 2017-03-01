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

insert into client_table (client_id,client_secret,resource_ids,secret_required,scoped,scope,authorized_grant_types,registered_redirect_uri,authorities,access_token_validity_seconds,refresh_token_validity_seconds,additional_information)
 values ('newClient','$2a$10$dVQLmf9NPpBn4FZRtZdC7eGYpJk93rNEg8Z4kdarvHDdM3XszlE6m',null,true,true,'read','client_credentials,password,refresh_token,authorization_code',null,'ROLE_ADMIN',300,600,'{}');

insert into user_table (id, authorities, email, enabled, password, username)
values (1,'ROLE_ADMIN','teste@teste.com.br',true,'$2a$10$8AAAcIPOMzblBW3OlVEaFuBYlj3wPSRCN8R2O3nTaO9XdKRJkm17i','user');

alter table client_table owner to application;
alter table user_table owner to application;
