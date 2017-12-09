/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.Serializable;

/**
 *
 * @author Irdevelo
 */
public class Jugador implements Serializable {

    private String nombreJugador;

    private String contrasena;

    private String correo;

    private String nombre;

    private String apellidos;

    /**
     *
     * @return
     */
    public String getNombreJugador() {
        return nombreJugador;
    }

    /**
     *
     * @param nombreJugador
     */
    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    /**
     *
     * @return
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     *
     * @param contrasena
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     *
     * @return
     */
    public String getCorreo() {
        return correo;
    }

    /**
     *
     * @param correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     *
     * @param apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     *
     * @param nombreUsuario
     * @return
     */
    public static boolean verificarNombreUsuarioCorrecto(String nombreUsuario) {
        boolean nombreUsuarioCorrecto = true;
        String caracteres[] = nombreUsuario.split(" ");
        if (caracteres.length > 1) {
            nombreUsuarioCorrecto = false;
        }
        return nombreUsuarioCorrecto;
    }
}
