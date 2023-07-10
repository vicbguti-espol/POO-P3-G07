package modelo;

public class Pregunta {
private String textop;
private Nivel nivel;
private Materia materia;
private String rec;
private String[] ris = new String[3];

public Pregunta(Materia m, Nivel n, String tp,String rc,String[]ris){
    this.textop=tp;
    this.nivel=n;
    this.materia=m;
    this.rec=rc;
    this.ris=ris;
}

public void ingresarPregunta(){
    System.out.println("Ingresar el codigo de la materia");
    String materia = sc.nextLine();
        
    System.out.println("Ingresar el año del termino de la materia");
    String terminoa = sc.nextLine();

    System.out.println("Ingresar el numero del termino de la materia");
    String terminon = sc.nextLine();

    System.out.println("Ingresar el nivel de dificultad");
    String nivel = sc.nextLine();
    
    System.out.println("Ingresar texto de la respuesta correcta");
    String rc = sc.nextLine();

    for(int i=0;i<ris.lenght;i++){
        System.out.println("Ingresar texto de la respuesta incorrecta"+i);
        ris[i]=sc.nextLine();
    }
    // crear correcta implementacion de creacion de materia, if materia isntance of, else.... ask for codigo 
    this.preguntas.add(TerminoAcademico(terminoa, terminon),nivel);

}

}
