package negocio;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface que contiene los métodos remotos para la conexión con el servidor
 * RMI.
 *
 * @author Irvin Dereb Vera López.
 * @author Israel Reyes Ozuna.
 */
public interface IConexion extends Remote {

    /**
     *
     * @return Permite verificar si existe conexion con el servidor RMI.
     * @throws RemoteException
     */
    public boolean obtenerIPRMI() throws RemoteException;

}
