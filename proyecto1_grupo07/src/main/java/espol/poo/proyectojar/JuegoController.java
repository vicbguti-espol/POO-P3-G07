/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo.proyectojar;

import espol.poo.proyectojar.App;
import espol.poo.proyectojar.utilitaria.JuegoUtilitaria;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
    
    public Integer indNivel = 0;
    public Integer indPregunta = 0;
    
    Respuesta r = new Respuesta();
    Pregunta pregunta=new Pregunta();
    Boolean respuestaCorrecta;
    
    public ArrayList<NivelPregunta> preguntasPerLvl;
    Button btnContinuar = new Button("Continuar");
    
    String comodinesUsados="";
    
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
    
    public void buildJuego(){
       
        // Obtener preguntas por nivel
        preguntasPerLvl = getArrayNivelPregunta();
        
        mostrarpreguntas();
        crearPanel();
        
        tTranscurrido = App.tiempoJuego;
        
        btnContinuar.setOnMouseClicked(e ->  {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación de elección");
            alert.setHeaderText("Comfirmación de Operación");
            alert.setContentText("¿Estás seguro de que deseas continuar?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                actualizar();
            }
        });
        
        hbCont.getChildren().add(btnContinuar);
        
        
        // Instanciar temporizador
        Temporizador temp = new Temporizador();
        temp.setDaemon(true);
        temp.start();
                
        
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
        
        //PARA VER EN CONSOLA
        int maximo = preguntasPerLvl.size();
        int num = indPregunta+1;
        System.out.println("PRESIONASTE EL BOTON");
        System.out.println("Pregunta"+num+" de "+maximo+" del nivel: "+indNivel);
        //Condicion de avanzar si es correcta la respuesta
        
        respuestaCorrecta = r.getTipo().equals(TipoRespuesta.CORRECTA);
        if (respuestaCorrecta){
            //Condicion para avanzar a la siguiente pregunta
            indPregunta++;
            if (maximo>indPregunta){
                mostrarpreguntas();
            }else if(maximo==indPregunta){
                System.out.println("SIGUIENTE NIVEL");
                indPregunta=0;
                indNivel++;
                mostrarpreguntas();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Resultado de la operación");
            alert.setContentText("Te equivocaste, Juego terminado :(");
            alert.showAndWait();
            // Cambiar a la pantalla main de términos
            try{
                App.setRoot("primary");
            } catch(IOException e){
                System.out.println(e);
            }
        }
        
    }
    
    public void mostrarpreguntas(){
        // Obtener preguntas por nivel
        Pregunta pregunta = preguntasPerLvl.get(indNivel).getPreguntas().get(indPregunta);
        //Mostrarlas en la aplicación
        buildPregunta(pregunta);
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
        // Borrar cualquier accion existente
        rbA.setOnMouseClicked(null);
        rbB.setOnMouseClicked(null);
        rbC.setOnMouseClicked(null);
        rbD.setOnMouseClicked(null);
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
     * Usando el TableView de Preguntas, se cargan las preguntas 
     * en base a la cantidad de preguntas por nivel seleccionado por 
     * el usuario usando un ObservableArrayList
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