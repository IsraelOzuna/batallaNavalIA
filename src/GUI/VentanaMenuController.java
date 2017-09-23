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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ozuna
 */
public class VentanaMenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    public void buscarPartida(ActionEvent event) throws IOException{
    FXMLLoader loger = new FXMLLoader(getClass().getResource("/GUI/VentanaBuscarPartida.fxml"));
        
       
        Parent root = (Parent)loger.load();
        Stage buscarPartida = new Stage();

        buscarPartida.setScene(new Scene(root));
        buscarPartida.show();

    }
    
   
}
