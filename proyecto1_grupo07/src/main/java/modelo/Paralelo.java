package modelo;

import java.util.*;

public class Paralelo {
    private int numero;
    private Materia materia;
    private TerminoAcademico termino;
    private ArrayList<Estudiante> estudiantes;

    public Paralelo(int numero, Materia materia, TerminoAcademico termino, ArrayList<Estudiante> estudiantes) {
        this.numero = numero;
        this.materia = materia;
        this.termino = termino;
        this.estudiantes = estudiantes;
    }

    static Scanner sc = new Scanner(System.in);
   
    public static void ingresarParalelo(ArrayList<Materia> materias, 
            ArrayList<Paralelo> paralelos, ArrayList<Estudiante> students){

        System.out.println("Ingresar el codigo de la materia");
        String codigo = sc.nextLine();
        Materia materia = Materia.verificarExistencia(codigo, materias);
        
        System.out.println("Ingresar el año del termino de la materia");
        int terminoa = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingresar el numero del termino de la materia");
        int terminon = sc.nextInt();
        sc.nextLine();

        TerminoAcademico termino = new TerminoAcademico(terminoa, terminon);
        
        System.out.println("Ingresar numero de paralelo:");
        int numparalelo = sc.nextInt();
        sc.nextLine();

        
        // System.out.println("Ingresar ruta de archivo:");
        // String rutaArchivo = sc.nextLine();

        paralelos.add(new Paralelo(numparalelo, materia, termino, 
                students));

        
    }

    public static void eliminarParalelo(ArrayList<Paralelo> paralelos){
        for(Paralelo p: paralelos){
            System.out.println(paralelos.indexOf(p)+1+". "+p.toString());
        }
        System.out.println("Ingrese indice de paralelo a eliminar");
        paralelos.remove(sc.nextInt()-1);
    }

    public int getnumero() {
        return numero;
    }

    public TerminoAcademico getTerminoAcademico() {
        return termino;
    }

    public Materia getMateria() {
        return materia;
    }
    
    public ArrayList <Estudiante> getEstudiantes(){
        return estudiantes;
    }

    @ Override
    public String toString(){
        return "Paralelo "+numero+ " de la materia "+materia.getNombre()+" en el termino "+termino.getNumTermino()+"-"+termino.getAñoTermino();
    }


}
