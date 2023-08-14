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
    private void switchtoParalelos(ActionEvent event)throws IOException  {
        App.setRoot("visualizarParalelos");
    }
    @FXML
    private void switchtoMaterias(ActionEvent event)throws IOException  {
        App.setRoot("visualizarMaterias");
    }
}
