package controlador;

//import io.socket.client.IO;
//import io.socket.client.Socket;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import negocio.ConfiguracionConexion;

/**
 * FXML Controller class
 *
 * @author Irdevelo
 */
public class VentanaBuscarPartidaController implements Initializable {

    private ResourceBundle idioma;
    private String nombreUsuario;
    //private Socket socket;

    Stage ventanaActual;

    @FXML
    private Label etiquetaBuscandoPartida;

    @Override
    public void initialize(URL url, ResourceBundle idioma) {
        this.idioma = idioma;
        configurarIdioma();
    }

    /**
     * Permite obtener el nombre del jugador desde la ventana anterior.
     *
     * @param nombreUsuario Clave del jugador para ingresar al sistema.
     */
    public void obtenerNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Permite la configuración del idioma de la pantalla.
     */
    public void configurarIdioma() {
        etiquetaBuscandoPartida.setText(idioma.getString("etBuscandoPartida"));
    }

    /**
     * Permite iniciar la búsqueda de una partida.
     */
    /*public void comenzarBusqueda() {
        try {
            ConfiguracionConexion conexionNode = new ConfiguracionConexion();
            String ipNode = conexionNode.obtenerIPNode();
            String puertoNode = conexionNode.obtenerPuertoNode();
            crearConexion(ipNode, puertoNode);
            //socket.emit("buscarPartida", nombreUsuario);
        } catch (URISyntaxException ex) {
            Logger.getLogger(VentanaBuscarPartidaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    /**
     * Permite crear conexión con el servidor Node.
     *
     * @param node Dirección IP del servidor Node con el que se establecerá la
     * conexión.
     * @param puerto Puerto por el que se establecerá la conexión con servidor
     * Node.
     * @throws URISyntaxException puede arrojar esta excepción si la dirección
     * a Node no es la correcta
     */
    /*private void crearConexion(String node, String puerto) throws URISyntaxException {

        socket = IO.socket("http://" + node + ":" + puerto);

        socket.on("SeHaEncontradoUnaFlotaEnemiga", (Object... os) -> {
            Platform.runLater(() -> {
                try {
                    desplegarTablero((String) os[0], (Boolean) os[1]);
                    socket.off("SeHaEncontradoUnaFlotaEnemiga");
                } catch (IOException ex) {
                    Logger.getLogger(VentanaBuscarPartidaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        });
        socket.connect();
    }*/

    /**
     * Permite desplegar la VentanaTablero.
     *
     * @param nombreRival Nombre del contrincante.
     * @param primerTirador Valor verdadero si es el primer tirador o valor
     * falso en caso de lo contrario.
     * @throws IOException puede arrojar esta excepción si no se completa 
     * correctamente la carga de la siguiente ventana
     */
    public void desplegarTablero(String nombreRival, Boolean primerTirador) throws IOException {
        FXMLLoader loger = new FXMLLoader(getClass().getResource("/vista/VentanaTablero.fxml"), idioma);
        Parent root = (Parent) loger.load();
        VentanaTableroController controladorTablero = loger.getController();
        //controladorTablero.adquirirDatos(socket, nombreUsuario, nombreRival, primerTirador);
        Stage tablero = new Stage();
        tablero.setScene(new Scene(root));
        tablero.initStyle(StageStyle.UNDECORATED);
        tablero.show();
        controladorTablero.setStageTablero(tablero);
        ventanaActual.close();
    }

    /**
     * Permite pasar como párametro la ventana actual para posteriormente
     * cerrarla.
     *
     * @param ventanaBuscar un Stage para que pueda ser cerrado cuando se
     * encuentre una partida
     */
    public void setStageBuscar(Stage ventanaBuscar) {
        ventanaActual = ventanaBuscar;
    }
}
