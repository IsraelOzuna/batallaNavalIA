/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Negocio.ICuenta;
import Negocio.Jugador;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Ozuna
 */
public class VentanaRegistrarUsuarioController implements Initializable {

    private ResourceBundle idioma;

    @FXML
    private TextField campoNombre;
    @FXML
    private TextField campoApellidos;
    @FXML
    private TextField campoCorreo;
    @FXML
    private TextField campoUsuario;
    @FXML
    private PasswordField campoContrasena;
    @FXML
    private Label etiquetaIngresarDatos;
    @FXML
    private Label etiquetaNombre;
    @FXML
    private Label etiquetaApellidos;
    @FXML
    private Label etiquetaCorreo;
    @FXML
    private Label etiquetaUsuario;
    @FXML
    private Label etiquetaContrasena;
    @FXML
    private JFXButton botonRegistrarse;
    @FXML
    private JFXButton botonCancelar;

    private static String formatoCorreo = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle idioma) {
        this.idioma = idioma;
        configurarIdioma();
    }

    public void configurarIdioma() {
        etiquetaIngresarDatos.setText((idioma.getString("etIngresarDatos")));
        etiquetaNombre.setText(idioma.getString("etNombre"));
        etiquetaApellidos.setText(idioma.getString("etApellidos"));
        etiquetaCorreo.setText(idioma.getString("etCorreo"));
        etiquetaUsuario.setText(idioma.getString("etUsuarioRegistro"));
        etiquetaContrasena.setText(idioma.getString("etContrasenaRegistro"));
        botonRegistrarse.setText(idioma.getString("botRegistrarse"));
        botonCancelar.setText(idioma.getString("botCancelar"));
    }

    @FXML
    private void registrarUsuario(ActionEvent event) {
        
        
        
        ICuenta stub;
        String host = "127.0.0.1";
        boolean usuarioExistente;

        if (verificarCamposVacios(campoNombre, campoApellidos, campoCorreo, campoUsuario, campoContrasena)) {
            Alert alertaCamposVacios = new Alert(Alert.AlertType.WARNING);
            alertaCamposVacios.setTitle(idioma.getString("tituloAdvertencia"));
            alertaCamposVacios.setHeaderText(idioma.getString("encabezadoCamposVacios"));
            alertaCamposVacios.setContentText(idioma.getString("contenidoCamposVacios"));
            alertaCamposVacios.show();
        } else if (verificarNombreUsuarioCorrecto(campoUsuario.getText())) {
            try {
                Registry registry = LocateRegistry.getRegistry(host);
                stub = (ICuenta) registry.lookup("ServidorBatallaNaval");
                usuarioExistente = stub.verificarExistenciaCuenta(campoUsuario.getText());
                if (usuarioExistente) {
                    Alert alertaCamposVacios = new Alert(Alert.AlertType.WARNING);
                    alertaCamposVacios.setTitle(idioma.getString("tituloAdvertencia"));
                    alertaCamposVacios.setHeaderText(idioma.getString("encabezadoUsuarioExistente"));
                    alertaCamposVacios.setContentText(idioma.getString("contenidoUsuarioExistente"));
                    alertaCamposVacios.show();
                } else {
                   
                    if(validarCorreo(campoCorreo.getText())){
                    registrarJugadorValidado(stub);
                    }else{
                    Alert correoInvalido = new Alert(Alert.AlertType.WARNING);
                    correoInvalido.setTitle(idioma.getString("tituloAdvertencia"));
                    correoInvalido.setHeaderText(idioma.getString("encabezadoCorreoInvalido"));
                    correoInvalido.setContentText(idioma.getString("contenidoCorreoInvalido"));
                    correoInvalido.show();
                    }
                    
                    
                }
            } catch (RemoteException | NotBoundException | NoSuchAlgorithmException ex) {
                Alert alertaNoConexion = new Alert(Alert.AlertType.WARNING);
                alertaNoConexion.setTitle(idioma.getString("tituloAdvertencia"));
                alertaNoConexion.setHeaderText(idioma.getString("encabezadoNoConexion"));
                alertaNoConexion.setContentText(idioma.getString("contenidoNoConexion"));
                alertaNoConexion.show();
            }
        } else {
   
            Alert alertaNombreIncorrecto = new Alert(Alert.AlertType.WARNING);
            alertaNombreIncorrecto.setTitle(idioma.getString("tituloAdvertencia"));
            alertaNombreIncorrecto.setHeaderText(idioma.getString("encabezadoUsuarioNoValido"));
            alertaNombreIncorrecto.setContentText(idioma.getString("contenidoUsuarioNoValido"));
            alertaNombreIncorrecto.show();
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

    public boolean verificarNombreUsuarioCorrecto(String nombreUsuario) {
        boolean nombreUsuarioCorrecto = true;
        String caracteres[] = nombreUsuario.split(" ");
        if (caracteres.length > 1) {
            nombreUsuarioCorrecto = false;
        }
        return nombreUsuarioCorrecto;
    }

    public boolean verificarCamposVacios(TextField campoNombre, TextField campoApellidos, TextField campoCorreo, TextField campoUsuario, PasswordField campoContrasena) {
        boolean camposVacios = false;
        if (campoNombre.getText().isEmpty() | campoApellidos.getText().isEmpty() | campoCorreo.getText().isEmpty() | campoUsuario.getText().isEmpty() | campoContrasena.getText().isEmpty()) {
            camposVacios = true;
        }
        return camposVacios;
    }

    public void registrarJugadorValidado(ICuenta stub) throws NoSuchAlgorithmException, RemoteException {
        Jugador jugador = new Jugador();
        jugador.setNombreJugador(campoUsuario.getText());
        jugador.setContrasena(cifrarContrasena(campoContrasena.getText()));
        jugador.setCorreo(campoCorreo.getText());
        jugador.setNombre(campoNombre.getText());
        jugador.setApellidos(campoApellidos.getText());
        stub.registrarJugador(jugador);
        Alert alertaCamposVacios = new Alert(Alert.AlertType.WARNING);
        alertaCamposVacios.setTitle(idioma.getString("tituloCuadroDialogo"));
        alertaCamposVacios.setHeaderText(idioma.getString("encabezadoRegistroExitoso"));
        alertaCamposVacios.setContentText(idioma.getString("contenidoRegistroExitoso"));
        alertaCamposVacios.show();

    }
    
        public static boolean validarCorreo(String correo) {
        Pattern patron = Pattern.compile(formatoCorreo);
        Matcher matcher = patron.matcher(correo);
        return matcher.matches();
    }

    @FXML
    private void limitarCaracteresNombre(KeyEvent event) {
        if (campoNombre.getText().length() >= 50) {
            event.consume();
        }
    }

    @FXML
    private void limitarCaracteresApellidos(KeyEvent event) {
        if (campoNombre.getText().length() >= 50) {
            event.consume();
        }
    }

    @FXML
    private void limitarCaracteresCorreo(KeyEvent event) {
        if (campoNombre.getText().length() >= 260) {
            event.consume();
        }
    }

    @FXML
    private void limitarCaracteresUsuario(KeyEvent event) {
        if (campoNombre.getText().length() >= 30) {
            event.consume();
        }
    }

}
