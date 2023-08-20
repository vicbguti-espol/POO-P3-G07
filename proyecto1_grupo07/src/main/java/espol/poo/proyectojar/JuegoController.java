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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
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
    private BorderPane bpPrincipal;
    @FXML
    private Label lblPregunta;
    @FXML
    private RadioButton  rbA;
    @FXML
    private RadioButton  rbB;
    @FXML
    private ImageView  comodin50;
    @FXML
    private ImageView  comodinCompañero;
    @FXML
    private ImageView  comodinPublico;
    @FXML
    private RadioButton  rbC;
    @FXML
    private RadioButton  rbD;
    @FXML
    private HBox hbCont;
    @FXML
    private ListView lvPreguntas;
    
    // Declarar input para recibir respuesta 
    int tTranscurrido;
    Integer correctas = 0; 
    
    Integer indNivel = 0;
    Integer indPregunta = 0;
    
    Respuesta r = new Respuesta();
    Pregunta pregunta=new Pregunta();
    Boolean respuestaCorrecta;
    
    ArrayList<NivelPregunta> preguntasPerLvl;
    
    String comodinesUsados=null;
    
    @FXML
    public void initialize() throws InterruptedException {
        buildJuego();
        comodin50.setOnMouseClicked(e->{
           comodin50(); 
        });
        comodinCompañero.setOnMouseClicked(e->{
           comodinCompañero(); 
        });
        comodinPublico.setOnMouseClicked(e->{
           comodinPublico(); 
        });
    }

    private class Manejador implements EventHandler<Event>{
        @Override
        public void handle(Event e){
            indPregunta++;
            lvPreguntas.getSelectionModel().select(indPregunta+(indNivel*NuevoJuegoController.cantPregNivSeleccionado));
            System.out.println("Corriendo click");
            // buildJuego();
            actualizar();
        }
    }
    
    public void actualizar(){
        // similar a buildJuego
    }
    /**
     * Actualiza la pregunta en los componentes FXML para eliminar dos respuesta incorrectas
     * 
     */
    public void actualizar50(){
        Pregunta pregunta50 = new Pregunta(pregunta.getTexto(),pregunta.getNivel(),pregunta.getMateria(),pregunta.getRespuestas());
        Collections.shuffle(pregunta50.getRespuestas());
        for(int i=0;i<2;i++){
            Respuesta respuestaindice=pregunta50.getRespuestas().get(i);
            if(respuestaindice.getTipo().equals(TipoRespuesta.CORRECTA)){
                i--;
            }
            else{
               respuestaindice.setTexto(""); 
            }
        }
        buildPregunta(pregunta50);
    }
    /**
     * Constructor de cuadros de dialogos para la selección de comodines
     * @param tipo
     **/
    public void notificacionComodin(String tipo){
        Alert comodinUsado=new Alert(AlertType.INFORMATION);
        if(tipo.equals("50")) {
            comodinUsado.setTitle("50 probabilidades de ganar!");
            comodinUsado.setContentText("No lo arruines ahora...");
        }else if(tipo.equals("publico")){
            comodinUsado.setTitle("Hora de preguntarle al público!");
            comodinUsado.setContentText("¿Cual cree el curso que será la respuesta?");
        }else{
            comodinUsado.setTitle("Hora de preguntarle al compañero de apoyo");
            comodinUsado.setContentText("¿Cual cree "+ NuevoJuegoController.apoyoSeleccionado.getNombre()+" que será la respuesta?");
        }
        comodinUsado.showAndWait();
    }
    /**
     * Comodin 50/50
     * Envia al constructor de dialogo y deshabilita el comodin
     * Tambien registra su uso en la variable comodinesUsados
     **/
    public void comodin50(){
        notificacionComodin("50");
        ColorAdjust grayscale= new ColorAdjust();
        grayscale.setSaturation(-1);
        comodin50.setEffect(grayscale);
        comodin50.setMouseTransparent(true);
        actualizar50();
        comodinesUsados+=" Usó el comodin 50/50 en el nivel: "+indNivel+" y la pregunta: "+indPregunta+". \n";
        
    }
    /**
     * Comodin Publico
     * Envia un cuadro de dialogo que incia la consulta al curso.
     **/
    public void comodinPublico(){
        notificacionComodin("publico");
        ColorAdjust grayscale= new ColorAdjust();
        grayscale.setSaturation(-1);
        comodinPublico.setEffect(grayscale);
        comodinPublico.setMouseTransparent(true);
        comodinesUsados+=" Usó el comodin de curso en el nivel: "+indNivel+" y la pregunta: "+indPregunta+". \n";
    }
    /**
     * Comodin Compañero
     * Envia un cuadro de dialogo que incia la consulta con el nombre del participante de apoyo seleccionado.
     **/
    public void comodinCompañero(){
        notificacionComodin("compañero");
        ColorAdjust grayscale= new ColorAdjust();
        grayscale.setSaturation(-1);
        comodinCompañero.setEffect(grayscale);
        comodinCompañero.setMouseTransparent(true);
        comodinesUsados+=" Usó el comodin de compañero "+NuevoJuegoController.apoyoSeleccionado.getNombre()+" en el nivel: "+indNivel+" y la pregunta: "+indPregunta+". \n";
    }

    /**
     * crearPanel
     * Usando el TableView de Preguntas, se cargan las preguntas en base a la cantidad de preguntas por nivel seleccionado por el usuario usando un ObservableArrayList
     **/
    public void crearPanel(){
        ObservableList<String> listaPreguntas=FXCollections.observableArrayList();
        for(NivelPregunta np:preguntasPerLvl){
            for(Pregunta p: np.getPreguntas()){
                System.out.println("a"+np.getPreguntas().indexOf(p));
                listaPreguntas.add("Pregunta "+String.valueOf(np.getPreguntas().indexOf(p)+1));
            }
        }
        lvPreguntas.setItems(listaPreguntas);
        lvPreguntas.getSelectionModel().select(0);
        lvPreguntas.setMouseTransparent(true);
    }
    
    public void buildJuego(){
        respuestaCorrecta = r.getTipo().equals(TipoRespuesta.CORRECTA);
        tTranscurrido = App.tiempoJuego;
        
        // Obtener preguntas por nivel
        preguntasPerLvl = getArrayNivelPregunta();
        crearPanel();
        
        pregunta = preguntasPerLvl.get(indNivel).getPreguntas().get(indPregunta);
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
    
    
    

