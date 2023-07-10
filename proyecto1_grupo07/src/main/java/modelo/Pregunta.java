package modelo;

import java.util.*;

public class Pregunta {
private String textop;
private int nivel;
private Materia materia;
private String[] respuestas = new String[4];

public static ArrayList<Pregunta> preguntas=new ArrayList<Pregunta>();

Scanner sc = new Scanner(System.in);

public Pregunta(Materia m, String tp, int n, String rc, String[]ris){
    this.textop=tp;
    this.nivel=n;
    this.materia=m;
    this.respuestas=respuestas;
}

public String getTextop(){
    return textop;
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

    String[] respuestas = new String[4];

    System.out.println("Ingresar texto de la respuesta correcta");
    respuestas[0]= sc.nextLine();

    for(int i=0;i<ris.length-1;i++){
        System.out.println("Ingresar texto de la respuesta incorrecta "+i);
        respuestas[i+1]=sc.nextLine();
    }

    preguntas.add(new Pregunta(materia,pregunta,niveluser,respuestas));

}

public void visualizarPreguntas(){
    for(Pregunta p: preguntas){
        System.out.println(preguntas.indexOf(p)+1+". "+p.getTextop());
    }
}

public void eliminarPregunta(){
    System.out.println("Ingrese el numero de pregunta que desea eliminar");
    preguntas.remove(sc.nextInt()-1);
}

}
