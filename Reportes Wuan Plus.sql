use wuanplus;
DROP VIEW IF EXISTS reporte_empleados_creativo;
DROP VIEW IF EXISTS reporte_empleados_produccion;
DROP VIEW IF EXISTS reporte_empleados_finanzas;

DROP VIEW IF EXISTS reporte_proyectos_creativo;
DROP VIEW IF EXISTS reporte_proyectos_produccion;
DROP VIEW IF EXISTS reporte_proyectos_finanzas;

DROP VIEW IF EXISTS reporte_facturas_creativo;
DROP VIEW IF EXISTS reporte_facturas_produccion;
DROP VIEW IF EXISTS reporte_facturas_finanzas;


-- Vista 1

-- Reporte de Facturas con Detalles de Productos y Clientes

CREATE VIEW Facturas_Productos_Clientes AS
SELECT f.num_Factura, c.nombre_Empresa AS Cliente,
	   SUM(p.precio) AS PrecioTotalProductos,
       COUNT(p.id_proyecto) AS CantidadProductos,
       f.precio_Total
FROM Factura f JOIN Producto_Tienda p ON f.num_Factura = p.num_Factura
	JOIN Cliente c ON f.RUC = c.RUC
GROUP BY f.num_Factura, c.nombre_Empresa, f.precio_Total;

-- Vista 2

-- Reporte de Proyectos ordenados por duración

CREATE VIEW Proyectos_Departamento_Producción_Duracion AS
SELECT 
    s.titulo AS Proyecto,
    s.duracion AS Duracion,
    dp.nombre AS Departamento_Produccion
FROM 
    segmento s
    INNER JOIN departamento_produccion dp ON s.id_dep_prod = dp.id_departamento
ORDER BY 
    s.duracion DESC;

-- Vista 5

-- Reporte de Proyectos por Departamento Creativo Ordenados por Presupuesto

CREATE VIEW Reporte_Proyectos_Departamento_Creativo AS
SELECT 
    pa.titulo AS Proyecto,
    pa.presupuesto AS Presupuesto,
    dc.nombre AS Departamento_Creativo
FROM 
    publicidad_anuncio_canal pa
    INNER JOIN departamento_creativo dc ON pa.id_dep_creativo = dc.id_departamento
UNION
SELECT 
    pw.titulo AS Proyecto,
    pw.presupuesto AS Presupuesto,
    dc.nombre AS Departamento_Creativo
FROM 
    publicidad_anuncio_web pw
    INNER JOIN departamento_creativo dc ON pw.id_dep_creativo = dc.id_departamento
ORDER BY 
    Presupuesto DESC;

-- Vista 6

-- Reporte de Pagos a Empleados con Información del Departamento

CREATE VIEW Pagos_Empleados_por_Departamento AS
SELECT p.id_pago, e.id_empleado, e.nombre, e.apellido,
	   p.pago_neto, df.nombre AS DepartamentoFinanzas,
       (SELECT SUM(p2.pago_neto)
       FROM rol_pago p2
       WHERE p2.id_Dep_Finanzas = df.id_Departamento) AS TotalPagosDepartamento
FROM rol_pago p JOIN Empleado e ON p.id_empleado = e.id_empleado
	JOIN departamento_finanzas df ON p.id_Dep_Finanzas = df.id_Departamento;
    
    
SELECT * FROM facturas_productos_clientes;
SELECT * FROM pagos_empleados_por_departamento;


-- Vistas Extra
-- Reporte de Proyectos en Departamento Creativo con Subconsulta para comisiones altas

CREATE VIEW Proyectos_Comisión_Alta AS
SELECT 
    pa.titulo AS Proyecto,
    pa.presupuesto AS Presupuesto,
    pa.comision_a_empresa AS Comision,
    dc.nombre AS Departamento_Creativo
FROM 
    publicidad_anuncio_canal pa
    INNER JOIN departamento_creativo dc ON pa.id_dep_creativo = dc.id_departamento
WHERE 
    pa.comision_a_empresa > (
        SELECT AVG(comision_a_empresa) 
        FROM publicidad_anuncio_canal
    )
UNION
SELECT 
    pw.titulo AS Proyecto,
    pw.presupuesto AS Presupuesto,
    pw.comision_a_empresa AS Comision,
    dc.nombre AS Departamento_Creativo
FROM 
    publicidad_anuncio_web pw
    INNER JOIN departamento_creativo dc ON pw.id_dep_creativo = dc.id_departamento
WHERE 
    pw.comision_a_empresa > (
        SELECT AVG(comision_a_empresa) 
        FROM publicidad_anuncio_web
    );

-- Reporte de Empleados por Departamento
CREATE VIEW reporte_empleados_creativo AS
SELECT *
FROM empleado e
WHERE e.id_dep_creativo = e.id_dir_dep_creativo;

CREATE VIEW reporte_empleados_produccion AS
SELECT *
FROM empleado e
WHERE e.id_dep_prod = e.id_dir_dep_prod;

CREATE VIEW reporte_empleados_finanzas AS
SELECT *
FROM empleado e
WHERE e.id_dep_finanzas = e.id_dir_dep_finanzas;


-- proyectos por depa
CREATE VIEW reporte_proyectos_creativo AS
SELECT p.id_proyecto, p.titulo, p.presupuesto, p.descripcion, p.fechaInicio, p.fechaFin
FROM publicidad_anuncio_canal p
WHERE p.id_dep_creativo = (SELECT id_dir_dep_creativo FROM empleado WHERE id_empleado = CURRENT_USER)
UNION
SELECT p.id_proyecto, p.titulo, p.presupuesto, p.descripcion, p.fechaInicio, p.fechaFin
FROM publicidad_anuncio_web p
WHERE p.id_dep_creativo = (SELECT id_dir_dep_creativo FROM empleado WHERE id_empleado = CURRENT_USER);

CREATE VIEW reporte_proyectos_produccion AS
SELECT p.id_proyecto, p.titulo, p.presupuesto, p.descripcion, p.fecha_inicio, p.fecha_fin
FROM segmento p
WHERE p.id_dep_prod = (SELECT id_dir_dep_prod FROM empleado WHERE id_empleado = CURRENT_USER)
UNION
SELECT p.id_proyecto, p.titulo, p.presupuesto, p.descripcion, p.fecha_inicio, p.fecha_fin
FROM producto_tienda p
WHERE p.id_dep_prod = (SELECT id_dir_dep_prod FROM empleado WHERE id_empleado = CURRENT_USER);

CREATE VIEW reporte_proyectos_finanzas AS
SELECT f.num_factura, f.precio_total, f.fecha, c.nombre_empresa
FROM factura f
JOIN cliente c ON f.RUC = c.RUC
WHERE f.id_dep_finanzas = (SELECT id_dir_dep_finanzas FROM empleado WHERE id_empleado = CURRENT_USER);


-- factura x depa

CREATE VIEW reporte_facturas_creativo AS
SELECT f.num_factura, f.precio_total, f.fecha, c.nombre_empresa
FROM factura f
JOIN cliente c ON f.RUC = c.RUC
WHERE EXISTS (
    SELECT 1
    FROM publicidad_anuncio_canal p
    WHERE p.num_factura = f.num_factura AND p.id_dep_creativo = (SELECT id_dir_dep_creativo FROM empleado WHERE id_empleado = CURRENT_USER)
)
OR EXISTS (
    SELECT 1
    FROM publicidad_anuncio_web p
    WHERE p.num_factura = f.num_factura AND p.id_dep_creativo = (SELECT id_dir_dep_creativo FROM empleado WHERE id_empleado = CURRENT_USER)
);

CREATE VIEW reporte_facturas_produccion AS
SELECT f.num_factura, f.precio_total, f.fecha, c.nombre_empresa
FROM factura f
JOIN cliente c ON f.RUC = c.RUC
WHERE EXISTS (
    SELECT 1
    FROM segmento p
    WHERE p.num_factura = f.num_factura AND p.id_dep_prod = (SELECT id_dir_dep_prod FROM empleado WHERE id_empleado = CURRENT_USER)
)
OR EXISTS (
    SELECT 1
    FROM producto_tienda p
    WHERE p.num_factura = f.num_factura AND p.id_dep_prod = (SELECT id_dir_dep_prod FROM empleado WHERE id_empleado = CURRENT_USER)
);

CREATE VIEW reporte_facturas_finanzas AS
SELECT f.num_factura, f.precio_total, f.fecha, c.nombre_empresa
FROM factura f
JOIN cliente c ON f.RUC = c.RUC
WHERE f.id_dep_finanzas = (SELECT id_dir_dep_finanzas FROM empleado WHERE id_empleado = CURRENT_USER);



