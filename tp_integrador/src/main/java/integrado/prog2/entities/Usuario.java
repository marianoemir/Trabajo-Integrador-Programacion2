package integrado.prog2.entities;

import integrado.prog2.enums.Rol;
import java.time.LocalDateTime;

public class Usuario extends Base {

    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String contrasenia;
    private Rol rol;

    public Usuario() {
    }

    // Constructor para crear usuarios nuevos.
    public Usuario(Long id, boolean eliminado,
            LocalDateTime createdAt,
            String nombre, String apellido,
            String mail, String celular,
            String contrasenia, Rol rol) {

        super(id, eliminado, createdAt);

        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.celular = celular;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    //Constructor para kcuando el DAO lea un usuario desde la base de datos.
    public Usuario(String nombre,
            String apellido,
            String mail,
            String celular,
            String contrasenia,
            Rol rol) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.celular = celular;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{"
                + "id=" + id
                + ", nombre='" + nombre + '\''
                + ", apellido='" + apellido + '\''
                + ", mail='" + mail + '\''
                + ", rol=" + rol
                + '}';
    }

}
