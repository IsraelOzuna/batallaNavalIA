/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import io.socket.client.Socket;
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
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ozuna
 */
public class VentanaMenuController implements Initializable {

    private ResourceBundle idioma;
    private String nombreUsuario;    

    @FXML
    private Button botonIniciarPartida;
    @FXML
    private Button botonCerrarSesion;
    @FXML
    private TableColumn columnaJugador;
    @FXML
    private TableColumn columnaPuntaje;
    @FXML
    private Label etiquetaNombreUsuario;

    @Override
    public void initialize(URL url, ResourceBundle idioma) {
        this.idioma = idioma;
        configurarIdioma();
    }

    public void obtenerNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        etiquetaNombreUsuario.setText(nombreUsuario);
    }

    public void configurarIdioma() {
        botonIniciarPartida.setText(idioma.getString("botIniciarPartida"));
        botonCerrarSesion.setText(idioma.getString("botCerrarSesion"));
        columnaJugador.setText(idioma.getString("columJugador"));
        columnaPuntaje.setText(idioma.getString("columPuntaje"));
    }

    @FXML
    public void buscarPartida(ActionEvent event) throws IOException {
        FXMLLoader loger = new FXMLLoader(getClass().getResource("/Vista/VentanaBuscarPartida.fxml"), idioma);
        Parent root = (Parent) loger.load();

        VentanaBuscarPartidaController controladorBuscarPartida = loger.getController();
        controladorBuscarPartida.obtenerNombreUsuario(nombreUsuario);
        controladorBuscarPartida.comenzarBusqueda();

        Stage buscarPartida = new Stage();
        buscarPartida.setScene(new Scene(root));
        buscarPartida.show();
    }
}