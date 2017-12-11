package negocio;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iro19
 */
public class JugadorTest {
    
    public JugadorTest() {
    }
   
    @Test
    public void testVerificarNombreUsuarioCorrecto() {        
        String nombreJugador = "isrozuna";
        boolean expResult = true;
        boolean result = Jugador.verificarNombreUsuarioCorrecto(nombreJugador);
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testVerificarNombreUsuarioCorrectoFallido() {        
        String nombreJugador = "isro zuna";
        boolean expResult = false;
        boolean result = Jugador.verificarNombreUsuarioCorrecto(nombreJugador);
        assertEquals(expResult, result);        
    }
    
}
