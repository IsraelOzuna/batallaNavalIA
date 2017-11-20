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
import negocio.Barco;

/**
 * FXML Controller class
 *
 * @author Ozuna
 */
public class VentanaTableroController implements Initializable {

    private ResourceBundle idioma;
    @FXML
    private GridPane tableroOponente;
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

    private String[] coordenadasBarcosEnemigos;

    private Boolean primerTirador;

    private Socket socket;

    private String nombreUsuario;

    private String nombreRival;

    private final String coordenadasOcupadas[] = new String[16];

    private int posicionesASalvo = 16;

    private int contadorCoordenadas = 0;

    private int contadorTiros = 3;
    
    private int contadorTirosContrincante = 0;

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

    @FXML
    public void desactivarBoton(ActionEvent event) {
        
        if(contadorTiros > 0){
            JFXButton botonPresionado = (JFXButton) event.getSource();
            botonPresionado.setDisable(true);
            botonPresionado.setStyle("-fx-background-color: FF2625");
            socket.emit("tiroRecibido", botonPresionado.getId(), primerTirador, nombreUsuario);
            contadorTiros--;    
        }
        
         if(contadorTiros==0){
            contadorTiros = 3;
            tableroOponente.setDisable(true);
            }
    }

    public boolean verificarPosicionesBarcos() {
        return posicionesASalvo == 0;
    }

    public void registrarTiroRecibido(String tiroRecibido) {
        for (int i = 0; i < coordenadasOcupadas.length; i++) {
            if(tiroRecibido.equals(coordenadasOcupadas[i])){
                posicionesASalvo--;
            } 
        }           
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

    public void guardarCoordenadas(String coordenadas[]) {
        for (int i = 0; i < coordenadas.length; i++) {
            coordenadasOcupadas[contadorCoordenadas] = coordenadas[i];
            contadorCoordenadas++;
        }
    }

    @FXML
    public void colocarBarco1() {
        Barco barco = new Barco();
        int tamanoBarco = 1;
        if (verificarCombos()) {
            int ordenadaNumero = comboFilas.getValue();
            String ordenadaLetra = comboColumnas.getValue();

            String coordenadasBarco1[] = barco.generarCoordenadas(barco.convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1, tamanoBarco);

            if (barco.verificarCoordenadas(coordenadasBarco1, coordenadasOcupadas)) {
                guardarCoordenadas(coordenadasBarco1);
                GridPane.setConstraints(barco1, barco.convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1);
                tableroPropio.getChildren().add(barco1);
                barco1.setDisable(true);
            } else {
                mostrarMensajeInformacion("tituloAdvertencia", "encabezadoPosOcupada", "contenidoPosOcupada");
            }
        }
    }

    @FXML
    public void colocarBarco2() {
        Barco barco = new Barco();
        int tamanoBarco = 2;
        if (verificarCombos()) {

            int ordenadaNumero = comboFilas.getValue();
            String ordenadaLetra = comboColumnas.getValue();

            String coordenadasBarco2[] = barco.generarCoordenadas(barco.convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1, tamanoBarco);

            if (!barco.limitarTablero(coordenadasBarco2, tamanoBarco)) {
                if (barco.verificarCoordenadas(coordenadasBarco2, coordenadasOcupadas)) {
                    guardarCoordenadas(coordenadasBarco2);
                    GridPane.setConstraints(barco2, barco.convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1);
                    tableroPropio.getChildren().add(barco2);
                    barco2.setDisable(true);
                } else {
                    mostrarMensajeInformacion("tituloAdvertencia", "encabezadoPosOcupada", "contenidoPosOcupada");
                }
            } else {
                System.out.println("fuera");
            }
        }
    }

    @FXML
    public void colocarBarco3() {
        Barco barco = new Barco();
        int tamanoBarco = 3;
        if (verificarCombos()) {

            int ordenadaNumero = comboFilas.getValue();
            String ordenadaLetra = comboColumnas.getValue();

            String coordenadasBarco3[] = barco.generarCoordenadas(barco.convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1, tamanoBarco);

            if (!barco.limitarTablero(coordenadasBarco3, tamanoBarco)) {
                if (barco.verificarCoordenadas(coordenadasBarco3, coordenadasOcupadas)) {
                    guardarCoordenadas(coordenadasBarco3);
                    GridPane.setConstraints(barco3, barco.convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1);
                    tableroPropio.getChildren().add(barco3);
                    barco3.setDisable(true);
                } else {
                    mostrarMensajeInformacion("tituloAdvertencia", "encabezadoPosOcupada", "contenidoPosOcupada");
                }
            } else {
                System.out.println("fuera");
            }
        }
    }

    @FXML
    public void colocarBarco4() {
        Barco barco = new Barco();
        int tamanoBarco = 5;
        if (verificarCombos()) {

            int ordenadaNumero = comboFilas.getValue();
            String ordenadaLetra = comboColumnas.getValue();

            String coordenadasBarco4[] = barco.generarCoordenadas(barco.convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1, tamanoBarco);

            if (!barco.limitarTablero(coordenadasBarco4, tamanoBarco)) {
                if (barco.verificarCoordenadas(coordenadasBarco4, coordenadasOcupadas)) {
                    guardarCoordenadas(coordenadasBarco4);
                    GridPane.setConstraints(barco4, barco.convertirLetrasANumeros(ordenadaLetra), ordenadaNumero + 1);
                    tableroPropio.getChildren().add(barco4);
                    barco4.setDisable(true);
                } else {
                    mostrarMensajeInformacion("tituloAdvertencia", "encabezadoPosOcupada", "contenidoPosOcupada");
                }
            } else {
                System.out.println("fuera");
            }
        }
        
        for(int i=0; i<coordenadasOcupadas.length;i++){
            System.out.println(coordenadasOcupadas[i]);
        }
        
    }

    @FXML
    public void colocarBarco5() {
        Barco barco = new Barco();
        int tamanoBarco = 5;
        if (verificarCombos()) {

            int ordenadaNumero = comboFilas.getValue();
            String ordenadaLetra = comboColumnas.getValue();

            String coordenadasBarco5[] = barco.generarCoordenadas(barco.convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1, tamanoBarco);

            if (!barco.limitarTablero(coordenadasBarco5, tamanoBarco)) {
                if (barco.verificarCoordenadas(coordenadasBarco5, coordenadasOcupadas)) {
                    guardarCoordenadas(coordenadasBarco5);
                    GridPane.setConstraints(barco5, barco.convertirLetrasANumeros(ordenadaLetra), ordenadaNumero + 1);
                    tableroPropio.getChildren().add(barco5);
                    barco5.setDisable(true);
                } else {
                    mostrarMensajeInformacion("tituloAdvertencia", "encabezadoPosOcupada", "contenidoPosOcupada");
                }
            } else {
                System.out.println("fuera");
            }
        }
    }

    @FXML
    private void empezarPartida(ActionEvent event) {
        socket.emit("configurarPartida", primerTirador, nombreUsuario);
    }

    public void mostrarMensajeInformacion(String titulo, String encabezado, String contenido) {
        Alert advertencia = new Alert(Alert.AlertType.INFORMATION);
        advertencia.setTitle(idioma.getString(titulo));
        advertencia.setHeaderText(idioma.getString(encabezado));
        advertencia.setContentText(idioma.getString(contenido));
        advertencia.show();
    }

    private void obtenerPosicionamientoBarcosEnmigos() {

        socket.on("iniciarPartida", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                if (primerTirador) {
                    tableroOponente.setDisable(false);
                }
            }
        });

        socket.on("tiroContrincante", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                registrarTiroRecibido((String) os[0]);
                contadorTirosContrincante ++;
                System.out.println("contador Contrincante" + contadorTirosContrincante);
                if (verificarPosicionesBarcos()) {
                    socket.emit("perderPartida", primerTirador, nombreUsuario);
                    System.out.println("perdiste");
                }else{
                    System.out.println("Valor esperado "+contadorTirosContrincante);
                    if(contadorTirosContrincante == 3){
                        System.out.println("Valor esperado "+contadorTirosContrincante);
                        contadorTirosContrincante = 0;
                        tableroOponente.setDisable(false);
                    }
                }
            }
        });

        socket.on("ganarPartida", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                System.out.println("ganaste");
            }
        });

    }

    public void adquirirDatos(Socket socket, String nombreUsuario, String nombreRival, Boolean primerTirador) {
        this.socket = socket;
        this.nombreUsuario = nombreUsuario;
        this.nombreRival = nombreRival;
        this.primerTirador = primerTirador;
        etiquetaMiUsuario.setText(nombreUsuario);
        etiquetaUsuarioRival.setText(nombreRival);
        obtenerPosicionamientoBarcosEnmigos();
    }

    public void configurarIdioma() {
        etiquetaFilas.setText(idioma.getString("etFilas"));
        etiquetaColumnas.setText(idioma.getString("etColumnas"));
        botonEmpezar.setText(idioma.getString("botonEmpezar"));
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

}
