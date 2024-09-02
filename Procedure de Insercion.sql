use wuanplus;

DROP PROCEDURE crear_segmento;
DROP PROCEDURE crear_PublicidadAnuncioCanal;
DROP PROCEDURE crear_PublicidadAnuncioWeb;
DROP PROCEDURE crear_ProductoTienda;
DROP PROCEDURE crear_Cliente;
DROP PROCEDURE crear_Empleado;
DROP PROCEDURE crear_RolPago;
DELIMITER //

CREATE PROCEDURE crear_Segmento(
    IN p_RUC CHAR(13),
    IN p_num_factura CHAR(10),
    IN p_rating VARCHAR(20),
    IN p_duracion INT,
    IN p_estado BOOLEAN,
    IN p_titulo VARCHAR(20),
    IN p_presupuesto INT,
    IN p_descripcion VARCHAR(300),
    IN p_fecha_inicio DATE,
    IN p_fecha_fin DATE,
    IN p_id_dep_prod CHAR(10),
    IN p_comision_a_empresa DECIMAL(5,2)
)
BEGIN
	DECLARE v_num_factura INT;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar en segmento';
    END;
	
    -- Inicia una nueva transacción
    START TRANSACTION;
    
    -- Inserta primero en la tabla factura
    INSERT INTO factura (RUC, precio_total, fecha, id_dep_finanzas)
    VALUES (p_RUC, p_comision_a_empresa, NOW(), 'DF001');
    
    -- Captura el ID de factura generado
    SET v_num_factura = LAST_INSERT_ID();

    -- Inserta en producto_tienda usando el num_factura generado
    INSERT INTO segmento (RUC, num_factura, rating, duracion, estado, titulo, presupuesto, descripcion, fecha_inicio, fecha_Fin, id_dep_prod, comision_a_empresa)
    VALUES (p_RUC, v_num_factura, p_rating, p_duracion, p_estado, p_titulo, p_presupuesto, p_descripcion, p_fecha_inicio, p_fecha_fin, p_id_dep_prod,p_comision_a_empresa);


    -- Si no hubo errores, confirmar la transacción
    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE crear_PublicidadAnuncioCanal(
    IN p_RUC CHAR(13),
    IN p_num_factura CHAR(10),
    IN p_titulo VARCHAR(20),
    IN p_presupuesto INT,
    IN p_descripcion VARCHAR(300),
    IN p_fecha_inicio DATE,
    IN p_fecha_fin DATE,
    IN p_duracion INT,
    IN p_id_dep_creativo CHAR(10),
    IN p_comision_a_empresa DECIMAL(5,2)
)
BEGIN
	DECLARE v_num_factura INT;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        -- Manejo de excepciones: Rollback y salida
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar en publicidad_anuncio_canal';
    END;
    
    START TRANSACTION;
    
    -- Inserta primero en la tabla factura
    INSERT INTO factura (RUC, precio_total, fecha, id_dep_finanzas)
    VALUES (p_RUC, p_comision_a_empresa, NOW(), 'DF001');
    
    -- Captura el ID de factura generado
    SET v_num_factura = LAST_INSERT_ID();
    
    INSERT INTO publicidad_anuncio_canal (RUC, num_factura, titulo, presupuesto, descripcion, fechaInicio,
    fechaFin, duracion, id_dep_creativo, comision_a_empresa)
    VALUES (p_RUC, v_num_factura, p_titulo, p_presupuesto, p_descripcion, p_fecha_inicio, p_fecha_fin
    , p_duracion , p_id_dep_creativo, p_comision_a_empresa);
    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE crear_PublicidadAnuncioWeb(
    IN p_RUC CHAR(13),
    IN p_num_factura CHAR(10),
    IN p_titulo VARCHAR(20),
    IN p_presupuesto INT,
    IN p_descripcion VARCHAR(300),
    IN p_fecha_inicio DATE,
    IN p_fecha_fin DATE,
    IN p_tamano_banner VARCHAR(20),
    IN p_id_dep_creativo CHAR(10),
    IN p_comision_a_empresa DECIMAL(5,2)
)
BEGIN
	DECLARE v_num_factura INT;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        -- Manejo de excepciones: Rollback y salida
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar en publicidad_anuncio_web';
    END;
    
    START TRANSACTION;
    
    -- Inserta primero en la tabla factura
    INSERT INTO factura (RUC, precio_total, fecha, id_dep_finanzas)
    VALUES (p_RUC, p_comision_a_empresa, NOW(), 'DF001');
    
    -- Captura el ID de factura generado
    SET v_num_factura = LAST_INSERT_ID();
    
    INSERT INTO publicidad_anuncio_web (RUC, num_factura, titulo, presupuesto, descripcion, fechaInicio,
    fechaFin, tamano_banner, id_dep_creativo, comision_a_empresa)
    VALUES (p_RUC, v_num_factura, p_titulo, p_presupuesto, p_descripcion, p_fecha_inicio, p_fecha_fin, p_tamano_banner,
    p_id_dep_creativo, p_comision_a_empresa);
    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE crear_ProductoTienda(
    IN p_RUC CHAR(13),
    IN p_num_factura CHAR(10),
    IN p_categoria VARCHAR(20),
    IN p_precio DECIMAL(5,2),
    IN p_titulo VARCHAR(20),
    IN p_presupuesto INT,
    IN p_descripcion VARCHAR(300),
    IN p_fecha_inicio DATE,
    IN p_fecha_fin DATE,
    IN p_id_dep_prod CHAR(10),
    IN p_comision_a_empresa DECIMAL(5,2)
)
BEGIN
    DECLARE v_num_factura INT;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        -- Manejo de excepciones: Rollback y salida
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar en publicidad_anuncio_canal';
    END;
    
    START TRANSACTION;
    
    -- Inserta primero en la tabla factura
    INSERT INTO factura (RUC, precio_total, fecha, id_dep_finanzas)
    VALUES (p_RUC, p_comision_a_empresa, NOW(), 'DF001');
    
    -- Captura el ID de factura generado
    SET v_num_factura = LAST_INSERT_ID();
    
    INSERT INTO publicidad_anuncio_canal (RUC, num_factura, categoria, precio , titulo, presupuesto, descripcion, fecha_inicio,
    fecha_fin, id_dep_prod, comision_a_empresa)
    VALUES (p_RUC, v_num_factura, p_categoria, p_precio, p_titulo, p_presupuesto, p_descripcion, p_fecha_inicio, p_fecha_fin,
    p_id_dep_prod, p_comision_a_empresa);
    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE crear_Cliente(
    IN p_RUC CHAR(13),
    IN p_nombre_empresa VARCHAR(20),
    IN p_decrip_empresa VARCHAR(200),
    IN p_direccion VARCHAR(100),
    IN p_sitio_web VARCHAR(100),
    IN p_id_persona_contacto CHAR(10)
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        -- Manejo de excepciones: Rollback y salida
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar en cliente';
    END;

    START TRANSACTION;
    INSERT INTO cliente (RUC, nombre_empresa, decrip_empresa , direccion, sitio_web, id_persona_contacto)
    VALUES (p_RUC, p_nombre_empresa, p_decrip_empresa , p_direccion, p_sitio_web, p_id_persona_contacto);
    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE crear_PersonaContacto(
    IN p_cedula CHAR(10),
    IN p_nombre VARCHAR(20),
    IN p_apellido VARCHAR(20),
    IN p_email VARCHAR(20),
    IN p_telefono VARCHAR(20)
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        -- Manejo de excepciones: Rollback y salida
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar en persona_contacto';
    END;

    START TRANSACTION;
    INSERT INTO persona_contacto (cedula, nombre, apellido, email, telefono)
    VALUES (p_cedula, p_nombre, p_apellido, p_email, p_telefono);
    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE crear_Empleado(
    IN p_id_empleado CHAR(10),
    IN p_sueldoBase INT,
    IN p_nombre VARCHAR(20),
    IN p_apellido VARCHAR(20),
    IN p_puesto VARCHAR(20),
    IN p_contrasena VARCHAR(20),
    IN p_direccion VARCHAR(20),
    IN p_id_dep_creativo CHAR(10),
    IN p_id_dep_prod CHAR(10),
    IN p_id_dep_finanzas CHAR(10),
    IN p_id_dir_dep_creativo CHAR(10),
    IN p_id_dir_dep_prod CHAR(10),
    IN p_id_dir_dep_finanzas CHAR(10)
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        -- Manejo de excepciones: Rollback y salida
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar en empleado';
    END;

    START TRANSACTION;
    INSERT INTO empleado (id_empleado, sueldoBase, nombre, apellido, puesto, contrasena, direccion,
    id_dep_creativo, id_dep_prod, id_dep_finanzas, id_dir_dep_creativo, id_dir_dep_prod,
    id_dir_dep_finanzas)
    VALUES (p_id_empleado, p_sueldoBase, p_nombre, p_apellido, p_puesto, p_contrasena, p_direccion,
    p_id_dep_creativo, p_id_dep_prod, p_id_dep_finanzas, p_id_dir_dep_creativo, p_id_dir_dep_prod,
    p_id_dir_dep_finanzas);
    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE crear_RolPago(
    IN p_id_pago CHAR(10),
    IN p_pago_neto INT,
    IN p_id_empleado CHAR(10),
    IN p_id_dep_finanzas CHAR(10)
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        -- Manejo de excepciones: Rollback y salida
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar en rol_pago';
    END;

    START TRANSACTION;
    INSERT INTO rol_pago (id_pago, pago_neto, id_empleado, id_dep_finanzas)
    VALUES (p_id_pago, p_pago_neto, p_id_empleado, p_id_dep_finanzas);
    COMMIT;
END //

DELIMITER 