package modelo;

import java.util.ArrayList;

public class Pregunta {
private String texto;
private int nivel;
private Materia materia;
private ArrayList<Respuesta> respuestas;

private static ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();

Scanner sc = new Scanner(System.in);

public Pregunta(Materia m, String tp, int n, String rc, String[]ris){
    this.texto = tp;
    this.nivel = n;
    this.materia = m;
    this.respuestaCorrecta = rc;
    this.respuestasIncorrectas = ris;
}

public ArrayList<Respuesta> getRespuestas() {
    return respuestas;
}

public String getTexto(){
    return texto;
}

public Materia getMateria(){
    return materia;
}

public int getNivel(){
    return nivel;
}

public static ArrayList<Pregunta> getPreguntas(){
    return preguntas;
}

public void ingresarPregunta(){
    System.out.println("Ingresar el codigo de la materia");
    String codmateria = sc.nextLine();
    
    Materia materia=Materia.verificarExistencia(codmateria);

    /*for(Materia i: Materia.materias){
        if (i.getCodigo().equals(codmateria)){
            Materia materia=i;
        }
        else{
            System.out.println("Materia no encontrada");
        }
    }*/
    int niveluser;
    
    do{
        System.out.println("Ingresar el nivel de dificultad");
        niveluser = sc.nextInt();
        sc.nextLine();
    }while(nivel<1 || nivel>materia.getCantNiveles());
    

    System.out.println("Ingresar el texto de la pregunta");
    String pregunta = sc.nextLine();
    
    System.out.println("Ingresar texto de la respuesta correcta");
    String rc = sc.nextLine();

    String[] ris = new String[3];

    for(int i=0;i<ris.length;i++){
        System.out.println("Ingresar texto de la respuesta incorrecta "+i);
        ris[i]=sc.nextLine();
    }

    preguntas.add(new Pregunta(materia,pregunta,niveluser,rc,ris));

}

public void eliminarPregunta(){
    System.out.println("Ingrese el numero de pregunta que desea eliminar");
    preguntas.remove(sc.nextInt()-1);
}

}
