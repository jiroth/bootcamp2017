-- create database ServicioClima;

-- use ServicioClima;

create table Atmosfera (
	idAtmosfera int not null auto_increment,
    humedad int not null,
    visibilidad decimal(3,1) not null,
    primary key(idAtmosfera)
);

create table Viento (
	idViento int not null auto_increment,
    direccion int not null,
    velocidad int not null,
    primary key(idViento)
);

create table Ubicacion (
	idUbicacion int not null auto_increment,
    ciudad varchar(100) not null,
    pais varchar(100) not null,
    primary key(idUbicacion)
);

create table Pronostico (
	idPronostico int not null auto_increment,
    fecha date not null,
    idUbicacion int not null,
    temperatura int not null,
    estado varchar(200) not null,
    idAtmosfera int not null,
    idViento int not null,
    primary key(idPronostico),
    constraint PronosticoUbicacion foreign key (idUbicacion) references Ubicacion (idUbicacion),
    constraint PronosticoAtmosfera foreign key (idAtmosfera) references Atmosfera (idAtmosfera),
    constraint PronosticoViento foreign key (idViento) references Viento (idViento)
);

create table PronosticoExtendido (
	idPronosticoExtendido int not null auto_increment,
    fecha date not null,
    dia varchar(50) not null,
    estado varchar(200) not null,
    minima int not null,
    maxima int not null,
    idPronostico int not null,
    primary key(idPronosticoExtendido),
    constraint PronosticoExtendidoPronostico foreign key (idPronostico) references Pronostico (idPronostico)
);