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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import modelo.academico.Materia;
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

    int tTranscurrido;
    Juego juego = App.juego;
    int tiempoJuego = App.tiempoJuego;
    Integer correctas = 0; 
    
    public Integer indNivel = 0;
    public Integer indPregunta = 0;
    public int preguntasAvanzadas=0;
    Respuesta r = new Respuesta();
    Pregunta pregunta=new Pregunta();
    Boolean respuestaCorrecta;
    
    public ArrayList<NivelPregunta> preguntasPerLvl;
    Button btnContinuar = new Button("Continuar");
    
    Temporizador temp;
    
    ArrayList<RadioButton> radioButtons;
    
    
    // ArrayList<PreguntaComodin> comodinesUsados = App.juego.getComodinesUtilizados();
    
    @FXML
    public void initialize() throws InterruptedException {
        
        // Obtener lista de radioButtons de respuestas
        radioButtons = new ArrayList<>();
        radioButtons.add(rbA);
        radioButtons.add(rbB);
        radioButtons.add(rbC);
        radioButtons.add(rbD);
        
        buildJuego();
        Circle mask = new Circle(comodin50.getFitWidth() / 2, comodin50.getFitHeight() / 2,Math.min(comodin50.getFitWidth(), comodin50.getFitHeight()) / 2);
        comodin50.setClip(mask);
        comodin50.setStyle("-fx-border-color: white; -fx-border-width: 2px;");
        comodin50.setOnMouseClicked(e->{
            
           comodin50();
           
        });
        Circle mask2 = new Circle(comodinCompañero.getFitWidth() / 2, comodinCompañero.getFitHeight() / 2,Math.min(comodinCompañero.getFitWidth(), comodinCompañero.getFitHeight()) / 2);
        comodinCompañero.setClip(mask2);
        comodinCompañero.setStyle("-fx-border-color: white; -fx-border-width: 10px;");
        comodinCompañero.setOnMouseClicked(e->{
           comodinCompañero(); 
        });
        Circle mask3 = new Circle(comodinPublico.getFitWidth() / 2, comodinPublico.getFitHeight() / 2,Math.min(comodinPublico.getFitWidth(), comodinPublico.getFitHeight()) / 2);
        comodinPublico.setClip(mask3);
        comodinPublico.setStyle("-fx-border-color: white; -fx-border-width: 2px;");
        comodinPublico.setOnMouseClicked(e->{
           comodinPublico(); 
        });
    }
    
    public void buildJuego(){
       
        // Obtener preguntas por nivel
        preguntasPerLvl = App.juego.getArrayNivelPregunta();
        
        mostrarpreguntas();
        crearPanel();
        
        tTranscurrido = tiempoJuego;
        
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
        temp = new Temporizador();
        temp.setDaemon(true);
        temp.start();
        
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
        
        Alert alert;
        Image img;
        
        
        
        //PARA VER EN CONSOLA
        
        backupDuracion();
        
        // Actualizar el tiempo para cada pregunta 
        // (estos dos siempre tienen que ser tipos de datos primitivos)
        tTranscurrido = tiempoJuego;
        
        lvPreguntas.getSelectionModel().select(indPregunta+(indNivel*NuevoJuegoController.cantPregNivSeleccionado)+1);
        
        int maximo = preguntasPerLvl.get(indNivel).getPreguntas().size();
        int num = indPregunta+1;
        System.out.println("PRESIONASTE EL BOTON");
        System.out.println("Pregunta"+num+" de "+maximo+" del nivel: "+indNivel);
        
        //Condicion de avanzar si es correcta la respuesta
        respuestaCorrecta = r.getTipo().equals(TipoRespuesta.CORRECTA);
        //Sintaxis del recorrido de un juego completo
        int Preguntastotales=0;
        for(NivelPregunta i : preguntasPerLvl){
	Preguntastotales+=i.getPreguntas().size();
        }
        if (respuestaCorrecta){
            //Condicion para avanzar a la siguiente pregunta o nivel
            preguntasAvanzadas++;
            if (preguntasAvanzadas!=Preguntastotales){
                if(maximo==num){
//                    // Suspender thread
//                    temp.suspendThread();
//                    System.out.println("SIGUIENTE NIVEL");
//                    // Dialogo para que ingrese el premio que quiere al pasar un nivel
//                    TextInputDialog dialog = new TextInputDialog();
//                    dialog.setTitle("Ingreso de Premio");
//                    dialog.setHeaderText("Ingrese su premio por pasar el nivel: "+indNivel);
//                    dialog.setContentText("Premio: ");
//                    Optional<String> result = dialog.showAndWait();
//                    result.ifPresent(premio -> {
//                    // Guardar la información ingresada por el usuario (premio)
//                    System.out.println("Premio ingresado: " + premio);
//                    
//                    });
//                    
                    indPregunta=0;
                    indNivel++;
//                    // Continuar thread
//                    temp.resumeThread();
                    mostrarpreguntas();
                    
                }else if (maximo>num){
                    indPregunta++;
                    mostrarpreguntas();
                }
            } else {
                 
                img = new Image("/espol/poo/proyectojar/files/Asset 5xxhdpi.png");
                alert = endAlert("Terminaste el Juego. GANASTE! :)", img, 144, 144);
                
                terminarJuego(alert, true);
            }
            // Salida cuando se equivoca en una pregunta
        } else {
            img = new Image("/espol/poo/proyectojar/files/Asset 1xxhdpi.png");
            alert = endAlert("Te equivocaste, Juego terminado :(", img, 144, 65);
            
            terminarJuego(alert, false);
            
            
        }
        System.out.println("Respondido "+preguntasAvanzadas+" preguntas de "+Preguntastotales);
    }
    
    /**
     * Terminar juego con alerta en función de si necesita premio o no de ingresar
     * @param alert
     * @param premio 
     */
    public void terminarJuego(Alert alert, boolean premio){
        // Cambiar a la pantalla main de términos
        tTranscurrido=120;
        // Cuadro de alerta mientras se detine el tiempo            
        alert.showAndWait();
        // Ingresar premio en caso de requerirlo
        premio:ingresarPremio();
        
        
        
        // Actualizar archivo de juegos
        Juego.actualizarJuegos();
        // Establecer el nivel máximo alcanzado
        juego.setLvlMax(obtenerNivelAlcanzado());
        // Establecer la cantidad de preguntas contestadas
        juego.setPreguntasContestadas(preguntasAvanzadas);
        
//        tTranscurrido=0;
        temp.terminarJuego();
    }
    
    /**
     * Obtener el nivel máximo alcanzado
     * @return 
     */
    public int obtenerNivelAlcanzado(){
        return preguntasPerLvl.get(indNivel).getNivel();
    }
    
    
    /**
     * Dialog y obtener premio para juego
     */
    public void ingresarPremio(){
        //Dialogo premio final Juego.
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ingreso de Premio");
        dialog.setHeaderText("Ingrese su premio por pasar CasiPolitecnico");
        dialog.setContentText("Premio: ");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(premio -> {
        // Guardar la información ingresada por el usuario (premio)
        System.out.println("Premio ingresado: " + premio);
        juego.setPremio(premio);
        });
    }
    
    /**
     * Obtener alerta a partir de un mensaje
     * @param message
     * @return 
     */
    public Alert endAlert(String message){
        Alert alert; 
        
        alert = new Alert(Alert.AlertType.INFORMATION);
        
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Resultado de la operación");
        alert.setContentText(message);
        
        return alert; 
    }
    
    
    /**
     * Obtener alerta con mensaje e imagen
     * @param message
     * @param img
     * @return 
     */
    public Alert endAlert(String message, Image img, int height, int width){
        Alert alert; 
        ImageView imageView; 
        
        alert = endAlert(message);
        
        imageView = new ImageView(img);
        imageView.setFitHeight(height); // Ajusta la altura de la imagen
        imageView.setFitWidth(width); // Ajusta el ancho de la imagen
        
        alert.setGraphic(imageView);
        
        return alert; 
    }
    
    /**
     * Guardar el tiempo que le tomó al usuario responder la pregunta
     */
    public void backupDuracion(){
        int segundosJuego;
        int segundosPregunta;
        
        // Obtener los segundos totales actuales del juego
        segundosJuego = juego.getSegundos();
        // Obtener los segundos que tomó hacer la anterior pregunta
        segundosPregunta = tiempoJuego - tTranscurrido;
        
        // Actualizar los segundos totales de todas las preguntas del juego
        juego.setSegundos( segundosJuego + segundosPregunta );
        
        
        // System.out.println("Segundos totales del juego: " + segundosJuego);
    }
    
    public void mostrarpreguntas(){
        // Obtener preguntas por nivel
        pregunta = preguntasPerLvl.get(indNivel).getPreguntas().
                get(indPregunta);
        
        
        //Mostrarlas en la aplicación
        buildPregunta(pregunta);
        
    }
    
    public void buildPregunta(Pregunta p){
        // Construir la estructura de pregunta con sus posibles respuestas
        lblPregunta.setText(p.getTexto());
        
        // Shuffle de respuestas de la pregunta
        Collections.shuffle(p.getRespuestas());
        
        // Construir respuestas de la pregunta
        buildRespuestas(p.getRespuestas());
    }
    
    public void buildRespuestas(ArrayList<Respuesta> respuestas){
        ArrayList<String> literales;
        
        literales = obtenerLiterales();
        
        int i = 0;
        for (RadioButton rb: radioButtons){
            // Borrar cualquier accion existente
            rb.setOnMouseClicked(null);
            // Manejar evento al hacer click en el radio button
            rb.setOnMouseClicked(new manejadorRespuesta(i, respuestas));
            // Establecer texto radioButton
            rb.setText(literales.get(i) + respuestas.get(i) + "");
            i++;
        }
    }
    
    public ArrayList<String> obtenerLiterales(){
        ArrayList<String> literales;
        
        literales = new ArrayList<>();
        literales.add("A)");
        literales.add("B)");
        literales.add("C)");
        literales.add("D)");
        
        return literales;
    }

    
    private class manejadorRespuesta implements EventHandler<Event>{
        int indice;
        ArrayList<Respuesta> respuestas; 
        
        /**
         * Constructor de clase
         * @param i
         * @param r 
         */
        public manejadorRespuesta(int i, ArrayList<Respuesta> r){
            indice = i;
            respuestas = r; 
        }
        
        /**
         * Manejador del evento de clase
         * @param e 
         */
        @Override
        public void handle(Event e){
            r = respuestas.get(indice);
        }
    }
    
    
    /**
     * Actualiza la pregunta en los componentes FXML para eliminar dos respuesta incorrectas
     * 
     */
    public void actualizar50(){
        Pregunta pregunta50;
        ArrayList<Respuesta> respuestas;
        
        pregunta = preguntasPerLvl.get(indNivel).getPreguntas().
                get(indPregunta);

        // Crear copias de respuestas
        respuestas = new ArrayList<>();
        for (Respuesta r: pregunta.getRespuestas()){
            respuestas.add(new Respuesta(r));
        }

        
        Collections.shuffle(respuestas);
        
        System.out.println(pregunta);

        for(int i=0;i<2;i++){
            Respuesta respuestaindice=respuestas.get(i);
            if(respuestaindice.getTipo().equals(TipoRespuesta.CORRECTA)){
                i--;
            }
            else{
               respuestaindice.setTexto(""); 
            }
        }
        
        System.out.println(pregunta);
        
        // System.out.println(pregunta50.getRespuestas());
        buildRespuestas(respuestas);
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
        Comodin comodinUsado;
        
        notificacionComodin("50");
        System.out.println("Se ejecuta");
        ColorAdjust grayscale= new ColorAdjust();
        grayscale.setSaturation(-1);
        comodin50.setEffect(grayscale);
        comodin50.setMouseTransparent(true);
        actualizar50();

        comodinUsado = Comodin.CINCUENTA;
        agregarComodin(comodinUsado);
        
        
        // comodinesUsados.add(new PreguntaComodin(preguntaActual, comodinUsado));
        // comodinesUsados+=" Usó el comodin 50/50 en el nivel: "+indNivel+" y la pregunta: "+indPregunta+". \n";
        
    }
    /**
     * Comodin Publico
     * Envia un cuadro de dialogo que incia la consulta al curso.
     **/
    public void comodinPublico(){
        Comodin comodinUsado;
        
        notificacionComodin("publico");
        ColorAdjust grayscale= new ColorAdjust();
        grayscale.setSaturation(-1);
        comodinPublico.setEffect(grayscale);
        comodinPublico.setMouseTransparent(true);
        
        comodinUsado = Comodin.SALON; 
        agregarComodin(comodinUsado);
        // comodinesUsados+=" Usó el comodin de curso en el nivel: "+indNivel+" y la pregunta: "+indPregunta+". \n";
    }
    /**
     * Comodin Compañero
     * Envia un cuadro de dialogo que incia la consulta con el nombre del participante de apoyo seleccionado.
     **/
    public void comodinCompañero(){
        Comodin comodinUsado;
        
        notificacionComodin("compañero");
        ColorAdjust grayscale= new ColorAdjust();
        grayscale.setSaturation(-1);
        comodinCompañero.setEffect(grayscale);
        comodinCompañero.setMouseTransparent(true);
        
        comodinUsado = Comodin.COMPANERO;
        agregarComodin(comodinUsado);
        // comodinesUsados+=" Usó el comodin de compañero "+NuevoJuegoController.apoyoSeleccionado.getNombre()+" en el nivel: "+indNivel+" y la pregunta: "+indPregunta+". \n";
    }
    
    /**
     * Agregar comodín a la lista de comodines del juego
     * @param comodinUsado 
     */
    public void agregarComodin(Comodin comodinUsado){
        ArrayList<Pregunta> preguntasNivel;
        Pregunta preguntaActual;
        
        preguntasNivel = preguntasPerLvl.get(indNivel).getPreguntas();
        preguntaActual = preguntasNivel.get(indPregunta);
        
        // comodinesUsados.add(new PreguntaComodin(preguntaActual, comodinUsado));
        preguntaActual.setComodinUsado(comodinUsado);
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
                listaPreguntas.add("Pregunta "+String.valueOf(np.getPreguntas().indexOf(p)+1));
            }
        }
        lvPreguntas.setItems(listaPreguntas);
        lvPreguntas.getSelectionModel().select(0);
        lvPreguntas.setMouseTransparent(true);
    }
    
    

    class Temporizador extends Thread {
        boolean progression = true;
        boolean suspendThread = false;
        
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