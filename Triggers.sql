use wuanplus;
DELIMITER //
CREATE TRIGGER factura_producto_tienda
AFTER INSERT ON producto_tienda
FOR EACH ROW
BEGIN
	INSERT factura(RUC, precio_total, fecha, id_dep_finanzas)
    VALUES(NEW.RUC, NEW.presupuesto*NEW.comision_a_empresa, NOW(), 'DF001');
END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER factura_anuncio_canal
AFTER INSERT ON publicidad_anuncio_canal
FOR EACH ROW
BEGIN
	INSERT factura(RUC, precio_total, fecha, id_dep_finanzas)
    VALUES(NEW.RUC, NEW.presupuesto*NEW.comision_a_empresa, NOW(), 'DF001');
END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER factura_anuncio_web
AFTER INSERT ON publicidad_anuncio_web
FOR EACH ROW
BEGIN
	INSERT factura(RUC, precio_total, fecha, id_dep_finanzas)
    VALUES(NEW.RUC, NEW.presupuesto*NEW.comision_a_empresa, NOW(), 'DF001');
END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER factura_segmento
AFTER INSERT ON segmento
FOR EACH ROW
BEGIN
	INSERT factura(RUC, precio_total, fecha, id_dep_finanzas)
    VALUES(NEW.RUC, NEW.presupuesto*NEW.comision_a_empresa, NOW(), 'DF001');
END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER insertarProductoTienda
BEFORE INSERT ON producto_tienda
FOR EACH ROW
BEGIN
    -- Validar que el precio sea positivo
    IF NEW.precio <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El precio debe ser un valor positivo.';
    END IF;
    SET NEW.fecha_inicio = NOW();
END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER insertarPublicidadAnuncioWeb
BEFORE INSERT ON publicidad_anuncio_web
FOR EACH ROW
BEGIN
	IF NEW.tamano_banner <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: La duración del anuncio debe ser mayor a cero.';
    END IF;
    SET NEW.fechaInicio = NOW();
END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER insertarPublicidadAnuncioCanal
BEFORE INSERT ON publicidad_anuncio_canal
FOR EACH ROW
BEGIN
    IF NEW.duracion <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: La duración del anuncio debe ser mayor a cero.';
    END IF;
    SET NEW.fechaInicio = NOW();
END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER insertarSegmento
BEFORE INSERT ON segmento
FOR EACH ROW
BEGIN
    IF NEW.duracion <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: El tamaño del segmento debe ser un valor positivo.';
    END IF;
    SET NEW.fecha_Inicio = NOW();
END//
DELIMITER ;