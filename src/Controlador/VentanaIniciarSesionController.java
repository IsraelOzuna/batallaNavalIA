package Controlador;

import Negocio.ICuenta;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    /**
     * Initializes the controller class.
     */
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
        ICuenta stub;
        //String host = "192.168.43.223";
        String host = "192.168.0.14";

        if (campoUsuario.getText().isEmpty() | campoContrasena.getText().isEmpty()) {
            Alert alertaCamposVacios = new Alert(Alert.AlertType.WARNING);
            alertaCamposVacios.setTitle(idioma.getString("tituloAdvertencia"));
            alertaCamposVacios.setHeaderText(idioma.getString("encabezadoCamposVacios"));
            alertaCamposVacios.setContentText(idioma.getString("contenidoCamposVacios"));
            alertaCamposVacios.show();
        } else {
            try {
                Registry registry = LocateRegistry.getRegistry(host);
                stub = (ICuenta) registry.lookup("ServidorBatallaNaval");

                usuarioEncontrado = stub.iniciarSesion(campoUsuario.getText(), cifrarContrasena(campoContrasena.getText()));

                if (usuarioEncontrado) {                                             
                    FXMLLoader loger = new FXMLLoader(getClass().getResource("/Vista/VentanaMenu.fxml"), idioma);
                    Parent root = (Parent) loger.load();
                    
                    VentanaMenuController controladorMenu = loger.getController();
                    controladorMenu.obtenerNombreUsuario(campoUsuario.getText());                                        
                    
                    Stage menu = new Stage();
                    menu.setScene(new Scene(root));                    
                    menu.show();
                } else {
                    Alert alertaDatosIncorrectos = new Alert(Alert.AlertType.WARNING);
                    alertaDatosIncorrectos.setTitle(idioma.getString("tituloAdvertencia"));
                    alertaDatosIncorrectos.setHeaderText(idioma.getString("encabezadoDatosIncorrectos"));
                    alertaDatosIncorrectos.setContentText(idioma.getString("contenidoDatosIncorrectos"));
                    alertaDatosIncorrectos.show();
                }

            } catch (RemoteException | NotBoundException | NoSuchAlgorithmException ex) {
                Alert alertaNoConexion = new Alert(Alert.AlertType.WARNING);
                alertaNoConexion.setTitle(idioma.getString("tituloAdvertencia"));
                alertaNoConexion.setHeaderText(idioma.getString("encabezadoNoConexion"));
                alertaNoConexion.setContentText(idioma.getString("contenidoNoConexion"));
                alertaNoConexion.show();               
            }
        }
    }

    public String cifrarContrasena(String contrasena) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(contrasena.getBytes());
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < hash.length; i++) {
            stringBuilder.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuilder.toString();
    }

    @FXML
    public void registrarUsuario(ActionEvent event) throws IOException {
        FXMLLoader loger = new FXMLLoader(getClass().getResource("/Vista/VentanaRegistrarUsuario.fxml"), idioma);

        Parent root = (Parent) loger.load();
        Stage registro = new Stage();

        registro.setScene(new Scene(root));
        registro.show();
    }
}
