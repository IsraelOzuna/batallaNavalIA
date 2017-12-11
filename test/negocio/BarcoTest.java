package negocio;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author iro19
 */
public class BarcoTest {

    public BarcoTest() {
    }

    @Test
    public void testGenerarCoordenadas() {
        int posicionColumna = 2;
        int posicionFila = 5;
        int tamanoBarco = 2;
        Barco instance = new Barco();
        String[] expResult = {"2,5", "3,5"};
        String[] result = instance.generarCoordenadas(posicionColumna, posicionFila, tamanoBarco);
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testVerificarCoordenadasLibres() {
        String[] coordenadas = {"B,2", "B,3", "B,4", "B,5", "B,6"};
        String[] coordenadasOcupadas = {"A,1", "B,1", "C,1", "D,1", "E,1", "F,1"};
        Barco instance = new Barco();
        boolean expResult = true;
        boolean result = instance.verificarCoordenadasLibres(coordenadas, coordenadasOcupadas);
        assertEquals(expResult, result);
    }

    @Test
    public void testVerificarCoordenadasLibresFallido() {
        String[] coordenadas = {"B,1", "B,2", "B,3", "B,4", "B,5"};
        String[] coordenadasOcupadas = {"A,1", "B,1", "C,1", "D,1", "E,1", "F,1"};
        Barco instance = new Barco();
        boolean expResult = false;
        boolean result = instance.verificarCoordenadasLibres(coordenadas, coordenadasOcupadas);
        assertEquals(expResult, result);
    }

    @Test
    public void testConvertirLetrasANumeros() {
        String letra = "A";
        Barco instance = new Barco();
        int expResult = 0;
        int result = instance.convertirLetrasANumeros(letra);
        assertEquals(expResult, result);
    }

    @Test
    public void testLimitarTablero() {
        String[] coordenadasBarco = {"1,A", "1,B", "1,C"};
        int longitudBarco = 3;
        Barco instance = new Barco();
        boolean expResult = false;
        boolean result = instance.limitarTablero(coordenadasBarco, longitudBarco);
        assertEquals(expResult, result);
    }

    @Test
    public void testLimitarTableroFallido() {
        String[] coordenadasBarco = {"8,A", "9,B", "10,C"};
        int longitudBarco = 3;
        Barco instance = new Barco();
        boolean expResult = true;
        boolean result = instance.limitarTablero(coordenadasBarco, longitudBarco);
        assertEquals(expResult, result);
    }

    @Test
    public void testLimitarBarco2() {
        String[] coordenadasBarco = {"0,1", "1,1"};        
        Barco instance = new Barco();
        boolean expResult = false;
        boolean result = instance.limitarBarco2(coordenadasBarco);
        assertEquals(expResult, result);
    }

    @Test
    public void testLimitarBarco2Excedio() {
        String[] coordenadasBarco = {"9,0", "10,0"};        
        Barco instance = new Barco();
        boolean expResult = true;
        boolean result = instance.limitarBarco2(coordenadasBarco);
        assertEquals(expResult, result);
    }

    @Test
    public void testLimitarBarco3() {
        String[] coordenadasBarco = {"3,5", "4,5", "5,5"};        
        Barco instance = new Barco();
        boolean expResult = false;
        boolean result = instance.limitarBarco3(coordenadasBarco);
        assertEquals(expResult, result);
    }

    @Test
    public void testLimitarBarco3Excedio() {
        String[] coordenadasBarco = {"8,5", "9,5", "10,5"};        
        Barco instance = new Barco();
        boolean expResult = true;
        boolean result = instance.limitarBarco3(coordenadasBarco);
        assertEquals(expResult, result);
    }

    @Test
    public void testLimitarBarco5() {
        String[] coordenadasBarco = {"0,1", "0,2", "0,3", "0,4", "0,5"};        
        Barco instance = new Barco();
        boolean expResult = false;
        boolean result = instance.limitarBarco5(coordenadasBarco);
        assertEquals(expResult, result);
    }

    @Test
    public void testLimitarBarco5Excedio() {
        String[] coordenadasBarco = {"6,1", "7,2", "8,3", "9,4", "10,5"};        
        Barco instance = new Barco();
        boolean expResult = false;
        boolean result = instance.limitarBarco5(coordenadasBarco);
        assertEquals(expResult, result);
    }

    @Test
    public void testVerificarLimiteExcedido() {
        String coordenada1 = "2";
        String coordenada2 = "4";
        String coordenada3 = "3";
        String coordenada4 = "7";
        String coordenada5 = "10";
        Barco instance = new Barco();
        boolean expResult = true;
        boolean result = instance.verificarLimiteExcedido(coordenada1, coordenada2, coordenada3, coordenada4, coordenada5);
        assertEquals(expResult, result);
    }

    @Test
    public void testVerificarLimiteExcedidoFallido() {
        String coordenada1 = "2";
        String coordenada2 = "4";
        String coordenada3 = "3";
        String coordenada4 = "7";
        String coordenada5 = "9";
        Barco instance = new Barco();
        boolean expResult = false;
        boolean result = instance.verificarLimiteExcedido(coordenada1, coordenada2, coordenada3, coordenada4, coordenada5);
        assertEquals(expResult, result);
    }

    @Test
    public void testVerificarPosicionesBarcosASalvo() {
        int posicionesASalvo = 0;
        boolean expResult = true;
        boolean result = Barco.verificarPosicionesBarcosASalvo(posicionesASalvo);
        assertEquals(expResult, result);
    }

    @Test
    public void testVerificarPosicionesBarcosASalvoFallido() {
        int posicionesASalvo = 3;
        boolean expResult = false;
        boolean result = Barco.verificarPosicionesBarcosASalvo(posicionesASalvo);
        assertEquals(expResult, result);
    }
}
