insert into address (street, house_number, postal_code, city, country_code) VALUES
('Street HERE', 1,'D123A','City here 1','US'),
('Street HERE 2', 1,'C123A','City here 2','AR'),
('Street HERE 3', 1,'F123A','City here 3','SA'),
('Street HERE 4', 1,'B123A','City here 4','MX'),
('Street HERE 5', 1,'D343A','City here 3','CA');

insert into geo_location (latitude, longitude) VALUES
(1.23123,2.213123),
(11.23123,21.213123),
(111.23123,211.213123),
(1111.23123,2111.213123),
(-9991.23123,-9992.213123);

insert into store (name, phone, address_id, geo_location_id, company_code) VALUES
('Albert Heign Store','+1-666-6578943',1,1,'AH'),
('Clothes Store','+1-666-6578943',2,2,'ET'),
('Product Store','+1-666-6578943',3,3,'ET'),
('Cleaning Products store','+1-666-6578943',4,4,'AH'),
('Store #234','+1-666-6578943',5,5,'AH');