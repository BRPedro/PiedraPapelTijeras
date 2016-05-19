create database Juego
go
use Juego
--drop database Juego
go
create table Persona(
	id		int identity(1,1) not null,
	nombre	Varchar(20) unique,
	constraint pk_idPersona	primary key (id)
)
go

create table Torneo(
	id		int identity(1,1) not null,
	id_primero	int,
	id_segundo  int,
	constraint pk_id	primary key (id),
	constraint fk_PersonaP	foreign key (id_primero) references Persona(id) ,
	constraint fk_PersonaS	foreign key (id_segundo) references Persona(id) 
	
)

create procedure insercionTorneo(
	@nombreP		Varchar(20), 
	@nombreS		Varchar(20)
	
)
as
BEGIN
	DECLARE	@idP Int
	DECLARE @idS Int
    Begin Try
    Begin Tran Tadd
		if((select count(*) from Persona where nombre=@nombreP)>0)
		begin
			set @idP =(select id from Persona where nombre=@nombreP)
		end
		else
		begin
			insert Persona(nombre) values (@nombreP)
			set @idP =(select id from Persona where nombre=@nombreP)
		end
		if((select count(*) from Persona where nombre=@nombreS)>0)
		begin
			set @idS =(select id from Persona where nombre=@nombreS)
		end
		else
		begin
			insert Persona(nombre) values (@nombreS)
			set @idS =(select id from Persona where nombre=@nombreS)
		end
		insert Torneo(id_primero,id_segundo) values (@idP,@idS)
    End try
    Begin Catch
		raiserror('Ocurrio un Error:',1,2)			
        Rollback TRAN Tadd
    End Catch
END
GO
exec insercionTorneo 'p','u'