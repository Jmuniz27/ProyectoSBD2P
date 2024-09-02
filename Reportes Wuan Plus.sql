use wuanplus;

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

