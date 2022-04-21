create sequence hibernate_sequence start 1 increment 1;

create table message (id int8 not null, creation_date timestamp, text varchar(255), primary key (id));

create table user_role (user_id int8 not null, roles varchar(255));

create table usr (
id int8 not null, activation_code varchar(255),
active boolean not null, email varchar(255),
first_name varchar(255) not null,
password varchar(255),
second_name varchar(255) not null,
username varchar(255),
primary key (id));

alter table if exists user_role add constraint FKfpm8swft53ulq2hl11yplpr5 foreign key (user_id) references usr;

