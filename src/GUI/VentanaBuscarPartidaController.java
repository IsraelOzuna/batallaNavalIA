/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Irdevelo
 */
public class VentanaBuscarPartidaController implements Initializable {

    private ResourceBundle idioma;

    @FXML
    private Label etiquetaBuscandoPartida;
    @FXML
    private ImageView imagenMundo;

    @Override
    public void initialize(URL url, ResourceBundle idioma) {
        this.idioma = idioma;
        configurarIdioma();
    }

    public void configurarIdioma() {
        etiquetaBuscandoPartida.setText(idioma.getString("etBuscandoPartida"));
    }
    
    public void jugarPartida() throws IOException{
        FXMLLoader loger = new FXMLLoader(getClass().getResource("/GUI/VentanaTablero.fxml"), idioma);

        Parent root = (Parent) loger.load();
        Stage tableros = new Stage();

        tableros.setScene(new Scene(root));
        tableros.show();
    }
}
