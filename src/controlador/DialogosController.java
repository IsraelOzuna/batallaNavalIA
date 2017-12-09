/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Irdevelo
 */
public class DialogosController {
    
    /**
     *
     * @param titulo
     * @param encabezado
     * @param contenido
     * @param idioma
     */
    public static void mostrarMensajeAdvertencia(String titulo, String encabezado, String contenido, ResourceBundle idioma) {
        Alert advertencia = new Alert(Alert.AlertType.WARNING);
        advertencia.setTitle(idioma.getString(titulo));
        advertencia.setHeaderText(idioma.getString(encabezado));
        advertencia.setContentText(idioma.getString(contenido));
        ButtonType botonOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        advertencia.getButtonTypes().setAll(botonOK);
        advertencia.showAndWait();
    }
    
    /**
     *
     * @param titulo
     * @param encabezado
     * @param contenido
     * @param idioma
     */
    public static void mostrarMensajeInformacion(String titulo, String encabezado, String contenido, ResourceBundle idioma) {
        Alert advertencia = new Alert(Alert.AlertType.INFORMATION);
        advertencia.setTitle(idioma.getString(titulo));
        advertencia.setHeaderText(idioma.getString(encabezado));
        advertencia.setContentText(idioma.getString(contenido));
        ButtonType botonOK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        advertencia.getButtonTypes().setAll(botonOK);
        advertencia.showAndWait();
    }
}
