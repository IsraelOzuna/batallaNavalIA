/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import negocio.ConfiguracionConexion;
import negocio.IConexion;
import negocio.IJugador;

/**
 * FXML Controller class
 *
 * @author Irdevelo
 */
public class VentanaPeticionIPController implements Initializable {

    @FXML
    private TextField campoIPRMI1;
    @FXML
    private TextField campoIPRMI2;
    @FXML
    private TextField campoIPRMI3;
    @FXML
    private TextField campoIPRMI4;
    @FXML
    private TextField campoIPNode1;
    @FXML
    private TextField campoIPNode2;
    @FXML
    private TextField campoIPNode3;
    @FXML
    private TextField campoIPNode4;
    @FXML
    private TextField campoPuertoNode;

    private String ipRMI;

    private String ipNode;

    private String puertoNode;

    private Socket socket;

    private boolean conexionCorrecta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void desplegarIniciarSesion(ActionEvent event) throws IOException {

        if (verificarCamposVaciosIPRMI(campoIPRMI1, campoIPRMI2, campoIPRMI3, campoIPRMI4) && verificarCamposVaciosIPNode(campoIPNode1, campoIPNode2, campoIPNode3, campoIPNode4, campoPuertoNode)) {
            if (verificarLongitudCamposIPRMI(campoIPRMI1, campoIPRMI2, campoIPRMI3, campoIPRMI4) && verificarLongitudCamposIPNode(campoIPNode1, campoIPNode2, campoIPNode3, campoIPNode4)) {
                String ipServidorRMI = null;
                ipRMI = campoIPRMI1.getText() + "." + campoIPRMI2.getText() + "." + campoIPRMI3.getText() + "." + campoIPRMI4.getText();
                ipNode = campoIPNode1.getText() + "." + campoIPNode2.getText() + "." + campoIPNode3.getText() + "." + campoIPNode4.getText();
                puertoNode = campoPuertoNode.getText();
                ConfiguracionConexion conexionRMI = new ConfiguracionConexion();

                Registry registry = LocateRegistry.getRegistry();
                IConexion stubConexion;

                try {
                    stubConexion = (IConexion) registry.lookup("ServidorBatallaNaval");
                    ipServidorRMI = stubConexion.obtenerIPRMI();
                } catch (RemoteException | NotBoundException ex) {
                    Logger.getLogger(VentanaPeticionIPController.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (ipServidorRMI.equals(ipRMI)) {
                    verificarConexionNode();
                    socket.emit("validarConexion");

                    try {
                        Thread hilo = new Thread();
                        hilo.sleep(5000);
                        hilo.join();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(VentanaPeticionIPController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (conexionCorrecta) {
                        conexionRMI.actualizarIP(ipRMI, ipNode, puertoNode);
                        ResourceBundle idioma = ResourceBundle.getBundle("recursos.idioma_es_MX");
                        FXMLLoader loger = new FXMLLoader(getClass().getResource("/vista/VentanaIniciarSesion.fxml"), idioma);
                        Parent root = (Parent) loger.load();
                        Stage iniciarSesion = new Stage();
                        iniciarSesion.setScene(new Scene(root));
                        iniciarSesion.show();
                        Stage ventanaAnterior = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        ventanaAnterior.close();
                    }else{
                    System.out.println("No hay conexion con node");
                    }

                } else {
                    System.out.println("No hay conexion RMI en la casa de tu abuelita");
                }
            } else {
                mostrarMensajeAdvertencia("tituloAdvertencia", "encabezadoCampoExcedido", "contenidoCampoExcedido");
            }
        } else {
            mostrarMensajeAdvertencia("tituloAdvertencia", "encabezadoCamposVacios", "contenidoCamposVacios");
        }

    }

    public void verificarConexionNode() {
        try {
            socket = IO.socket("http://" + ipNode + ":" + puertoNode);
        } catch (URISyntaxException ex) {
            Logger.getLogger(VentanaPeticionIPController.class.getName()).log(Level.SEVERE, null, ex);
        }
        socket.on("ipCorrecta", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("entro");
                        conexionCorrecta = true;
                    }
                });
            }
        });
        socket.connect();
    }

    public void mostrarMensajeAdvertencia(String titulo, String encabezado, String contenido) {
        ResourceBundle idioma = ResourceBundle.getBundle("recursos.idioma_es_MX");
        Alert advertencia = new Alert(Alert.AlertType.WARNING);
        advertencia.setTitle(idioma.getString(titulo));
        advertencia.setHeaderText(idioma.getString(encabezado));
        advertencia.setContentText(idioma.getString(contenido));
        ButtonType botonOK = new ButtonType("OK", ButtonData.OK_DONE);
        advertencia.getButtonTypes().setAll(botonOK);
        advertencia.show();
    }

    public boolean verificarCamposVaciosIPRMI(TextField campoIPRMI1, TextField campoIPRMI2, TextField campoIPRMI3, TextField campoIPRMI4) {
        boolean camposLlenos = true;
        if (campoIPRMI1.getText().isEmpty() || campoIPRMI2.getText().isEmpty() || campoIPRMI3.getText().isEmpty() || campoIPRMI4.getText().isEmpty()) {
            camposLlenos = false;
        }
        return camposLlenos;
    }

    public boolean verificarCamposVaciosIPNode(TextField campoIPNode1, TextField campoIPNode2, TextField campoIPNode3, TextField campoIPNode4, TextField campoPuertoNode) {
        boolean camposLlenos = true;
        if (campoIPNode1.getText().isEmpty() || campoIPNode2.getText().isEmpty() || campoIPNode3.getText().isEmpty() || campoIPNode4.getText().isEmpty() || campoPuertoNode.getText().isEmpty()) {
            camposLlenos = false;
        }
        return camposLlenos;
    }

    public boolean verificarLongitudCamposIPRMI(TextField campoIPRMI1, TextField campoIPRMI2, TextField campoIPRMI3, TextField campoIPRMI4) {
        boolean camposCorrectos = true;
        if (campoIPRMI1.getText().length() > 3 || campoIPRMI2.getText().length() > 3 || campoIPRMI3.getText().length() > 3 || campoIPRMI4.getText().length() > 3) {
            camposCorrectos = false;
        }
        return camposCorrectos;
    }

    public boolean verificarLongitudCamposIPNode(TextField campoIPNode1, TextField campoIPNode2, TextField campoIPNode3, TextField campoIPNode4) {
        boolean camposCorrectos = true;
        if (campoIPNode1.getText().length() > 3 || campoIPNode2.getText().length() > 3 || campoIPNode3.getText().length() > 3 || campoIPNode4.getText().length() > 3) {
            camposCorrectos = false;
        }
        return camposCorrectos;
    }

}
