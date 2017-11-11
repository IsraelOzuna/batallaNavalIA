/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batallanaval;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Irdevelo
 */
public class BatallaNaval extends Application {
    
    @Override
    public void start(Stage ventanaIniciarSesion) throws IOException {
        ResourceBundle idioma = ResourceBundle.getBundle("recursos.idioma_es_MX");
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/VentanaIniciarSesion.fxml"),idioma);
        
        Scene scene = new Scene(root);
        
        ventanaIniciarSesion.setScene(scene);
        ventanaIniciarSesion.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
