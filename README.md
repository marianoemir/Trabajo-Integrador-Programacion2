# Food Store - Trabajo Integrador Programación II

## Descripción

Food Store es una aplicación de consola desarrollada en Java para la gestión de pedidos, usuarios, productos y categorías.

El sistema implementa una arquitectura por capas utilizando:

* Entidades (Models)
* DAO (Data Access Object)
* Services
* SQLite como base de datos
* JDBC para el acceso a datos

## Tecnologías Utilizadas

* Java 21
* Maven
* SQLite
* JDBC
* DBeaver (administración de la base de datos)

## Estructura del Proyecto

```text
src/main/java
│
├── config
│   └── ConexionDB
│
├── entities
│   ├── Base
│   ├── Usuario
│   ├── Categoria
│   ├── Producto
│   ├── Pedido
│   └── DetallePedido
│
├── dao
│   ├── UsuarioDAO
│   ├── CategoriaDAO
│   ├── ProductoDAO
│   └── PedidoDAO
│
├── service
│   ├── UsuarioService
│   ├── CategoriaService
│   ├── ProductoService
│   └── PedidoService
│
├── enums
│   ├── Rol
│   ├── Estado
│   └── FormaPago
│
└── exception
    └── EntityNotFoundException
```

## Base de Datos

El proyecto utiliza SQLite.

La base de datos se encuentra incluida en el repositorio mediante el archivo:

```text
foodstore.db
```

La estructura de la base está definida en:

```text
schema.sql
```

Este archivo se conserva como documentación y respaldo de la estructura de tablas.

## Tablas del Sistema

* categoria
* producto
* usuario
* pedido
* detalle_pedido

## Funcionalidades

### Usuarios

* Alta de usuarios
* Modificación de usuarios
* Baja lógica (Soft Delete)
* Consulta y listado de usuarios

### Categorías

* Alta
* Modificación
* Eliminación
* Consulta

### Productos

* Alta
* Modificación
* Eliminación
* Consulta

### Pedidos

* Creación de pedidos
* Gestión de detalles de pedido
* Manejo de transacciones
* Cálculo de totales

## Soft Delete

La eliminación de usuarios se implementa mediante baja lógica.

En lugar de eliminar físicamente el registro de la base de datos, se actualiza el campo:

```text
eliminado = 1
```

Esto permite conservar el historial y mantener la integridad de los datos.



## Ejecución

1. Clonar el repositorio.
2. Abrir el proyecto en NetBeans.
3. Ejecutar Maven para descargar dependencias.
4. Verificar que el archivo `foodstore.db` se encuentre en la ubicación configurada.
5. Ejecutar la clase `Main`.



## Visualización de la Base de Datos con DBeaver

Para inspeccionar la base de datos SQLite:

1. Abrir DBeaver.
2. Seleccionar **Nueva Conexión**.
3. Elegir **SQLite**.
4. En el campo **Database File**, seleccionar el archivo:

```text
foodstore.db
```

5. Finalizar la conexión.
6. Expandir la conexión creada y abrir la carpeta **Tables** para visualizar las tablas del sistema.

Tablas disponibles:

* categoria
* producto
* usuario
* pedido
* detalle_pedido

Ejemplo de consulta:

```sql
SELECT * FROM usuario;
```

Para visualizar únicamente los usuarios activos:

```sql
SELECT * FROM usuario
WHERE eliminado = 0;
```


## Integrantes

### Base de Datos y Usuarios

* Mariano Chirino

### Categorías y Productos

* Andres Fabre

### Pedidos y Documentación

* Facundo Quiroga 
