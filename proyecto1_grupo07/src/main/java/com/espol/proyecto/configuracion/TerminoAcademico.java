package com.espol.proyecto.configuracion;

// Importar clases
import java.util.ArrayList;
import java.util.Scanner;


public class TerminoAcademico {
    // Atributos 
    private int numTermino;
    private int añoTermino;
    private ArrayList<Materia> materias;
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Paralelo> paralelos;
    
    Scanner sc = new Scanner(System.in);
    
    // Constructor
    public TerminoAcademico(int numTermino, int añoTermino){
        this.numTermino = numTermino;
        this.añoTermino = añoTermino;
    }
    
    // Setters
    public void setNumTermino(int numTermino) {
        this.numTermino = numTermino;
    }

    public void setAñoTermino(int añoTermino) {
        this.añoTermino = añoTermino;
    }
    
    // Métodos que pueden ser retirados
    
    public void ingresarMateria(){
        // Pedir al usuario
        System.out.println("Ingresar el nombre de la materia");
        String nombre = sc.nextLine();
        
        System.out.println("Ingresar el código de la materia");
        String codigo = sc.nextLine();
        
        System.out.println("Ingresar la cantida de niveles");
        int cantNiveles = sc.nextInt();
        sc.nextLine();

        // Agregar la materia
        this.materias.add(new Materia(codigo, nombre, cantNiveles));
    }
    

    public void modificarNombre(){
        // Pedir al usuario
        System.out.println("Ingresar el código de la materia");
        String codigoMateria = sc.nextLine();
        
        // Pedir al usuario
        System.out.println("Ingresar el nuevo nombre de la materia");
        String nombre = sc.nextLine();
        // Recorrer lista
        for (Materia materia: this.materias){
            if (materia.getCodigo().equals(codigoMateria)){
                materia.setNombre(nombre);
            }
        }
        
    }
    
    // Método modificarCantNiveles()
    public void modificarCantNiveles(){
        
        // Pedir datos al usuario
        System.out.println("Ingresar el código de la materia a modificar la cantidad de niveles: ");
        String codigo = sc.nextLine();
        System.out.println("Ingresar la cantidad de niveles: ");
        int cantNiveles = sc.nextInt();
        sc.nextLine();
        
        // Recorrer lista
        for (Materia materia: this.materias){
            if (materia.getCodigo().equals(codigo)){
                materia.setCantNiveles(cantNiveles);
            }
        }
    }
    
    @Override
    public String toString(){
        return "numTermino=" + numTermino + ", añoTermino="  + añoTermino;
    }
    
    public boolean equals(Object o){
        if (o == this){
            return true;
        }
        if (o != null && getClass() == o.getClass()){
            TerminoAcademico ter = (TerminoAcademico) o;
            return numTermino == ter.getNumTermino() && añoTermino == ter.getAñoTermino();
        } else{
           return false;
        }
    }
}
