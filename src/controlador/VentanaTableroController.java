/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
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
    private Pane panelPropio;
    @FXML
    private GridPane tableroPropio;
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
    
    private String[] coordenadasBarcosEnemigos;

    private int contadorCoordenadas = 0;
    
    private Boolean primerTirador;
    
    private Socket socket;
    
    private String nombreUsuario;
    
    private String nombreRival;
    
    @FXML
    private JFXButton botonEmpezar;
    @FXML
    private Label etiquetaUsuarioRival;
    @FXML
    private Label etiquetaMiUsuario;

 
    @Override
    public void initialize(URL url, ResourceBundle idioma) {
        this.idioma = idioma;
        configurarIdioma();        
        tableroOponente.setDisable(true);
    }

    public void configurarIdioma() {
        etiquetaFilas.setText(idioma.getString("etFilas"));
        etiquetaColumnas.setText(idioma.getString("etColumnas"));
        botonEmpezar.setText(idioma.getString("botonEmpezar"));
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
             mostrarMensajeInformacion("tituloInformacion", "encabezadoComboColumnas", "contenidoComboColumnas");
            comboVacio = false;
        }

        if (comboFilas.getItems().isEmpty()) {
            mostrarMensajeInformacion("tituloInformacion", "encabezadoComboFilas", "contenidoComboFilas");
            comboVacio = false;
        }
        return comboVacio;
    }

    public void posicionOcupada() {
        mostrarMensajeInformacion("tituloAdvertencia", "encabezadoPosOcupada", "contenidoPosOcupada");
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
                GridPane.setConstraints(barco4, convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1);
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
                GridPane.setConstraints(barco5, convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1);
                tableroPropio.getChildren().add(barco5);
                barco5.setDisable(true);
            } else {
                posicionOcupada();
            }
        }
    }

    public void mostrarMensajeInformacion(String titulo, String encabezado, String contenido) {
        Alert advertencia = new Alert(Alert.AlertType.INFORMATION);
        advertencia.setTitle(idioma.getString(titulo));
        advertencia.setHeaderText(idioma.getString(encabezado));
        advertencia.setContentText(idioma.getString(contenido));
        advertencia.show();
    }
    
    public void adquirirDatos(Socket socket,String nombreUsuario,String nombreRival, Boolean primerTirador){
        this.socket=socket;
        this.nombreUsuario=nombreUsuario;
        this.nombreRival=nombreRival;
        this.primerTirador=primerTirador;
        etiquetaMiUsuario.setText(nombreUsuario);
        etiquetaUsuarioRival.setText(nombreRival);
        obtenerPosicionamientoBarcosEnmigos();
    }
    
    @FXML
    private void empezarPartida(ActionEvent event) {
        socket.emit("configurarPartida", (Object[]) coordenadasOcupadas,primerTirador, nombreUsuario);
    }
    
    private void obtenerPosicionamientoBarcosEnmigos(){
    
        socket.on("posicionesBarcos", new Emitter.Listener(){
            @Override
            public void call(Object... os) {
               coordenadasBarcosEnemigos = (String[]) os[0];
            }
            
        });
        
        socket.on("inciarPartida", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                if(primerTirador){
                tableroOponente.setDisable(false);
                }
            }
        });
    
        socket.on("tiroContrincante", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                System.out.println("Hola Rancho Viejo");  
            }
        });
    
    }

}