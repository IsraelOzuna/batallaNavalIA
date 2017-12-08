/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void desplegarIniciarSesion(ActionEvent event) throws IOException {

        if (verificarCamposVaciosIPRMI(campoIPRMI1, campoIPRMI2, campoIPRMI3, campoIPRMI4) && verificarCamposVaciosIPNode(campoIPNode1, campoIPNode2, campoIPNode3, campoIPNode4, campoPuertoNode)) {
            if (verificarLongitudCamposIPRMI(campoIPRMI1, campoIPRMI2, campoIPRMI3, campoIPRMI4) && verificarLongitudCamposIPNode(campoIPNode1, campoIPNode2, campoIPNode3, campoIPNode4)) {

                ConfiguracionConexion conexionIP = new ConfiguracionConexion();
                ipRMI = campoIPRMI1.getText() + "." + campoIPRMI2.getText() + "." + campoIPRMI3.getText() + "." + campoIPRMI4.getText();
                ipNode = campoIPNode1.getText() + "." + campoIPNode2.getText() + "." + campoIPNode3.getText() + "." + campoIPNode4.getText();
                puertoNode = campoPuertoNode.getText();

                conexionIP.actualizarIP(ipRMI, ipNode, puertoNode);

                ResourceBundle idioma = ResourceBundle.getBundle("recursos.idioma_es_MX");
                FXMLLoader loger = new FXMLLoader(getClass().getResource("/vista/VentanaIniciarSesion.fxml"), idioma);
                Parent root = (Parent) loger.load();
                Stage iniciarSesion = new Stage();
                iniciarSesion.setScene(new Scene(root));
                iniciarSesion.show();
                Stage ventanaAnterior = (Stage) ((Node) event.getSource()).getScene().getWindow();
                ventanaAnterior.close();
            } else {
                mostrarMensajeAdvertencia("tituloAdvertencia", "encabezadoCampoExcedido", "contenidoCampoExcedido");
            }
        } else {
            mostrarMensajeAdvertencia("tituloAdvertencia", "encabezadoCamposVacios", "contenidoCamposVacios");
        }

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
