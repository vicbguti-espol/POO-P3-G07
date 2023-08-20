package espol.poo.proyectojar.utilitaria;

import espol.poo.proyectojar.JuegoController;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import modelo.juego.NivelPregunta;
import modelo.juego.Pregunta;

public class JuegoUtilitaria {
    
    ArrayList<NivelPregunta> nivelPreguntas;
    Integer indNivelActual;
    Integer indPreguntaActual;
    HBox hbCont;
    Boolean correcto;
    
    
    TextField tfPemio;
    String premio;

    /**
     * Constructor de JuegoUtilitaria 
     * @param nivelPreguntas
     * @param indNivelActual
     * @param indPreguntaActual
     * @param hbCont
     * @param correcto 
     */
    public JuegoUtilitaria(ArrayList<NivelPregunta> nivelPreguntas, 
            Integer indNivelActual, Integer indPreguntaActual, HBox hbCont,
            Boolean correcto) {
        this.nivelPreguntas = nivelPreguntas;
        this.indNivelActual = indNivelActual;
        this.indPreguntaActual = indPreguntaActual;
        this.correcto = correcto;
        this.hbCont = hbCont;
    }
    
    
    /**
     * Agregar premio siempre que haya alcanzado superar el nivel o los niveles
     */
   public void agregarPremio(){
       ArrayList<Pregunta> preguntasNivel;
       
       Integer sizeNivelPreguntas;
       Integer sizePreguntasNivel;
       
       // Obtener las preguntas del nivel
       preguntasNivel = nivelPreguntas.get(indNivelActual).getPreguntas();
       // Obtener el tamaño de la lista de preguntas
       sizePreguntasNivel = preguntasNivel.size();
       
       // Obtener el tamaño de la lista de niveles
       sizeNivelPreguntas = nivelPreguntas.size();
       
       // De llegar a alcanzar la última del pregunta del nivel o termino los niveles
       // y además si respondío correctamente esta pregunta
       if ((indNivelActual.equals(sizePreguntasNivel - 1) || 
               (indNivelActual).equals(sizeNivelPreguntas - 1)) && (correcto) ){
           // Agregar el TextField de premio
           addTFPremio();
       }
       
   } 
   
   /**
    * Agregar TextField de premio un contenedor de tipo HBOx
    * @return 
    */
   private TextField addTFPremio(){
       tfPemio = new TextField("Ingresar premio");
       
       // Agregar texfield del premio al contenedor que tiene el botón
       hbCont.getChildren().add(tfPemio);
       
       return tfPemio;
   }
   
   /**
    * Obtener la cadena del premio ingresado
    * @return 
    */
   public String getPremio(){
       ObservableList<Node> childrenCont;
       Integer sizeChildrenCont;
       
       // Obtener el premio del TextField
       premio = tfPemio.getText();
       
       childrenCont = hbCont.getChildren();
       sizeChildrenCont = childrenCont.size();
       
       // Eliminar el último elemento del HBox (TextField Premio)
       childrenCont.remove(sizeChildrenCont - 1);
       
       // App.juego.setPremio(cu.getPremio());
       return premio;
   }
   
}
