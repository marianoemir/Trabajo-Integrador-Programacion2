package integrado.prog2;

import integrado.prog2.entities.Usuario;
import integrado.prog2.enums.Rol;
import integrado.prog2.exception.EntityNotFoundException;
import integrado.prog2.service.UsuarioService;
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
                case 1 -> System.out.println("(pendiente compañero A)");
                case 2 -> System.out.println("(pendiente compañero A)");
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