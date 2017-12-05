/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import negocio.ConfiguracionConexion;
import negocio.IJugador;

/**
 * FXML Controller class
 *
 * @author Ozuna
 */
public class VentanaMenuController implements Initializable {

    private ResourceBundle idioma;

    private String nombreUsuario;

    @FXML
    private Button botonIniciarPartida;
    @FXML
    private Button botonCerrarSesion;
    @FXML
    private TableColumn columnaJugador;
    @FXML
    private TableColumn columnaPuntaje;
    @FXML
    private Label etiquetaNombreUsuario;

    private String ipNode;
    @FXML
    private ImageView imagenBarcoMenu;
    @FXML
    private ImageView imagenMisil;
    @FXML
    private ImageView imagenRadar;
    @FXML
    private TableView<?> tablaRank;
    @FXML
    private TableColumn<?, ?> columnaPosicion;

    ConfiguracionConexion conexionRMI = new ConfiguracionConexion();
    String ipRMI = conexionRMI.obtenerIPRMI();

    @Override
    public void initialize(URL url, ResourceBundle idioma) {
        this.idioma = idioma;
        configurarIdioma();
    }

    public void obtenerNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        etiquetaNombreUsuario.setText(nombreUsuario);
    }

    public void configurarIdioma() {
        botonIniciarPartida.setText(idioma.getString("botIniciarPartida"));
        botonCerrarSesion.setText(idioma.getString("botCerrarSesion"));
        columnaJugador.setText(idioma.getString("columJugador"));
        columnaPuntaje.setText(idioma.getString("columPuntaje"));
    }

    @FXML
    public void buscarPartida(ActionEvent event) throws IOException {
        FXMLLoader loger = new FXMLLoader(getClass().getResource("/vista/VentanaBuscarPartida.fxml"), idioma);
        Parent root = (Parent) loger.load();

        VentanaBuscarPartidaController controladorBuscarPartida = loger.getController();
        controladorBuscarPartida.obtenerNombreUsuario(nombreUsuario);
        controladorBuscarPartida.comenzarBusqueda();

        Stage buscarPartida = new Stage();
        buscarPartida.setScene(new Scene(root));
        buscarPartida.show();
        Stage ventanaAnterior = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ventanaAnterior.close();
    }

    public void obtenerIpNode(String ipNode) {
        this.ipNode = ipNode;
    }

    @FXML
    private void cerrarSesion(ActionEvent event) throws IOException {
        IJugador stub;
        try {
            Registry registry = LocateRegistry.getRegistry(ipRMI);
            stub = (IJugador) registry.lookup("ServidorBatallaNaval");
            stub.cerrarSesion(nombreUsuario);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(VentanaMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        FXMLLoader loger = new FXMLLoader(getClass().getResource("/vista/VentanaIniciarSesion.fxml"), idioma);
        Parent root = (Parent) loger.load();
        Stage menu = new Stage();
        menu.setScene(new Scene(root));
        menu.show();
        Stage ventanaRegistrar = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ventanaRegistrar.close();

    }
}
