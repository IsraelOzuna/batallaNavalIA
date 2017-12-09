/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Irdevelo
 */
public class Barco {
    
    /**
     *
     * @param posicionColumna
     * @param posicionFila
     * @param tamanoBarco
     * @return
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
     *
     * @param coordenadas
     * @param coordenadasOcupadas
     * @return
     */
    public boolean verificarCoordenadas(String coordenadas[], String coordenadasOcupadas[]) {
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
     *
     * @param letra
     * @return
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
     *
     * @param coordenadasBarco
     * @param longitudCoordenadas
     * @return
     */
    public boolean limitarTablero(String[] coordenadasBarco, int longitudCoordenadas) {
        boolean limiteExcedido = false;
        String coordenadasAOcupar[] = null;

        if (longitudCoordenadas == 2) {
            limiteExcedido = limitarBarco2(coordenadasBarco, coordenadasAOcupar);
        } else if (longitudCoordenadas == 3) {
            limiteExcedido = limitarBarco3(coordenadasBarco, coordenadasAOcupar);
        } else if (longitudCoordenadas == 5) {
            limiteExcedido = limitarBarco5(coordenadasBarco, coordenadasAOcupar);
        }
        return limiteExcedido;
    }

    /**
     *
     * @param coordenadasBarco
     * @param coordenadasAOcupar
     * @return
     */
    public boolean limitarBarco2(String[] coordenadasBarco, String[] coordenadasAOcupar) {
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
     *
     * @param coordenadasBarco
     * @param coordenadasAOcupar
     * @return
     */
    public boolean limitarBarco3(String[] coordenadasBarco, String[] coordenadasAOcupar) {
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
     *
     * @param coordenadasBarco
     * @param coordenadasAOcupar
     * @return
     */
    public boolean limitarBarco5(String[] coordenadasBarco, String[] coordenadasAOcupar) {
        boolean limiteExcedido = false;
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
     *
     * @param coordenada1
     * @param coordenada2
     * @param coordenada3
     * @param coordenada14
     * @param coordenada5
     * @return
     */
    public boolean verificarLimiteExcedido(String coordenada1, String coordenada2, String coordenada3, String coordenada14, String coordenada5) {
        boolean limiteExcedido = false;
        if (Integer.parseInt(coordenada1) >= 9 || Integer.parseInt(coordenada2) >= 9 || Integer.parseInt(coordenada3) >= 9
                || Integer.parseInt(coordenada14) >= 9 || Integer.parseInt(coordenada5) > 9) {
            limiteExcedido = true;
        }
        return limiteExcedido;
    }
}
