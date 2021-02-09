CREATE TABLE usuarios (
 id  int auto_increment primary key,
 email  VARCHAR(15), 
 contraseña VARCHAR(100)
);

create table tipoUsuarios(
 id int auto_increment primary key,
 nombre varchar(25)
);

create table tabla_permisos(
	cod int auto_increment primary key,
	id_user int not null,
	id_tipo int not null,
	foreign key (id_user) references usuarios(id),
	foreign key (id_tipo) references tipoUsuarios(id)
);
	