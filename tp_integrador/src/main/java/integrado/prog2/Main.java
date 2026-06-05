package integrado.prog2;

import integrado.prog2.entities.Usuario;
import integrado.prog2.enums.Rol;
import integrado.prog2.exception.EntityNotFoundException;
import integrado.prog2.service.UsuarioService;
import java.util.Scanner;

/**
 *
 * @author Mariano_Chirino
 */
public class Main {

    /*
    El sistema debe iniciar mostrando un menú principal similar al siguiente (el texto puede variar, pero la idea es la misma):
    === SISTEMA DE PEDIDOS (FOOD STORE) === 
    1. Categorías 2. Productos 3. Usuarios 4. Pedidos 0. Salir Seleccione:
    Cada opción abre un submenú CRUD. Ejemplo para Categorías:
    1. Listar 
    2. Crear 
    3. Editar 
    4. Eliminar Seleccione:
    
    Pautas importantes del menú:
    
    ● El menú debe validar entradas (opciones fuera de rango, ids inexistentes, números mal ingresados).
    ● Las operaciones deben mostrar mensajes claros (éxito / error) y retornar al menú. 
    ● Para facilitar pruebas, es válido listar elementos antes de pedir un ID (por ejemplo: listar categorías y luego pedir id).
     */
    public static void main(String[] args) {

//        Scanner sc = new Scanner(System.in);
//        UsuarioService usuarioService = new UsuarioService();
//
//        int opcion;
//
//        do {
//
//            System.out.println("\n=== MENU USUARIOS ===");
//            System.out.println("1. Listar");
//            System.out.println("2. Crear");
//            System.out.println("3. Editar");
//            System.out.println("4. Eliminar");
//            System.out.println("0. Salir");
//
//            opcion = sc.nextInt();
//
//            switch (opcion) {
//
//                case 1:
//                    // listar
//                    break;
//
//                case 2:
//                    // crear
//                    break;
//
//                case 3:
//                    // editar
//                    break;
//
//                case 4:
//                    // eliminar
//                    break;
//
//            }
//
//        } while (opcion != 0);
        UsuarioService usuarioService = new UsuarioService();

// 1. Crear usuario de prueba
        Usuario u = new Usuario();
        u.setNombre("jose");
        u.setApellido("Perez");
        u.setMail("juan@mail.com");
        u.setCelular("261111111");
        u.setContrasenia("1234");
        u.setRol(Rol.USUARIO);
        usuarioService.guardar(u);
        System.out.println("Usuario guardado");

// 2. Listar
        usuarioService.listar().forEach(System.out::println);

    }
}
