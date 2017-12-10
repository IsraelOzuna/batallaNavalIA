package controlador;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import negocio.ConfiguracionConexion;
import negocio.IJugador;
import negocio.IPuntaje;
import negocio.Puntaje;

/**
 * Plantilla que contiene atributos y métodos necesarios para el control de la
 * vista VentanaMenu
 *
 * @author Irvin Dereb Vera López.
 * @author Israel Reyes Ozuna.
 */
public class VentanaMenuController implements Initializable {

    private ResourceBundle idioma;

    private String nombreUsuario;

    @FXML
    private Button botonIniciarPartida;
    @FXML
    private Button botonCerrarSesion;
    @FXML
    private Label etiquetaNombreUsuario;
    @FXML
    private Label etiquetaJugador;
    @FXML
    private Label etiquetaPuntaje;
    @FXML
    private Label etiquetaJugador1;
    @FXML
    private Label etiquetaJugador2;
    @FXML
    private Label etiquetaJugador3;
    @FXML
    private Label etiquetaPuntajeJugador1;
    @FXML
    private Label etiquetaPuntajeJugador2;
    @FXML
    private Label etiquetaPuntajeJugador3;

    private String ipNode;

    ConfiguracionConexion conexionRMI = new ConfiguracionConexion();

    String ipRMI = conexionRMI.obtenerIPRMI();

    @Override
    public void initialize(URL url, ResourceBundle idioma) {
        this.idioma = idioma;
        configurarIdioma();
        llenarTabla();
    }

    /**
     * Permite obtener el nombre del jugador desde la ventana anterior.
     *
     * @param nombreUsuario Clave del jugador para ingresar al sistema.
     */
    public void obtenerNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        etiquetaNombreUsuario.setText(nombreUsuario);
    }

    /**
     * Permite la configuración del idioma de la pantalla.
     */
    public void configurarIdioma() {
        botonIniciarPartida.setText(idioma.getString("botIniciarPartida"));
        botonCerrarSesion.setText(idioma.getString("botCerrarSesion"));
        etiquetaJugador.setText(idioma.getString("columJugador"));
        etiquetaPuntaje.setText(idioma.getString("columPuntaje"));
    }

    /**
     * Permite obtener la dirección IP Node desde el archivo properties.
     *
     * @param ipNode Tomará el valor de la IP Node registrada en el archivo
     * properties.
     */
    public void obtenerIpNode(String ipNode) {
        this.ipNode = ipNode;
    }

    /**
     * Permite llenar la tabla de ranking con los mejores puntajes obtenidos por
     * los jugadores
     */
    public void llenarTabla() {
        IPuntaje stubPuntaje;
        List<Puntaje> mejoresPuntajes = null;
        try {
            conexionRMI = new ConfiguracionConexion();
            ipRMI = conexionRMI.obtenerIPRMI();
            Registry registry;
            registry = LocateRegistry.getRegistry(ipRMI);
            stubPuntaje = (IPuntaje) registry.lookup("ServidorBatallaNaval");
            mejoresPuntajes = stubPuntaje.obtenerMejoresPuntajes();
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(VentanaMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

        etiquetaJugador1.setText(mejoresPuntajes.get(0).getNombreJugador());
        etiquetaPuntajeJugador1.setText(String.valueOf(mejoresPuntajes.get(0).getPuntosTotales()));
        etiquetaJugador2.setText(mejoresPuntajes.get(1).getNombreJugador());
        etiquetaPuntajeJugador2.setText(String.valueOf(mejoresPuntajes.get(1).getPuntosTotales()));
        etiquetaJugador3.setText(mejoresPuntajes.get(2).getNombreJugador());
        etiquetaPuntajeJugador3.setText(String.valueOf(mejoresPuntajes.get(2).getPuntosTotales()));
    }

    /**
     * Permite desplegar la ventana para buscar partida
     *
     * @param event Un clic en el boton Iniciar Partida
     * @throws IOException
     */
    @FXML
    public void buscarPartida(ActionEvent event) throws IOException {
        FXMLLoader loger = new FXMLLoader(getClass().getResource("/vista/VentanaBuscarPartida.fxml"), idioma);
        Parent root = (Parent) loger.load();

        VentanaBuscarPartidaController controladorBuscarPartida = loger.getController();
        controladorBuscarPartida.obtenerNombreUsuario(nombreUsuario);
        controladorBuscarPartida.comenzarBusqueda();

        Stage buscarPartida = new Stage();
        buscarPartida.setScene(new Scene(root));
        buscarPartida.initStyle(StageStyle.UNDECORATED);
        buscarPartida.show();
        Stage ventanaAnterior = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ventanaAnterior.close();
        controladorBuscarPartida.setStageBuscar(buscarPartida);
    }

    /**
     * Permite Permite que un jugador cierre sesión.
     *
     * @param event Un clic en el botón Cerrar sesión
     * @throws IOException
     */
    @FXML
    public void cerrarSesion(ActionEvent event) throws IOException {
        IJugador stubJugador;
        try {
            Registry registry = LocateRegistry.getRegistry(ipRMI);
            stubJugador = (IJugador) registry.lookup("ServidorBatallaNaval");
            stubJugador.cerrarSesion(nombreUsuario);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(VentanaMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

        FXMLLoader loger = new FXMLLoader(getClass().getResource("/vista/VentanaIniciarSesion.fxml"), idioma);
        Parent root = (Parent) loger.load();
        Stage iniciarSesion = new Stage();
        iniciarSesion.setScene(new Scene(root));
        iniciarSesion.initStyle(StageStyle.UNDECORATED);
        iniciarSesion.show();
        Stage ventanaRegistrar = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ventanaRegistrar.close();
    }
}
