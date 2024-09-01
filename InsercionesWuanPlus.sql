
USE wuanPlus;
-- Departamento Creativo
INSERT INTO departamento_creativo (id_departamento, descripcion, nombre, email) VALUES
('DC001', 'Departamento Creativo', 'Creativo', 'creativo@example.com');

-- Departamento Producción
INSERT INTO departamento_produccion (id_departamento, descripcion, nombre, email) VALUES
('DP001', 'Departamento Producción', 'Producción', 'produccion@gmail.com');

-- Departamento Finanzas
INSERT INTO departamento_finanzas (id_departamento, descripcion, nombre, email) VALUES
('DF001', 'Departamento Finanzas', 'Finanzas', 'finanzas@gmail.com');

-- Inserción inicial de empleados sin asignar departamentos
INSERT INTO empleado (id_empleado, sueldoBase, nombre, apellido, puesto, contrasena, direccion, id_dep_creativo, id_dep_prod, id_dep_finanzas, id_dir_dep_creativo, id_dir_dep_prod, id_dir_dep_finanzas) VALUES
('0912345688', 1000, 'Carlos', 'Pérez', 'Director Creativo', 'pass123', 'Av. Creatividad 123', 'DC001', NULL, NULL, 'DC001', NULL, NULL),
('0912345689', 800, 'María', 'Gómez', 'Diseñador', 'pass123', 'Calle Diseño 456', 'DC001', NULL, NULL, NULL, NULL, NULL),
('0912345694', 750, 'Laura', 'Vega', 'Asistente Creativo', 'pass123', 'Pasaje Inspiración', 'DC001', NULL, NULL, NULL, NULL, NULL),
('0912345690', 1000, 'Juan', 'Díaz', 'Director Producción', 'pass123', 'Av. Producción 101', NULL, 'DP001', NULL, NULL, 'DP001', NULL),
('0912345691', 800, 'Ana', 'Martínez', 'Operario', 'pass123', 'Calle Fábrica 202', NULL, 'DP001', NULL, NULL, NULL, NULL),
('0912345695', 750, 'Pedro', 'Castillo', 'Asistente Producción', 'pass123', 'Av. Trabajo 303', NULL, 'DP001', NULL, NULL, NULL, NULL),
('0912345692', 1000, 'Luis', 'Torres', 'Director Finanzas', 'pass123', 'Av. Finanzas 404', NULL, NULL, 'DF001', NULL, NULL, 'DF001'),
('0912345693', 800, 'Sofía', 'Ramírez', 'Contador', 'pass123', 'Calle Balance 505', NULL, NULL, 'DF001', NULL, NULL, NULL),
('0912345696', 750, 'Jorge', 'López', 'Asistente Finanzas', 'pass123', 'Pasaje Ahorro 606', NULL, NULL, 'DF001', NULL, NULL, NULL),
('0912345697', 800, 'Lucía', 'Pimentel', 'Contador', 'milu123', 'Calle Contab. 707', 'DC001', NULL, NULL, NULL, NULL, NULL),
('0931248686', 800, 'Cristina', 'Pimentel', 'Contador', 'pas2344', 'Av. Presupuesto 808', NULL, NULL, 'DF001', NULL, NULL, NULL);

INSERT INTO persona_contacto (cedula, nombre, apellido, email, telefono) VALUES
('0912345678', 'Carlos', 'Pérez', 'carlos@gmail.com', '0987654321'),
('0912345679', 'María', 'Gómez', 'maria@gmail.com', '0987654322'),
('0912345680', 'Juan', 'Díaz', 'juan@gmail.com', '0987654323'),
('0912345681', 'Ana', 'Martínez', 'ana@gmail.com', '0987654324'),
('0912345682', 'Luis', 'Torres', 'luis@gmail.com', '0987654325'),
('0912345683', 'Sofía', 'Ramírez', 'sofia@gmail.com', '0987654326'),
('0912345684', 'Jorge', 'López', 'jorge@gmail.com', '0987654327'),
('0912345685', 'Marta', 'Sánchez', 'marta@gmail.com', '0987654328'),
('0912345686', 'Pedro', 'Castillo', 'pedro@gmail.com', '0987654329'),
('0912345687', 'Laura', 'Vega', 'laura@gmail.com', '0987654330');

INSERT INTO cliente (RUC, nombre_empresa, decrip_empresa, direccion, sitio_web, id_persona_contacto) VALUES
('0912345678001', 'Tech Innovators', 'Empresa dedicada a la innovación tecnológica y desarrollo de software.', 'Av. Principal 123, Ciudad Tech', 'www.techinnovators.com', '0912345678'),
('0912345679002', 'Green Solutions', 'Compañía enfocada en soluciones sostenibles y energías renovables.', 'Calle Verde 456, Ecociudad', 'www.greensolutions.com', '0912345679'),
('0912345680003', 'HealthCare Plus', 'Proveedor de servicios médicos y productos de salud.', 'Av. Salud 789, Ciudad Médica', 'www.healthcareplus.com', '0912345680'),
('0912345681001', 'EducaTech', 'Empresa especializada en tecnología educativa y plataformas de e-learning.', 'Calle del Conocimiento 101, Ciudad Educación', 'www.educatech.com', '0912345681'),
('0912345682002', 'Fashion Forward', 'Empresa de moda y diseño de ropa a la vanguardia.', 'Pasaje Estilo 202, Ciudad Moda', 'www.fashionforward.com', '0912345682'),
('0912345683003', 'AutoDrive', 'Compañía dedicada a la venta y distribución de vehículos automotrices.', 'Carretera Autopista 303, Ciudad Motor', 'www.autodrive.com', '0912345683'),
('0912345684001', 'Foodies Delight', 'Empresa que ofrece servicios de catering y productos gourmet.', 'Calle Sabores 404, Ciudad Gourmet', 'www.foodiesdelight.com', '0912345684'),
('0912345685002', 'EcoHome', 'Proveedor de soluciones y productos para el hogar ecológico.', 'Av. Eco 505, Ciudad Verde', 'www.ecohome.com', '0912345685'),
('0912345686003', 'TravelMore', 'Agencia de viajes y turismo con paquetes personalizados.', 'Calle Viajes 606, Ciudad Destino', 'www.travelmore.com', '0912345686'),
('0912345687001', 'Digital Solutions', 'Empresa líder en marketing digital y servicios en línea.', 'Plaza Digital 707, Ciudad Web', 'www.digitalsolutions.com', '0912345687');

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
('F00010', '0912345687001', 6000, '2024-07-10', 'DF001'),
('F00011', '0912345678001', 1600, '2024-09-01', 'DF001');

INSERT INTO rol_pago (id_pago, pago_neto, id_empleado, id_dep_finanzas) VALUES
('P00001', 1200, '0912345688', 'DF001'),
('P00002', 1000, '0912345689', 'DF001'),
('P00003', 1200, '0912345690', 'DF001'),
('P00004', 1000, '0912345691', 'DF001'),
('P00005', 1200, '0912345692', 'DF001'),
('P00006', 1000, '0912345693', 'DF001'),
('P00007', 1200, '0912345694', 'DF001'),
('P00008', 1000, '0912345695', 'DF001'),
('P00009', 1200, '0912345696', 'DF001'),
('P00010', 1000, '0912345697', 'DF001'),
('P00011', 1200, '0912345688', 'DF001'),
('P00012', 1000, '0912345689', 'DF001');

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

INSERT INTO producto_tienda (id_proyecto, RUC, num_factura, categoria, precio, titulo, presupuesto, descripcion, fecha_inicio, fecha_fin, id_dep_prod, comision_a_empresa) VALUES
('PT0001', '0912345678001', 'F00001', 'Electronica', 500.00, 'Smart Watch', 1500, 'Reloj inteligente', '2024-07-01', '2024-07-31', 'DP001', 10.00),
('PT0002', '0912345679002', 'F00002', 'Electrodomesticos', 700.00, 'Aire Acondicionado', 2000, 'Aire que enfria', '2024-07-02', '2024-08-16', 'DP001', 12.00),
('PT0003', '0912345680003', 'F00003', 'Ropa', 300.00, 'Corbata', 2500, 'Corbata para vestir', '2024-07-03', '2024-08-31', 'DP001', 15.00),
('PT0004', '0912345681001', 'F00004', 'Hogar', 400.00, 'Alfombra', 3000, 'Alfombra para la entrada', '2024-07-04', '2024-09-16', 'DP001', 10.00),
('PT0005', '0912345682002', 'F00005', 'Electronica', 600.00, 'Celular', 3500, 'Celular marca Huawei', '2024-07-05', '2024-09-30', 'DP001', 12.00),
('PT0006', '0912345683003', 'F00006', 'Electrodomesticos', 800.00, 'Tostadora', 4000, 'Tostadora para sanduches', '2024-07-06', '2024-10-16', 'DP001', 15.00),
('PT0007', '0912345684001', 'F00007', 'Ropa', 200.00, 'Pantalon', 4500, 'Pantalon de vestir', '2024-07-07', '2024-10-31', 'DP001', 10.00),
('PT0008', '0912345685002', 'F00008', 'Hogar', 900.00, 'Almohada', 5000, 'Almohada para la cama', '2024-07-08', '2024-11-16', 'DP001', 12.00),
('PT0009', '0912345686003', 'F00009', 'Electronica', 874.00, 'Computadora', 5500, 'Computadora marca HP', '2024-07-09', '2024-11-30', 'DP001', 15.00),
('PT0010', '0912345687001', 'F00010', 'Electrodomesticos', 798.00, 'Lavadora', 6000, 'Lavadora para la ropa', '2024-07-10', '2024-12-16', 'DP001', 10.00),
('PT0011', '0912345687001', 'F00011', 'Ropa', 567.00, 'Camisa', 6000, 'Camisa de vestir', '2024-07-10', '2024-12-16', 'DP001', 10.00);


-- publicidad_anuncio_canal
INSERT INTO publicidad_anuncio_canal (id_proyecto, RUC, num_factura, titulo, presupuesto, descripcion, fechaInicio, fechaFin, duracion, id_dep_creativo, comision_a_empresa) VALUES
('PAC0001', '0912345678001', 'F00001', 'Campaña Verano', 1500, 'Anuncio de temporada de verano enfocado en promociones de ropa de playa.', '2024-07-01', '2024-07-31', 30, 'DC001', 10.00),
('PAC0002', '0912345679002', 'F00002', 'Lanzamiento Prod.', 2000, 'Promoción del lanzamiento de un nuevo smartphone en redes sociales.', '2024-07-02', '2024-08-16', 45, 'DC001', 12.00),
('PAC0003', '0912345680003', 'F00003', 'Fin de Temporada', 2500, 'Descuentos de fin de temporada para liquidar inventarios en tiendas físicas.', '2024-07-03', '2024-08-31', 60, 'DC001', 15.00),
('PAC0004', '0912345681001', 'F00004', 'Black Friday', 3000, 'Campaña publicitaria para ventas del Black Friday en comercio electrónico.', '2024-07-04', '2024-09-16', 30, 'DC001', 10.00),
('PAC0005', '0912345682002', 'F00005', 'Campaña Navidad', 3500, 'Anuncios navideños en televisión y redes sociales para aumentar ventas en diciembre.', '2024-07-05', '2024-09-30', 45, 'DC001', 12.00),
('PAC0006', '0912345683003', 'F00006', 'San Valentín', 4000, 'Publicidad enfocada en regalos y ofertas especiales para el Día de San Valentín.', '2024-07-06', '2024-10-16', 60, 'DC001', 15.00),
('PAC0007', '0912345684001', 'F00007', 'Promoción Verano', 4500, 'Campaña de verano con promociones exclusivas en productos de belleza y cuidado personal.', '2024-07-07', '2024-10-31', 30, 'DC001', 10.00),
('PAC0008', '0912345685002', 'F00008', 'Regreso a Clases', 5000, 'Promoción para la temporada de regreso a clases con ofertas en útiles escolares.', '2024-07-08', '2024-11-16', 45, 'DC001', 12.00),
('PAC0009', '0912345686003', 'F00009', 'Día de la Madre', 5500, 'Publicidad dirigida a incrementar ventas de joyería y perfumes para el Día de la Madre.', '2024-07-09', '2024-11-30', 60, 'DC001', 15.00),
('PAC0010', '0912345687001', 'F00010', 'Cyber Monday', 6000, 'Campaña publicitaria en línea para el Cyber Monday, centrada en ofertas de tecnología.', '2024-07-10', '2024-12-16', 30, 'DC001', 10.00),
('PAC0011', '0912345686003', 'F00011', 'Año Nuevo', 6500, 'Anuncios enfocados en la celebración de Año Nuevo y promociones para eventos y celebraciones.', '2024-07-11', '2024-12-31', 45, 'DC001', 12.00);

-- publicidad_anuncio_web
INSERT INTO publicidad_anuncio_web (id_proyecto, RUC, num_factura, titulo, presupuesto, descripcion, fechaInicio, fechaFin, tamano_banner, id_dep_creativo, comision_a_empresa) VALUES
('PAW0001', '0912345678001', 'F00001', 'Campaña Web Verano', 1500, 'Banner promocional de verano para ofertas en productos de playa.', '2024-07-01', '2024-07-31', '300x250', 'DC001', 10.00),
('PAW0002', '0912345679002', 'F00002', 'Lanzamiento Web', 2000, 'Banner en sitios web para el lanzamiento de un nuevo smartphone.', '2024-07-02', '2024-08-16', '728x90', 'DC001', 12.00),
('PAW0003', '0912345680003', 'F00003', 'Fin de Temporada', 2500, 'Anuncio web para liquidación de productos al final de la temporada.', '2024-07-03', '2024-08-31', '300x250', 'DC001', 15.00),
('PAW0004', '0912345681001', 'F00004', 'Black Friday Online', 3000, 'Banner publicitario en línea para las ofertas de Black Friday.', '2024-07-04', '2024-09-16', '728x90', 'DC001', 10.00),
('PAW0005', '0912345682002', 'F00005', 'Navidad en Línea', 3500, 'Anuncio web navideño para promociones en comercio electrónico.', '2024-07-05', '2024-09-30', '300x250', 'DC001', 12.00),
('PAW0006', '0912345683003', 'F00006', 'San Valentín Online', 4000, 'Publicidad web para ofertas especiales del Día de San Valentín.', '2024-07-06', '2024-10-16', '728x90', 'DC001', 15.00),
('PAW0007', '0912345684001', 'F00007', 'Promoción Belleza', 4500, 'Anuncio en línea de productos de belleza con promociones de verano.', '2024-07-07', '2024-10-31', '300x250', 'DC001', 10.00),
('PAW0008', '0912345685002', 'F00008', 'Regreso Clases Web', 5000, 'Banner en sitios educativos para la temporada de regreso a clases.', '2024-07-08', '2024-11-16', '728x90', 'DC001', 12.00),
('PAW0009', '0912345686003', 'F00009', 'Día de la Madre', 5500, 'Anuncio web para promociones en regalos del Día de la Madre.', '2024-07-09', '2024-11-30', '300x250', 'DC001', 15.00),
('PAW0010', '0912345687001', 'F00010', 'Cyber Monday', 6000, 'Banner en línea para las ofertas del Cyber Monday, enfocadas en tecnología.', '2024-07-10', '2024-12-16', '728x90', 'DC001', 10.00),
('PAW0011', '0912345687001', 'F00011', 'Año Nuevo Online', 6500, 'Publicidad web enfocada en las celebraciones y promociones de Año Nuevo.', '2024-07-11', '2024-12-31', '300x250', 'DC001', 12.00);
