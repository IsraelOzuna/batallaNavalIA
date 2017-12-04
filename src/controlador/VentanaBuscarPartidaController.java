/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import io.socket.client.IO;
import static io.socket.client.IO.socket;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import negocio.ConfiguracionConexion;

/**
 * FXML Controller class
 *
 * @author Irdevelo
 */
public class VentanaBuscarPartidaController implements Initializable {

    private ResourceBundle idioma;
    private String nombreUsuario;
    private Socket socket;

    @FXML
    private Label etiquetaBuscandoPartida;

    @Override
    public void initialize(URL url, ResourceBundle idioma) {
        this.idioma = idioma;
        configurarIdioma();
    }

    public void obtenerNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void configurarIdioma() {
        etiquetaBuscandoPartida.setText(idioma.getString("etBuscandoPartida"));
    }

    public void comenzarBusqueda() {
        try {
            ConfiguracionConexion conexionNode = new ConfiguracionConexion();
            String ipNode = conexionNode.obtenerIPNode();
            String puertoNode = conexionNode.obtenerPuertoNode();
            crearConexion(ipNode, puertoNode);
            socket.emit("buscarPartida", nombreUsuario);
        } catch (URISyntaxException ex) {

        }
    }

    private void crearConexion(String node, String puerto) throws URISyntaxException {

        socket = IO.socket("http://" + node + ":" +puerto);

        socket.on("SeHaEncontradoUnaFlotaEnemiga", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                Platform.runLater(() -> {
                    try {
                        desplegarTablero((String) os[0], (Boolean) os[1]);
                        socket.off("SeHaEncontradoUnaFlotaEnemiga");
                    } catch (IOException ex) {
                        Logger.getLogger(VentanaBuscarPartidaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                //socket.disconnect();
            }
        });
        socket.connect();
    }

    public void desplegarTablero(String nombreRival, Boolean primerTirador) throws IOException {
        FXMLLoader loger = new FXMLLoader(getClass().getResource("/vista/VentanaTablero.fxml"), idioma);
        Parent root = (Parent) loger.load();

        VentanaTableroController controladorTablero = loger.getController();
        controladorTablero.adquirirDatos(socket, nombreUsuario, nombreRival, primerTirador);

        Stage tablero = new Stage();
        tablero.setScene(new Scene(root));
        tablero.show();
        //Stage ventanaAnterior = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //ventanaAnterior.close();
    }
}
