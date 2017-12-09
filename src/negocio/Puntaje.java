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
public class Puntaje implements Serializable{
    
    private int puntosTotales;
    private String nombreJugador;

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
    public int getPuntosTotales() {
        return puntosTotales;
    }

    /**
     *
     * @param puntosTotales
     */
    public void setPuntosTotales(int puntosTotales) {
        this.puntosTotales = puntosTotales;
    }    
}
