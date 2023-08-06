package modelo.juego;

import modelo.academico.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class Pregunta implements Serializable {
    private static final long serialVersionUID = 1;
    private String texto;
    private int nivel;
    private Materia materia;
    private ArrayList<Respuesta> respuestas;
    //public static ArrayList<Pregunta> preguntas= new ArrayList<>();
    public static ArrayList<Pregunta> preguntas= cargarPreguntas();
    private static final String path="archivo\\preguntas.ser";
    
    public Pregunta(){
        
    }
    
    public Pregunta(String texto, int nivel, Materia materia,
            ArrayList<Respuesta> respuestas) {
        this.texto = texto;
        this.nivel = nivel;
        this.materia = materia;
        this.respuestas = respuestas;
    }

    /**
    * Getters y setters
    */
    public String getTexto() {
        return texto;
    }

    public int getNivel(){
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public ArrayList<Respuesta> getRespuestas() {
        return respuestas;
    }
    
    public void setRespuestas(ArrayList<Respuesta> r){
        respuestas=r;
    }
    
    public static ArrayList<Pregunta> getPreguntasMateria(ArrayList<Pregunta>
            preguntas, Materia materia){
        // Declarar las preguntas de la materia
        ArrayList<Pregunta> preguntasMateria = new ArrayList<>();
        for (Pregunta p: preguntas){
            if (p.getMateria().equals(materia)){
                preguntasMateria.add(p);
            }
        }
        return preguntasMateria;
    }
    
    public static Pregunta copy(Pregunta p){
        ArrayList<Respuesta> respuestas = new ArrayList<>(p.getRespuestas());
        int nivel = p.getNivel();
        return new Pregunta(p.getTexto(), nivel, p.getMateria(), respuestas);
    }
    
    public void removeRespuestasIncorrectas(int n){
        
        // Crear iterador de respuestas 
        Iterator<Respuesta> it = respuestas.iterator();
        // Declarar contador cada que se elimine una respuesta equívoca
        int contador = 0;
        // Remover n respuestas equívocas
        while (it.hasNext() && (contador < n)){
            Respuesta r = it.next();
            if (r.getTipo().equals(TipoRespuesta.INCORRECTA)){
                it.remove();
                contador++;
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pregunta other = (Pregunta) obj;
        if (this.nivel != other.nivel) {
            return false;
        }
        if (!Objects.equals(this.texto, other.texto)) {
            return false;
        }
        if (!Objects.equals(this.materia, other.materia)) {
            return false;
        }
        return Objects.equals(this.respuestas, other.respuestas);
    }
    
    @Override
    public String toString(){
        ArrayList<String> literales = new ArrayList<>();
        literales.add("A");
        literales.add("B");
        literales.add("C");
        literales.add("D");
        String pregunta = "Nivel: " + nivel + "\nPregunta:\n"+ texto + 
                "\nOpciones de respuesta:\n";
        // Shuffle de respuestas de la pregunta
        Collections.shuffle(respuestas);
        for (int i = 0; i < respuestas.size(); i++){
            
            pregunta += literales.get(i) + ". " + respuestas.get(i) + "\n";
        }
        
        return pregunta;
    }
    public static void main(String[] args) {
        //subirArchivo();
        //eliminarPregunta(preguntas.get(0));
        for(Pregunta p: preguntas){
            System.out.println(p.toString());
        }
        
    }
    public static void subirArchivo() {
        preguntas.add(new Pregunta("Cuanto es 2+2?",2, Materia.materias.get(0), new ArrayList<>(Arrays.asList(new Respuesta("4",TipoRespuesta.CORRECTA),new Respuesta("5",TipoRespuesta.INCORRECTA),new Respuesta("0",TipoRespuesta.INCORRECTA),new Respuesta("22",TipoRespuesta.INCORRECTA)))));
        preguntas.add(new Pregunta("Cuanto es 10-12?",2,Materia.materias.get(0), new ArrayList<Respuesta>(Arrays.asList(new Respuesta("4",TipoRespuesta.INCORRECTA),new Respuesta("-2",TipoRespuesta.CORRECTA),new Respuesta("0",TipoRespuesta.INCORRECTA),new Respuesta("22",TipoRespuesta.INCORRECTA)))));
        preguntas.add(new Pregunta("Cuanto es 1+12",3, Materia.materias.get(0), new ArrayList<Respuesta>(Arrays.asList(new Respuesta("0",TipoRespuesta.INCORRECTA),new Respuesta("13",TipoRespuesta.CORRECTA),new Respuesta("7",TipoRespuesta.INCORRECTA),new Respuesta("112",TipoRespuesta.INCORRECTA)))));
        preguntas.add(new Pregunta("Cuanto es 1+1",3, Materia.materias.get(0), new ArrayList<Respuesta>(Arrays.asList(new Respuesta("0",TipoRespuesta.INCORRECTA),new Respuesta("2",TipoRespuesta.CORRECTA),new Respuesta("7",TipoRespuesta.INCORRECTA),new Respuesta("112",TipoRespuesta.INCORRECTA)))));
        try(ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(path))){
            out.writeObject(preguntas);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    public static ArrayList<Pregunta> cargarPreguntas() {
        ArrayList<Pregunta> preguntascargadas = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            preguntascargadas= (ArrayList<Pregunta>) in.readObject();
        } catch (EOFException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return preguntascargadas;
    }
    // Para editar una materia seria necesario eliminarla y agregarla nuevamente en tiempo de compilacion.
    public static void agregarPregunta(Pregunta p){
        if(!preguntas.contains(p)){
            preguntas.add(p);
        }
        try(ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(path))){
            out.writeObject(preguntas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void eliminarPregunta(Pregunta p){
            preguntas.remove(p);
        try(ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(path))){
            out.writeObject(preguntas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

    
