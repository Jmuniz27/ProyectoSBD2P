use wuanplus;

DROP PROCEDURE actualizar_segmento;
DROP PROCEDURE actualizar_RolPago;
DROP PROCEDURE actualizar_PublicidadAnuncioWeb;
DROP PROCEDURE actualizar_PublicidadAnuncioCanal;
DROP PROCEDURE actualizar_ProductoTienda;
DROP PROCEDURE actualizar_PersonaContacto;
DROP PROCEDURE actualizar_Empleado;
DROP PROCEDURE actualizar_Cliente;

DELIMITER //

CREATE PROCEDURE actualizar_segmento(
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
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Ha ocurrido un error y la transacción ha sido revertida.';
    END;

    START TRANSACTION;

    UPDATE segmento
    SET RUC = p_RUC, num_factura = p_num_factura, rating = p_rating, 
        duracion = p_duracion, estado = p_estado, titulo = p_titulo, 
        presupuesto = p_presupuesto, descripcion = p_descripcion, 
        fecha_inicio = p_fecha_inicio, fecha_fin = p_fecha_fin, 
        id_dep_prod = p_id_dep_prod, comision_a_empresa = p_comision_a_empresa
    WHERE id_proyecto = p_id_proyecto;

    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE actualizar_PublicidadAnuncioCanal(
    IN p_id_proyecto CHAR(10),
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
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al actualizar en publicidad_anuncio_canal';
    END;

    START TRANSACTION;

    UPDATE publicidad_anuncio_canal
    SET id_proyecto = p_id_proyecto, RUC = p_RUC, num_factura = p_num_factura, titulo = p_titulo,
    presupuesto = p_presupuesto, descripcion = p_descripcion, fecha_inicio = p_fecha_inicio,
    fecha_fin = p_fecha_fin, duracion = p_duracion, id_dep_creativo = p_id_dep_creativo,
    comision_a_empresa = id_comision_a_empresa
    WHERE id_proyecto = p_id_proyecto;

    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE actualizar_PublicidadAnuncioWeb(
    IN p_id_proyecto CHAR(10),
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
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al actualizar en publicidad_anuncio_web';
    END;

    START TRANSACTION;

    UPDATE publicidad_anuncio_web
    SET id_proyecto = p_id_proyecto, RUC = p_RUC, num_factura = p_num_factura, titulo = p_titulo,
    presupuesto = p_presupuesto, descripcion = p_descripcion, fecha_inicio = p_fecha_inicio,
    fecha_fin = p_fecha_fin, tamano_banner = p_tamano_banner, id_dep_creativo = p_id_dep_creativo,
    comision_a_empresa = id_comision_a_empresa
    WHERE id_proyecto = p_id_proyecto;

    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE actualizar_ProductoTienda(
    IN p_id_proyecto CHAR(10),
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
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al actualizar en producto_tienda';
    END;

    START TRANSACTION;

    UPDATE producto_tienda
    SET id_proyecto = p_id_proyecto, RUC = p_RUC, num_factura = p_num_factura, 
    categoria = p_categoria, precio = p_precio ,titulo = p_titulo, presupuesto = p_presupuesto, 
    descripcion = p_descripcion, fecha_inicio = p_fecha_inicio, fecha_fin = p_fecha_fin, 
    id_dep_prod = p_id_dep_prod, comision_a_empresa = id_comision_a_empresa
    WHERE id_proyecto = p_id_proyecto;

    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE actualizar_Cliente(
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
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al actualizar en cliente';
    END;

    START TRANSACTION;

    UPDATE cliente
    SET RUC = p_RUC, nombre_empresa = p_nombre_empresa, descrip_empresa = p_descrip_empresa,
    direccion = p_direccion, sitio_web = p_sitio_web, id_persona_contacto = p_id_persona_contacto
    WHERE RUC = p_RUC;

    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE actualizar_PersonaContacto(
    IN p_cedula CHAR(10),
    IN p_nombre VARCHAR(20),
    IN p_apellido VARCHAR(20),
    IN p_email VARCHAR(20),
    IN p_telefono VARCHAR(20)
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al actualizar en persona_contacto';
    END;

    START TRANSACTION;

    UPDATE persona_contacto
    SET cedula = p_cedula, nombre = p_nombre, apellido = p_apellido,
    email = p_email, telefono = p_telefono
    WHERE cedula = p_cedula;

    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE actualizar_Empleado(
    IN p_id_empleado CHAR(10),
    IN p_sueldoBase INT,
    IN p_nombre VARCHAR(20),
    IN p_apellido VARCHAR(20),
    IN p_puesto VARCHAR(20),
    IN p_contraseña VARCHAR(20),
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
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al actualizar en empleado';
    END;

    START TRANSACTION;

    UPDATE empleado
    SET id_empleado = p_id_empleado, sueldo_Base = p_sueldoBase, nombre = p_nombre, apellido = p_apellido,
    puesto = p_puesto, contraseña = p_contraseña, direccion = p_direccion, id_dep_creativo = p_id_dep_creativo,
    id_dep_prod = p_id_dep_prod, id_dep_finanzas = p_id_dep_finanzas, id_dir_dep_creativo = p_id_dir_dep_creativo,
    id_dir_dep_prod = p_id_dir_dep_prod, id_dir_dep_finanzas = p_id_dir_dep_finanzas
	WHERE p_id_empleado=id_empleado;
    
    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE actualizar_RolPago(IN p_id_pago CHAR(10),
									IN p_pago_neto INT,
                                    IN p_id_empleado CHAR(10),
                                    IN p_id_dep_finanzas CHAR(10))
BEGIN
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
		ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al actualizar en Rol de Pago';
	END;
    
    START TRANSACTION;
    
    UPDATE RolPago
    SET id_pago = p_id_pago, pago_neto = p_pago_neto, 
    id_empleado = p_id_empleado, id_dep_finanzas = p_id_dep_finanzas
    WHERE id_pago = p_id_pago;

	COMMIT;
END //

DELIMITER ;
