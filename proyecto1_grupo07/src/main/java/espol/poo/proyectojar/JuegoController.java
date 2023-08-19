/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo.proyectojar;

import espol.poo.proyectojar.App;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import modelo.juego.Comodin;
import modelo.juego.Juego;
import modelo.juego.NivelPregunta;
import modelo.juego.Pregunta;
import modelo.juego.Respuesta;
import modelo.juego.TipoRespuesta;

/**
 * FXML Controller class
 *
 * @author Omen
 */
public class JuegoController {

    @FXML
    private Label lblTemp;
    /**
     * Initializes the controller class.
     */
    @FXML
    private Label lblPregunta;
    @FXML
    private RadioButton  rbA;
    @FXML
    private RadioButton  rbB;
    @FXML
    private RadioButton  rbC;
    @FXML
    private RadioButton  rbD;
    @FXML
    private HBox hbCont;
    
    // Declarar input para recibir respuesta 
    int tTranscurrido;
    Integer correctas = 0; 
    
    Integer indNivel = 0;
    Integer indPregunta = 0;
    
    Respuesta r = new Respuesta();
    Boolean respuestaCorrecta;
    
    ArrayList<NivelPregunta> preguntasPerLvl;
    
    @FXML
    public void initialize() throws InterruptedException {
        buildJuego();
    }
    
    
    private class Manejador implements EventHandler<Event>{
        @Override
        public void handle(Event e){
            indPregunta++;
            System.out.println("Corriendo click");
            // buildJuego();
            actualizar();
        }
    }
    
    public void actualizar(){
        // similar a buildJuego
    }
    
    public void comodin50(){
        // Una vez clickeado el comodín
        // borrar botones
    }
    
    public void comodinPublico(){
        // alerta
    }
    
    public void comodinCompañero(){
        // alerta
        // pregúntale al compañero: juego.getApoyo() [botón]
    }
    
    public void crearPanel(){
        // Panel
    }
    
    public void buildJuego(){
        respuestaCorrecta = r.getTipo().equals(TipoRespuesta.CORRECTA);
        tTranscurrido = App.tiempoJuego;
        
        // Obtener preguntas por nivel
        preguntasPerLvl = getArrayNivelPregunta();
        
        Pregunta pregunta = preguntasPerLvl.get(indNivel).getPreguntas().get(indPregunta);
        buildPregunta(pregunta);
        
        Button btnContinuar = new Button("Continuar");
        btnContinuar.setOnMouseClicked(e ->  new Manejador());
        hbCont.getChildren().add(btnContinuar);
        
        // Instanciar temporizador
        Temporizador temp = new Temporizador();
        temp.setDaemon(true);
        temp.start();
                
        
    }
    
    public ArrayList<NivelPregunta> getArrayNivelPregunta(){
        ArrayList<NivelPregunta> arrayNivelPregunta = new ArrayList<>();
        Map<Integer, ArrayList<Pregunta>> preguntasPerLvl = App.juego.
                getPreguntasByLevel(App.juego.getnPreguntasPerLvl());
        
        for (Map.Entry<Integer, ArrayList<Pregunta>> entry : preguntasPerLvl.
                entrySet()) {
            arrayNivelPregunta.add(new NivelPregunta(entry.getKey(), 
                    entry.getValue()));
        }
        
        return arrayNivelPregunta;
    }
    
    /**
     * Construir el hbox con el botón continuar o con el textfield del premio
     * @param premio
     * @param nextPregunta 
     */
    public void buildContinuar(boolean premio, Pregunta nextPregunta){
        hbCont.getChildren().clear();
        
        TextField txtPremio = new TextField();
        Button btnContinuar = new Button("Continuar");
        
        if (premio){
            // Manejar el evento para guardar premio
            btnContinuar.setOnMouseClicked((e) -> 
            {  
                App.juego.setPremio(txtPremio.getText());
            });
            // Agregar textfield y botón
            hbCont.getChildren().addAll(txtPremio, btnContinuar);
        } else{
            btnContinuar.setOnMouseClicked(e -> buildPregunta(nextPregunta));
        }
        
        hbCont.getChildren().add(btnContinuar);
        
    }
    
    public void buildPregunta(Pregunta p){
        // Construir la estructura de pregunta con sus posibles respuestas
        lblPregunta.setText(p.getTexto());
        
        // Shuffle de respuestas de la pregunta
        Collections.shuffle(p.getRespuestas());
        
        // Construir respuestas de la pregunta
        buildRespuestas(p);
                        
        
    }
    
    public void buildRespuestas(Pregunta p){
        // Construcción de RadioButton
        rbA.setOnMouseClicked(e -> r = p.getRespuestas().get(0));
        rbB.setOnMouseClicked(e -> r = p.getRespuestas().get(1));
        rbC.setOnMouseClicked(e -> r = p.getRespuestas().get(2));
        rbD.setOnMouseClicked(e -> r = p.getRespuestas().get(3));
        
        rbA.setText("A) " + p.getRespuestas().get(0));
        rbB.setText("B) " + p.getRespuestas().get(1));
        rbC.setText("C) " + p.getRespuestas().get(2));
        rbD.setText("D) " + p.getRespuestas().get(3));
    }
    
    class Temporizador extends Thread {
        boolean progression = true;
        
        @Override
        public void run(){
            while (progression){
                // Dormir thread cada segundo
                try{
                    Thread.sleep(1000);
                } catch(InterruptedException i){
                    i.printStackTrace();
                }
                
                decrementador();
            }
        }
        
        public void decrementador(){
            // Decrementar temporizador
            tTranscurrido--;
            // Cambiar el tiempo en el label
            Platform.runLater(() ->
                lblTemp.setText(String.valueOf(tTranscurrido)));
            
            
            if(tTranscurrido == 0 ){
                // terminar y quieres pasar a la siguiente preguntaa
                // terminar una vez que continuar
                progression = false;
                
                Platform.runLater(() -> {
                    terminarJuego();
                });
            }
        }
        
        public void terminarJuego(){
            // Mostrar alerta de agregación exitosa
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Resultado de la operación");
            alert.setContentText("Juego Terminado");
            
            alert.showAndWait();
            // Cambiar a la pantalla main de términos
            try{
                App.setRoot("primary");
            } catch(IOException e){
                System.out.println(e);
            }
        }
            
    }
    }
    
    
    

