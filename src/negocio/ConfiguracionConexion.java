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
 * Plantilla con atributos y métodos necesarios para la conexión a los
 * servidores de RMI y Node
 *
 * @author Irvin Dereb Vera López.
 * @author Israel Reyes Ozuna.
 */
public class ConfiguracionConexion {

    private String ipRMI;
    private String ipNode;
    private String puertoNode;
    private static Socket socket;

    /**
     * Permite verificar si existe conexion con el servidor Node.
     *
     * @param ipNode Dirección IP del servidor Node con la que se intentará
     * comprobar conexión.
     * @param puerto Puerto por el que se intentará comprobar conexión.
     * @return Valor verdadero si la conexión se comprueba o un valor falso en
     * caso de ser lo contrario.
     * @throws IOException puede arrojar esta excepción si la dirección ingresada
     * no es correcta o contiene caracteres no validos
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
     * Permite actualizar en el archivo Properties las direcciones a las cuales
     * se conectará el juego.
     *
     * @param rmi Dirección IP del servidor RMI con el que se establecerá la
     * conexión.
     * @param node Dirección IP del servidor Node con el que se establecerá la
     * conexión.
     * @param puerto Puerto por el que se establecerá la conexión con servidor
     * Node.
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
     * Permite obtener la direción IP del archivo Properties para la conexión
     * con el servidor RMI.
     *
     * @return La dirección IP para la conexión con el servidor RMI.
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
     * Permite obtener la direción IP del archivo Properties para la conexión
     * con el servidor RMI.
     *
     * @return La dirección IP para la conexión con el servidor Node.
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
     * Permite obtener el puerto para la conexión con el servidor Node.
     *
     * @return El puerto para la conexión con el servidor Node.
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
