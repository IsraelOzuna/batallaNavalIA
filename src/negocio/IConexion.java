/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Irdevelo
 */
public interface IConexion extends Remote{
    
    public boolean obtenerIPRMI()throws RemoteException;
    
}
