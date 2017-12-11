package controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import io.socket.client.Socket;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import negocio.Barco;
import negocio.ConfiguracionConexion;
import negocio.IPartida;
import negocio.IPuntaje;

/**
 * Plantilla que contiene atrbutos y metodos utilizados para el manejo de las 
 * diferentes acciones que se realizan en la vista VentanaTablero
 *
 * @author Irvin Dereb Vera López
 * @author Israel Reyes Ozuna
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
    @FXML
    private JFXButton botonEmpezar;
    @FXML
    private Label etiquetaUsuarioRival;
    @FXML
    private Label etiquetaMiUsuario;
    @FXML
    private JFXButton botonAbandonarPartida;

    private String nombreRival;

    private Boolean esPrimerTirador;

    private Socket socket;

    private String nombreJugador;

    private final String coordenadasOcupadas[] = new String[16];

    private int posicionesASalvo = 16;

    private int contadorCoordenadas = 0;

    private int contadorTiros = 3;

    private int contadorTirosContrincante = 0;

    private int contadorBarcosAcomodados = 5;

    private IPuntaje stubPuntaje;

    private IPartida stubPartida;

    private Stage ventanaActual;

    ConfiguracionConexion conexionRMI = new ConfiguracionConexion();
    String ipRMI = conexionRMI.obtenerIPRMI();

    @Override
    public void initialize(URL url, ResourceBundle idioma) {
        this.idioma = idioma;
        configurarIdioma();
        tableroOponente.setDisable(true);
    }

    /**
     * Permite desactivar los botones que sean pulsados al momento de realizar
     * los tiros al contrincante
     * 
     * @param event Un clic en cualquier botón
     */
    @FXML
    public void desactivarBoton(ActionEvent event) {

        if (contadorTiros > 0) {
            JFXButton botonPresionado = (JFXButton) event.getSource();
            botonPresionado.setDisable(true);
            botonPresionado.setStyle("-fx-background-color: FF2625");
            socket.emit("tiroRecibido", botonPresionado.getId(), esPrimerTirador, nombreJugador);
            contadorTiros--;
        }

        if (contadorTiros == 0) {
            contadorTiros = 3;
            tableroOponente.setDisable(true);
        }
    }

    /**
     * Permite la configuración del idioma de la pantalla.
     */
    public void configurarIdioma() {
        etiquetaFilas.setText(idioma.getString("etFilas"));
        etiquetaColumnas.setText(idioma.getString("etColumnas"));
        botonEmpezar.setText(idioma.getString("botonEmpezar"));
        botonAbandonarPartida.setText(idioma.getString("botonAbandonarPartida"));
    }

    /**
     * Permite verificar si ambos combos contienen información para poder 
     * acomodar un barco
     * 
     * @return Valor verdadero si ambos combos contienen información y falso 
     * si ambos o alguno de los dos está vacio
     */
    public boolean verificarCombos() {
        boolean comboVacio = true;
        if (comboColumnas.getItems().isEmpty()) {
            DialogosController.mostrarMensajeInformacion("tituloCuadroDialogo", "encabezadoComboColumnas", "contenidoComboColumnas", idioma);
            comboVacio = false;
        }

        if (comboFilas.getItems().isEmpty()) {
            DialogosController.mostrarMensajeInformacion("tituloCuadroDialogo", "encabezadoComboFilas", "contenidoComboFilas", idioma);
            comboVacio = false;
        }
        return comboVacio;
    }

    /**
     * Permite llenar el combo en cual se seleccionan las filas para posicionar
     * un barco
     */
    @FXML
    public void llenarComboFilas() {
        ObservableList<Integer> numeroFilas = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        comboFilas.setItems(numeroFilas);
    }

    /**
     * Permite llenar el combo en el cual se seleccionan las columnas para
     * posicionar un barco
     */
    @FXML
    public void llenarComboColumnas() {
        ObservableList<String> letrasColumnas = FXCollections.observableArrayList("A", "B", "C", "D", "E", "F", "G",
                "H", "I", "J");
        comboColumnas.setItems(letrasColumnas);
    }

    /**
     * Permite, con la ayuda de otros métodos implementados en la clase Barco,
     * realizar el acomodo del barco de una posición en el tablero 
     */
    @FXML
    public void colocarBarco1() {
        Barco barco = new Barco();
        int tamanoBarco = 1;
        if (verificarCombos()) {
            int ordenadaNumero = comboFilas.getValue();
            String ordenadaLetra = comboColumnas.getValue();

            String coordenadasBarco1[] = barco.generarCoordenadas(barco.convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1, tamanoBarco);

            if (barco.verificarCoordenadasLibres(coordenadasBarco1, coordenadasOcupadas)) {
                contadorBarcosAcomodados--;
                guardarCoordenadas(coordenadasBarco1);
                GridPane.setConstraints(barco1, barco.convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1);
                tableroPropio.getChildren().add(barco1);
                barco1.setDisable(true);
            } else {
                DialogosController.mostrarMensajeInformacion("tituloCuadroDialogo", "encabezadoPosOcupada", "contenidoPosOcupada", idioma);
            }
        }
    }

    /**
     * Permite, con la ayuda de otros métodos implementados en la clase Barco, 
     * realizar el acomodo del barco de dos posiciones en el tablero
     */
    @FXML
    public void colocarBarco2() {
        Barco barco = new Barco();
        int tamanoBarco = 2;
        if (verificarCombos()) {

            int ordenadaNumero = comboFilas.getValue();
            String ordenadaLetra = comboColumnas.getValue();

            String coordenadasBarco2[] = barco.generarCoordenadas(barco.convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1, tamanoBarco);

            if (!barco.limitarTablero(coordenadasBarco2, tamanoBarco)) {
                if (barco.verificarCoordenadasLibres(coordenadasBarco2, coordenadasOcupadas)) {
                    contadorBarcosAcomodados--;
                    guardarCoordenadas(coordenadasBarco2);
                    GridPane.setConstraints(barco2, barco.convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1);
                    tableroPropio.getChildren().add(barco2);
                    barco2.setDisable(true);
                } else {
                    DialogosController.mostrarMensajeInformacion("tituloCuadroDialogo", "encabezadoPosOcupada", "contenidoPosOcupada", idioma);
                }
            } else {
                DialogosController.mostrarMensajeInformacion("tituloCuadroDialogo", "encabezadoBarcoFuera", "contenidoBarcoFuera", idioma);
            }
        }
    }

    /**
     * Permite, con la ayuda de otros metodos implementados en la clase Barco,
     * realizar el acomodo del barco de tres posiciones en el tablero
     */
    @FXML
    public void colocarBarco3() {
        Barco barco = new Barco();
        int tamanoBarco = 3;
        if (verificarCombos()) {

            int ordenadaNumero = comboFilas.getValue();
            String ordenadaLetra = comboColumnas.getValue();

            String coordenadasBarco3[] = barco.generarCoordenadas(barco.convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1, tamanoBarco);

            if (!barco.limitarTablero(coordenadasBarco3, tamanoBarco)) {
                if (barco.verificarCoordenadasLibres(coordenadasBarco3, coordenadasOcupadas)) {
                    contadorBarcosAcomodados--;
                    guardarCoordenadas(coordenadasBarco3);
                    GridPane.setConstraints(barco3, barco.convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1);
                    tableroPropio.getChildren().add(barco3);
                    barco3.setDisable(true);
                } else {
                    DialogosController.mostrarMensajeInformacion("tituloCuadroDialogo", "encabezadoPosOcupada", "contenidoPosOcupada", idioma);
                }
            } else {
                DialogosController.mostrarMensajeInformacion("tituloCuadroDialogo", "encabezadoBarcoFuera", "contenidoBarcoFuera", idioma);
            }
        }
    }

    /**
     * Permite, con la ayuda de otros métodos implementados en la clase Barco, 
     * realizar el acomodo del primer barco de cinco posiciones en el tablero
     */
    @FXML
    public void colocarBarco4() {
        Barco barco = new Barco();
        int tamanoBarco = 5;
        if (verificarCombos()) {

            int ordenadaNumero = comboFilas.getValue();
            String ordenadaLetra = comboColumnas.getValue();

            String coordenadasBarco4[] = barco.generarCoordenadas(barco.convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1, tamanoBarco);

            if (!barco.limitarTablero(coordenadasBarco4, tamanoBarco)) {
                if (barco.verificarCoordenadasLibres(coordenadasBarco4, coordenadasOcupadas)) {
                    contadorBarcosAcomodados--;
                    guardarCoordenadas(coordenadasBarco4);
                    GridPane.setConstraints(barco4, barco.convertirLetrasANumeros(ordenadaLetra), ordenadaNumero + 1);
                    tableroPropio.getChildren().add(barco4);
                    barco4.setDisable(true);
                } else {
                    DialogosController.mostrarMensajeInformacion("tituloCuadroDialogo", "encabezadoPosOcupada", "contenidoPosOcupada", idioma);
                }
            } else {
                DialogosController.mostrarMensajeInformacion("tituloCuadroDialogo", "encabezadoBarcoFuera", "contenidoBarcoFuera", idioma);
            }
        }

    }

    /**
     * Permite, con la ayuda de otros métodos implementados en la clase Barco,
     * realizar el acomodo del segundo barco de cinco posiciones en el tablero
     */
    @FXML
    public void colocarBarco5() {
        Barco barco = new Barco();
        int tamanoBarco = 5;
        if (verificarCombos()) {

            int ordenadaNumero = comboFilas.getValue();
            String ordenadaLetra = comboColumnas.getValue();

            String coordenadasBarco5[] = barco.generarCoordenadas(barco.convertirLetrasANumeros(ordenadaLetra), ordenadaNumero - 1, tamanoBarco);

            if (!barco.limitarTablero(coordenadasBarco5, tamanoBarco)) {
                if (barco.verificarCoordenadasLibres(coordenadasBarco5, coordenadasOcupadas)) {
                    contadorBarcosAcomodados--;
                    guardarCoordenadas(coordenadasBarco5);
                    GridPane.setConstraints(barco5, barco.convertirLetrasANumeros(ordenadaLetra), ordenadaNumero + 1);
                    tableroPropio.getChildren().add(barco5);
                    barco5.setDisable(true);
                } else {
                    DialogosController.mostrarMensajeInformacion("tituloCuadroDialogo", "encabezadoPosOcupada", "contenidoPosOcupada", idioma);
                }
            } else {
                DialogosController.mostrarMensajeInformacion("tituloCuadroDialogo", "encabezadoBarcoFuera", "contenidoBarcoFuera", idioma);
            }
        }
    }

    /**
     * Permite realizar la conexión con el servidor de Node para poder comenzar
     * a jugar
     * @param event clic en el botón empezar 
     */
    @FXML
    public void empezarPartida(ActionEvent event) {
        if (contadorBarcosAcomodados == 0) {
            socket.emit("configurarPartida", esPrimerTirador, nombreJugador);
            botonEmpezar.setDisable(true);
        } else {
            DialogosController.mostrarMensajeInformacion("tituloCuadroDialogo", "encabezadoAcomodarBarcos", "contenidoAcomodarBarcos", idioma);
        }
    }
    
    /**
     * Permite realizar diferentes acciones de acuerdo a la respuesta que se 
     * esta esperando del servidor de Node
     */
    private void jugarPartida() {
        /**
         * Espera una señal del servidor de Node para poder comenzar una partida
         */
        socket.on("iniciarPartida", (Object... os) -> {
            if (esPrimerTirador) {
                tableroOponente.setDisable(false);
            }
        });
                
        /**
         * Espera una señal del servidor de Node para relizar acciones con el 
         * tiro que es recibido, ya sea perder o continuar con la partida
         */
        socket.on("tiroContrincante", (Object... os) -> {
            registrarTiroRecibido((String) os[0]);
            Platform.runLater(() -> {
                marcarDisparoRecibido((String) os[0]);
            });
            contadorTirosContrincante++;
            if (Barco.verificarPosicionesBarcosASalvo(posicionesASalvo)) {
                socket.emit("perderPartida", esPrimerTirador, nombreJugador);
                Platform.runLater(() -> {
                    try {
                        DialogosController.mostrarMensajeInformacion("tituloPerdiste", "encabezadoPerdiste", "contenidoPerdiste", idioma);
                        volverMenu();
                    } catch (IOException ex) {
                        Logger.getLogger(VentanaTableroController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                try {
                    String ipRMI1 = conexionRMI.obtenerIPRMI();
                    Registry registry = LocateRegistry.getRegistry(ipRMI1);
                    stubPuntaje = (IPuntaje) registry.lookup("ServidorBatallaNaval");
                    stubPuntaje.actualizarPuntajeJugador(30, nombreJugador);
                    stubPartida = (IPartida) registry.lookup("ServidorBatallaNaval");
                    stubPartida.actualizarPartidasPerdidas(nombreJugador);
                }catch (RemoteException | NotBoundException ex) {
                    Logger.getLogger(VentanaTableroController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (contadorTirosContrincante == 3) {
                contadorTirosContrincante = 0;
                tableroOponente.setDisable(false);
            }
        });

        /**
         * Espera una señal del servidor de Node para saber si un jugador 
         * abandonó la partida y quien fue
         */
        socket.on("jugadorAbandonoPartida", (Object... os) -> {
            Platform.runLater(() -> {
                try {
                    DialogosController.mostrarMensajeInformacion("tituloCuadroDialogo", "encabezadoRivalAbandono", "contenidoRivalAbandono", idioma);
                    volverMenu();
                } catch (IOException ex) {
                    Logger.getLogger(VentanaTableroController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        });

        /**
         * Espera una señal del servidor de Node para saber si un jugador
         * de acuerdo a los tiros realizados pudo ganar la partida
         */
        socket.on("ganarPartida", (Object... os) -> {
            Platform.runLater(() -> {
                try {
                    DialogosController.mostrarMensajeInformacion("tituloGanaste", "encabezadoGanaste", "contenidoGanaste", idioma);
                    volverMenu();
                } catch (IOException ex) {
                    Logger.getLogger(VentanaTableroController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            try {
                Registry registry = LocateRegistry.getRegistry(ipRMI);
                stubPuntaje = (IPuntaje) registry.lookup("ServidorBatallaNaval");
                stubPuntaje.actualizarPuntajeJugador(100, nombreJugador);
                stubPartida = (IPartida) registry.lookup("ServidorBatallaNaval");
                stubPartida.actualizarPartidasGanadas(nombreJugador);
            } catch (RemoteException | NotBoundException ex) {
                Logger.getLogger(VentanaTableroController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    /**
     * Permite que de acuerdo a lo ocurrido durante la partida, el jugador 
     * regrese al menú principal 
     * @throws IOException  puede arrojar esta excepción si no se completa 
     * correctamente la carga de la siguiente ventana
     */
    public void volverMenu() throws IOException {
        socket.off("iniciarPartida");
        socket.off("tiroContrincante");
        socket.off("ganarPartida");
        socket.off("interrumpirPartida");
        socket.disconnect();
        FXMLLoader loger = new FXMLLoader(getClass().getResource("/vista/VentanaMenu.fxml"), idioma);
        Parent root = (Parent) loger.load();
        VentanaMenuController controladorMenu = loger.getController();
        controladorMenu.obtenerNombreUsuario(nombreJugador);
        Stage menu = new Stage();
        menu.setScene(new Scene(root));
        menu.initStyle(StageStyle.UNDECORATED);
        menu.show();
        ventanaActual.close();
    }

    /**
     * Permite recuperar datos de la ventana Buscar para saber quienes son los 
     * jugadores que estarán en esa partida
     * 
     * @param socket el lugar a donde se hará la conexión en Node
     * @param nombreJugador El nombre de usuario del jugador local
     * @param nombreRival El nombre de usuario del rival
     * @param primerTirador para saber si es el primer o segundo tirador
     */
    public void adquirirDatos(Socket socket, String nombreJugador, String nombreRival, Boolean primerTirador) {
        this.socket = socket;
        this.nombreJugador = nombreJugador;
        this.nombreRival = nombreRival;
        this.esPrimerTirador = primerTirador;
        etiquetaMiUsuario.setText(nombreJugador);
        etiquetaUsuarioRival.setText(nombreRival);
        jugarPartida();
    }

    /**
     * Permite almacenar todas las coordenadas donde fueron posicionados los
     * barcos del jugador
     * 
     * @param coordenadas arreglo en donde se almacenan las coordenadas
     */
    public void guardarCoordenadas(String coordenadas[]) {
        for (String coordenada : coordenadas) {
            coordenadasOcupadas[contadorCoordenadas] = coordenada;
            contadorCoordenadas++;
        }
    }

    /**
     * Permite guardar el tiro del rival para saber si atino a un barco o no
     * 
     * @param tiroRecibido la coordenada a la cual tiró el rival
     */
    public void registrarTiroRecibido(String tiroRecibido) {
        for (String coordenadaOcupada : coordenadasOcupadas) {
            if (tiroRecibido.equals(coordenadaOcupada)) {
                posicionesASalvo--;
            }
        }
    }

    /**
     * Permite marcar en el tablero propio los disparos que el rival ha hecho
     * 
     * @param tiroRecibido la coordenada a la cual tiró el rival
     */
    public void marcarDisparoRecibido(String tiroRecibido) {
        String arregloCoordenadasDisparo[];
        arregloCoordenadasDisparo = tiroRecibido.split(",");
        ImageView bala = new ImageView("imagenes/balazo.png");
        bala.setFitHeight(39);
        bala.setFitWidth(39);
        GridPane.setConstraints(bala, Integer.parseInt(arregloCoordenadasDisparo[0]), Integer.parseInt(arregloCoordenadasDisparo[1]));
        tableroPropio.getChildren().add(bala);
    }

    /**
     * Permite recibir uns instancia del tablero para poder cerrarlo cuando
     * sea necesario
     * 
     * @param ventanaActual scene del tablero
     */
    public void setStageTablero(Stage ventanaActual) {
        this.ventanaActual = ventanaActual;
    }

    /**
     * Permite verificar cual jugador fue el que abandonó la partida
     * 
     * @param event clic en abandonar partida
     */
    @FXML
    public void abandonarPartida(ActionEvent event) {
        socket.emit("interrumpirPartida", esPrimerTirador, nombreJugador);
        try {
            volverMenu();
        } catch (IOException ex) {
            Logger.getLogger(VentanaTableroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
