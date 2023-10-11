insert into role(id,nome) values (1, 'ROLE_USER');
insert into role(id,nome) values (2, 'ROLE_ADMIN');

insert into participantes(id,email,endereco,login,nome,senha,telefone) values
    (1,'user@gmail.com','endereco','user','user','$2a$10$HKveMsPlst41Ie2LQgpijO691lUtZ8cLfcliAO1DD9TtZxEpaEoJe', '(99) 99999-9999');
insert into participantes(id,email,endereco,login,nome,senha,telefone) values
    (2,'admin@gmail.com','endereco','admin','admin','$2a$10$HKveMsPlst41Ie2LQgpijO691lUtZ8cLfcliAO1DD9TtZxEpaEoJe', '(99) 99999-9999');

insert into participantes_roles(participante_id,role_id) values(1, 1);
insert into participantes_roles(participante_id,role_id) values(2, 2);

INSERT INTO leiloes (id, data_final, data_inicio, hora_final, hora_inicio) VALUES
    (1, '2023-06-22', '2023-06-21', '12:00:00', '12:00:00'),
    (2, '2023-07-22', '2023-07-21', '12:00:00', '12:00:00'),
    (3, '2023-08-22', '2023-08-21', '12:00:00', '12:00:00'),
    (4, '2023-09-22', '2023-09-21', '12:00:00', '12:00:00'),
    (5, '2023-10-22', '2023-10-21', '12:00:00', '12:00:00');