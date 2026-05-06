create database PRG6_MI2A_2026
use PRG6_MI2A_2026

create table Phonebooks_040 (
	idContact int identity(1,1) primary key, 
	firstName varchar(255) not null, 
	lastName varchar(255), 
	birthDate date,
	phoneNumber varchar (20), 
	status varchar(10),
	address varchar(255)
);

alter table Phonebooks_040 
add constraint check_phoneNumber check (phoneNumber not like '%[^0-9]%');

alter table Phonebooks_040 
add constraint check_status check (status in ('1','2'));



