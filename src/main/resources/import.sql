/* Populate tables */
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Jenny', 'Pucha', 'jenny.pucha@uncorreo.com', '2023-01-24', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('John', 'Smith', 'john.smith@uncorreo.com', '2023-01-24', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Javier', 'Lucero', 'javier.lucero@uncorreo.com', '2023-01-24', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Silvana', 'Torres', 'silvana.torres@uncorreo.com', '2023-01-24', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Ricardo', 'Lima', 'rlima@uncorreo.com', '2023-01-24', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Erick', 'Garcia', 'egarcia.@uncorreo.com', '2023-01-24', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Roberto', 'Humbolt', 'rhumbolt@uncorreo.com', '2023-01-24', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Rafael', 'Jima', 'rjima@uncorreo.com', '2023-01-25', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Juan', 'Valdivieso', 'jvaldivieso@uncorreo.com', '2023-01-25', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('James', 'Goity', 'jgoity@uncorreo.com', '2023-01-25', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Bruce', 'Latorre', 'blatorre@uncorreo.com', '2023-01-25', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Patricio', 'Ponce', 'pponce@uncorreo.com', '2023-01-25', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Fabian', 'Rios', 'frios@uncorreo.com', '2023-01-25', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Yazmin', 'Barrera', 'jbarrera@uncorreo.com', '2023-01-25', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Samantha', 'Diaz', 'sdiaz@uncorreo.com', '2023-01-25', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Franklin', 'Villota', 'fvillota@uncorreo.com', '2023-01-25', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Phillip', 'Carter', 'pcarter@uncorreo.com', '2023-01-25', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Stephane', 'Littrel', 'slittrel@uncorreo.com', '2023-01-25', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Victor', 'Villavicencio', 'v.villavicencio@uncorreo.com', '2023-01-25', '');  
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Andres', 'Aguas', 'aaguas@uncorreo.com', '2023-01-25', ''); 
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Kathy', 'Flores', 'kflores@uncorreo.com', '2023-01-26', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Lizbeth', 'Sanchez', 'lsanchez@uncorreo.com', '2023-01-26', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Freddy', 'Chicaiza', 'fchicaiza@uncorreo.com', '2023-01-26', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('John', 'Mena', 'jmena@uncorreo.com', '2023-01-26', '');
INSERT INTO clientes (nombre, apellido, email, create_at, foto) VALUES('Angel', 'Cuenca', 'acuenca@uncorreo.com', '2023-01-26', '');

/* Populate tabla productos */
INSERT INTO productos (nombre, precio, create_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());

/* Creamos algunas facturas */
INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 7);

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(3, 2, 6);

/* Creamos algunos usuarios con sus roles */
INSERT INTO `users` (username, password, enabled) VALUES ('andres','$2a$10$O9wxmH/AeyZZzIS09Wp8YOEMvFnbRVJ8B4dmAMVSGloR62lj.yqXG',1);
INSERT INTO `users` (username, password, enabled) VALUES ('admin','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',1);
INSERT INTO `users` (username, password, enabled) VALUES ('jpucha','$2a$10$TR9gUw3LmxhPoeYvhvj.4uLgTszmDW2yYqWUiMdTBUH7qg.EDQ72e',1);

INSERT INTO `authorities` (user_id, authority) VALUES (1,'ROLE_USER');
INSERT INTO `authorities` (user_id, authority) VALUES (2,'ROLE_ADMIN');
INSERT INTO `authorities` (user_id, authority) VALUES (2,'ROLE_USER');
INSERT INTO `authorities` (user_id, authority) VALUES (3,'ROLE_USER');