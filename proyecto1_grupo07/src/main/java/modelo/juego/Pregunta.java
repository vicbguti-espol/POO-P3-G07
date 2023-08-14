package modelo.juego;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import modelo.juego.Respuesta;
import modelo.juego.TipoRespuesta;
import java.util.*;
import modelo.academico.Materia;

public class Pregunta {
    private String texto;
    private int nivel;
    private Materia materia;
    private ArrayList<Respuesta> respuestas;
    
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
     * Obtener un arreglo de tipo Pregunta a partir de una ruta de archivo
     * @param pathPreguntas
     * @return 
     */
    public static ArrayList<Pregunta> cargarPreguntas(String pathPreguntas) {
        ArrayList<Pregunta> preguntascargadas = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(pathPreguntas))) {
            preguntascargadas= (ArrayList<Pregunta>) in.readObject();
        } catch (EOFException e) {

        } catch (Exception e) {
            System.out.println(e);;
        }
        return preguntascargadas;
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
        return new Pregunta(p.getTexto(), 
                nivel, p.getMateria(), 
                respuestas);
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
    }

    

