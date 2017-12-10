package controlador;

import negocio.Jugador;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import negocio.ConfiguracionConexion;
import negocio.IJugador;
import negocio.Utileria;

/**
 * Plantilla que contiene atributos y métodos necesarios para el control de la
 * vista VentanaRegistrarUsuario
 *
 * @author Irvin Dereb Vera López.
 * @author Israel Reyes Ozuna.
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

    @Override
    public void initialize(URL url, ResourceBundle idioma) {
        this.idioma = idioma;
        configurarIdioma();
    }

    /**
     * Permite la configuración del idioma de la pantalla.
     */
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

    /**
     * Permite hacer el proceso de registro de un jugador en el sistema.
     *
     * @param event Un clic en el boton Registrar.
     */
    @FXML
    public void registrarUsuario(ActionEvent event) {
        IJugador stubJugador;
        boolean usuarioExistente;

        if (verificarCamposVacios(campoNombre, campoApellidos, campoCorreo, campoUsuario, campoContrasena)) {
            DialogosController.mostrarMensajeAdvertencia("tituloAdvertencia", "encabezadoCamposVacios", "contenidoCamposVacios", idioma);
        } else if (verificarLongitud(campoNombre, campoApellidos, campoCorreo, campoUsuario)) {

            DialogosController.mostrarMensajeAdvertencia("tituloAdvertencia", "encabezadoLongitudExcedida", "contenidoLongitudExcedida", idioma);

        } else if (Jugador.verificarNombreUsuarioCorrecto(campoUsuario.getText())) {
            try {
                ConfiguracionConexion conexionRMI = new ConfiguracionConexion();
                String ipRMI = conexionRMI.obtenerIPRMI();

                Registry registry = LocateRegistry.getRegistry(ipRMI);
                stubJugador = (IJugador) registry.lookup("ServidorBatallaNaval");
                usuarioExistente = stubJugador.verificarExistenciaCuenta(campoUsuario.getText());
                if (usuarioExistente) {
                    DialogosController.mostrarMensajeAdvertencia("tituloAdvertencia", "encabezadoUsuarioExistente", "contenidoUsuarioExistente", idioma);
                } else if (Utileria.validarCorreo(campoCorreo.getText())) {
                    registrarJugadorValidado(stubJugador);
                } else {
                    DialogosController.mostrarMensajeAdvertencia("tituloAdvertencia", "encabezadoCorreoInvalido", "contenidoCorreoInvalido", idioma);
                }
            } catch (RemoteException | NotBoundException | NoSuchAlgorithmException ex) {
                Logger.getLogger(VentanaRegistrarUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                DialogosController.mostrarMensajeAdvertencia("tituloAdvertencia", "encabezadoNoConexion", "contenidoNoConexion", idioma);
            }
        } else {
            DialogosController.mostrarMensajeAdvertencia("tituloAdvertencia", "encabezadoUsuarioNoValido", "contenidoUsuarioNoValido", idioma);
        }
    }

    /**
     * Permite corroborar que los campos no estén vacíos.
     *
     * @param campoNombre Campo en donde el usuario introduce su nombre.
     * @param campoApellidos Campo en donde el usuario introduce sus apellidos.
     * @param campoCorreo Campo en donde el usuario introduce su correo.
     * @param campoUsuario Campo en donde el usuario introduce su nombre de
     * jugador.
     * @param campoContrasena Campo en donde el usuario introduce su contraseña.
     * @return Un valor verdadero si todos los campos están llenos o un valor
     * falso en caso de que alguno esté vacío.
     */
    public boolean verificarCamposVacios(TextField campoNombre, TextField campoApellidos, TextField campoCorreo, TextField campoUsuario, PasswordField campoContrasena) {
        boolean camposVacios = false;
        if (campoNombre.getText().isEmpty() || campoApellidos.getText().isEmpty() || campoCorreo.getText().isEmpty() || campoUsuario.getText().isEmpty() || campoContrasena.getText().isEmpty()) {
            camposVacios = true;
        }
        return camposVacios;
    }

    /**
     * Permite verificar que el límite permitido de los campos no sea excedido.
     *
     * @param campoNombre Campo en donde el usuario introduce su nombre.
     * @param campoApellidos Campo en donde el usuario introduce sus apellidos.
     * @param campoCorreo Campo en donde el usuario introduce su correo.
     * @param campoUsuario Campo en donde el usuario introduce su nombre de
     * jugador.
     * @return
     */
    public boolean verificarLongitud(TextField campoNombre, TextField campoApellidos, TextField campoCorreo, TextField campoUsuario) {
        boolean longitudExcedida = false;

        if (campoNombre.getText().length() > 50 || campoApellidos.getText().length() > 50
                || campoCorreo.getText().length() > 320 || campoUsuario.getText().length() > 30) {
            longitudExcedida = true;
        }
        return longitudExcedida;
    }

    /**
     * Permite registrar al jugador una vez que se ha validado.
     *
     * @param stub
     * @throws NoSuchAlgorithmException
     * @throws RemoteException
     */
    public void registrarJugadorValidado(IJugador stub) throws NoSuchAlgorithmException, RemoteException {
        Jugador jugador = new Jugador();
        jugador.setNombreJugador(campoUsuario.getText());
        Utileria contraseña = new Utileria();
        jugador.setContrasena(contraseña.cifrarContrasena(campoContrasena.getText()));
        jugador.setCorreo(campoCorreo.getText());
        jugador.setNombre(campoNombre.getText());
        jugador.setApellidos(campoApellidos.getText());
        stub.registrarJugador(jugador);
        DialogosController.mostrarMensajeInformacion("tituloCuadroDialogo", "encabezadoRegistroExitoso", "contenidoRegistroExitoso", idioma);
        campoNombre.setText("");
        campoApellidos.setText("");
        campoContrasena.setText("");
        campoCorreo.setText("");
        campoUsuario.setText("");
    }

    /**
     * Permite regresar a la VentanaIniciarSesión.
     *
     * @param event Un clic en el botón regresar.
     * @throws IOException
     */
    @FXML
    public void regresarVentanaIniciarSesion(ActionEvent event) throws IOException {
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
