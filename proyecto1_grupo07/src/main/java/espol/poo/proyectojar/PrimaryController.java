package espol.poo.proyectojar;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PrimaryController {
    @FXML
    private void switchtoNuevoJuego(ActionEvent event)throws IOException  {
        App.setRoot("nuevoJuego");
    }
    
    @FXML
    private void switchToConfig(ActionEvent event) throws IOException {
        App.setRoot("config");
    }
    
    @FXML
    private void switchToReporte(ActionEvent event) throws IOException {
        App.setRoot("reporte");
    }
}
