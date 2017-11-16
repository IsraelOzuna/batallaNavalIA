/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import negocio.Jugador;
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
import negocio.IJugador;

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
        IJugador stub;
        //String host = "192.168.43.223";
        //String host = "192.168.0.14";
        String host = "127.0.0.1";
        boolean usuarioExistente;

        if (verificarCamposVacios(campoNombre, campoApellidos, campoCorreo, campoUsuario, campoContrasena)) {
            mostrarMensajeAdvertencia("tituloAdvertencia", "encabezadoCamposVacios", "contenidoCamposVacios");
        } else if (verificarLongitud(campoNombre, campoApellidos, campoCorreo, campoUsuario)) {
            
            mostrarMensajeAdvertencia("tituloAdvertencia", "encabezadoLongitudExcedida", "contenidoLongitudExcedida");

        } else if (verificarNombreUsuarioCorrecto(campoUsuario.getText())) {
            try {
                Registry registry = LocateRegistry.getRegistry(host);
                stub = (IJugador) registry.lookup("ServidorBatallaNaval");
                usuarioExistente = stub.verificarExistenciaCuenta(campoUsuario.getText());
                if (usuarioExistente) {
                    mostrarMensajeAdvertencia("tituloAdvertencia", "encabezadoUsuarioExistente", "contenidoUsuarioExistente");
                } else if (validarCorreo(campoCorreo.getText())) {
                    registrarJugadorValidado(stub);
                } else {
                    mostrarMensajeAdvertencia("tituloAdvertencia", "encabezadoCorreoInvalido", "contenidoCorreoInvalido");
                }
            } catch (RemoteException | NotBoundException | NoSuchAlgorithmException ex) {
                mostrarMensajeAdvertencia("tituloAdvertencia", "encabezadoNoConexion", "contenidoNoConexion");
            }
        } else {
            mostrarMensajeAdvertencia("tituloAdvertencia", "encabezadoUsuarioNoValido", "contenidoUsuarioNoValido");
        }
    }

    public void mostrarMensajeAdvertencia(String titulo, String encabezado, String contenido) {
        Alert advertencia = new Alert(Alert.AlertType.WARNING);
        advertencia.setTitle(idioma.getString(titulo));
        advertencia.setHeaderText(idioma.getString(encabezado));
        advertencia.setContentText(idioma.getString(contenido));
        advertencia.show();
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

    public boolean verificarLongitud(TextField campoNombre, TextField campoApellidos, TextField campoCorreo, TextField campoUsuario) {
        boolean longitudExcedida = false;

        if (campoNombre.getText().length() > 50 | campoApellidos.getText().length() > 50| 
                campoCorreo.getText().length() > 320 | campoUsuario.getText().length() > 30){
            longitudExcedida = true;
        }
        return longitudExcedida;
    }

    public void registrarJugadorValidado(IJugador stub) throws NoSuchAlgorithmException, RemoteException {
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
}