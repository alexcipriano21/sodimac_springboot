drop database if exists sodimac;
create database sodimac;
use sodimac;

create table Usuario (
  id int auto_increment primary key,
  nombres char(50) not null,
  apellidos char(50) not null,
  dni char(8) not null unique,
  password char(255) not null,
  telefono char(9) not null,
  correo char(60) not null unique,
  rol char(20) default 'CLIENTE'
);

create table RecuperarPassword (
  correo char(60) primary key,
  token char(255) not null,
  expiraAt datetime not null,
  foreign key (correo) references Usuario(correo) on delete cascade
);

create table Categoria (
  id int auto_increment primary key,
  detalle char(50) unique
);

create table Sucursal (
  id int auto_increment primary key,
  nombre char(50) not null,
  direccion char(100) not null
);

create table Producto (
  id int auto_increment primary key,
  nombre char(100) not null,
  marca char(30) not null,
  precio decimal(10,2) not null,
  sku char(20) not null unique,
  idCategoria int,
  imagen char(255),
  descripcion char(255),
  foreign key (idCategoria) references Categoria(id)
);

create table Inventario (
  idSucursal int,
  idProducto int,
  stock int not null,
  primary key (idSucursal, idProducto),
  foreign key (idSucursal) references Sucursal(id),
  foreign key (idProducto) references Producto(id)
);

create table Pedido (
  id int auto_increment primary key,
  idUsuario int,
  total decimal(10,2) not null,
  estado char(20) default 'PREPARACION',
  fecha timestamp default current_timestamp,
  foreign key (idUsuario) references Usuario(id)
);

-- INSERTS (sin INTO)
insert Usuario (nombres, apellidos, dni, password, telefono, correo, rol)
values ('Juan','Perez','12345678','123456','987654321','juan@gmail.com','CLIENTE');

insert Categoria (detalle)
values ('Herramientas'), ('Construccion');

insert Producto (nombre, marca, precio, sku, idCategoria, imagen, descripcion)
values ('Taladro','Bosch',250.00,'SKU001',1,'img1.jpg','Taladro electrico');

insert Sucursal (nombre, direccion)
values ('Sucursal Centro','Av. Peru 123');

insert Inventario (idSucursal, idProducto, stock)
values (1,1,50);

insert Pedido (idUsuario, total, estado)
values (1,250.00,'PREPARACION');