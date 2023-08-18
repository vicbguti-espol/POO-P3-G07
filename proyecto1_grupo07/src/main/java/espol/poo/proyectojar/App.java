package espol.poo.proyectojar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import modelo.academico.TerminoAcademico;

import modelo.juego.Juego;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    
    
    public static int añoActual = 2023;
    public static Juego juego;
    public static TerminoAcademico terminoJuego = new TerminoAcademico(); 
    
    public static int tiempoJuego = 60;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    //metodo para cambiar el contenido de la escena
    public static void changeRoot(Parent rootNode) {
        scene.setRoot(rootNode);
    }

    public static void main(String[] args) {
        launch();
    }

}