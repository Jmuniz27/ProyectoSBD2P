-- Crear usuario para el Departamento Creativo
CREATE USER 'usuario_creativo'@'%' IDENTIFIED BY 'password123';
GRANT SELECT ON wuanplus.cliente to 'usuario_creativo'@'%';
GRANT SELECT ON wuanPlus.reporte_empleados_creativo TO 'usuario_creativo'@'%';
GRANT SELECT ON wuanPlus.reporte_proyectos_creativo TO 'usuario_creativo'@'%';
GRANT SELECT ON wuanPlus.reporte_facturas_creativo TO 'usuario_creativo'@'%';

-- Crear usuario para el Departamento Producción
CREATE USER 'usuario_produccion'@'%' IDENTIFIED BY 'password123';
GRANT SELECT ON wuanplus.cliente to 'usuario_produccion'@'%';
GRANT SELECT ON wuanPlus.reporte_empleados_produccion TO 'usuario_produccion'@'%';
GRANT SELECT ON wuanPlus.reporte_proyectos_produccion TO 'usuario_produccion'@'%';
GRANT SELECT ON wuanPlus.reporte_facturas_produccion TO 'usuario_produccion'@'%';

-- Crear usuario para el Departamento Finanzas
CREATE USER 'usuario_finanzas'@'%' IDENTIFIED BY 'password123';
GRANT SELECT ON wuanplus.cliente to 'usuario_finanzas'@'%';
GRANT SELECT ON wuanPlus.reporte_empleados_finanzas TO 'usuario_finanzas'@'%';
GRANT SELECT ON wuanPlus.reporte_proyectos_finanzas TO 'usuario_finanzas'@'%';
GRANT SELECT ON wuanPlus.reporte_facturas_finanzas TO 'usuario_finanzas'@'%';


-- Crear usuario para el Director General
CREATE USER 'director_general'@'%' IDENTIFIED BY 'password123';
GRANT ALL PRIVILEGES ON wuanPlus.* TO 'director_general'@'%';

-- Crear usuario para un Cliente
CREATE USER 'cliente'@'%' IDENTIFIED BY 'password123';
-- GRANT SELECT ON wuanPlus.cliente TO 'cliente'@'%';
-- GRANT SELECT ON wuanPlus.factura TO 'cliente'@'%';

-- reportes tablas
-- para cliente
GRANT SELECT, INSERT, UPDATE, DELETE ON wuanPlus.cliente TO 'cliente'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE ON wuanPlus.factura TO 'cliente'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE ON wuanPlus.empleado TO 'cliente'@'%';
GRANT SELECT ON wuanPlus.departamento_creativo TO 'cliente'@'%';
GRANT SELECT ON wuanPlus.departamento_produccion TO 'cliente'@'%';
GRANT SELECT ON wuanPlus.departamento_finanzas TO 'cliente'@'%';

FLUSH PRIVILEGES;