/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.poo.proyectojar;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import espol.poo.modelo.academico.Materia;
import espol.poo.modelo.juego.Pregunta;
import espol.poo.modelo.juego.Respuesta;
import espol.poo.modelo.juego.TipoRespuesta;
import java.util.*;
import javafx.scene.control.Button;
import espol.poo.modelo.juego.NivelPregunta;

/**
 *
 * @author joshz
 */
public class preguntaMenuController implements Initializable {
    @FXML
    private TextField txtPregunta;
    @FXML
    private TextField txtCorrecta;
    @FXML
    private TextField txtIncorrecta1;
    @FXML
    private TextField txtIncorrecta2;
    @FXML
    private TextField txtIncorrecta3;
    @FXML
    private ComboBox cmbMateria;
    @FXML
    private TextField txtnivel;
    @FXML
    private Button btnguardar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarComboBox();
    }
    public void cargarComboBox(){
        cmbMateria.getItems().setAll(Materia.materias);
    }
    @FXML
    private void agregarPregunta(ActionEvent event) throws IOException{
        if("".equals(txtPregunta.getText())||"".equals(txtCorrecta.getText())||"".equals(txtIncorrecta1.getText())||"".equals(txtIncorrecta2.getText())||"".equals(txtIncorrecta3.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Falta uno o mas atributos en la pregunta");
            alert.showAndWait();
            
        } else {
            ArrayList<Respuesta> answers =new ArrayList<>(List.of(
            new Respuesta(txtCorrecta.getText(),TipoRespuesta.CORRECTA),
            new Respuesta(txtIncorrecta1.getText(),TipoRespuesta.INCORRECTA),
            new Respuesta(txtIncorrecta2.getText(),TipoRespuesta.INCORRECTA),
            new Respuesta(txtIncorrecta3.getText(),TipoRespuesta.INCORRECTA)));
            int numeronivel = Integer.parseInt(txtnivel.getText());
            Materia m=(Materia) cmbMateria.getValue();
            Pregunta.agregarPregunta(new Pregunta(txtPregunta.getText(),numeronivel,m,answers));
            App.setRoot("ConfPreguntas");

        }
    }
    @FXML
    private void switchToPrimary(ActionEvent event) throws IOException{
        App.setRoot("ConfPreguntas");
    }
}
