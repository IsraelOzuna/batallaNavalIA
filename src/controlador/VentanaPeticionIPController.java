/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import io.socket.client.Socket;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import negocio.ConfiguracionConexion;
import negocio.IConexion;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void desplegarIniciarSesion(ActionEvent event) throws IOException {
        ResourceBundle idioma = ResourceBundle.getBundle("recursos.idioma_es_MX");
        if (verificarCamposVaciosIPRMI(campoIPRMI1, campoIPRMI2, campoIPRMI3, campoIPRMI4) && verificarCamposVaciosIPNode(campoIPNode1, campoIPNode2, campoIPNode3, campoIPNode4, campoPuertoNode)) {
            if (verificarLongitudCamposIPRMI(campoIPRMI1, campoIPRMI2, campoIPRMI3, campoIPRMI4) && verificarLongitudCamposIPNode(campoIPNode1, campoIPNode2, campoIPNode3, campoIPNode4)) {
                boolean conectadoRMI = false;
                ipRMI = campoIPRMI1.getText() + "." + campoIPRMI2.getText() + "." + campoIPRMI3.getText() + "." + campoIPRMI4.getText();
                ipNode = campoIPNode1.getText() + "." + campoIPNode2.getText() + "." + campoIPNode3.getText() + "." + campoIPNode4.getText();
                puertoNode = campoPuertoNode.getText();
                ConfiguracionConexion conexionRMI = new ConfiguracionConexion();

                try {
                    Registry registry = LocateRegistry.getRegistry(ipRMI);
                    IConexion stubConexion;
                    stubConexion = (IConexion) registry.lookup("ServidorBatallaNaval");
                    conectadoRMI = stubConexion.obtenerIPRMI();
                } catch (RemoteException | NotBoundException ex) {
                    DialogosController.mostrarMensajeAdvertencia("tituloAdvertencia", "encabezadoNoConexionRMI", "contenidoNoConexionRMI",idioma);
                }

                if (conectadoRMI) {
                    try {
                        if (ConfiguracionConexion.verificarConexionNode(ipNode, puertoNode)) {
                            conexionRMI.actualizarIP(ipRMI, ipNode, puertoNode);
                            FXMLLoader loger = new FXMLLoader(getClass().getResource("/vista/VentanaIniciarSesion.fxml"), idioma);
                            Parent root;
                            root = (Parent) loger.load();
                            Stage iniciarSesion = new Stage();
                            iniciarSesion.setScene(new Scene(root));
                            iniciarSesion.initStyle(StageStyle.UNDECORATED);
                            iniciarSesion.show();
                            Stage ventanaAnterior = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            ventanaAnterior.close();
                        }
                    } catch (IOException ex) {
                        DialogosController.mostrarMensajeAdvertencia("tituloAdvertencia", "encabezadoNoConexionNode", "contenidoNoConexionNode",idioma);
                    }
                }
            } else {
                DialogosController.mostrarMensajeAdvertencia("tituloAdvertencia", "encabezadoCampoExcedido", "contenidoCampoExcedido",idioma);
            }
        } else {
            DialogosController.mostrarMensajeAdvertencia("tituloAdvertencia", "encabezadoCamposVacios", "contenidoCamposVacios",idioma);
        }
    }

    /**
     *
     * @param campoIPRMI1
     * @param campoIPRMI2
     * @param campoIPRMI3
     * @param campoIPRMI4
     * @return
     */
    public boolean verificarCamposVaciosIPRMI(TextField campoIPRMI1, TextField campoIPRMI2, TextField campoIPRMI3, TextField campoIPRMI4) {
        boolean camposLlenos = true;
        if (campoIPRMI1.getText().isEmpty() || campoIPRMI2.getText().isEmpty() || campoIPRMI3.getText().isEmpty() || campoIPRMI4.getText().isEmpty()) {
            camposLlenos = false;
        }
        return camposLlenos;
    }

    /**
     *
     * @param campoIPNode1
     * @param campoIPNode2
     * @param campoIPNode3
     * @param campoIPNode4
     * @param campoPuertoNode
     * @return
     */
    public boolean verificarCamposVaciosIPNode(TextField campoIPNode1, TextField campoIPNode2, TextField campoIPNode3, TextField campoIPNode4, TextField campoPuertoNode) {
        boolean camposLlenos = true;
        if (campoIPNode1.getText().isEmpty() || campoIPNode2.getText().isEmpty() || campoIPNode3.getText().isEmpty() || campoIPNode4.getText().isEmpty() || campoPuertoNode.getText().isEmpty()) {
            camposLlenos = false;
        }
        return camposLlenos;
    }

    /**
     *
     * @param campoIPRMI1
     * @param campoIPRMI2
     * @param campoIPRMI3
     * @param campoIPRMI4
     * @return
     */
    public boolean verificarLongitudCamposIPRMI(TextField campoIPRMI1, TextField campoIPRMI2, TextField campoIPRMI3, TextField campoIPRMI4) {
        boolean camposCorrectos = true;
        if (campoIPRMI1.getText().length() > 3 || campoIPRMI2.getText().length() > 3 || campoIPRMI3.getText().length() > 3 || campoIPRMI4.getText().length() > 3) {
            camposCorrectos = false;
        }
        return camposCorrectos;
    }

    /**
     *
     * @param campoIPNode1
     * @param campoIPNode2
     * @param campoIPNode3
     * @param campoIPNode4
     * @return
     */
    public boolean verificarLongitudCamposIPNode(TextField campoIPNode1, TextField campoIPNode2, TextField campoIPNode3, TextField campoIPNode4) {
        boolean camposCorrectos = true;
        if (campoIPNode1.getText().length() > 3 || campoIPNode2.getText().length() > 3 || campoIPNode3.getText().length() > 3 || campoIPNode4.getText().length() > 3) {
            camposCorrectos = false;
        }
        return camposCorrectos;
    }

}
