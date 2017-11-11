/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Ozuna
 */
public class VentanaTableroController implements Initializable {

    private ResourceBundle idioma;
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
    @FXML
    private ImageView barco1;
    @FXML
    private ImageView barco2;
    @FXML
    private ImageView barco3;
    @FXML
    private ImageView barco4;
    @FXML
    private ImageView barco5;
    @FXML
    private Label etiquetaFilas;
    @FXML
    private Label etiquetaColumnas;

    private String coordenadasOcupadas[] = new String[16];

    private int contadorCoordenadas = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle idioma) {
        this.idioma = idioma;
        configurarIdioma();
    }

    public void configurarIdioma() {
        etiquetaFilas.setText(idioma.getString("etFilas"));
        etiquetaColumnas.setText(idioma.getString("etColumnas"));
    }

    public String[] generarCoordenadas(int posicionColumna, int posicionFila, int tamanoBarco) {

        String coordenadas[] = null;

        switch (tamanoBarco) {
            case 1:
                coordenadas = new String[1];
                coordenadas[0] = String.valueOf(posicionColumna) + "," + String.valueOf(posicionFila);
                break;

            case 2:
                coordenadas = new String[2];
                coordenadas[0] = posicionColumna + "," + posicionFila;
                coordenadas[1] = (posicionColumna + 1) + "," + posicionFila;
                break;

            case 3:
                coordenadas = new String[3];
                coordenadas[0] = posicionColumna + "," + posicionFila;
                coordenadas[1] = (posicionColumna + 1) + "," + posicionFila;
                coordenadas[2] = (posicionColumna + 2) + "," + posicionFila;
                break;

            case 5:
                coordenadas = new String[5];
                coordenadas[0] = posicionColumna + "," + posicionFila;
                coordenadas[1] = posicionColumna + "," + (posicionFila + 1);
                coordenadas[2] = posicionColumna + "," + (posicionFila + 2);
                coordenadas[3] = posicionColumna + "," + (posicionFila + 3);
                coordenadas[4] = posicionColumna + "," + (posicionFila + 4);
                break;

            default:
        }
        return coordenadas;
    }

    public boolean verificarCoordenadas(String coordenadas[]) {
        boolean posicionDisponible = true;

        for (int i = 0; i < coordenadasOcupadas.length; i++) {
            for (int j = 0; j < coordenadas.length; j++) {
                if (coordenadas[j].equals(coordenadasOcupadas[i])) {
                    posicionDisponible = false;
                    break;
                }
            }
        }
        return posicionDisponible;
    }

    public void guardarCoordenadas(String coordenadas[]) {
        for (int i = 0; i < coordenadas.length; i++) {
            coordenadasOcupadas[contadorCoordenadas] = coordenadas[i];
            contadorCoordenadas++;
        }
    }

    public int convertirLetrasANumeros(String letra) {
        int numeroConvertido = 0;
        switch (letra) {
            case "A":
                numeroConvertido = 0;
                break;
            case "B":
                numeroConvertido = 1;
                break;
            case "C":
                numeroConvertido = 2;
                break;
            case "D":
                numeroConvertido = 3;
                break;
            case "E":
                numeroConvertido = 4;
                break;
            case "F":
                numeroConvertido = 5;
                break;
            case "G":
                numeroConvertido = 6;
                break;
            case "H":
                numeroConvertido = 7;
                break;
            case "I":
                numeroConvertido = 8;
                break;
            case "J":
                numeroConvertido = 9;
                break;

        }

        return numeroConvertido;
    }

    public boolean verificarCombos() {
        boolean comboVacio = true;
        if (comboColumnas.getItems().isEmpty()) {
            Alert alertaCamposVacios = new Alert(Alert.AlertType.INFORMATION);
            alertaCamposVacios.setTitle(idioma.getString("tituloInformacion"));
            alertaCamposVacios.setHeaderText(idioma.getString("encabezadoComboColumnas"));
            alertaCamposVacios.setContentText(idioma.getString("contenidoComboColumnas"));
            alertaCamposVacios.show();
            comboVacio = false;
        }

        if (comboFilas.getItems().isEmpty()) {
            Alert alertaCamposVacios = new Alert(Alert.AlertType.INFORMATION);
            alertaCamposVacios.setTitle(idioma.getString("tituloInformacion"));
            alertaCamposVacios.setHeaderText(idioma.getString("encabezadoComboFilas"));
            alertaCamposVacios.setContentText(idioma.getString("contenidoComboFilas"));
            alertaCamposVacios.show();
            comboVacio = false;
        }
        return comboVacio;
    }

    public void posicionOcupada() {
        Alert alertaCamposVacios = new Alert(Alert.AlertType.INFORMATION);
        alertaCamposVacios.setTitle(idioma.getString("tituloAdvertencia"));
        alertaCamposVacios.setHeaderText(idioma.getString("encabezadoPosOcupada"));
        alertaCamposVacios.setContentText(idioma.getString("contenidoPosOcupada"));
        alertaCamposVacios.show();
    }

    @FXML
    public void desactivarBoton(ActionEvent event) {
        JFXButton botonPresionado = (JFXButton) event.getSource();
        botonPresionado.setDisable(true);
        botonPresionado.setStyle("-fx-background-color: FF2625");
    }

    @FXML
    public void llenarComboFilas() {
        ObservableList<Integer> numeroFilas = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        comboFilas.setItems(numeroFilas);
    }

    @FXML
    public void llenarComboColumnas() {
        ObservableList<String> letrasColumnas = FXCollections.observableArrayList("A", "B", "C", "D", "E", "F", "G",
                "H", "I", "J");
        comboColumnas.setItems(letrasColumnas);
    }

    @FXML
    public void colocarBarco1() {
        int tamanoBarco = 1;
        if (verificarCombos()) {
            int ordenadaNumero = comboFilas.getValue();
            String ordenadaLetra = comboColumnas.getValue();

            String coordenadasBarco1[] = generarCoordenadas(convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1, tamanoBarco);

            if (verificarCoordenadas(coordenadasBarco1)) {
                guardarCoordenadas(coordenadasBarco1);
                GridPane.setConstraints(barco1, convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1);
                tableroPropio.getChildren().add(barco1);
                barco1.setDisable(true);
            } else {
                posicionOcupada();
            }
        }
    }

    @FXML
    public void colocarBarco2() {
        int tamanoBarco = 2;
        if (verificarCombos()) {

            int ordenadaNumero = comboFilas.getValue();
            String ordenadaLetra = comboColumnas.getValue();

            String coordenadasBarco2[] = generarCoordenadas(convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1, tamanoBarco);

            if (verificarCoordenadas(coordenadasBarco2)) {
                guardarCoordenadas(coordenadasBarco2);
                GridPane.setConstraints(barco2, convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1);
                tableroPropio.getChildren().add(barco2);
                barco2.setDisable(true);
            } else {
                posicionOcupada();
            }
        }
    }

    @FXML
    public void colocarBarco3() {
        int tamanoBarco = 3;
        if (verificarCombos()) {

            int ordenadaNumero = comboFilas.getValue();
            String ordenadaLetra = comboColumnas.getValue();

            String coordenadasBarco3[] = generarCoordenadas(convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1, tamanoBarco);

            if (verificarCoordenadas(coordenadasBarco3)) {
                guardarCoordenadas(coordenadasBarco3);
                GridPane.setConstraints(barco3, convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1);
                tableroPropio.getChildren().add(barco3);
                barco3.setDisable(true);
            } else {
                posicionOcupada();
            }
        }
    }

    @FXML
    public void colocarBarco4() {
        int tamanoBarco = 5;
        if (verificarCombos()) {

            int ordenadaNumero = comboFilas.getValue();
            String ordenadaLetra = comboColumnas.getValue();

            String coordenadasBarco4[] = generarCoordenadas(convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1, tamanoBarco);

            if (verificarCoordenadas(coordenadasBarco4)) {
                guardarCoordenadas(coordenadasBarco4);
                GridPane.setConstraints(barco4, convertirLetrasANumeros(ordenadaLetra), ordenadaNumero + 1);
                tableroPropio.getChildren().add(barco4);
                barco4.setDisable(true);
            } else {
                posicionOcupada();
            }
        }
    }

    @FXML
    public void colocarBarco5() {
        int tamanoBarco = 5;
        if (verificarCombos()) {

            int ordenadaNumero = comboFilas.getValue();
            String ordenadaLetra = comboColumnas.getValue();

            String coordenadasBarco5[] = generarCoordenadas(convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1, tamanoBarco);

            if (verificarCoordenadas(coordenadasBarco5)) {
                guardarCoordenadas(coordenadasBarco5);
                GridPane.setConstraints(barco5, convertirLetrasANumeros(ordenadaLetra), ordenadaNumero + 1);
                tableroPropio.getChildren().add(barco5);
                barco5.setDisable(true);
            } else {
                posicionOcupada();
            }
        }
    }
}