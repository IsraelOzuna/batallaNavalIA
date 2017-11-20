/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author iro19
 */
public class Barco {    
    
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

    public boolean verificarCoordenadas(String coordenadas[], String coordenadasOcupadas[]) {
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

    public boolean limitarTablero(String[] coordenadasBarco, int longitudCoordenadas) {
        boolean limiteExcedido = false;
        String coordenadaColumna1 = null;
        String coordenadaColumna2 = null;
        String coordenadaColumna3 = null;
        String coordenadaColumna4 = null;
        String coordenadaColumna5 = null;

        String aux[];

        switch (longitudCoordenadas) {

            case 2:
                for (int i = 0; i < coordenadasBarco.length; i++) {
                    aux = coordenadasBarco[i].split(",");
                    if (i == 0) {
                        coordenadaColumna1 = aux[0];
                    } else {
                        coordenadaColumna2 = aux[0];
                    }
                }
                if (Integer.parseInt(coordenadaColumna1) >= 9 || Integer.parseInt(coordenadaColumna2) > 9) {
                    limiteExcedido = true;
                }
                break;

            case 3:
                for (int i = 0; i < coordenadasBarco.length; i++) {
                    aux = coordenadasBarco[i].split(",");
                    if (i == 0) {
                        coordenadaColumna1 = aux[0];
                    } else if (i == 1) {
                        coordenadaColumna2 = aux[0];
                    } else if (i == 2) {
                        coordenadaColumna3 = aux[0];
                    }
                }
                if (Integer.parseInt(coordenadaColumna1) >= 9 || Integer.parseInt(coordenadaColumna2) >= 9
                        || Integer.parseInt(coordenadaColumna3) > 9) {
                    limiteExcedido = true;
                }
                break;

            case 5:
                for (int i = 0; i < coordenadasBarco.length; i++) {
                    aux = coordenadasBarco[i].split(",");
                    if (i == 0) {
                        coordenadaColumna1 = aux[1];
                    } else if (i == 1) {
                        coordenadaColumna2 = aux[1];
                    } else if (i == 2) {
                        coordenadaColumna3 = aux[1];
                    } else if (i == 3) {
                        coordenadaColumna4 = aux[1];
                    } else if (i == 4) {
                        coordenadaColumna5 = aux[1];
                    }
                }

                if (Integer.parseInt(coordenadaColumna1) >= 9 || Integer.parseInt(coordenadaColumna2) >= 9
                        || Integer.parseInt(coordenadaColumna3) >= 9 || Integer.parseInt(coordenadaColumna4) >= 9
                        || Integer.parseInt(coordenadaColumna5) > 9) {
                    limiteExcedido = true;
                }

                break;
        }

        return limiteExcedido;
    }

}
