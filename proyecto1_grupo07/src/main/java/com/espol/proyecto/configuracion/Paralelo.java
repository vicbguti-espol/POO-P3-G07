package main.java.com.espol.proyecto.configuracion;

import java.util.ArrayList;
import java.util.Scanner;

import com.espol.proyecto.configuracion.TerminoAcademico;

public class Paralelo {
    private Materia materia;
    private Termino termino;
    private int numero;
    private String rutaarchivo;
    private ArrayList<Estudiante> estudiantes;

    Scanner sc = new Scanner(System.in);

    public Paralelo(Materia m, Termino t, int n, ArrayList e){
        this.materia=m;
        this.termino=t;
        this.numero=n;
        this.estudiantes=e;
    }

    public void ingresarParalelo(){
        System.out.println("Ingresar el codigo de la materia");
        String materia = sc.nextLine();
        
        System.out.println("Ingresar el año del termino de la materia");
        String terminoa = sc.nextLine();

        System.out.println("Ingresar el numero del termino de la materia");
        String terminon = sc.nextLine();
        
        System.out.println("Ingresar numero de paralelo:");
        int numparalelo = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingresar ruta de archivo:");
        String rutaarchivo = sc.nextLine();
        sc.nextLine();

        //proceso de agregar estudiantes -- codigomateria-paralelo-año-termino.csv


        this.paralelos.add(new Paralelo(materia,TerminoAcademico(terminoa, terminon) , numparalelo, rutaarchivo));

        
    }

    public String toString(){
        return "Paralelo "+numparalelo+ " de la materia "+materia+" en el termino "+terminon+"-"+terminoa;
    }

    public void eliminarParalelo(){
        for (Paralelo imp: Paralelos){
            System.out.print(paralelos.indexOf(imp),".",imp.toString());    
        }
        System.out.println("Ingrese indice de paralelo a eliminar");
        int indice=sc.nextInt();
        sc.nextLine();
        paralelos.remove(paralelos.get(indice));
    }


}
