/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Irdevelo
 */
public interface IPuntaje extends Remote {

    /**
     *
     * @param puntajeObtenido
     * @param nombreJugador
     * @throws RemoteException
     */
    public void actualizarPuntajeJugador(int puntajeObtenido, String nombreJugador) throws RemoteException;

    /**
     *
     * @return
     * @throws RemoteException
     */
    public List<negocio.Puntaje> obtenerMejoresPuntajes()throws RemoteException;
}
