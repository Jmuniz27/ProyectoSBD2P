use wuanplus;

DELIMITER //

CREATE PROCEDURE crear_segmento(
    IN p_id_proyecto CHAR(10),
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
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        -- En caso de error, se realiza un rollback de la transacción
        ROLLBACK;
        -- Opcionalmente, puedes imprimir un mensaje de error
        SELECT 'Ha ocurrido un error y la transacción ha sido revertida.';
    END;

    -- Inicia una nueva transacción
    START TRANSACTION;

    -- Inserción del nuevo segmento
    INSERT INTO segmento (
        id_proyecto, RUC, num_factura, rating, duracion, estado, 
        titulo, presupuesto, descripcion, fecha_inicio, fecha_fin, 
        id_dep_prod, comision_a_empresa
    ) VALUES (
        p_id_proyecto, p_RUC, p_num_factura, p_rating, p_duracion, p_estado, 
        p_titulo, p_presupuesto, p_descripcion, p_fecha_inicio, p_fecha_fin, 
        p_id_dep_prod, p_comision_a_empresa
    );

    -- Si no hubo errores, confirmar la transacción
    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE InsertPublicidadAnuncioCanal(
    IN p_id_anuncio INT,
    IN p_id_canal INT,
    IN p_fecha_inicio DATE,
    IN p_fecha_fin DATE,
    IN p_monto DECIMAL(10, 2)
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        -- Manejo de excepciones: Rollback y salida
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar en publicidad_anuncio_canal';
    END;

    START TRANSACTION;
    INSERT INTO publicidad_anuncio_canal (id_anuncio, id_canal, fecha_inicio, fecha_fin, monto)
    VALUES (p_id_anuncio, p_id_canal, p_fecha_inicio, p_fecha_fin, p_monto);
    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE InsertPublicidadAnuncioWeb(
    IN p_id_anuncio INT,
    IN p_url VARCHAR(255),
    IN p_fecha_inicio DATE,
    IN p_fecha_fin DATE,
    IN p_monto DECIMAL(10, 2)
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        -- Manejo de excepciones: Rollback y salida
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar en publicidad_anuncio_web';
    END;

    START TRANSACTION;
    INSERT INTO publicidad_anuncio_web (id_anuncio, url, fecha_inicio, fecha_fin, monto)
    VALUES (p_id_anuncio, p_url, p_fecha_inicio, p_fecha_fin, p_monto);
    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE InsertProductoTienda(
    IN p_id_producto INT,
    IN p_nombre VARCHAR(100),
    IN p_categoria VARCHAR(50),
    IN p_precio DECIMAL(10, 2),
    IN p_stock INT
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        -- Manejo de excepciones: Rollback y salida
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar en producto_tienda';
    END;

    START TRANSACTION;
    INSERT INTO producto_tienda (id_producto, nombre, categoria, precio, stock)
    VALUES (p_id_producto, p_nombre, p_categoria, p_precio, p_stock);
    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE InsertCliente(
    IN p_id_cliente INT,
    IN p_nombre VARCHAR(100),
    IN p_direccion VARCHAR(255),
    IN p_telefono VARCHAR(20),
    IN p_email VARCHAR(100)
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        -- Manejo de excepciones: Rollback y salida
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar en cliente';
    END;

    START TRANSACTION;
    INSERT INTO cliente (id_cliente, nombre, direccion, telefono, email)
    VALUES (p_id_cliente, p_nombre, p_direccion, p_telefono, p_email);
    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE InsertPersonaContacto(
    IN p_id_persona INT,
    IN p_nombre VARCHAR(100),
    IN p_telefono VARCHAR(20),
    IN p_email VARCHAR(100),
    IN p_id_cliente INT
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        -- Manejo de excepciones: Rollback y salida
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar en persona_contacto';
    END;

    START TRANSACTION;
    INSERT INTO persona_contacto (id_persona, nombre, telefono, email, id_cliente)
    VALUES (p_id_persona, p_nombre, p_telefono, p_email, p_id_cliente);
    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE InsertEmpleado(
    IN p_id_empleado INT,
    IN p_nombre VARCHAR(100),
    IN p_direccion VARCHAR(255),
    IN p_telefono VARCHAR(20),
    IN p_email VARCHAR(100),
    IN p_id_departamento INT
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        -- Manejo de excepciones: Rollback y salida
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar en empleado';
    END;

    START TRANSACTION;
    INSERT INTO empleado (id_empleado, nombre, direccion, telefono, email, id_departamento)
    VALUES (p_id_empleado, p_nombre, p_direccion, p_telefono, p_email, p_id_departamento);
    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE InsertRolPago(
    IN p_id_rol INT,
    IN p_descripcion VARCHAR(100),
    IN p_sueldo DECIMAL(10, 2)
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        -- Manejo de excepciones: Rollback y salida
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar en rol_pago';
    END;

    START TRANSACTION;
    INSERT INTO rol_pago (id_rol, descripcion, sueldo)
    VALUES (p_id_rol, p_descripcion, p_sueldo);
    COMMIT;
END //

DELIMITER ;



