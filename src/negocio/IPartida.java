package negocio;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface que contiene los métodos remotos pertenecientes las partidas.
 *
 * @author Irvin Dereb Vera López.
 * @author Israel Reyes Ozuna.
 */
public interface IPartida extends Remote {

    /**
     * Permite actualizar las partidas de un jugador cuando gana una
     * confrontación.
     *
     * @param nombreJugador Clave del jugador para ingresar al sistema.
     * @throws RemoteException
     */
    public void actualizarPartidasGanadas(String nombreJugador) throws RemoteException;

    /**
     * Permite actualizar las partidas de un jugador cuando pierde una
     * confrontación.
     *
     * @param nombreJugador Clave del jugador para ingresar al sistema.
     * @throws RemoteException
     */
    public void actualizarPartidasPerdidas(String nombreJugador) throws RemoteException;

}