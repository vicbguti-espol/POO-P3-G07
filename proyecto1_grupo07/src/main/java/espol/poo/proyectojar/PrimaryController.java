package espol.poo.proyectojar;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

/**
 *
 * @author Omen
 */
public class PrimaryController {

    @FXML
    private Button btnJugar;
    
    @FXML
    private void initialize(){
        // Manejar evento de botón Jugar
        btnJugar.setOnMouseClicked(e -> nuevoJuego());
    }
    
    private void switchtoNuevoJuego() {
        try {
            App.setRoot("nuevoJuego");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void switchToConfig(ActionEvent event) throws IOException {
        App.setRoot("config");
    }
    
    @FXML
    private void switchToReporte(ActionEvent event) throws IOException {
        App.setRoot("reporte");
    }
    
    /**
     * Cambiar a configuraciones de nuevo juego
     */
    public void nuevoJuego(){

        if (App.terminoJuego != null){
            // Cambiar a reporte
            switchtoNuevoJuego();
        } else{
            // Indicar al usuario que no se cuenta con término académico para el juego
            terminoNoSeleccionado();
        }
        
    }
    
    /**
     * Inidicar al usuario que no existe término para el juego
     */
    public void terminoNoSeleccionado(){
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Término académico no encontrado");
        error.setHeaderText("Término académico no seleccionado");
        error.setContentText("Seleccionar término para juego\nen configuración de términos académicos");
        error.showAndWait();
    }
}
