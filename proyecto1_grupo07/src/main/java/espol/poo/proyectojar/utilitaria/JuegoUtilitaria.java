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
    Boolean correcto;
    HBox hbCont;
    
    

    
   public void agregarPremio(){
       ArrayList<Pregunta> preguntasNivel;
       
       Integer sizeNivelPreguntas;
       Integer sizePreguntasNivel;
       
       // Obtener las preguntas del nivel
       preguntasNivel = nivelPreguntas.get(indNivelActual).getPreguntas();
       // Obtener el tamaño de la lista de preguntas
       sizePreguntasNivel = preguntasNivel.size();
       
       // De llegar a alcanzar la última del pregunta del nivel y además 
       // respondío correctamente esta pregunta
       if ((indNivelActual.equals(sizePreguntasNivel - 1)) && (correcto)){
           
       }
       
   } 
   
   public TextField TFPremio(){
       TextField tfPemio;
       
       tfPemio = new TextField("Ingresar premio");
       
       // Agregar texfield del premio al contenedor que tiene el botón
       hbCont.getChildren().add(tfPemio);
       
       return tfPemio;
   }
   
   /**
    * Eliminar el último TextField de premio una vez es presionado continuar
    */
   public void removeTFPremio(){
       ObservableList<Node> childrenCont;
       Integer sizeChildrenCont;
       
       childrenCont = hbCont.getChildren();
       sizeChildrenCont = childrenCont.size();
       
       // Eliminar el último elemento del HBox (TextField Premio)
       childrenCont.remove(sizeChildrenCont - 1);
   }
   
  
   private String getPremio(TextField tfPemio){
       return tfPemio.getText();
       
   }
}
