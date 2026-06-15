package integrado.prog2;

import integrado.prog2.entities.Usuario;
import integrado.prog2.entities.Categoria;
import integrado.prog2.entities.Producto;
import integrado.prog2.enums.Rol;
import integrado.prog2.exception.EntityNotFoundException;
import integrado.prog2.service.UsuarioService;
import integrado.prog2.service.CategoriaService;
import integrado.prog2.service.ProductoService;

import java.util.List;
import java.util.Scanner;
import integrado.prog2.entities.Pedido;//import Andres
import integrado.prog2.service.PedidoService; //import Andres

public class Main {

    static Scanner sc = new Scanner(System.in);
    static UsuarioService usuarioService = new UsuarioService();
    static PedidoService pedidoService = new PedidoService();

    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println("\n=== FOOD STORE ===");
            System.out.println("1. Categorias");
            System.out.println("2. Productos");
            System.out.println("3. Usuarios");
            System.out.println("4. Pedidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> menuCategorias();
                case 2 -> menuProductos();
                case 3 -> menuUsuarios();
                case 4 -> menuPedidos(); //Andres
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opcion invalida.");
            }

        } while (opcion != 0);
    }

    static void menuUsuarios() {

        int opcion;

        do {
            System.out.println("\n=== MENU USUARIOS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> listarUsuarios();
                case 2 -> crearUsuario();
                case 3 -> editarUsuario();
                case 4 -> eliminarUsuario();
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opcion invalida.");
            }

        } while (opcion != 0);
    }

    static void listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listar();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios cargados.");
        } else {
            usuarios.forEach(System.out::println);
        }
    }

    static void crearUsuario() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Mail: ");
        String mail = sc.nextLine();
        System.out.print("Celular: ");
        String celular = sc.nextLine();
        System.out.print("Contrasenia: ");
        String contrasenia = sc.nextLine();
        System.out.println("Rol (1. ADMIN / 2. USUARIO): ");
        int rolOpcion = leerEntero();
        Rol rol = rolOpcion == 1 ? Rol.ADMIN : Rol.USUARIO;

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setMail(mail);
        usuario.setCelular(celular);
        usuario.setContrasenia(contrasenia);
        usuario.setRol(rol);

        try {
            usuarioService.guardar(usuario);
            System.out.println("Usuario creado correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void editarUsuario() {
        listarUsuarios();
        System.out.print("Ingrese el ID del usuario a editar: ");
        Long id = leerLong();

        try {
            Usuario usuario = usuarioService.buscarPorId(id);
            System.out.print("Nuevo nombre (" + usuario.getNombre() + "): ");
            String nombre = sc.nextLine();
            System.out.print("Nuevo apellido (" + usuario.getApellido() + "): ");
            String apellido = sc.nextLine();
            System.out.print("Nuevo mail (" + usuario.getMail() + "): ");
            String mail = sc.nextLine();
            System.out.print("Nuevo celular (" + usuario.getCelular() + "): ");
            String celular = sc.nextLine();

            if (!nombre.isBlank()) usuario.setNombre(nombre);
            if (!apellido.isBlank()) usuario.setApellido(apellido);
            if (!mail.isBlank()) usuario.setMail(mail);
            if (!celular.isBlank()) usuario.setCelular(celular);

            usuarioService.actualizar(usuario);
            System.out.println("Usuario actualizado correctamente.");

        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void eliminarUsuario() {
        listarUsuarios();
        System.out.print("Ingrese el ID del usuario a eliminar: ");
        Long id = leerLong();

        System.out.print("Confirma la eliminacion? (S/N): ");
        String confirmacion = sc.nextLine();

        if (confirmacion.equalsIgnoreCase("S")) {
            try {
                usuarioService.eliminar(id);
                System.out.println("Usuario eliminado correctamente.");
            } catch (EntityNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Operacion cancelada.");
        }
    }
    
    static void menuCategorias() {

        int opcion;

        do {
            System.out.println("\n=== MENU CATEGORIAS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> listarCategorias();
                case 2 -> crearCategoria();
                case 3 -> editarCategoria();
                case 4 -> eliminarCategoria();
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opcion invalida.");
            }

        } while (opcion != 0);
    }    
    
    static void listarCategorias() {
        List<Categoria> categorias = categoriaService.listar();
        if (categorias.isEmpty()) {
            System.out.println("No hay categorias cargadas.");
        } else {
            categorias.forEach(System.out::println);
        }
    }

    static void crearCategoria() {
        
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
     
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();

        Categoria categoria = new Categoria();
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);

        try {
            
            categoriaService.guardar(categoria);
            System.out.println("Categoría creada correctamente.");
            
        } catch (IllegalArgumentException e) {
            
            System.out.println("Error: " + e.getMessage());
        }
    }    
    
    static void editarCategoria() {
        listarCategorias();
        System.out.print("Ingrese el ID de la categoria a editar: ");
        Long id = leerLong();

        try {
            Categoria categoria = categoriaService.buscarPorId(id);
            System.out.print("Nuevo nombre (" + categoria.getNombre() + "): ");
            String nombre = sc.nextLine();
            System.out.print("Nueva descripción (" + categoria.getDescripcion() + "): ");
            String descripcion = sc.nextLine();

            if (!nombre.isBlank()) categoria.setNombre(nombre);
            if (!descripcion.isBlank()) categoria.setDescripcion(descripcion);

            categoriaService.actualizar(categoria);
            System.out.println("Categoría actualizada correctamente.");

        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    static void eliminarCategoria() {
        listarCategorias();
        System.out.print("Ingrese el ID de la categoria a eliminar: ");
        Long id = leerLong();

        System.out.print("Confirma la eliminacion? (S/N): ");
        String confirmacion = sc.nextLine();

        if (confirmacion.equalsIgnoreCase("S")) {
            try {
                
                categoriaService.eliminar(id);
                System.out.println("Categoria eliminada correctamente.");
                
            } catch (EntityNotFoundException e) {
                
                System.out.println("Error: " + e.getMessage());
                
            }
        } else {
            System.out.println("Operacion cancelada.");
        }
    }


    static void menuProductos() {

        int opcion;

        do {
            System.out.println("\n=== MENU PRODUCTOS ===");
            System.out.println("1. Listar");
            System.out.println("2. Crear");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1 -> listarProductos();
                case 2 -> crearProducto();
                case 3 -> editarProducto();
                case 4 -> eliminarProducto();
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opcion invalida.");
            }

        } while (opcion != 0);
    }
    
    static void listarProductos() {
        List<Producto> productos = productoService.listar();
        if (productos.isEmpty()) {
            System.out.println("No hay productos cargados.");
        } else {
            productos.forEach(System.out::println);
        }
    }

    static void crearProducto() {

        try{
        
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        
        System.out.print("Precio: ");
        Double precio = Double.parseDouble(sc.nextLine());
        
        System.out.print("Descripción: ");
        String descripcion = sc.nextLine();
        
        System.out.print("Stock: ");
        Integer stock = leerEntero();
    
        System.out.print("Imagen: ");
        String imagen = sc.nextLine();
        
        
        System.out.print("\nCATEGORÍAS DISPONIBLES: ");
        
        categoriaService.listar()
                .forEach(System.out::println);

        System.out.print("ID Categoria: ");

        Long categoriaId = leerLong();

        Categoria categoria =
                categoriaService.buscarPorId(categoriaId);        

        Producto producto = new Producto();
        
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setDescripcion(descripcion);
        producto.setStock(stock);
        producto.setImagen(imagen);
        producto.setDisponible(true);
        producto.setCategoria(categoria);

        productoService.guardar(producto);

        System.out.println(
                "Producto creado correctamente.");

        } catch (EntityNotFoundException e) {

            System.out.println(
                    "Categoria inexistente.");

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Error: " + e.getMessage());

        } 
    }
    
    static void editarProducto() {
        listarProductos();
        System.out.print("Ingrese el ID del producto a editar: ");
        Long id = leerLong();

        try {
            Producto producto = productoService.buscarPorId(id);
            
            System.out.print("Nuevo nombre (" + producto.getNombre() + "): ");
            String nombre = sc.nextLine();
            
            System.out.print("Nuevo precio (" + producto.getPrecio() + "): ");
            String precioTxt = sc.nextLine();
            
            System.out.print("Nueva descripción (" + producto.getDescripcion() + "): ");
            String descripcion = sc.nextLine();
            
            System.out.print("Nuevo Stock (" + producto.getStock() + "): ");
            String stockTxt = sc.nextLine();
            
            System.out.print("Nueva imagen (" + producto.getImagen() + "): ");
            String imagen = sc.nextLine();

            if (!nombre.isBlank()) producto.setNombre(nombre);
            if (!precioTxt.isBlank()) producto.setPrecio(Double.parseDouble(precioTxt));
            if (!descripcion.isBlank()) producto.setDescripcion(descripcion);
            if (!stockTxt.isBlank()) producto.setStock(Integer.parseInt(stockTxt));
            if (!imagen.isBlank()) producto.setImagen(imagen);
            
            System.out.println("Categoria actual: " + producto.getCategoria().getNombre());            

            System.out.print("\nCATEGORÍAS DISPONIBLES: ");

            categoriaService.listar()
                    .forEach(System.out::println);

            System.out.print("ID de nueva categoria (Para mantener el mismo presionar <enter>): ");
            
            String categoriaTexto = sc.nextLine();
            
            if (!categoriaTexto.isBlank()) {
                Long categoriaId =Long.parseLong(categoriaTexto);
                Categoria categoria =categoriaService.buscarPorId(categoriaId);
                producto.setCategoria(categoria);
            }            
            
            
            productoService.actualizar(producto);
            System.out.println("Producto actualizado correctamente.");

            } catch (EntityNotFoundException e) {
                System.out.println("Error: " + e.getMessage());

            } catch (NumberFormatException e) {

            System.out.println(
                    "Error: valor numerico invalido.");
        }
    }

    static void eliminarProducto() {
        
        listarProductos();
        System.out.print("Ingrese el ID del producto a eliminar: ");
        Long id = leerLong();

        System.out.print("Confirma la eliminacion? (S/N): ");
        String confirmacion = sc.nextLine();

        if (confirmacion.equalsIgnoreCase("S")) {
            try {
                
                productoService.eliminar(id);
                System.out.println("Producto eliminado correctamente.");
                
            } catch (EntityNotFoundException e) {
                
                System.out.println("Error: " + e.getMessage());
                
            }
        } else {
            System.out.println("Operacion cancelada.");
        }
    }    
    
    
    static int leerEntero() {
        try {
            int valor = Integer.parseInt(sc.nextLine());
            return valor;
        } catch (NumberFormatException e) {
            System.out.println("Ingrese un numero valido.");
            return -1;
        }
    }

    static Long leerLong() {
        try {
            return Long.parseLong(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ingrese un numero valido.");
            return -1L;
        }
    }
    
    // Andres----------------------------------------------------------------------------------------------
    static void menuPedidos() {

    int opcion;

    do {

        System.out.println("\n=== MENU PEDIDOS ===");
        System.out.println("1. Listar");
        System.out.println("2. Crear");
        System.out.println("3. Editar");
        System.out.println("4. Eliminar");
        System.out.println("0. Volver");
        System.out.print("Seleccione: ");

        opcion = leerEntero();

        switch (opcion) {

            case 1 -> listarPedidos();

            case 2 -> crearPedido();

            case 3 -> editarPedido();

            case 4 -> eliminarPedido();

            case 0 -> System.out.println("Volviendo...");

            default -> System.out.println("Opcion invalida.");
        }

    } while (opcion != 0);
}
    
    static void listarPedidos() {

    List<Pedido> pedidos = pedidoService.listar();

    if (pedidos.isEmpty()) {

        System.out.println("No hay pedidos cargados.");

    } else {

        pedidos.forEach(System.out::println);

    }
}
    
    static void crearPedido() {

    System.out.println("Funcion en desarrollo.");

}
    
    static void editarPedido() {

    listarPedidos();

    System.out.print("Ingrese ID del pedido: ");

    Long id = leerLong();

    try {

        Pedido pedido =
                pedidoService.buscarPorId(id);

        System.out.println(
                "1. PENDIENTE");
        System.out.println(
                "2. CONFIRMADO");
        System.out.println(
                "3. TERMINADO");
        System.out.println(
                "4. CANCELADO");

        System.out.print(
                "Nuevo estado: ");

        int opcionEstado =
                leerEntero();

        // TODO setEstado()

        System.out.println(
                "1. TARJETA");
        System.out.println(
                "2. TRANSFERENCIA");
        System.out.println(
                "3. EFECTIVO");

        System.out.print(
                "Forma de pago: ");

        int opcionPago =
                leerEntero();

        // TODO setFormaPago()

        pedidoService.actualizar(
                pedido);

        System.out.println(
                "Pedido actualizado.");

    } catch (
            EntityNotFoundException e) {

        System.out.println(
                "Error: "
                + e.getMessage());
    }
}
    
    static void eliminarPedido() {

    listarPedidos();

    System.out.print(
            "Ingrese el ID del pedido a eliminar: ");

    Long id = leerLong();

    System.out.print(
            "Confirma la eliminacion? (S/N): ");

    String confirmacion =
            sc.nextLine();

    if (confirmacion.equalsIgnoreCase("S")) {

        try {

            pedidoService.eliminar(id);

            System.out.println(
                    "Pedido eliminado correctamente.");

        } catch (
                EntityNotFoundException e) {

            System.out.println(
                    "Error: "
                    + e.getMessage());
        }

    } else {

        System.out.println(
                "Operacion cancelada.");
    }
}
    
}