/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Ozuna
 */
public class VentanaTableroController implements Initializable {

    @FXML
    private Pane panelOponente;
    @FXML
    private GridPane tableroOponente;
    @FXML
    private JFXButton botonA1;
    @FXML
    private JFXButton botonB1;
    @FXML
    private JFXButton botonC1;
    @FXML
    private JFXButton botonD1;
    @FXML
    private JFXButton botonE1;
    @FXML
    private JFXButton botonF1;
    @FXML
    private JFXButton botonG1;
    @FXML
    private JFXButton botonH1;
    @FXML
    private JFXButton botonI1;
    @FXML
    private JFXButton botonJ1;
    @FXML
    private JFXButton botonA2;
    @FXML
    private JFXButton botonA3;
    @FXML
    private JFXButton botonA4;
    @FXML
    private JFXButton botonA5;
    @FXML
    private JFXButton botonA6;
    @FXML
    private JFXButton botonA7;
    @FXML
    private JFXButton botonA8;
    @FXML
    private JFXButton botonA9;
    @FXML
    private JFXButton botonA10;
    @FXML
    private JFXButton botonB2;
    @FXML
    private JFXButton botonB3;
    @FXML
    private JFXButton botonB4;
    @FXML
    private JFXButton botonB5;
    @FXML
    private JFXButton botonB6;
    @FXML
    private JFXButton botonB7;
    @FXML
    private JFXButton botonB8;
    @FXML
    private JFXButton botonB9;
    @FXML
    private JFXButton botonB10;
    @FXML
    private JFXButton botonC2;
    @FXML
    private JFXButton botonC3;
    @FXML
    private JFXButton botonC4;
    @FXML
    private JFXButton botonC5;
    @FXML
    private JFXButton botonC6;
    @FXML
    private JFXButton botonC7;
    @FXML
    private JFXButton botonC8;
    @FXML
    private JFXButton botonC9;
    @FXML
    private JFXButton botonC10;
    @FXML
    private JFXButton botonD2;
    @FXML
    private JFXButton botonD3;
    @FXML
    private JFXButton botonD4;
    @FXML
    private JFXButton botonD5;
    @FXML
    private JFXButton botonD6;
    @FXML
    private JFXButton botonD7;
    @FXML
    private JFXButton botonD8;
    @FXML
    private JFXButton botonD9;
    @FXML
    private JFXButton botonD10;
    @FXML
    private JFXButton botonE2;
    @FXML
    private JFXButton botonE3;
    @FXML
    private JFXButton botonE4;
    @FXML
    private JFXButton botonE5;
    @FXML
    private JFXButton botonE6;
    @FXML
    private JFXButton botonE7;
    @FXML
    private JFXButton botonE8;
    @FXML
    private JFXButton botonE9;
    @FXML
    private JFXButton botonE10;
    @FXML
    private JFXButton botonF2;
    @FXML
    private JFXButton botonF3;
    @FXML
    private JFXButton botonF4;
    @FXML
    private JFXButton botonF5;
    @FXML
    private JFXButton botonF6;
    @FXML
    private JFXButton botonF7;
    @FXML
    private JFXButton botonF8;
    @FXML
    private JFXButton botonF9;
    @FXML
    private JFXButton botonF10;
    @FXML
    private JFXButton botonG2;
    @FXML
    private JFXButton botonG3;
    @FXML
    private JFXButton botonG4;
    @FXML
    private JFXButton botonG5;
    @FXML
    private JFXButton botonG6;
    @FXML
    private JFXButton botonG7;
    @FXML
    private JFXButton botonG8;
    @FXML
    private JFXButton botonG9;
    @FXML
    private JFXButton botonG10;
    @FXML
    private JFXButton botonH2;
    @FXML
    private JFXButton botonH3;
    @FXML
    private JFXButton botonH4;
    @FXML
    private JFXButton botonH5;
    @FXML
    private JFXButton botonH6;
    @FXML
    private JFXButton botonH7;
    @FXML
    private JFXButton botonH8;
    @FXML
    private JFXButton botonH9;
    @FXML
    private JFXButton botonH10;
    @FXML
    private JFXButton botonI2;
    @FXML
    private JFXButton botonI4;
    @FXML
    private JFXButton botonI5;
    @FXML
    private JFXButton botonI6;
    @FXML
    private JFXButton botonI7;
    @FXML
    private JFXButton botonI8;
    @FXML
    private JFXButton botonI9;
    @FXML
    private JFXButton botonI10;
    @FXML
    private JFXButton botonJ2;
    @FXML
    private JFXButton botonJ3;
    @FXML
    private JFXButton botonJ4;
    @FXML
    private JFXButton botonJ5;
    @FXML
    private JFXButton botonJ6;
    @FXML
    private JFXButton botonJ7;
    @FXML
    private JFXButton botonJ8;
    @FXML
    private JFXButton botonJ9;
    @FXML
    private JFXButton botonJ10;
    @FXML
    private JFXButton botonI3;
    @FXML
    private Pane panelPropio;
    @FXML
    private GridPane tableroPropio;
    @FXML
    private Label etiquetaA;
    @FXML
    private Label etiquetaB;
    @FXML
    private Label etiquetaC;
    @FXML
    private Label etiquetaD;
    @FXML
    private Label etiquetaE;
    @FXML
    private Label etiquetaF;
    @FXML
    private Label etiquetaG;
    @FXML
    private Label etiquetaH;
    @FXML
    private Label etiquetaI;
    @FXML
    private Label etiquetaJ;
    @FXML
    private Label etiqueta1;
    @FXML
    private Label etiqueta2;
    @FXML
    private Label etiqueta3;
    @FXML
    private Label etiqueta4;
    @FXML
    private Label etiqueta5;
    @FXML
    private Label etiqueta6;
    @FXML
    private Label etiqueta7;
    @FXML
    private Label etiqueta8;
    @FXML
    private Label etiqueta9;
    @FXML
    private Label etiqueta10;
    @FXML
    private JFXComboBox<Integer> comboFilas;
    @FXML
    private JFXComboBox<String> comboColumnas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void desactivarBoton(ActionEvent event) {
        JFXButton botonPresionado = (JFXButton) event.getSource();
        botonPresionado.setDisable(true);
        botonPresionado.setStyle("-fx-background-color: FF2625");
    }

    @FXML
    private void llenarComboFilas() {
        ObservableList<Integer> numeroFilas = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);        
        comboFilas.setItems(numeroFilas);
    }
    
    @FXML
    private void llenarComboColumnas(){
        ObservableList<String> letrasColumnas = FXCollections.observableArrayList("A","B","C","D","E","F","G",
                "H","I","J");
        comboColumnas.setItems(letrasColumnas);
    }
}
