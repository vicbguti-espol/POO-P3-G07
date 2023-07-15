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
        return "Paralelo {materia: " + materia.getNombre() + ", paralelo: " + numero + ", termino: " + termino + "}";
    }


}
