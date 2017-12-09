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
public class Partida implements Serializable{
    
    private String nombreJugador;
    
    private int partidasGanadas;
    
    private int partidasPerdidas;

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
    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    /**
     *
     * @param partidasGanadas
     */
    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    /**
     *
     * @return
     */
    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }

    /**
     *
     * @param partidasPerdidas
     */
    public void setPartidasPerdidas(int partidasPerdidas) {
        this.partidasPerdidas = partidasPerdidas;
    } 
}
