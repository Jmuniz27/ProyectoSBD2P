-- Crear usuario para el Departamento Creativo
CREATE USER 'usuario_creativo'@'%' IDENTIFIED BY 'password123';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON wuanPlus.departamento_creativo TO 'usuario_creativo'@'%';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON wuanPlus.empleado TO 'usuario_creativo'@'%';

-- Crear usuario para el Departamento Producción
CREATE USER 'usuario_produccion'@'%' IDENTIFIED BY 'password123';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON wuanPlus.departamento_produccion TO 'usuario_produccion'@'%';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON wuanPlus.empleado TO 'usuario_produccion'@'%';

-- Crear usuario para el Departamento Finanzas
CREATE USER 'usuario_finanzas'@'%' IDENTIFIED BY 'password123';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON wuanPlus.departamento_finanzas TO 'usuario_finanzas'@'%';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON wuanPlus.empleado TO 'usuario_finanzas'@'%';

-- Crear usuario para el Director General
CREATE USER 'director_general'@'%' IDENTIFIED BY 'password123';
GRANT ALL PRIVILEGES ON wuanPlus.* TO 'director_general'@'%';

-- Crear usuario para un Cliente
CREATE USER 'cliente'@'%' IDENTIFIED BY 'password123';
-- GRANT SELECT ON wuanPlus.cliente TO 'cliente'@'%';
-- GRANT SELECT ON wuanPlus.factura TO 'cliente'@'%';

FLUSH PRIVILEGES;