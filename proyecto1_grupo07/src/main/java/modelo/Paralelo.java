package modelo;

import java.util.*;
import java.util.Scanner;

public class Paralelo {
    private Materia materia;
    private TerminoAcademico termino;
    private int numero;
    private ArrayList <Estudiante> estudiantes;


    public static ArrayList<Paralelo>paralelos=new ArrayList<Paralelo>(); 

    Scanner sc = new Scanner(System.in);

    public Paralelo(Materia m, TerminoAcademico t, int n,ArrayList<Estudiante> e){
        this.materia=m;
        this.termino=t;
        this.numero=n;
        this.estudiantes=e;
    }

    public void ingresarParalelo(){
        System.out.println("Ingresar el codigo de la materia");
        String codmateria = sc.nextLine();

        Materia materia=Materia.verificarExistencia(codmateria);
        
        System.out.println("Ingresar el año del termino de la materia");
        int terminoa = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingresar el numero del termino de la materia");
        int terminon = sc.nextInt();
        sc.nextLine();

        TerminoAcademico termino=new TerminoAcademico(terminoa, terminon);
        
        System.out.println("Ingresar numero de paralelo:");
        int numparalelo = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingresar ruta de archivo:");
        String rutaarchivo = sc.nextLine();
        sc.nextLine();

        //proceso de agregar estudiantes -- codigomateria-paralelo-año-termino.csv
        paralelos.add(new Paralelo(materia, termino ,numparalelo,Estudiante.estudiantes));

        
    }

    public String toString(){
        return "Paralelo "+numero+ " de la materia "+materia+" en el termino "+termino.getAñoTermino()+"-"+termino.getNumTermino();
    }

    public void eliminarParalelo(){
        for(Paralelo p: paralelos){
            System.out.println(paralelos.indexOf(p)+1+". Paralelo "+p.getnumero()+" de la materia "+p.getMateria().getNombre()+" del termino academico "+p.getTerminoAcademico());
        }
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


}
