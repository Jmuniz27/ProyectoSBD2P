-- Crear usuario para el Departamento Creativo
CREATE USER 'usuario_creativo'@'proyectosbdgrupo8.mysql.database.azure.com' IDENTIFIED BY 'password123';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON wuanPlus.departamento_creativo TO 'usuario_creativo'@'proyectosbdgrupo8.mysql.database.azure.com';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON wuanPlus.empleado TO 'usuario_creativo'@'proyectosbdgrupo8.mysql.database.azure.com';

-- Crear usuario para el Departamento Producción
CREATE USER 'usuario_produccion'@'proyectosbdgrupo8.mysql.database.azure.com' IDENTIFIED BY 'password123';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON wuanPlus.departamento_produccion TO 'usuario_produccion'@'proyectosbdgrupo8.mysql.database.azure.com';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON wuanPlus.empleado TO 'usuario_produccion'@'proyectosbdgrupo8.mysql.database.azure.com';

-- Crear usuario para el Departamento Finanzas
CREATE USER 'usuario_finanzas'@'proyectosbdgrupo8.mysql.database.azure.com' IDENTIFIED BY 'password123';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON wuanPlus.departamento_finanzas TO 'usuario_finanzas'@'proyectosbdgrupo8.mysql.database.azure.com';
-- GRANT SELECT, INSERT, UPDATE, DELETE ON wuanPlus.empleado TO 'usuario_finanzas'@'proyectosbdgrupo8.mysql.database.azure.com';

-- Crear usuario para el Director General
CREATE USER 'director_general'@'proyectosbdgrupo8.mysql.database.azure.com' IDENTIFIED BY 'password123';
-- GRANT ALL PRIVILEGES ON wuanPlus.* TO 'director_general'@'proyectosbdgrupo8.mysql.database.azure.com';

-- Crear usuario para un Cliente
CREATE USER 'cliente'@'proyectosbdgrupo8.mysql.database.azure.com' IDENTIFIED BY 'password123';
-- GRANT SELECT ON wuanPlus.cliente TO 'cliente'@'proyectosbdgrupo8.mysql.database.azure.com';
-- GRANT SELECT ON wuanPlus.factura TO 'cliente'@'proyectosbdgrupo8.mysql.database.azure.com';
