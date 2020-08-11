insert into address (id, street, house_number, postal_code, city, country_code) VALUES
(1,'Street HERE', 1,'D123A','City here','US');

insert into geo_location (id, latitude, longitude) VALUES
(1,1.23123,2.213123);

insert into store (id, name, phone, address_id, geo_location_id, company_code) VALUES
(1,'Store Name','+1-666-6578943',1,1,'AH');