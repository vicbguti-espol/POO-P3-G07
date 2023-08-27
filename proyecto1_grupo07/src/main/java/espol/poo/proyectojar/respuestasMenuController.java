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
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import espol.poo.modelo.juego.Respuesta;
import espol.poo.modelo.juego.TipoRespuesta;

/**
 *
 * @author joshz
 */
public class respuestasMenuController implements Initializable{
    @FXML
    private Text txtrc;
    @FXML
    private Text txtri1;
    @FXML
    private Text txtri2;
    @FXML
    private Text txtri3;
    @FXML
    private Text txtpregunta;
    @FXML
    private Button btnSalir;
    
    @FXML
    private void switchToPrimary(ActionEvent event) throws IOException{
        App.setRoot("ConfPreguntas");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        txtpregunta.setText(ConfPreguntasController.preguntaEditable.getTexto());
        ArrayList<Respuesta> answers = ConfPreguntasController.preguntaEditable.getRespuestas();
        for (Respuesta respuesta : answers) {
                if (respuesta.getTipo() == TipoRespuesta.CORRECTA) {
                    txtrc.setText(respuesta.getTexto());
                } else if (txtri1.getText().isEmpty()) {
                    txtri1.setText(respuesta.getTexto());
                } else if (txtri2.getText().isEmpty()) {
                    txtri2.setText(respuesta.getTexto());
                } else if (txtri3.getText().isEmpty()) {
                    txtri3.setText(respuesta.getTexto());
                }
            }
    }
}
