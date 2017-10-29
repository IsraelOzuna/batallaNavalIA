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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ozuna
 */
public class VentanaMenuController implements Initializable {

    private ResourceBundle idioma;
    @FXML
    private ImageView imagenBarcoMenu;
    @FXML
    private ImageView imagenMisil;
    @FXML
    private ImageView imagenRadar;
    @FXML
    private TableView<?> tablaRank;
    @FXML
    private TableColumn<?, ?> columnaPosicion;


    @Override
    public void initialize(URL url, ResourceBundle idioma) {
        this.idioma = idioma;           
       configurarIdioma();             
    }

    @FXML
    private Button botonIniciarPartida;
    @FXML
    private Button botonCerrarSesion;
    @FXML
    private TableColumn columnaJugador;
    @FXML
    private TableColumn columnaPuntaje;

    public void configurarIdioma() {
        botonIniciarPartida.setText(idioma.getString("botIniciarPartida"));
        botonCerrarSesion.setText(idioma.getString("botCerrarSesion"));
        columnaJugador.setText(idioma.getString("columJugador"));
        columnaPuntaje.setText(idioma.getString("columPuntaje"));
    }

    @FXML
    public void buscarPartida(ActionEvent event) throws IOException {
        FXMLLoader loger = new FXMLLoader(getClass().getResource("/GUI/VentanaBuscarPartida.fxml"), idioma);

        Parent root = (Parent) loger.load();
        Stage buscarPartida = new Stage();

        buscarPartida.setScene(new Scene(root));
        buscarPartida.show();

    }

}
