package modelo;

import java.util.*;

public class Pregunta {
private String textopregunta;
private static int nivel;
private Materia materia;
private String[] respuestas = new String[4];

public static ArrayList<Pregunta> preguntas=new ArrayList<Pregunta>();

public Pregunta(Materia materia, String texto, int nivel, String[]respuestas){
    this.textopregunta=texto;
    this.nivel=nivel;
    this.materia=materia;
    this.respuestas=respuestas;
}

public String getTextopregunta(){
    return textopregunta;
}
public static void ingresarPregunta(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Ingresar el codigo de la materia");
    Materia materia=Materia.verificarExistencia(sc.nextLine());

    do{
        System.out.println("Ingresar el nivel de dificultad");
        nivel = sc.nextInt();
        sc.nextLine();
    }while(nivel<1 || nivel>materia.getCantNiveles());
    

    System.out.println("Ingresar el texto de la pregunta");
    String pregunta = sc.nextLine();

    String[] respuestas = new String[4];

    System.out.println("Ingresar texto de la respuesta correcta");
    respuestas[0]= sc.nextLine();

    for(int i=0;i<respuestas.length-1;i++){
        System.out.println("Ingresar texto de la respuesta incorrecta "+(i+1));
        respuestas[i+1]=sc.nextLine();
    }

    preguntas.add(new Pregunta(materia,pregunta,nivel,respuestas));

}

public static void visualizarPreguntas(){
    for(Pregunta p: preguntas){
        System.out.println(preguntas.indexOf(p)+1+". "+p.toString());
    }
}

public static void eliminarPregunta(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Ingrese el numero de pregunta que desea eliminar");
    preguntas.remove(sc.nextInt()-1);
}

public String toString(){
    return " "+textopregunta+ " con opciones de respuesta " + Arrays.toString(respuestas)+" de nivel " +nivel+" pertenece a la materia "+materia.getNombre();
}

public static void main(String[] args) {
    Materia.materias.add(new Materia("0001","FP",3));
    Materia.materias.add(new Materia("0002","FEM",2));
    Materia.materias.add(new Materia("0003","CYS",4));
    Materia.materias.add(new Materia("0004","ARP",1));
    Materia.materias.add(new Materia("0005","CVV",2));
    Materia.materias.add(new Materia("0006","FM",5));
    
    String [] filler={"rc","ri1","ri2","ri3"};

    preguntas.add(new Pregunta(Materia.verificarExistencia("0003"), "Exaco?", 2, filler));
    preguntas.add(new Pregunta(Materia.verificarExistencia("0006"), "Cuanto y con que permiso?", 5, filler));

    visualizarPreguntas();
    eliminarPregunta();
    visualizarPreguntas();

}

}

