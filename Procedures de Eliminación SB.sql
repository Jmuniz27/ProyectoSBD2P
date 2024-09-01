use wuanplus;

DELIMITER //

CREATE PROCEDURE eliminar_segmento(
    IN p_id_proyecto CHAR(10)
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SELECT 'Ha ocurrido un error y la transacción ha sido revertida.';
    END;

    START TRANSACTION;

    DELETE FROM segmento WHERE id_proyecto = p_id_proyecto;

    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE eliminar_PublicidadAnuncioCanal(
    IN p_id_proyecto CHAR(10)
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al eliminar en publicidad_anuncio_canal';
    END;

    START TRANSACTION;

    DELETE FROM publicidad_anuncio_canal WHERE p_id_proyecto = id_proyecto;

    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE eliminar_PublicidadAnuncioWeb(
    IN p_id_proyecto CHAR(10)
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al eliminar en publicidad_anuncio_web';
    END;

    START TRANSACTION;

    DELETE FROM publicidad_anuncio_web WHERE id_proyecto = p_id_proyecto;

    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE eliminar_ProductoTienda(
    IN p_id_proyecto CHAR(10)
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al eliminar en producto_tienda';
    END;

    START TRANSACTION;

    DELETE FROM producto_tienda WHERE id_proyecto = p_id_proyecto;

    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE eliminar_Cliente(
    IN p_RUC CHAR(13)
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al eliminar en cliente';
    END;

    START TRANSACTION;

    DELETE FROM cliente WHERE  RUC =  p_RUC;

    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE eliminar_PersonaContacto(
    IN p_cedula CHAR(10)
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al eliminar en persona_contacto';
    END;

    START TRANSACTION;

    DELETE FROM persona_contacto WHERE cedula = p_cedula;

    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE eliminar_Empleado(
	IN p_id_empleado CHAR(10))
    BEGIN
		DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
			ROLLBACK;
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error el eliminar el empleado';
	END;
    
    START TRANSACTION;
    
    DELETE FROM Empleado WHERE id_empleado = p_id_empleado;
    
    COMMIT;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE eliminar_RolPago(
	IN p_id_pago CHAR(10))
    BEGIN
		DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
        BEGIN
			ROLLBACK;
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al eliminar Rol de pago';
		END;
        
        START TRANSACTION;
        
        DELETE FROM RolPago WHERE id_pago = p_id_pago;
        
        COMMIT;
END //

DELIMITER ;