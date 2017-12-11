package negocio;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iro19
 */
public class UtileriaTest {
    
    public UtileriaTest() {
    }
       
    @Test
    public void testCifrarContrasena() throws Exception {        
        String contrasena = "israel124";
        Utileria instance = new Utileria();
        String expResult = "d6f581d378718452812449e3b5bcea04d2fcf2aafcd4f1a598d4e016c0eecb1c";
        String result = instance.cifrarContrasena(contrasena);
        assertEquals(expResult, result);        
    }        

    @Test
    public void testValidarCorreo() {        
        String correo = "iro1904@hotmail.com";
        boolean expResult = true;
        boolean result = Utileria.validarCorreo(correo);
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testValidarCorreoFallido() {        
        String correo = "iro1904@@@h,.aipu.otmail.com";
        boolean expResult = false;
        boolean result = Utileria.validarCorreo(correo);
        assertEquals(expResult, result);        
    }
}
