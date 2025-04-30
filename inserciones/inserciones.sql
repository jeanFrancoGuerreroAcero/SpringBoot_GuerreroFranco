insert into personas( last_name,full_name, programming_languaje, rol_id) values
("Sandra", "Leal", "Java", 1),
("María", "Lizarazo", "Python", 2),
("Herney", "López", "JavaScript", 1);

insert into roles(name) values ("administrador"), ("cliente");

insert into passport(expiration,id,person_id,number) values ('2029-10-02',2,1, 'NUM98765');

insert into project(id, name) values (1, "Amazon"), (2, "facebook");

insert into personas_project(persona_id,project_id) values(1,1),(2,2);


select * from roles;
select * from personas;
select * from passport;
select * from project;
select * from personas_project;
