package modelo;

import java.util.*;

public class Pregunta {
    private String texto;
    private int nivel;
    private Materia materia;
    private ArrayList<Respuesta> respuestas;
    
    public Pregunta(String texto, int nivel, Materia materia) {
        this.texto = texto;
        this.nivel = nivel;
        this.materia = materia;
        respuestas= new ArrayList<>();
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
    
    public String toString(){
        return " "+texto+ " con opciones de respuesta " + respuestas +" de nivel " +nivel+" pertenece a la materia "+materia.getNombre();
    }
}
