package negocio;

import java.util.HashMap;
import java.util.Map;

/**
 * Plantilla que contiene métodos utilizados para acomodar los barcos en el
 * tablero.
 *
 * @author Irvin Dereb Vera López.
 * @author Israel Reyes Ozuna.
 */
public class Barco {

    /**
     * Permite generar las coordenadas que ocupará un barco a partir de la
     * posición seleccionada y su tamaño.
     *
     * @param posicionColumna Posición de la columna seleccionada por el jugador
     * para acomodar el barco.
     * @param posicionFila Posición de la fila seleccionada por el jugador para
     * acomodar el barco.
     * @param tamanoBarco Indica el tamaño de casilas que ocupará el barco.
     * @return Un arreglo con las posiciones que el barco ocupará en caso de ser
     * acomodado.
     */
    public String[] generarCoordenadas(int posicionColumna, int posicionFila, int tamanoBarco) {
        String coordenadas[] = null;

        if (tamanoBarco == 1) {
            coordenadas = new String[1];
            coordenadas[0] = String.valueOf(posicionColumna) + "," + String.valueOf(posicionFila);
        } else if (tamanoBarco == 2) {
            coordenadas = new String[2];
            coordenadas[0] = posicionColumna + "," + posicionFila;
            coordenadas[1] = (posicionColumna + 1) + "," + posicionFila;
        } else if (tamanoBarco == 3) {
            coordenadas = new String[3];
            coordenadas[0] = posicionColumna + "," + posicionFila;
            coordenadas[1] = (posicionColumna + 1) + "," + posicionFila;
            coordenadas[2] = (posicionColumna + 2) + "," + posicionFila;
        } else if (tamanoBarco == 5) {
            coordenadas = new String[5];
            coordenadas[0] = posicionColumna + "," + posicionFila;
            coordenadas[1] = posicionColumna + "," + (posicionFila + 1);
            coordenadas[2] = posicionColumna + "," + (posicionFila + 2);
            coordenadas[3] = posicionColumna + "," + (posicionFila + 3);
            coordenadas[4] = posicionColumna + "," + (posicionFila + 4);
        }

        return coordenadas;
    }

    /**
     * Permite verificar si las coordenadas que ocupará un barco no están
     * ocupadas
     *
     * @param coordenadas Arreglo con las coordenadas que el barco ocupará en
     * caso de ser posicionado.
     * @param coordenadasOcupadas Arreglo con las coordenadas que son ocupadas
     * actualmente en el tablero.
     * @return Un valor verdadero si las posciones están libres en el tablero o
     * un valor falso si las coordenadas ya están ocupadas.
     */
    public boolean verificarCoordenadasLibres(String coordenadas[], String coordenadasOcupadas[]) {
        boolean posicionDisponible = true;

        for (String coordenadasOcupada : coordenadasOcupadas) {
            for (String coordenada : coordenadas) {
                if (coordenada.equals(coordenadasOcupada)) {
                    posicionDisponible = false;
                    break;
                }
            }
        }
        return posicionDisponible;
    }

    /**
     * Permite la conversión de la coordenada de una columna a un numero entero.
     *
     * @param letra Columna seleccionada por el jugador.
     * @return Un valor entero a partir de la columna seleccionada por el
     * jugador.
     */
    public int convertirLetrasANumeros(String letra) {
        int numeroConvertido;

        Map<String, Integer> letras = new HashMap<>();
        letras.put("A", 0);
        letras.put("B", 1);
        letras.put("C", 2);
        letras.put("D", 3);
        letras.put("E", 4);
        letras.put("F", 5);
        letras.put("G", 6);
        letras.put("H", 7);
        letras.put("I", 8);
        letras.put("J", 9);

        numeroConvertido = letras.get(letra);

        return numeroConvertido;
    }

    /**
     * Permite verificar que los barcos que llegan no salgan del tablero
     *
     * @param coordenadasBarco Arreglo con las coordenadas que el barco ocupará.
     *
     * @param longitudBarco Indica el tamaño de casilas que ocupará el barco.
     * @return Valor verdadero si el barco excede el límite del tablero o un
     * valor falso si está dentro del rango del tablero.
     */
    public boolean limitarTablero(String coordenadasBarco [], int longitudBarco) {
        boolean limiteExcedido = false;        

        if (longitudBarco == 2) {
            limiteExcedido = limitarBarco2(coordenadasBarco);
        } else if (longitudBarco == 3) {
            limiteExcedido = limitarBarco3(coordenadasBarco);
        } else if (longitudBarco == 5) {
            limiteExcedido = limitarBarco5(coordenadasBarco);
        }
        return limiteExcedido;
    }

    /**
     * Permite verificar si el barco de 2 posiciones excede el limite del
     * tablero
     *
     * @param coordenadasBarco las coordenadas generadas donde el barco quiere
     * ser posicionado     
     * @return Valor verdadero si el barco de dos posiciones excede el limite y
     * un valor falso si puede ser acomodado en esas posiciones.
     */
    public boolean limitarBarco2(String coordenadasBarco []) {
        String coordenadasAOcupar[];
        boolean limiteExcedido = false;
        String coordenadaColumna1 = null;
        String coordenadaColumna2 = null;
        for (int i = 0; i < coordenadasBarco.length; i++) {
            coordenadasAOcupar = coordenadasBarco[i].split(","); 
            if (i == 0) {
                coordenadaColumna1 = coordenadasAOcupar[0];
            } else {
                coordenadaColumna2 = coordenadasAOcupar[0];
            }
        }
        if (Integer.parseInt(coordenadaColumna1) >= 9 || Integer.parseInt(coordenadaColumna2) > 9) {
            limiteExcedido = true;
        }
        return limiteExcedido;
    }

    /**
     * Permite verificar si el barco de 3 posiciones excede el limite del
     * tablero
     *
     * @param coordenadasBarco las coordenadas generadas donde el barco quiere
     * ser posicionado     
     * @return Valor verdadero si el barco de tres posiciones excede el limite y
     * un valor falso si puede ser acomodado en esas posiciones
     */
    public boolean limitarBarco3(String coordenadasBarco[]) {
        String coordenadasAOcupar[];
        boolean limiteExcedido = false;
        String coordenadaColumna1 = null;
        String coordenadaColumna2 = null;
        String coordenadaColumna3 = null;
        for (int i = 0; i < coordenadasBarco.length; i++) {
            coordenadasAOcupar = coordenadasBarco[i].split(",");
            if (i == 0) {
                coordenadaColumna1 = coordenadasAOcupar[0];
            } else if (i == 1) {
                coordenadaColumna2 = coordenadasAOcupar[0];
            } else if (i == 2) {
                coordenadaColumna3 = coordenadasAOcupar[0];
            }
        }
        if (Integer.parseInt(coordenadaColumna1) >= 9 || Integer.parseInt(coordenadaColumna2) >= 9
                || Integer.parseInt(coordenadaColumna3) > 9) {
            limiteExcedido = true;
        }

        return limiteExcedido;
    }

    /**
     * Permite verificar si los barcos de 5 posiciones exceden el limite del
     * tablero
     *
     * @param coordenadasBarco las coordenadas generadas donde el barco quiere
     * ser posicionado     
     * @return Valor verdadero si el barco de 5 posiciones excede el limite y un
     * valor falso si puede ser acomodado en esas posiciones
     */
    public boolean limitarBarco5(String coordenadasBarco[]) {
        String coordenadasAOcupar[];
        boolean limiteExcedido;
        String coordenadaColumna1 = null;
        String coordenadaColumna2 = null;
        String coordenadaColumna3 = null;
        String coordenadaColumna4 = null;
        String coordenadaColumna5 = null;
        for (int i = 0; i < coordenadasBarco.length; i++) {
            coordenadasAOcupar = coordenadasBarco[i].split(",");
            if (i == 0) {
                coordenadaColumna1 = coordenadasAOcupar[1];
            } else if (i == 1) {
                coordenadaColumna2 = coordenadasAOcupar[1];
            } else if (i == 2) {
                coordenadaColumna3 = coordenadasAOcupar[1];
            } else if (i == 3) {
                coordenadaColumna4 = coordenadasAOcupar[1];
            } else if (i == 4) {
                coordenadaColumna5 = coordenadasAOcupar[1];
            }
        }

        limiteExcedido = verificarLimiteExcedido(coordenadaColumna1, coordenadaColumna2, coordenadaColumna3, coordenadaColumna4, coordenadaColumna5);

        return limiteExcedido;
    }

    /**
     * Método auxiliar para el método limitarBarco5 que ayuda a saber si el
     * barco excede el limite del tablero
     *
     * @param coordenada1 primera coordenada generada del barco
     * @param coordenada2 segunda coordenada generada del barco
     * @param coordenada3 tercera coordenada generada del barco
     * @param coordenada4 cuarta coordenada generada del barco
     * @param coordenada5 quinta coordenada generada del barco
     * @return Valor verdadero si el barco excede el limite del tablero y un
     * valor falso si puede ser acomodado en las coordenadas dadas
     */
    public boolean verificarLimiteExcedido(String coordenada1, String coordenada2, String coordenada3, String coordenada4, String coordenada5) {
        boolean limiteExcedido = false;
        if (Integer.parseInt(coordenada1) >= 9 || Integer.parseInt(coordenada2) >= 9 || Integer.parseInt(coordenada3) >= 9
                || Integer.parseInt(coordenada4) >= 9 || Integer.parseInt(coordenada5) > 9) {
            limiteExcedido = true;
        }
        return limiteExcedido;
    }

    /**
     * Permite revisar si aún existen barcos disponibles para poder seguir
     * jugando
     *
     * @param posicionesASalvo numero que indica si hay más de una casilla
     * que contiene un barco
     * 
     * @return Valo verdadero si no hay más barcos y valor falso si aún quedan
     * disponibles
     */
    public static boolean verificarPosicionesBarcosASalvo(int posicionesASalvo) {
        return posicionesASalvo == 0;
    }

}
