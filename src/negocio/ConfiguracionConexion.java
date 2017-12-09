/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import controlador.VentanaPeticionIPController;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Irdevelo
 */
public class ConfiguracionConexion {

    private String ipRMI;
    private String ipNode;
    private String puertoNode;
    private static Socket socket;

    /**
     *
     * @param ipNode
     * @param puerto
     * @return
     * @throws IOException
     */
    public static boolean verificarConexionNode(String ipNode, String puerto) throws IOException {
        boolean estaConectado = false;
        socket = new Socket(ipNode, Integer.parseInt(puerto));
        if (socket.isConnected()) {
            estaConectado = true;
        }
        return estaConectado;
    }

    /**
     *
     * @param rmi
     * @param node
     * @param puerto
     */
    public void actualizarIP(String rmi, String node, String puerto) {

        Properties properties = new Properties();

        try (InputStream cargarIP = ClassLoader.getSystemResourceAsStream("recursos/direccionesIP.properties")) {
            properties.load(cargarIP);
        } catch (IOException ex) {
            Logger.getLogger(VentanaPeticionIPController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String ruta = System.getProperty("user.dir");
        ruta += "/src/recursos/direccionesIP.properties";

        File archivoIP = new File(ruta);

        try (FileOutputStream os = new FileOutputStream(archivoIP);) {
            properties.setProperty("rmi", rmi);
            properties.setProperty("node", node);
            properties.setProperty("puertoNode", puerto);
            properties.store(os, "Actualización de IP");
        } catch (IOException ex) {
            Logger.getLogger(VentanaPeticionIPController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return
     */
    public String obtenerIPRMI() {
        Properties properties = new Properties();
        try (InputStream cargarIP = ClassLoader.getSystemResourceAsStream("recursos/direccionesIP.properties")) {
            properties.load(cargarIP);
            ipRMI = properties.getProperty("rmi");
        } catch (IOException ex) {
            Logger.getLogger(VentanaPeticionIPController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ipRMI;
    }

    /**
     *
     * @return
     */
    public String obtenerIPNode() {
        Properties properties = new Properties();
        try (InputStream cargarIP = ClassLoader.getSystemResourceAsStream("recursos/direccionesIP.properties")) {
            properties.load(cargarIP);
            ipNode = properties.getProperty("node");
        } catch (IOException ex) {
            Logger.getLogger(VentanaPeticionIPController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ipNode;
    }

    /**
     *
     * @return
     */
    public String obtenerPuertoNode() {
        Properties properties = new Properties();
        try (InputStream cargarPuertoNode = ClassLoader.getSystemResourceAsStream("recursos/direccionesIP.properties")) {
            properties.load(cargarPuertoNode);
            puertoNode = properties.getProperty("puertoNode");
        } catch (IOException ex) {
            Logger.getLogger(VentanaPeticionIPController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return puertoNode;
    }
}
