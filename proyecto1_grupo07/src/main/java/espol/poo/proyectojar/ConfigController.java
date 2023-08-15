package espol.poo.proyectojar;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ConfigController {
    @FXML
    private void switchtoParalelos(ActionEvent event)throws IOException  {
        App.setRoot("visualizarParalelos");
    }
    
    @FXML
    private void switchtoMaterias(ActionEvent event)throws IOException  {
        App.setRoot("visualizarMaterias");
    }
    
    @FXML
    private void switchToPrimary(ActionEvent event) throws IOException{
        App.setRoot("primary");
    }
}

