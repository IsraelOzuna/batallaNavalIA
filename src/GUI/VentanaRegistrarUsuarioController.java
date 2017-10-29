/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
}
