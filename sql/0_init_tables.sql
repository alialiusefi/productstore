create table address
(
    id           bigserial,
    street       text,
    house_number integer,
    postal_code  varchar(10) not null,
    city         text,
    country_code varchar(2)  not null,
    constraint ADDRESS_ID_PK primary key (id)
);

create table geo_location
(
    id        bigserial,
    latitude  decimal not null,
    longitude decimal not null,
    constraint GEOLOCATION_ID_PK primary key (id)
);

create table store
(
    id             bigserial,
    name           varchar(100) not null,
    phone          varchar(50)  not null,
    address_id     bigint       not null,
    geo_location_id bigint       not null,
    company_code   varchar(2)   not null,
    constraint STORE_ID_PK primary key (id),
    constraint ADDRESS_ID_FK foreign key (address_id) references address,
    constraint GEOLOCATION_ID_FK foreign key (geo_location_id) references geo_location
);

