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

    private ResourceBundle recurso;

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
    public void initialize(URL url, ResourceBundle recurso) {
        this.recurso=recurso;
        configurarIdioma();
    }
    
    public void configurarIdioma(){
        etiquetaIngresarDatos.setText((recurso.getString("etIngresarDatos")));
        etiquetaNombre.setText(recurso.getString("etNombre"));
        etiquetaApellidos.setText(recurso.getString("etApellidos"));
        etiquetaCorreo.setText(recurso.getString("etCorreo"));
        etiquetaUsuario.setText(recurso.getString("etUsuarioRegistro"));
        etiquetaContrasena.setText(recurso.getString("etContrasenaRegistro"));
        botonRegistrarse.setText(recurso.getString("botRegistrarse"));
        botonCancelar.setText(recurso.getString("botCancelar"));        
    }
}
