package modelo;

import java.util.*;

public class Pregunta {
    private String texto;
    private int nivel;
    private Materia materia;
    private ArrayList<Respuesta> respuestas;
    
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

    public void ingresarRespuestas(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresar texto de la respuesta correcta");
        String a = sc.nextLine();
        respuestas.add(new Respuesta(a,TipoRespuesta.CORRECTA));
        for(int i=0;i<3;i++){
            System.out.println("Ingresar texto de la respuesta incorrecta "+(i+1));
            String b = sc.nextLine();
            respuestas.add(new Respuesta(b,TipoRespuesta.INCORRECTA));
        }
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
    
    public String toString(){
        String pregunta = "Nivel: " + nivel + "\nPregunta:\n"+ texto + 
                "\nOpciones de respuesta:\n";
        // Shuffle de respuestas de la pregunta
        Collections.shuffle(respuestas);
        for (int i = 0; i < respuestas.size(); i++){
            pregunta += (i+1) + ". " + respuestas.get(i) + "\n";
        }
        
        return pregunta;
    }
}
