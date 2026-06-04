CREATE TABLE categoria (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    eliminado INTEGER DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    nombre TEXT NOT NULL,
    descripcion TEXT
);
CREATE TABLE usuario (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    eliminado INTEGER DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    nombre TEXT NOT NULL,
    apellido TEXT NOT NULL,
    mail TEXT NOT NULL UNIQUE,
    celular TEXT,
    contrasenia TEXT NOT NULL,
    rol TEXT NOT NULL
);
CREATE TABLE producto (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    eliminado INTEGER DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    nombre TEXT NOT NULL,
    precio REAL NOT NULL,
    descripcion TEXT,
    stock INTEGER NOT NULL,
    imagen TEXT,
    disponible INTEGER DEFAULT 1,
    categoria_id INTEGER NOT NULL,
    FOREIGN KEY (categoria_id)
        REFERENCES categoria(id)
);
CREATE TABLE pedido (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    eliminado INTEGER DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    fecha DATE NOT NULL,
    estado TEXT NOT NULL,
    total REAL DEFAULT 0,
    forma_pago TEXT NOT NULL,
    usuario_id INTEGER NOT NULL,
    FOREIGN KEY (usuario_id)
        REFERENCES usuario(id)
);
CREATE TABLE detalle_pedido (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    eliminado INTEGER DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    cantidad INTEGER NOT NULL,
    subtotal REAL NOT NULL,
    pedido_id INTEGER NOT NULL,
    producto_id INTEGER NOT NULL,
    FOREIGN KEY (pedido_id)
        REFERENCES pedido(id),
    FOREIGN KEY (producto_id)
        REFERENCES producto(id)
);