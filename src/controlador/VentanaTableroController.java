package controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
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
 *
 * @author Irdevelo
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

    private String nombreRival;

    private Boolean esPrimerTirador;

    private Socket socket;

    private String nombreUsuario;

    private final String coordenadasOcupadas[] = new String[16];

    private int posicionesASalvo = 16;

    private int contadorCoordenadas = 0;

    private int contadorTiros = 3;

    private int contadorTirosContrincante = 0;

    private int contadorBarcosAcomodados = 5;

    private IPuntaje stubPuntaje;

    private IPartida stubPartida;

    private Stage ventanaAnterior;

    ConfiguracionConexion conexionRMI = new ConfiguracionConexion();
    String ipRMI = conexionRMI.obtenerIPRMI();

    @FXML
    private JFXButton botonAbandonarPartida;

    @Override
    public void initialize(URL url, ResourceBundle idioma) {
        this.idioma = idioma;
        configurarIdioma();
        tableroOponente.setDisable(true);
    }

    /**
     *
     * @param event
     */
    @FXML
    public void desactivarBoton(ActionEvent event) {

        if (contadorTiros > 0) {
            JFXButton botonPresionado = (JFXButton) event.getSource();
            botonPresionado.setDisable(true);
            botonPresionado.setStyle("-fx-background-color: FF2625");
            socket.emit("tiroRecibido", botonPresionado.getId(), esPrimerTirador, nombreUsuario);
            contadorTiros--;
        }

        if (contadorTiros == 0) {
            contadorTiros = 3;
            tableroOponente.setDisable(true);
        }
    }

    /**
     *
     */
    public void configurarIdioma() {
        etiquetaFilas.setText(idioma.getString("etFilas"));
        etiquetaColumnas.setText(idioma.getString("etColumnas"));
        botonEmpezar.setText(idioma.getString("botonEmpezar"));
        botonAbandonarPartida.setText(idioma.getString("botonAbandonarPartida"));
    }

    /**
     *
     * @return
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
     *
     */
    @FXML
    public void llenarComboFilas() {
        ObservableList<Integer> numeroFilas = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        comboFilas.setItems(numeroFilas);
    }

    /**
     *
     */
    @FXML
    public void llenarComboColumnas() {
        ObservableList<String> letrasColumnas = FXCollections.observableArrayList("A", "B", "C", "D", "E", "F", "G",
                "H", "I", "J");
        comboColumnas.setItems(letrasColumnas);
    }

    /**
     *
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
     *
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
     *
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
     *
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
     *
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
     *
     * @param event
     */
    @FXML
    public void empezarPartida(ActionEvent event) {
        if (contadorBarcosAcomodados == 0) {
            socket.emit("configurarPartida", esPrimerTirador, nombreUsuario);
            botonEmpezar.setDisable(true);
        } else {
            DialogosController.mostrarMensajeInformacion("tituloCuadroDialogo", "encabezadoAcomodarBarcos", "contenidoAcomodarBarcos", idioma);
        }
    }

    private void jugarPartida() {
        socket.on("iniciarPartida", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                if (esPrimerTirador) {
                    tableroOponente.setDisable(false);
                }
            }
        });

        socket.on("tiroContrincante", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                registrarTiroRecibido((String) os[0]);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        marcarDisparoRecibido((String) os[0]);
                    }
                });
                contadorTirosContrincante++;
                if (verificarPosicionesBarcosASalvo()) {
                    socket.emit("perderPartida", esPrimerTirador, nombreUsuario);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                DialogosController.mostrarMensajeInformacion("tituloPerdiste", "encabezadoPerdiste", "contenidoPerdiste", idioma);
                                volverMenu();
                            } catch (IOException ex) {
                                Logger.getLogger(VentanaTableroController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    });

                    try {
                        String ipRMI = conexionRMI.obtenerIPRMI();
                        Registry registry = LocateRegistry.getRegistry(ipRMI);
                        stubPuntaje = (IPuntaje) registry.lookup("ServidorBatallaNaval");
                        stubPuntaje.actualizarPuntajeJugador(30, nombreUsuario);
                        stubPartida = (IPartida) registry.lookup("ServidorBatallaNaval");
                        stubPartida.actualizarPartidasPerdidas(nombreUsuario);
                    } catch (RemoteException | NotBoundException ex) {
                        Logger.getLogger(VentanaTableroController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else if (contadorTirosContrincante == 3) {
                    contadorTirosContrincante = 0;
                    tableroOponente.setDisable(false);
                }
            }
        });

        socket.on("jugadorAbandonoPartida", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            DialogosController.mostrarMensajeInformacion("tituloCuadroDialogo", "encabezadoRivalAbandono", "contenidoRivalAbandono", idioma);
                            volverMenu();
                        } catch (IOException ex) {
                            Logger.getLogger(VentanaTableroController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            }

        });

        socket.on("ganarPartida", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            DialogosController.mostrarMensajeInformacion("tituloGanaste", "encabezadoGanaste", "contenidoGanaste", idioma);
                            volverMenu();
                        } catch (IOException ex) {
                            Logger.getLogger(VentanaTableroController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                });

                try {
                    Registry registry = LocateRegistry.getRegistry(ipRMI);
                    stubPuntaje = (IPuntaje) registry.lookup("ServidorBatallaNaval");
                    stubPuntaje.actualizarPuntajeJugador(100, nombreUsuario);
                    stubPartida = (IPartida) registry.lookup("ServidorBatallaNaval");
                    stubPartida.actualizarPartidasGanadas(nombreUsuario);
                } catch (RemoteException | NotBoundException ex) {
                    Logger.getLogger(VentanaTableroController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    /**
     *
     * @throws IOException
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
        controladorMenu.obtenerNombreUsuario(nombreUsuario);
        Stage menu = new Stage();
        menu.setScene(new Scene(root));
        menu.initStyle(StageStyle.UNDECORATED);
        menu.show();
        ventanaAnterior.close();
    }

    /**
     *
     * @param socket
     * @param nombreUsuario
     * @param nombreRival
     * @param primerTirador
     */
    public void adquirirDatos(Socket socket, String nombreUsuario, String nombreRival, Boolean primerTirador) {
        this.socket = socket;
        this.nombreUsuario = nombreUsuario;
        this.nombreRival = nombreRival;
        this.esPrimerTirador = primerTirador;
        etiquetaMiUsuario.setText(nombreUsuario);
        etiquetaUsuarioRival.setText(nombreRival);
        jugarPartida();
    }

    /**
     *
     * @param coordenadas
     */
    public void guardarCoordenadas(String coordenadas[]) {
        for (String coordenada : coordenadas) {
            coordenadasOcupadas[contadorCoordenadas] = coordenada;
            contadorCoordenadas++;
        }
    }

    /**
     *
     * @return
     */
    public boolean verificarPosicionesBarcosASalvo() {
        return posicionesASalvo == 0;
    }

    /**
     *
     * @param tiroRecibido
     */
    public void registrarTiroRecibido(String tiroRecibido) {
        for (String coordenadasOcupada : coordenadasOcupadas) {
            if (tiroRecibido.equals(coordenadasOcupada)) {
                posicionesASalvo--;
            }
        }
    }

    /**
     *
     * @param tiroRecibido
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
     *
     * @param ventanaBuscar
     */
    public void setStageTablero(Stage ventanaBuscar) {
        ventanaAnterior = ventanaBuscar;
    }

    /**
     *
     * @param event
     */
    @FXML
    public void abandonarPartida(ActionEvent event) {
        socket.emit("interrumpirPartida", esPrimerTirador, nombreUsuario);
        try {
            volverMenu();
        } catch (IOException ex) {
            Logger.getLogger(VentanaTableroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
