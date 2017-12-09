package controlador;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.NoSuchAlgorithmException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import negocio.ConfiguracionConexion;
import negocio.IJugador;
import negocio.Utileria;

public class VentanaIniciarSesionController implements Initializable {

    private ResourceBundle idioma;

    @FXML
    private Label etiquetaUsuario;
    @FXML
    private Label etiquetaContraseña;
    @FXML
    private Label etiquetaBatallaNaval;
    @FXML
    private Button botonIngles;
    @FXML
    private Button botonEspañol;
    @FXML
    private Button botonIniciarSesion;
    @FXML
    private Button botonRegistrarse;
    @FXML
    private TextField campoUsuario;
    @FXML
    private PasswordField campoContrasena;

    @Override
    public void initialize(URL url, ResourceBundle idioma) {
        this.idioma = idioma;
        configurarIdioma();
    }

    public void configurarIdioma() {
        etiquetaBatallaNaval.setText(idioma.getString("etBatallaNaval"));
        etiquetaUsuario.setText(idioma.getString("etUsuario"));
        etiquetaContraseña.setText(idioma.getString("etContraseña"));
        botonIngles.setText(idioma.getString("botIngles"));
        botonEspañol.setText(idioma.getString("botEspañol"));
        botonIniciarSesion.setText(idioma.getString("botIniciarSesion"));
        botonRegistrarse.setText(idioma.getString("botRegistrarseAqui"));
    }

    @FXML
    public void cambiarIdiomaIngles(ActionEvent event) {
        idioma = ResourceBundle.getBundle("recursos.idioma_en_US");
        configurarIdioma();
    }

    @FXML
    public void cambiarIdiomaEspañol(ActionEvent event) {
        idioma = ResourceBundle.getBundle("recursos.idioma_es_MX");
        configurarIdioma();
    }

    @FXML
    public void ingresar(ActionEvent event) throws IOException {
        boolean usuarioEncontrado;
        IJugador stubJugador;
        if (campoUsuario.getText().isEmpty() || campoContrasena.getText().isEmpty()) {
            mostrarMensajeAdvertencia("tituloAdvertencia", "encabezadoCamposVacios", "contenidoCamposVacios");
        } else {
            try {
                ConfiguracionConexion conexionRMI = new ConfiguracionConexion();
                String ipRMI = conexionRMI.obtenerIPRMI();

                Registry registry = LocateRegistry.getRegistry(ipRMI);
                stubJugador = (IJugador) registry.lookup("ServidorBatallaNaval");
                Utileria contraseña = new Utileria();

                usuarioEncontrado = stubJugador.iniciarSesion(campoUsuario.getText(), contraseña.cifrarContrasena(campoContrasena.getText()));

                if (usuarioEncontrado) {
                    if (stubJugador.verificarJugadorConectado(campoUsuario.getText())) {
                        mostrarMensajeAdvertencia("tituloAdvertencia", "encabezadoUsuarioConectado", "contenidoUsuarioConectado");
                    } else {
                        FXMLLoader loger = new FXMLLoader(getClass().getResource("/vista/VentanaMenu.fxml"), idioma);
                        Parent root = (Parent) loger.load();
                        VentanaMenuController controladorMenu = loger.getController();
                        controladorMenu.obtenerNombreUsuario(campoUsuario.getText());
                        Stage menu = new Stage();
                        menu.setScene(new Scene(root));
                        menu.initStyle(StageStyle.UNDECORATED);                                            
                        menu.show();
                        Stage ventanaAnterior = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        ventanaAnterior.close();
                    }

                } else {
                    mostrarMensajeAdvertencia("tituloAdvertencia", "encabezadoDatosIncorrectos", "contenidoDatosIncorrectos");
                }

            } catch (RemoteException | NotBoundException | NoSuchAlgorithmException ex) {
                Logger.getLogger(VentanaIniciarSesionController.class.getName()).log(Level.SEVERE, null, ex);
                mostrarMensajeAdvertencia("tituloAdvertencia", "encabezadoNoConexion", "contenidoNoConexion");
            }
        }
    }

    @FXML
    public void desplegarVentanaRegistrarUsuario(ActionEvent event) throws IOException {
        FXMLLoader loger = new FXMLLoader(getClass().getResource("/vista/VentanaRegistrarUsuario.fxml"), idioma);
        Parent root = (Parent) loger.load();
        Stage registro = new Stage();
        registro.setScene(new Scene(root));
        registro.show();
        Stage ventanaAnterior = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ventanaAnterior.close();
    }

    public void mostrarMensajeAdvertencia(String titulo, String encabezado, String contenido) {
        Alert advertencia = new Alert(Alert.AlertType.WARNING);
        advertencia.setTitle(idioma.getString(titulo));
        advertencia.setHeaderText(idioma.getString(encabezado));
        advertencia.setContentText(idioma.getString(contenido));
        ButtonType botonOK = new ButtonType("OK", ButtonData.OK_DONE);
        advertencia.getButtonTypes().setAll(botonOK);
        advertencia.show();
    }
}
