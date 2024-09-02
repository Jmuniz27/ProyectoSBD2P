drop user if exists 'usuario_creativo'@'%';
drop user if exists 'usuario_produccion'@'%';
drop user if exists 'usuario_finanzas'@'%';
drop USER 'cliente'@'%';

-- Crear usuario para el Departamento Creativo y permisos
CREATE USER 'usuario_creativo'@'%' IDENTIFIED BY 'password123';
GRANT ALL privileges ON wuanplus.publicidad_anuncio_canal to 'usuario_creativo'@'%';
GRANT ALL privileges ON wuanplus.publicidad_anuncio_web to 'usuario_creativo'@'%';
GRANT SELECT ON wuanplus.cliente to 'usuario_creativo'@'%';
GRANT SELECT ON wuanPlus.reporte_empleados_creativo TO 'usuario_creativo'@'%';
GRANT SELECT ON wuanPlus.empleado TO 'usuario_creativo'@'%';
GRANT SELECT ON wuanPlus.rol_pago TO 'usuario_creativo'@'%';
GRANT SELECT ON wuanPlus.reporte_proyectos_creativo TO 'usuario_creativo'@'%';
GRANT SELECT ON wuanPlus.Reporte_Proyectos_Departamento_Creativo TO 'usuario_creativo'@'%';
GRANT SELECT ON wuanPlus.Proyectos_Comisión_Alta TO 'usuario_creativo'@'%';

-- Crear usuario para el Departamento Producción
CREATE USER 'usuario_produccion'@'%' IDENTIFIED BY 'password123';
GRANT ALL privileges ON wuanplus.segmento to 'usuario_produccion'@'%';
GRANT ALL privileges ON wuanplus.producto_tienda to 'usuario_produccion'@'%';
GRANT SELECT ON wuanPlus.empleado TO 'usuario_creativo'@'%';
GRANT SELECT ON wuanPlus.rol_pago TO 'usuario_creativo'@'%';
GRANT SELECT ON wuanplus.cliente to 'usuario_produccion'@'%';
Grant select on proyectos_departamento_producción_duracion TO'usuario_produccion'@'%';
GRANT SELECT ON wuanPlus.reporte_empleados_produccion TO 'usuario_produccion'@'%';
GRANT SELECT ON wuanPlus.reporte_proyectos_produccion TO 'usuario_produccion'@'%';

-- Crear usuario para el Departamento Finanzas
-- dir finanzas: insert, update, delete consultar rol pago. Solo consultar clientes, empleados. No acceso PARA NADA a segmento ni producto tienda ni publicidad web ni publicidad canal. Acceso a reportes: Facturas_Productos_Clientes y Pagos_Empleados_por_Departamento .
CREATE USER 'usuario_finanzas'@'%' IDENTIFIED BY 'password123';
GRANT ALL privileges ON wuanplus.rol_pago to 'usuario_finanzas'@'%';
GRANT SELECT ON wuanplus.cliente to 'usuario_finanzas'@'%';
GRANT SELECT ON wuanPlus.empleado TO 'usuario_finanzas'@'%';
GRANT SELECT ON  wuanplus.Facturas_Productos_Clientes TO 'usuario_finanzas'@'%';
GRANT SELECT ON  wuanplus.Pagos_Empleados_por_Departamento TO 'usuario_finanzas'@'%';

-- Crear usuario para el Director General
CREATE USER 'director_general'@'%' IDENTIFIED BY 'password123';
GRANT ALL PRIVILEGES ON wuanPlus.* TO 'director_general'@'%';

-- Crear usuario para un Cliente
CREATE USER 'cliente'@'%' IDENTIFIED BY 'password123';
	GRANT SELECT ON wuanPlus.Facturas_Productos_Clientes  TO 'cliente'@'%';
	GRANT SELECT ON wuanPlus.factura TO 'cliente'@'%';
    -- Solo consultar clientes, empleados y rol pago. No acceso PARA NADA a publicidad web ni publicidad canal.
    grant select on wuanplus.cliente to 'cliente'@'%';


FLUSH PRIVILEGES;
