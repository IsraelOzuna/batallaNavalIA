package negocio;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Plantilla que contiene diversos métodos utilizados para el funcionamiento de
 * tareas específicas
 *
 * @author Irvin Dereb Vera López.
 * @author Israel Reyes Ozuna.
 */
public class Utileria {

    /**
     * Permite cifrar una contraseña antes de mandarla a la BD.
     *
     * @param contrasena Contraseña del jugador para ingresar al sistema.
     * @return una cadena que representa la ingresada por el usuario pero
     * cifrada 
     * 
     * @throws NoSuchAlgorithmException puede arrojar esta excepción si ocurre
     * un fallo al momento de que el método trata de cifrar la contraseña
     */
    public String cifrarContrasena(String contrasena) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(contrasena.getBytes());
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < hash.length; i++) {
            stringBuilder.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuilder.toString();
    }

    /**
     * Permite validar si el correo ingresado contiene el formato correcto.
     *
     * @param correo Correo electrónico del jugador.
     * @return Un valor verdadero si el correo tiene el formato correcto o un
     * valor falso en caso de lo contrario
     */
    public static boolean validarCorreo(String correo) {
        String formatoCorreo = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern patron = Pattern.compile(formatoCorreo);
        Matcher matcher = patron.matcher(correo);
        return matcher.matches();
    }
}
