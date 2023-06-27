REM*************** DEFINICION DE BASE DE DATOS, USUARIO Y PERMISOS *************************
CREATE TABLESPACE master
      DATAFILE 'master' size 100 M;

CREATE USER master
      IDENTIFIED BY master
         DEFAULT TABLESPACE master;

grant DBA to master;

REM******************* SUBSISTEMA DE AUTENTICACION *****************

******************************************************************************
******** TABLA: ROLES
******** BD: MASTER_ECO
******** DESCRIPCION: DEFINICION DE LOS ROLES DE LOS USUARIOS DE LA APLICACION.
******** CLAVE PRINCIPAL: NUMERO_ROL.
******** CLAVE SECUNDARIA: CODIGO_ROL.
******** RELACIONES:USUARIOS Y TAREAS. AMBAS A PARTIR DE TABLAS DE CRUZE. 
******** USO: CRUD DE LOS ROLES.
******** RESTRICCIONES:ESTADO_ROL. AC ACTIVO Y SE PUEDE ASIGNAR A CUALQUIER USUARIO NUEVO.  
******** BA INACTIVO Y NO SE PUEDE ASIGNAR A NUEVOS USUARIOS.
******** CREACION: 15-8-2012
******** MODIFICACION:
******************************************************************************
CREATE TABLE roles
   (	numero_rol number(10,0),
	fecha_alta_rol date constraint fecha_alta_rol_nn not null,
	fecha_baja_rol date, 
	estado_rol char(2) constraint estado_rol_nn not null,

	codigo_rol varchar2(25),
	
	descripcion_rol varchar2(100),
 
	constraint numero_rol_PK primary key (numero_rol),
	constraint estado_rol_ck check (estado_rol in ('AC','BA')),
	constraint codigo_rol_u unique (codigo_rol) 
   )

******************************************************************************
******** TABLA: TAREAS
******** BD: MASTER_ECO
******** DESCRIPCION: DEFINICION DE LAS POSIBLES TAREAS A REALIZAR DENTRO DE UNA APLICACION
******** POR PARTE DE CUALQUIER USUARIO. DE ESTA FORMA SE PUEDE DEJAR TAN ABIERTA O RESTRINGIDA COMO
******** LAS NECESIDADES DE CADA CLIENTE DICTAMINEN.
******** CLAVE PRINCIPAL:NUMERO_TAREA
******** CLAVE SECUNDARIA: CODIGO_TAREA
******** RELACIONES: ROLES MEDIANTE UNA TABLA DE CRUZE
******** USO: SOLO ESTABLECER LAS TAREAS, SIN POSIBILIDAD DESDE LA APLICACION DE SER CAMBIADA DE 
******** NINGUNA FORMA NI MANERA. LOS CAMBIOS VENDRIAN EN LA FASE DE DESARROLO.
******** RESTRICCIONES:ESTADO_TAREA. AC ACTIVA Y PUEDE SER ASIGNADA A CUALQUIER ROL. BA Y NO PUEDE SER
******** ASIGNADA A NINGUN ROL.
******** CREACION: 15-8-2012
******** MODIFICACION:
******************************************************************************        
CREATE TABLE  tareas  
   (	numero_tarea number(10,0),
	fecha_alta_tarea date constraint fecha_alta_tarea_nn not null,
	fecha_baja_tarea date, 
	estado_tarea char(2) constraint estado_tarea_nn not null,

	codigo_tarea varchar2(25),
	
	descripcion_tarea varchar2(100),
	vinculo_tarea varchar2 (50),
	posicion_menu number (6,0),

	constraint numero_tarea_PK primary key (numero_tarea),
	constraint estado_tarea_ck check (estado_tarea in ('AC','BA')),
	constraint codigo_tarea_u unique (codigo_tarea) 
   )

******************************************************************************
******** TABLA: USUARIOS
******** BD: MASTER_ECO
******** DESCRIPCION: DEFINICION DE LOS USUARIOS DE LAS APLICACIONES.
******** CLAVE PRINCIPAL: NUMERO_USUARIO.
******** CLAVE SECUNDARIA: CLAVE_USUARIO
******** RELACIONES: ROLES, PERFILES. AMBAS A TRAVES DE TABLA DE CRUZE
******** USO: CRUD USUARIOS Y ATENTICACION EN LOS ACCESOS.
******** RESTRICCIONES: ESTADO_USUARIO. AC ACTIVO Y POR LO TANTO FUNCIONAL. BA INACTIVO
******** Y POR LO TANTO SIN ACCESO A LOS PROGRAMAS. IN MOMENTANEAMENTE EN BAJA, SE PUEDE REACTIVAR.
******** CREACION: 15-8-2012
******** MODIFICACION:
******************************************************************************

CREATE TABLE usuarios
   (	numero_usuario number(10,0),
	fecha_alta_usuario date constraint fecha_alta_usuario_nn not null,
	fecha_baja_usuario date, 
	estado_usuario char(2) constraint estado_usuario_nn not null,
	
	codigo_usuario VARCHAR2(20), 

	clave_usuario VARCHAR2(10), 
	

	constraint numero_usuario_PK primary key (numero_usuario), 
	constraint estado_usuario_ck check (estado_usuario in ('AC','BA','IN')),
	constraint codigo_usuario_u unique (codigo_usuario) 
	 
  )

rem ******************* TABLAS DE CRUZE ***************************************  

******************************************************************************
******** TABLA: ROLES_TAREAS.
******** BD: MASTER
******** DESCRIPCION:
******** CLAVE PRINCIPAL:
******** CLAVE SECUNDARIA:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** CREACION:
******** MODIFICACION:
******************************************************************************
CREATE TABLE roles_tareas 
   (	numero_rol NUMBER(10,0), 
	numero_tarea NUMBER(10,0), 

	constraint roles_tareas_PK primary key (numero_rol,numero_tarea), 

	constraint numero_rol_FK foreign key (numero_rol)
	  references roles (numero_rol), 
	constraint numero_tarea_FK foreign key (numero_tarea)
	  references tareas (numero_tarea)
   )
REM******************* FIN SUBSISTEMA DE AUTENTICACION *****************

REM******************** TABLAS DE PEDIDOS  *************************

******************************************************************************
******** TABLA: PEDIDOS
******** BD: MASTER
******** DESCRIPCION:
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******************************************************************************

CREATE TABLE  "PEDIDOS" 
   (	"NUMERO_PEDIDO" NUMBER(5,0), 
	"FECHA_PEDIDO" DATE, 
	"PORTE_PEDIDO" NUMBER(11,2), 
	"SEGURO_PEDIDO" NUMBER(11,2), 
	"OTROS_CARGOS_PEDIDO" NUMBER(11,2), 
	"TOTAL_CARGOS_PEDIDO" NUMBER(11,2), 
	"TOTAL_BRUTO_PEDIDO" NUMBER(11,2), 
	"PORCENTAJE_IVA_PEDIDO" NUMBER(2,0), 
	"IVA_PEDIDO" NUMBER(11,2), 
	"TOTAL_FACTURA_PEDIDO" NUMBER(11,2), 
	"CODIGO_CLIENTE" NUMBER(5,0) NOT NULL ENABLE, 
	 CONSTRAINT "PEDIDOS_NUMERO_PEDIDO_PK" PRIMARY KEY ("NUMERO_PEDIDO") ENABLE, 
	 CONSTRAINT "PEDIDOS_COD_CLI_FK" FOREIGN KEY ("CODIGO_CLIENTE")
	  REFERENCES  "CLIENTES" ("CODIGO_CLIENTE") ENABLE
   );


******************************************************************************
******** TABLA: LINEA DE PEDIDOS
******** BD: MASTER
******** DESCRIPCION:
******** CLAVE PRINCIPAL:
******** RELACIONES:
******** USO:
******** RESTRICCIONES:
******** MODIFICACION: MoDIFICADO EL 21-1-2009
******************************************************************************

CREATE TABLE  "LINEA_PEDIDO"
   (	
	codigo_linea_pedido number(10,0),
	"CODIGO_ARTICULO" NUMBER(5,0), 
	"NUMERO_PEDIDO" NUMBER(5,0), 
	"PRECIO_UNIDAD_ARTICULO" NUMBER(11,2), 
	"NUMERO_UNIDADES_ARTICULO" NUMBER(5,0), 
	"PORCENTAJE_DESCUENTO" NUMBER(4,2), 
	 CONSTRAINT "LINEA_PEDIDO_NUM_PED_FK" FOREIGN KEY ("NUMERO_PEDIDO")
	  REFERENCES  "PEDIDOS" ("NUMERO_PEDIDO") ENABLE, 
	 CONSTRAINT "LINEA_PEDIDO_COD_ART_FK" FOREIGN KEY ("CODIGO_ARTICULO")
	  REFERENCES  "ARTICULOS" ("CODIGO_ARTICULO") ENABLE,
	CONSTRAINT "codigo_linea_pedido_PK" PRIMARY KEY (codigo_linea_pedido)
   );

REM******************** FIN TABLAS PEDIDOS *****************************



******************************************************************************
******** TABLA: PAISES.
******** BD: MASTER_ECO
******** DESCRIPCION: DEFINICION DE LOS PAISES PARA SU USO EN LAS DIRECCIONES.
******** CLAVE PRINCIPAL:NUMERO_PAIS.
******** CLAVE SECUNDARIA:
******** RELACIONES:DIRECCIONES.
******** USO: TABLAS AUXILIAR DE DIRECCIONES.
******** RESTRICCIONES:
******** CREACION:17-8-2012.
******** MODIFICACION:
******************************************************************************
CREATE TABLE  paises
   (	numero_pais  number(10,0),
	
	PAIs_ISONUM  number(6,0),
   	PAIs_ISO2  char(2),
   	PAIs_ISO3  char(3),
   	PAIs_NOMBRE  varchar2(80),
 
	constraint numero_pais_PK primary key (numero_pais )
   )

******************************************************************************
******** TABLA: PROVINCIAS.
******** BD: MASTER_ECO.
******** DESCRIPCION: DESCRIPCION DE LAS PROVINCIAS PARA SU USO EN DIRECCIONES.
******** CLAVE PRINCIPAL:NUMERO_PROVINCIA.
******** CLAVE SECUNDARIA:
******** RELACIONES: DIRECCIONES.
******** USO:  TABLA AUXILIAR DE DIRECCIONES.
******** RESTRICCIONES:
******** CREACION:17-8-2012.
******** MODIFICACION:
******************************************************************************
CREATE TABLE  provincias
   (	codigo_provincia  number(10,0),
	
	provincia  varchar2(255) NOT NULL,
 
	constraint numero_provincia_PK primary key (codigo_provincia)
   )

******************************************************************************
******** TABLA: MUNICIPIOS.
******** BD: MASTER_ECO.
******** DESCRIPCION: DEFINICION DE LOS MUNICIPIOS A USAR EN DIRECCIONES.
******** CLAVE PRINCIPAL: NUMERO_MUNICIPIO.
******** CLAVE SECUNDARIA:
******** RELACIONES: DIRECCIONES.
******** USO: TABLA AUXILIAR DE DIRECCIONES.
******** RESTRICCIONES:
******** CREACION:17-8-2012.
******** MODIFICACION:
******************************************************************************
CREATE TABLE  municipios
   (	numero_municipio  number(10,0),

	numero_provincia number(2,0) NOT NULL,
	municipio  varchar2(255) NOT NULL,

	constraint numero_municipios_PK primary key (numero_provincia,numero_municipio)
   )

rem ********** SECUENCIADORES PARA LA BASE DE DATOS  *******************

REM ***** AUTENTICACION
create sequence numero_rol;
create sequence numero_tarea;
create sequence numero_usuario;

create sequence num_linea_pedido;
create sequence numero_pedido;

create sequence numero_provincia

create sequence numero_pais;