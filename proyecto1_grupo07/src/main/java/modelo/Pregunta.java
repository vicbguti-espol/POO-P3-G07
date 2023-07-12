package modelo;

import java.util.*;

public class Pregunta {
    private String texto;
    private int nivel;
    private Materia materia;
    private ArrayList<Respuesta> respuestas;
    private static ArrayList<Pregunta> preguntas;

    /**
    * Getters y setters
    */
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }


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

    public int getNivel() {
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
  
    public void setRespuestas(ArrayList<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }


  public static void eliminarPregunta(){
      Scanner sc = new Scanner(System.in);
      System.out.println("Ingrese el numero de pregunta que desea eliminar");
      preguntas.remove(sc.nextInt()-1);
  }

    public static ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    public static void setPreguntas(ArrayList<Pregunta> preguntas) {
        Pregunta.preguntas = preguntas;
    }


    // Asignar los parámetros adecuados
    public Pregunta(Materia m, String tp, int n, String rc, String[]ris){
        this.textop=tp;
        this.nivel=n;
        this.materia=m;
        this.respuestas=respuestas;
    }

    // Pasarlo a main
    public void ingresarPregunta(){
        Scanner sc = new Scanner(System.in);
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

    // Pasarlo a main
    public void eliminarPregunta(){
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

