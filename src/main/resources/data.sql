insert into client_table (client_id,client_secret,resource_ids,secret_required,scoped,scope,authorized_grant_types,registered_redirect_uri,authorities,access_token_validity_seconds,refresh_token_validity_seconds,additional_information)
 values ('newClient','$2a$10$dVQLmf9NPpBn4FZRtZdC7eGYpJk93rNEg8Z4kdarvHDdM3XszlE6m',null,true,true,'read','client_credentials,password,refresh_token,authorization_code',null,'ROLE_ADMIN',300,600,'{}');

insert into user_table (id, authorities, email, enabled, password, username)
values (1,'ROLE_ADMIN','teste@teste.com.br',true,'$2a$10$8AAAcIPOMzblBW3OlVEaFuBYlj3wPSRCN8R2O3nTaO9XdKRJkm17i','user')