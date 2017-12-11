package controlador;

import org.junit.Test;

/**
 *
 * @author iro19
 */
public class VentanaTableroControllerTest {
    
    public VentanaTableroControllerTest() {
    }        
    
    @Test
    public void testGuardarCoordenadas() {        
        String[] coordenadas = {"1,2","2,2"};
        VentanaTableroController instance = new VentanaTableroController();
        instance.guardarCoordenadas(coordenadas);
    }

    @Test
    public void testRegistrarTiroRecibido() {        
        String tiroRecibido = "3,3";
        VentanaTableroController instance = new VentanaTableroController();
        instance.registrarTiroRecibido(tiroRecibido);
    }             
}
