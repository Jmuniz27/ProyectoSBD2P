USE wuanPlus;
-- Departamento Creativo
INSERT INTO departamento_creativo (id_departamento, descripcion, nombre, email, id_director) VALUES
('DC001', 'Departamento Creativo', 'Creativo', 'creativo@example.com', null);

-- Departamento Producción
INSERT INTO departamento_produccion (id_departamento, descripcion, nombre, email, id_director) VALUES
('DP001', 'Departamento Producción', 'Producción', 'produccion@example.com', null);

-- Departamento Finanzas
INSERT INTO departamento_finanzas (id_departamento, descripcion, nombre, email, id_director) VALUES
('DF001', 'Departamento Finanzas', 'Finanzas', 'finanzas@example.com', null);
-- Inserción inicial de empleados sin asignar departamentos
INSERT INTO empleado (id_empleado, sueldoBase, nombre, apellido, puesto, contrasenia, direccion, idSupervisor, id_dep_creativo, id_dep_prod, id_dep_finanzas) VALUES
('0912345688', 1000, 'Carlos', 'Perez', 'Director Creativo', 'pass123', 'Direccion1', '0912345688', NULL, NULL, NULL),
('0912345689', 800, 'Maria', 'Gomez', 'Diseñador', 'pass123', 'Direccion2', '0912345688', NULL, NULL, NULL),
('0912345694', 750, 'Laura', 'Vega', 'Asistente Creativo', 'pass123', 'Direccion10', '0912345688', NULL, NULL, NULL),
('0912345690', 1000, 'Juan', 'Diaz', 'Director Producción', 'pass123', 'Direccion3', '0912345690', NULL, NULL, NULL),
('0912345691', 800, 'Ana', 'Martinez', 'Operario', 'pass123', 'Direccion4', '0912345690', NULL, NULL, NULL),
('0912345695', 750, 'Pedro', 'Castillo', 'Asistente Producción', 'pass123', 'Direccion9', '0912345691', NULL, NULL, NULL),
('0912345692', 1000, 'Luis', 'Torres', 'Director Finanzas', 'pass123', 'Direccion5', '0912345692', NULL, NULL, NULL),
('0912345693', 800, 'Sofia', 'Ramirez', 'Contador', 'pass123', 'Direccion6', '0912345692', NULL, NULL, NULL),
('0912345696', 750, 'Jorge', 'Lopez', 'Asistente Finanzas', 'pass123', 'Direccion7', '0912345692', NULL, NULL, NULL),
('0912345697', 800, 'Lucia', 'Pimentel', 'Contador', 'milu123', 'Direccion8', '0912345692', NULL, NULL, NULL),
('0931248686', 800, 'Cristina', 'Pimentel', 'Contador', 'pas2344', 'Direccion9', '0912345692',NULL, NULL, NULL);

-- Actualización de empleados para asignarles a los departamentos correspondientes
UPDATE empleado SET id_dep_creativo = 'DC001' WHERE id_empleado IN ('0912345688', '0912345689', '0912345694');
UPDATE empleado SET id_dep_prod = 'DP001' WHERE id_empleado IN ('0912345690', '0912345691', '0912345695');
UPDATE empleado SET id_dep_finanzas = 'DF001' WHERE id_empleado IN ('0912345692', '0912345693', '0912345696','0931248686');

-- Modificacion para añadir un empleado más y que los directores sean sus propios supervisores

UPDATE empleado SET idSupervisor = '0912345692' WHERE id_empleado = '0912345696';
UPDATE empleado SET idSupervisor = '0912345688' WHERE id_empleado = '0912345688';
UPDATE empleado SET idSupervisor = '0912345690' WHERE id_empleado = '0912345690';
UPDATE empleado SET idSupervisor = '0912345692' WHERE id_empleado = '0912345692';

-- insercion supervisores
UPDATE departamento_creativo SET id_director = '0912345688' WHERE id_departamento = 'DC001';
UPDATE departamento_produccion SET id_director = '0912345690' WHERE id_departamento = 'DP001';
UPDATE departamento_finanzas SET id_director = '0912345692' WHERE id_departamento = 'DF001';

INSERT INTO persona_contacto (cedula, nombre, apellido, email) VALUES
('0912345678', 'Carlos', 'Perez', 'carlos.perez@example.com'),
('0912345679', 'Maria', 'Gomez', 'maria.gomez@example.com'),
('0912345680', 'Juan', 'Diaz', 'juan.diaz@example.com'),
('0912345681', 'Ana', 'Martinez', 'ana.martinez@example.com'),
('0912345682', 'Luis', 'Torres', 'luis.torres@example.com'),
('0912345683', 'Sofia', 'Ramirez', 'sofia.ramirez@example.com'),
('0912345684', 'Jorge', 'Lopez', 'jorge.lopez@example.com'),
('0912345685', 'Marta', 'Sanchez', 'marta.sanchez@example.com'),
('0912345686', 'Pedro', 'Castillo', 'pedro.castillo@example.com'),
('0912345687', 'Laura', 'Vega', 'laura.vega@example.com');
INSERT INTO cliente (RUC, nombre_empresa, decrip_empresa, direccion, sitio_web, id_persona_contacto) VALUES
('0912345678001', 'Empresa1', 'Descripcion1', 'Direccion1', 'www.empresa1.com', '0912345678'),
('0912345679002', 'Empresa2', 'Descripcion2', 'Direccion2', 'www.empresa2.com', '0912345679'),
('0912345680003', 'Empresa3', 'Descripcion3', 'Direccion3', 'www.empresa3.com', '0912345680'),
('0912345681001', 'Empresa4', 'Descripcion4', 'Direccion4', 'www.empresa4.com', '0912345681'),
('0912345682002', 'Empresa5', 'Descripcion5', 'Direccion5', 'www.empresa5.com', '0912345682'),
('0912345683003', 'Empresa6', 'Descripcion6', 'Direccion6', 'www.empresa6.com', '0912345683'),
('0912345684001', 'Empresa7', 'Descripcion7', 'Direccion7', 'www.empresa7.com', '0912345684'),
('0912345685002', 'Empresa8', 'Descripcion8', 'Direccion8', 'www.empresa8.com', '0912345685'),
('0912345686003', 'Empresa9', 'Descripcion9', 'Direccion9', 'www.empresa9.com', '0912345686'),
('0912345687001', 'Empresa10', 'Descripcion10', 'Direccion10', 'www.empresa10.com', '0912345687');

INSERT INTO factura (num_factura, RUC, precio_total, fecha, id_dep_finanzas) VALUES
('F00001', '0912345678001', 1500, '2024-07-01', 'DF001'),
('F00002', '0912345679002', 2000, '2024-07-02', 'DF001'),
('F00003', '0912345680003', 2500, '2024-07-03', 'DF001'),
('F00004', '0912345681001', 3000, '2024-07-04', 'DF001'),
('F00005', '0912345682002', 3500, '2024-07-05', 'DF001'),
('F00006', '0912345683003', 4000, '2024-07-06', 'DF001'),
('F00007', '0912345684001', 4500, '2024-07-07', 'DF001'),
('F00008', '0912345685002', 5000, '2024-07-08', 'DF001'),
('F00009', '0912345686003', 5500, '2024-07-09', 'DF001'),
('F00010', '0912345687001', 6000, '2024-07-10', 'DF001');
INSERT INTO rol_pago (id_pago, pagoNeto, id_empleado, id_dep_finanzas) VALUES
('P00001', 1200, '0912345688', 'DF001'),
('P00002', 1000, '0912345689', 'DF001'),
('P00003', 1200, '0912345690', 'DF001'),
('P00004', 1000, '0912345691', 'DF001'),
('P00005', 1200, '0912345692', 'DF001'),
('P00006', 1000, '0912345693', 'DF001'),
('P00007', 1200, '0912345694', 'DF001'),
('P00008', 1000, '0912345695', 'DF001'),
('P00009', 1200, '0912345696', 'DF001'),
('P00010', 1000, '0912345697', 'DF001');
INSERT INTO bono (id_bono, cantidad, tipo_bono) VALUES
('B00001', 200, 'Navideño'),
('B00002', 150, 'Productividad'),
('B00003', 300, 'Anual'),
('B00004', 250, 'Cumpleaños'),
('B00005', 100, 'Desempeño'),
('B00006', 180, 'Incentivo'),
('B00007', 220, 'Reconocimiento'),
('B00008', 270, 'Motivacional'),
('B00009', 190, 'Extra'),
('B00010', 230, 'Premio');
INSERT INTO catalogo (id_bono, id_pago) VALUES
('B00001', 'P00001'),
('B00002', 'P00002'),
('B00003', 'P00003'),
('B00004', 'P00004'),
('B00005', 'P00005'),
('B00006', 'P00006'),
('B00007', 'P00007'),
('B00008', 'P00008'),
('B00009', 'P00009'),
('B00010', 'P00010');
INSERT INTO segmento (id_proyecto, RUC, num_factura, rating, duracion, estado, titulo, presupuesto, descripcion, fecha_inicio, fecha_Fin, id_dep_prod, comision_a_empresa) VALUES
('S00001', '0912345678001', 'F00001', 'A', 30, TRUE, 'Proyecto1', 1500, 'Descripcion Proyecto 1', '2024-07-01', '2024-07-31', 'DP001', 10.00),
('S00002', '0912345679002', 'F00002', 'B', 45, FALSE, 'Proyecto2', 2000, 'Descripcion Proyecto 2', '2024-07-02', '2024-08-16', 'DP001', 12.00),
('S00003', '0912345680003', 'F00003', 'C', 60, TRUE, 'Proyecto3', 2500, 'Descripcion Proyecto 3', '2024-07-03', '2024-08-31', 'DP001', 15.00),
('S00004', '0912345681001', 'F00004', 'A', 30, FALSE, 'Proyecto4', 3000, 'Descripcion Proyecto 4', '2024-07-04', '2024-09-16', 'DP001', 10.00),
('S00005', '0912345682002', 'F00005', 'B', 45, TRUE, 'Proyecto5', 3500, 'Descripcion Proyecto 5', '2024-07-05', '2024-09-30', 'DP001', 12.00),
('S00006', '0912345683003', 'F00006', 'C', 60, FALSE, 'Proyecto6', 4000, 'Descripcion Proyecto 6', '2024-07-06', '2024-10-16', 'DP001', 15.00),
('S00007', '0912345684001', 'F00007', 'A', 30, TRUE, 'Proyecto7', 4500, 'Descripcion Proyecto 7', '2024-07-07', '2024-10-31', 'DP001', 10.00),
('S00008', '0912345685002', 'F00008', 'B', 45, FALSE, 'Proyecto8', 5000, 'Descripcion Proyecto 8', '2024-07-08', '2024-11-16', 'DP001', 12.00),
('S00009', '0912345686003', 'F00009', 'C', 60, TRUE, 'Proyecto9', 5500, 'Descripcion Proyecto 9', '2024-07-09', '2024-11-30', 'DP001', 15.00),
('S00010', '0912345687001', 'F00010', 'A', 30, FALSE, 'Proyecto10', 6000, 'Descripcion Proyecto 10', '2024-07-10', '2024-12-16', 'DP001', 10.00);
INSERT INTO horario_segmento (id_horario_segmento, id_segmento, horario) VALUES
('HS0001', 'S00001', '08:00-12:00'),
('HS0002', 'S00002', '10:00-14:00'),
('HS0003', 'S00003', '12:00-16:00'),
('HS0004', 'S00004', '14:00-18:00'),
('HS0005', 'S00005', '16:00-20:00'),
('HS0006', 'S00006', '18:00-22:00'),
('HS0007', 'S00007', '20:00-00:00'),
('HS0008', 'S00008', '22:00-02:00'),
('HS0009', 'S00009', '00:00-04:00'),
('HS0010', 'S00010', '02:00-06:00');
INSERT INTO producto_tienda (id_proyecto, RUC, num_factura, categoria, precio, titulo, presupuesto, descripcion, fecha_inicio, fecha_fin, id_dep_prod, comision_a_empresa) VALUES
('PT0001', '0912345678001', 'F00001', 'Electronica', 500.00, 'Producto1', 1500, 'Descripcion Producto 1', '2024-07-01', '2024-07-31', 'DP001', 10.00),
('PT0002', '0912345679002', 'F00002', 'Electrodomesticos', 700.00, 'Producto2', 2000, 'Descripcion Producto 2', '2024-07-02', '2024-08-16', 'DP001', 12.00),
('PT0003', '0912345680003', 'F00003', 'Ropa', 300.00, 'Producto3', 2500, 'Descripcion Producto 3', '2024-07-03', '2024-08-31', 'DP001', 15.00),
('PT0004', '0912345681001', 'F00004', 'Hogar', 400.00, 'Producto4', 3000, 'Descripcion Producto 4', '2024-07-04', '2024-09-16', 'DP001', 10.00),
('PT0005', '0912345682002', 'F00005', 'Electronica', 600.00, 'Producto5', 3500, 'Descripcion Producto 5', '2024-07-05', '2024-09-30', 'DP001', 12.00),
('PT0006', '0912345683003', 'F00006', 'Electrodomesticos', 800.00, 'Producto6', 4000, 'Descripcion Producto 6', '2024-07-06', '2024-10-16', 'DP001', 15.00),
('PT0007', '0912345684001', 'F00007', 'Ropa', 200.00, 'Producto7', 4500, 'Descripcion Producto 7', '2024-07-07', '2024-10-31', 'DP001', 10.00),
('PT0008', '0912345685002', 'F00008', 'Hogar', 900.00, 'Producto8', 5000, 'Descripcion Producto 8', '2024-07-08', '2024-11-16', 'DP001', 12.00),
('PT0009', '0912345686003', 'F00009', 'Electronica', 1000.00, 'Producto9', 5500, 'Descripcion Producto 9', '2024-07-09', '2024-11-30', 'DP001', 15.00),
('PT0010', '0912345687001', 'F00010', 'Electrodomesticos', 1200.00, 'Producto10', 6000, 'Descripcion Producto 10', '2024-07-10', '2024-12-16', 'DP001', 10.00);
-- Comision
INSERT INTO Comision (id_comision, id_producto_tienda, num_factura, valor_neto, porcentaje) VALUES
('C00001', 'PT0001', 'F00001', 50.00, 10.00),
('C00002', 'PT0002', 'F00002', 84.00, 12.00),
('C00003', 'PT0003', 'F00003', 75.00, 15.00),
('C00004', 'PT0004', 'F00004', 120.00, 10.00),
('C00005', 'PT0005', 'F00005', 105.00, 12.00),
('C00006', 'PT0006', 'F00006', 160.00, 15.00),
('C00007', 'PT0007', 'F00007', 90.00, 10.00),
('C00008', 'PT0008', 'F00008', 140.00, 12.00),
('C00009', 'PT0009', 'F00009', 165.00, 15.00),
('C00010', 'PT0010', 'F00010', 180.00, 10.00);

-- publicidad_anuncio_canal
INSERT INTO publicidad_anuncio_canal (id_proyecto, RUC, num_factura, titulo, presupuesto, descripcion, fechaInicio, fechaFin, duracion, id_dep_creativo, comision_a_empresa) VALUES
('PAC0001', '0912345678001', 'F00001', 'Anuncio1', 1500, 'Descripcion Anuncio 1', '2024-07-01', '2024-07-31', 30, 'DC001', 10.00),
('PAC0002', '0912345679002', 'F00002', 'Anuncio2', 2000, 'Descripcion Anuncio 2', '2024-07-02', '2024-08-16', 45, 'DC001', 12.00),
('PAC0003', '0912345680003', 'F00003', 'Anuncio3', 2500, 'Descripcion Anuncio 3', '2024-07-03', '2024-08-31', 60, 'DC001', 15.00),
('PAC0004', '0912345681001', 'F00004', 'Anuncio4', 3000, 'Descripcion Anuncio 4', '2024-07-04', '2024-09-16', 30, 'DC001', 10.00),
('PAC0005', '0912345682002', 'F00005', 'Anuncio5', 3500, 'Descripcion Anuncio 5', '2024-07-05', '2024-09-30', 45, 'DC001', 12.00),
('PAC0006', '0912345683003', 'F00006', 'Anuncio6', 4000, 'Descripcion Anuncio 6', '2024-07-06', '2024-10-16', 60, 'DC001', 15.00),
('PAC0007', '0912345684001', 'F00007', 'Anuncio7', 4500, 'Descripcion Anuncio 7', '2024-07-07', '2024-10-31', 30, 'DC001', 10.00),
('PAC0008', '0912345685002', 'F00008', 'Anuncio8', 5000, 'Descripcion Anuncio 8', '2024-07-08', '2024-11-16', 45, 'DC001', 12.00),
('PAC0009', '0912345686003', 'F00009', 'Anuncio9', 5500, 'Descripcion Anuncio 9', '2024-07-09', '2024-11-30', 60, 'DC001', 15.00),
('PAC0010', '0912345687001', 'F00010', 'Anuncio10', 6000, 'Descripcion Anuncio 10', '2024-07-10', '2024-12-16', 30, 'DC001', 10.00);

-- publicidad_anuncio_web
INSERT INTO publicidad_anuncio_web (id_proyecto, RUC, num_factura, titulo, presupuesto, descripcion, fechaInicio, fechaFin, tamano_banner, id_dep_creativo, comision_a_empresa) VALUES
('PAW0001', '0912345678001', 'F00001', 'AnuncioWeb1', 1500, 'Descripcion Anuncio Web 1', '2024-07-01', '2024-07-31', '300x250', 'DC001', 10.00),
('PAW0002', '0912345679002', 'F00002', 'AnuncioWeb2', 2000, 'Descripcion Anuncio Web 2', '2024-07-02', '2024-08-16', '728x90', 'DC001', 12.00),
('PAW0003', '0912345680003', 'F00003', 'AnuncioWeb3', 2500, 'Descripcion Anuncio Web 3', '2024-07-03', '2024-08-31', '300x250', 'DC001', 15.00),
('PAW0004', '0912345681001', 'F00004', 'AnuncioWeb4', 3000, 'Descripcion Anuncio Web 4', '2024-07-04', '2024-09-16', '728x90', 'DC001', 10.00),
('PAW0005', '0912345682002', 'F00005', 'AnuncioWeb5', 3500, 'Descripcion Anuncio Web 5', '2024-07-05', '2024-09-30', '300x250', 'DC001', 12.00),
('PAW0006', '0912345683003', 'F00006', 'AnuncioWeb6', 4000, 'Descripcion Anuncio Web 6', '2024-07-06', '2024-10-16', '728x90', 'DC001', 15.00),
('PAW0007', '0912345684001', 'F00007', 'AnuncioWeb7', 4500, 'Descripcion Anuncio Web 7', '2024-07-07', '2024-10-31', '300x250', 'DC001', 10.00),
('PAW0008', '0912345685002', 'F00008', 'AnuncioWeb8', 5000, 'Descripcion Anuncio Web 8', '2024-07-08', '2024-11-16', '728x90', 'DC001', 12.00),
('PAW0009', '0912345686003', 'F00009', 'AnuncioWeb9', 5500, 'Descripcion Anuncio Web 9', '2024-07-09', '2024-11-30', '300x250', 'DC001', 15.00),
('PAW0010', '0912345687001', 'F00010', 'AnuncioWeb10', 6000, 'Descripcion Anuncio Web 10', '2024-07-10', '2024-12-16', '728x90', 'DC001', 10.00);
INSERT INTO telefono (id_telefono, cedula, telefono) VALUES
('T00001', '0912345678', '0999999999'),
('T00002', '0912345679', '0999999998'),
('T00003', '0912345680', '0999999997'),
('T00004', '0912345681', '0999999996'),
('T00005', '0912345682', '0999999995'),
('T00006', '0912345683', '0999999994'),
('T00007', '0912345684', '0999999993'),
('T00008', '0912345685', '0999999992'),
('T00009', '0912345686', '0999999991'),
('T00010', '0912345687', '0999999990');