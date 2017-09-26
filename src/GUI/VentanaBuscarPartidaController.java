/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Irdevelo
 */
public class VentanaBuscarPartidaController implements Initializable {

    private ResourceBundle recurso;

    @FXML
    private Label etiquetaBuscandoPartida;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recurso = rb;
        configurarIdioma();
    }
    
    public void configurarIdioma() {
        etiquetaBuscandoPartida.setText(recurso.getString("etBuscandoPartida"));
    }

}
