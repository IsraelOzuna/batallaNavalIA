/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Irdevelo
 */
public class VentanaIniciarSesionController implements Initializable {
    private ResourceBundle recurso;
    
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle recurso) {
        this.recurso = recurso;
        configurarIdioma();
        
    }
    public void configurarIdioma(){
        etiquetaBatallaNaval.setText(recurso.getString("etBatallaNaval"));
        etiquetaUsuario.setText(recurso.getString("etUsuario"));
        etiquetaContraseña.setText(recurso.getString("etContraseña"));
        botonIngles.setText(recurso.getString("botIngles"));
        botonEspañol.setText(recurso.getString("botEspañol"));
        botonIniciarSesion.setText(recurso.getString("botIniciarSesion"));
        botonRegistrarse.setText(recurso.getString("botRegistrarse"));
    }

    @FXML
    private void cambiarIdiomaIngles(ActionEvent event) {
        recurso = ResourceBundle.getBundle("recursos.idioma_en_US");
        configurarIdioma();
    }
    
    @FXML
    private void cambiarIdiomaEspañol(ActionEvent event) {
        recurso = ResourceBundle.getBundle("recursos.idioma_es_MX");
        configurarIdioma();
    }
    
     @FXML
    private void iniciarSesion(ActionEvent event) throws IOException {
         System.out.println(recurso);
        FXMLLoader loger = new FXMLLoader(getClass().getResource("/GUI/VentanaMenu.fxml"),recurso);
        
       
        Parent root = (Parent)loger.load();
        Stage menu = new Stage();

        menu.setScene(new Scene(root));
        menu.show();
        
}
    
}