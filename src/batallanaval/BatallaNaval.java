/*--------------------------------------------------------*/
/*                  Batalla naval tuerta
    @version 1.0 / 11 de diciembre de 2017
    Desarrolladores: Irvin Dereb Vera López
                     Israel Reyes Ozuna
    Descripción: El juego presentado a continuación es una de
                 las variates del famoso juego "Batalla naval”
                 en el cual dos jugadores se enfrentaran hasta
                 que alguno logre derribar todos los barcos del 
                 rival pero con la dificultad de que nunca sabrá
                 si le ha dado a un barco hasta que termine con
                 todos                 
/*--------------------------------------------------------*/      

package batallanaval;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Irdevelo
 */
public class BatallaNaval extends Application {
    
    @Override
    public void start(Stage ventanaIP) throws IOException {
        ResourceBundle idioma = ResourceBundle.getBundle("recursos.idioma_es_MX");
        Parent root = FXMLLoader.load(getClass().getResource("/vista/VentanaPeticionIP.fxml"),idioma);
        
        Scene scene = new Scene(root);
        
        ventanaIP.setScene(scene);
        ventanaIP.initStyle(StageStyle.UNDECORATED);
        ventanaIP.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args); 
    }
    
}
