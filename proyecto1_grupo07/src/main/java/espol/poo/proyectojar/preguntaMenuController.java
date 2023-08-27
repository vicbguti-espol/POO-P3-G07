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
import modelo.academico.Materia;
import modelo.juego.Pregunta;
import modelo.juego.Respuesta;
import modelo.juego.TipoRespuesta;
import java.util.*;
import javafx.scene.control.Button;
import modelo.juego.NivelPregunta;

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
        if(ConfPreguntasController.preguntaEditable!=null) {
            ArrayList<Respuesta> respuestaspregunta = ConfPreguntasController.preguntaEditable.getRespuestas();
            txtPregunta.setText(ConfPreguntasController.preguntaEditable.getTexto());
            txtPregunta.setEditable(false);
            for (Respuesta respuesta : respuestaspregunta) {
                if (respuesta.getTipo() == TipoRespuesta.CORRECTA) {
                    txtCorrecta.setText(respuesta.getTexto());
                } else if (txtIncorrecta1.getText().isEmpty()) {
                    txtIncorrecta1.setText(respuesta.getTexto());
                } else if (txtIncorrecta2.getText().isEmpty()) {
                    txtIncorrecta2.setText(respuesta.getTexto());
                } else if (txtIncorrecta3.getText().isEmpty()) {
                    txtIncorrecta3.setText(respuesta.getTexto());
                }
            }
            
            
        }
    }
    public void cargarComboBox(){
        cmbMateria.getItems().setAll(Materia.materias);
    }
    @FXML
    private void agregarPregunta(ActionEvent event) throws IOException{
        if("".equals(txtCorrecta.getText())||"".equals(txtIncorrecta1.getText())||"".equals(txtIncorrecta2.getText())||"".equals(txtIncorrecta3.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Falta uno o mas atributos en la pregunta");
            alert.showAndWait();
            if(ConfPreguntasController.preguntaEditable==null){
                ArrayList<Respuesta> answers =new ArrayList<>(List.of(
                new Respuesta(txtCorrecta.getText(),TipoRespuesta.CORRECTA),
                new Respuesta(txtIncorrecta1.getText(),TipoRespuesta.INCORRECTA),
                new Respuesta(txtIncorrecta2.getText(),TipoRespuesta.INCORRECTA),
                new Respuesta(txtIncorrecta3.getText(),TipoRespuesta.INCORRECTA)));
                int numeronivel = Integer.parseInt(txtnivel.getText());
                String mnombre = (String) cmbMateria.getValue();
                Pregunta newP = null;
                for (Materia i : Materia.materias){
                    if (i.getNombre().equals(mnombre)){
                        newP = new Pregunta(txtPregunta.getText(),numeronivel,i,answers);
                    }
                }
                // newP es la pregunta a agregar
                Pregunta.agregarPregunta(newP);
            }
        } else {
            if(ConfPreguntasController.preguntaEditable==null){
                ArrayList<Respuesta> answers =new ArrayList<>(List.of(
                new Respuesta(txtCorrecta.getText(),TipoRespuesta.CORRECTA),
                new Respuesta(txtIncorrecta1.getText(),TipoRespuesta.INCORRECTA),
                new Respuesta(txtIncorrecta2.getText(),TipoRespuesta.INCORRECTA),
                new Respuesta(txtIncorrecta3.getText(),TipoRespuesta.INCORRECTA)));
                int numeronivel = Integer.parseInt(txtnivel.getText());
                String mnombre = (String) cmbMateria.getValue();
                Pregunta newP = null;
                for (Materia i : Materia.materias){
                    if (i.getNombre().equals(mnombre)){
                        newP = new Pregunta(txtPregunta.getText(),numeronivel,i,answers);
                    }
                }
                // newP es la pregunta a agregar
                Pregunta.agregarPregunta(newP);
            }
        }
    }
    @FXML
    private void switchToPrimary(ActionEvent event) throws IOException{
        App.setRoot("ConfPreguntas");
    }
}
