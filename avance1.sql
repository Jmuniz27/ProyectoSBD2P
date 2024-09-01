DROP DATABASE IF EXISTS wuanPlus;
CREATE DATABASE wuanPlus;

USE wuanPlus;

CREATE TABLE persona_contacto (
    cedula CHAR(10) PRIMARY KEY,
    nombre VARCHAR(20),
    apellido VARCHAR(20),
    email VARCHAR(20),
    telefono VARCHAR(20)
);

CREATE TABLE cliente (
    RUC CHAR(13) PRIMARY KEY,
    nombre_empresa VARCHAR(20),
    decrip_empresa VARCHAR(200),
    direccion VARCHAR(100),
    sitio_web VARCHAR(100),
    id_persona_contacto CHAR(10),
    FOREIGN KEY (id_persona_contacto) REFERENCES persona_contacto(cedula)
);

CREATE TABLE departamento_creativo (
    id_departamento CHAR(10) PRIMARY KEY,
    descripcion VARCHAR(300),
    nombre VARCHAR(20),
    email VARCHAR(20)
);

CREATE TABLE departamento_produccion (
    id_departamento CHAR(10) PRIMARY KEY,
    descripcion VARCHAR(300),
    nombre VARCHAR(20),
    email VARCHAR(20)
);

CREATE TABLE departamento_finanzas (
    id_departamento CHAR(10) PRIMARY KEY,
    descripcion VARCHAR(300),
    nombre VARCHAR(20),
    email VARCHAR(20)
);

CREATE TABLE empleado (
    id_empleado CHAR(10) PRIMARY KEY,
    sueldoBase INT,
    nombre VARCHAR(20),
    apellido VARCHAR(20),
    puesto VARCHAR(20),
    contrasena VARCHAR(20),
    direccion VARCHAR(20),
    id_dep_creativo CHAR(10),
    id_dep_prod CHAR(10),
    id_dep_finanzas CHAR(10),
    id_dir_dep_creativo CHAR(10),
    id_dir_dep_prod CHAR(10),
    id_dir_dep_finanzas CHAR(10),
    FOREIGN KEY (id_dep_creativo) REFERENCES departamento_creativo(id_departamento),
    FOREIGN KEY (id_dep_prod) REFERENCES departamento_produccion(id_departamento),
    FOREIGN KEY (id_dep_finanzas) REFERENCES departamento_finanzas(id_departamento),
    FOREIGN KEY (id_dir_dep_creativo) REFERENCES departamento_creativo(id_departamento),
    FOREIGN KEY (id_dir_dep_prod) REFERENCES departamento_produccion(id_departamento),
    FOREIGN KEY (id_dir_dep_finanzas) REFERENCES departamento_finanzas(id_departamento)
);

CREATE TABLE factura (
    num_factura CHAR(10) PRIMARY KEY,
    RUC CHAR(13),
    precio_total INT,
    fecha DATE,
    id_dep_finanzas CHAR(10),
    FOREIGN KEY (RUC) REFERENCES cliente(RUC),
    FOREIGN KEY (id_dep_finanzas) REFERENCES departamento_finanzas(id_departamento)
);

CREATE TABLE rol_pago (
    id_pago CHAR(10),
    pago_neto INT,
    id_empleado CHAR(10),
    id_dep_finanzas CHAR(10),
    unique(id_pago),
    PRIMARY KEY (id_pago, id_empleado),
    FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado),
    FOREIGN KEY (id_dep_finanzas) REFERENCES departamento_finanzas(id_departamento)
);

CREATE TABLE bono (
    id_bono CHAR(10) PRIMARY KEY,
    cantidad INT,
    tipo_bono VARCHAR(20)
);

CREATE TABLE catalogo (
    id_bono CHAR(10),
    id_pago CHAR(10),
    PRIMARY KEY (id_bono, id_pago),
    FOREIGN KEY (id_bono) REFERENCES bono(id_bono),
    FOREIGN KEY (id_pago) REFERENCES rol_pago(id_pago)
);

CREATE TABLE segmento (
    id_proyecto CHAR(10) PRIMARY KEY,
    RUC CHAR(13),
    num_factura CHAR(10),
    rating VARCHAR(20),
    duracion INT,
    estado BOOLEAN,
    titulo VARCHAR(20),
    presupuesto INT,
    descripcion VARCHAR(300),
    fecha_inicio DATE,
    fecha_Fin DATE,
    id_dep_prod CHAR(10),
    comision_a_empresa DECIMAL(5,2),
    FOREIGN KEY (RUC) REFERENCES cliente(RUC),
    FOREIGN KEY (num_factura) REFERENCES factura(num_factura),
    FOREIGN KEY (id_dep_prod) REFERENCES departamento_produccion(id_departamento)
);

CREATE TABLE producto_tienda (
    id_proyecto CHAR(10) PRIMARY KEY,
    RUC CHAR(13),
    num_factura CHAR(50),
    categoria VARCHAR(20),
    precio DECIMAL(5,2),
    titulo VARCHAR(20),
    presupuesto INT,
    descripcion VARCHAR(300),
    fecha_inicio DATE,
    fecha_fin DATE,
    id_dep_prod CHAR(10),
    comision_a_empresa DECIMAL(5,2),
    FOREIGN KEY (RUC) REFERENCES cliente(RUC),
    FOREIGN KEY (num_factura) REFERENCES factura(num_factura),
    FOREIGN KEY (id_dep_prod) REFERENCES departamento_produccion(id_departamento)
);

CREATE TABLE publicidad_anuncio_canal (
    id_proyecto CHAR(10) PRIMARY KEY,
    RUC CHAR(13),
    num_factura CHAR(10),
    titulo VARCHAR(20),
    presupuesto INT,
    descripcion VARCHAR(300),
    fechaInicio DATE,
    fechaFin DATE,
    duracion INT,
    id_dep_creativo CHAR(10),
    comision_a_empresa DECIMAL(5,2),
    FOREIGN KEY (RUC) REFERENCES cliente(RUC),
    FOREIGN KEY (num_factura) REFERENCES factura(num_factura),
    FOREIGN KEY (id_dep_creativo) REFERENCES departamento_creativo(id_departamento)
);

CREATE TABLE publicidad_anuncio_web (
    id_proyecto CHAR(10) PRIMARY KEY,
    RUC CHAR(13),
    num_factura CHAR(10),
    titulo VARCHAR(20),
    presupuesto INT,
    descripcion VARCHAR(300),
    fechaInicio DATE,
    fechaFin DATE,
    tamano_banner VARCHAR(20),
    id_dep_creativo CHAR(10),
    comision_a_empresa DECIMAL(5,2),
    FOREIGN KEY (RUC) REFERENCES cliente(RUC),
    FOREIGN KEY (num_factura) REFERENCES factura(num_factura),
    FOREIGN KEY (id_dep_creativo) REFERENCES departamento_creativo(id_departamento)
);