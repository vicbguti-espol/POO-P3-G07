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
    private Comodin comodinUsado;
    //public static ArrayList<Pregunta> preguntas= new ArrayList<>();
    public static ArrayList<Pregunta> preguntas= cargarPreguntas();
    //public static ArrayList<Pregunta> preguntas= new ArrayList<>(Arrays.asList(new Pregunta("Cuanto es 2+2?",2, Materia.materias.get(0), new ArrayList<>(Arrays.asList(new Respuesta("4",TipoRespuesta.CORRECTA),new Respuesta("5",TipoRespuesta.INCORRECTA),new Respuesta("0",TipoRespuesta.INCORRECTA),new Respuesta("22",TipoRespuesta.INCORRECTA)))),new Pregunta("Cuanto es 10-12?",2,Materia.materias.get(0), new ArrayList<Respuesta>(Arrays.asList(new Respuesta("4",TipoRespuesta.INCORRECTA),new Respuesta("-2",TipoRespuesta.CORRECTA),new Respuesta("0",TipoRespuesta.INCORRECTA),new Respuesta("22",TipoRespuesta.INCORRECTA)))),new Pregunta("Cuanto es 1+12",3, Materia.materias.get(0), new ArrayList<Respuesta>(Arrays.asList(new Respuesta("0",TipoRespuesta.INCORRECTA),new Respuesta("13",TipoRespuesta.CORRECTA),new Respuesta("7",TipoRespuesta.INCORRECTA),new Respuesta("112",TipoRespuesta.INCORRECTA)))),new Pregunta("Cuanto es 1+1",3, Materia.materias.get(0), new ArrayList<Respuesta>(Arrays.asList(new Respuesta("0",TipoRespuesta.INCORRECTA),new Respuesta("2",TipoRespuesta.CORRECTA),new Respuesta("7",TipoRespuesta.INCORRECTA),new Respuesta("112",TipoRespuesta.INCORRECTA))))));
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

    public Comodin getComodinUsado() {
        return comodinUsado;
    }

    public void setComodinUsado(Comodin comodinUsado) {
        this.comodinUsado = comodinUsado;
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
        subirArchivo();
        //eliminarPregunta(preguntas.get(0));
        for(Pregunta p: preguntas){
            System.out.println(p.toString());
        }
        
    }
    public static void subirArchivo() {
        
        preguntas = new ArrayList<>();
        
        preguntas.add(new Pregunta("Cuanto es 2+2?",2, Materia.materias.get(0), new ArrayList<>(Arrays.asList(new Respuesta("4",TipoRespuesta.CORRECTA),new Respuesta("5",TipoRespuesta.INCORRECTA),new Respuesta("0",TipoRespuesta.INCORRECTA),new Respuesta("22",TipoRespuesta.INCORRECTA)))));
        preguntas.add(new Pregunta("Cuanto es 10-12?",2,Materia.materias.get(0), new ArrayList<Respuesta>(Arrays.asList(new Respuesta("4",TipoRespuesta.INCORRECTA),new Respuesta("-2",TipoRespuesta.CORRECTA),new Respuesta("0",TipoRespuesta.INCORRECTA),new Respuesta("22",TipoRespuesta.INCORRECTA)))));
        preguntas.add(new Pregunta("Cuanto es 1+12",3, Materia.materias.get(0), new ArrayList<Respuesta>(Arrays.asList(new Respuesta("0",TipoRespuesta.INCORRECTA),new Respuesta("13",TipoRespuesta.CORRECTA),new Respuesta("7",TipoRespuesta.INCORRECTA),new Respuesta("112",TipoRespuesta.INCORRECTA)))));
        preguntas.add(new Pregunta("Cuanto es 1+1",3, Materia.materias.get(0), new ArrayList<Respuesta>(Arrays.asList(new Respuesta("0",TipoRespuesta.INCORRECTA),new Respuesta("2",TipoRespuesta.CORRECTA),new Respuesta("7",TipoRespuesta.INCORRECTA),new Respuesta("112",TipoRespuesta.INCORRECTA)))));
        
//        preguntas.add(new Pregunta("ANALICE EL CÓDIGO QUE SE MUESTRA A CONTINUACIÓN\n" +
//        "\n" +
//        "class CalculoSalarioException extends Exception{}\n" +
//        "class Person{\n" +
//        "   public void calcularSalario(double salario) throws CalculoSalarioException{\n" +
//        "		throw new CalculoSalarioException();\n" +
//        "		System.out.println(salario*2);\n" +
//        "   }\n" +
//        "}\n" +
//        "class Compania{\n" +
//        "	public static void main(String[] args){\n" +
//        "		new Person().calcularSalario(200);\n" +
//        "	}\n" +
//        "}\n" +
//        "¿Cuál de los siguientes enunciados de forma independiente es verdadero?",
//        3, Materia.materias.get(0), 
//        new ArrayList<Respuesta>(Arrays.asList(
//                new Respuesta("Este código compila sin problemas, la salida es 400",TipoRespuesta.INCORRECTA),
//                new Respuesta("Este código compila si se agrega un bloque try-catch en el método main de la clase Companía.",TipoRespuesta.CORRECTA),
//                new Respuesta("Este código compila  si el método calcularSalario devuelve un double en vez de ser un void",TipoRespuesta.INCORRECTA),
//                new Respuesta("Este código compila si agregamos un throws CalculoSalarioException en la firma del método main de la clase Companía.",TipoRespuesta.INCORRECTA))
//        )
//        ));
        
        preguntas.add(new Pregunta(
        "¿Cuál de las siguientes opciones describe mejor el concepto de \"encapsulación\" en la programación orientada a objetos?",
        1, Materia.materias.get(0),
        new ArrayList<Respuesta>(Arrays.asList(
                new Respuesta("El proceso de definir múltiples clases que comparten características similares.",TipoRespuesta.INCORRECTA),
                new Respuesta("La habilidad de una clase para ocultar ciertos detalles internos y exponer solo las operaciones necesarias.",TipoRespuesta.CORRECTA),
                new Respuesta("La capacidad de una clase para heredar propiedades y métodos de una clase padre.",TipoRespuesta.INCORRECTA),
                new Respuesta("La práctica de agrupar datos y los métodos que operan sobre esos datos en una única unidad.",TipoRespuesta.INCORRECTA))
        )
        ));
        
        preguntas.add(new Pregunta(
        "¿Qué es el \"polimorfismo\" en programación orientada a objetos?",
                1, Materia.materias.get(0), 
                new ArrayList<Respuesta>(Arrays.asList(
                new Respuesta("La encapsulación de datos y métodos en una única entidad.",TipoRespuesta.INCORRECTA),
                new Respuesta("La habilidad de objetos de diferentes clases para responder al mismo método de manera única.",TipoRespuesta.CORRECTA),
                new Respuesta(" La combinación de múltiples clases en una jerarquía.",TipoRespuesta.INCORRECTA),
                new Respuesta("La capacidad de una clase para heredar de sí misma.",TipoRespuesta.INCORRECTA))
        )
        ));
        
        
        
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

    

