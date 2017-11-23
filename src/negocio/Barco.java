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
            default:
                break;

        }

        return numeroConvertido;
    }

    public boolean limitarTablero(String[] coordenadasBarco, int longitudCoordenadas) {
        boolean limiteExcedido = false ;
        String coordenadasAOcupar[] = null;

        switch (longitudCoordenadas) {

            case 2:
                limiteExcedido = limitarBarco2(coordenadasBarco, coordenadasAOcupar);
                break;

            case 3:
                limiteExcedido = limitarBarco3(coordenadasBarco, coordenadasAOcupar);
                break;

            case 5:
                limiteExcedido = limitarBarco5(coordenadasBarco, coordenadasAOcupar);
                break;

            default:
                break;
        }

        return limiteExcedido;
    }

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

    public boolean limitarBarco3(String[] coordenadasBarco, String[] coordenadasAOcupar) {
        boolean limiteExcedido = false;
        String coordenadaColumna1 = null;
        String coordenadaColumna2 = null;
        String coordenadaColumna3 = null;
        for (int i = 0; i < coordenadasBarco.length; i++) {
            coordenadasAOcupar = coordenadasBarco[i].split(",");
            switch (i) {
                case 0:
                    coordenadaColumna1 = coordenadasAOcupar[0];
                    break;
                case 1:
                    coordenadaColumna2 = coordenadasAOcupar[0];
                    break;
                case 2:
                    coordenadaColumna3 = coordenadasAOcupar[0];
                    break;
                default:
                    break;
            }
        }
        if (Integer.parseInt(coordenadaColumna1) >= 9 || Integer.parseInt(coordenadaColumna2) >= 9
                || Integer.parseInt(coordenadaColumna3) > 9) {
            limiteExcedido = true;
        }

        return limiteExcedido;
    }

    public boolean limitarBarco5(String[] coordenadasBarco, String[] coordenadasAOcupar) {
        boolean limiteExcedido = false;
        String coordenadaColumna1 = null;
        String coordenadaColumna2 = null;
        String coordenadaColumna3 = null;
        String coordenadaColumna4 = null;
        String coordenadaColumna5 = null;
        for (int i = 0; i < coordenadasBarco.length; i++) {
            coordenadasAOcupar = coordenadasBarco[i].split(",");
            switch (i) {
                case 0:
                    coordenadaColumna1 = coordenadasAOcupar[1];
                    break;
                case 1:
                    coordenadaColumna2 = coordenadasAOcupar[1];
                    break;
                case 2:
                    coordenadaColumna3 = coordenadasAOcupar[1];
                    break;
                case 3:
                    coordenadaColumna4 = coordenadasAOcupar[1];
                    break;
                case 4:
                    coordenadaColumna5 = coordenadasAOcupar[1];
                    break;
                default:
                    break;
            }
        }

        if (Integer.parseInt(coordenadaColumna1) >= 9 || Integer.parseInt(coordenadaColumna2) >= 9
                || Integer.parseInt(coordenadaColumna3) >= 9 || Integer.parseInt(coordenadaColumna4) >= 9
                || Integer.parseInt(coordenadaColumna5) > 9) {
            limiteExcedido = true;
        }
        return limiteExcedido;
    }

}
