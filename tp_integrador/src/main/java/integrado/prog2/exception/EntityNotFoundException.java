package integrado.prog2.exception;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String mensaje) {
        super(mensaje);
    }
}