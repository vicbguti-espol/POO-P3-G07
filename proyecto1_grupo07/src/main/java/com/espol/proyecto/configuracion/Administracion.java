package com.espol.proyecto.configuracion;

import java.util.ArrayList;
import java.util.Scanner;

public class Administracion {
    // Atributos
    private ArrayList<TerminoAcademico> terminosAcademicos;
    private int añoActual;
    
    // Atributos de selección
    private TerminoAcademico terminoAcademicoJuego;
    private Materia materiaJuego;
    private Estudiante estudianteJuego;
    
    // Scanner
    Scanner sc = new Scanner(System.in);
    
    // Constructor
    public Administracion(){
        this.terminosAcademicos = new ArrayList<TerminoAcademico>();
        añoActual = 2023;
    }
    
    // Getters y setters
        public ArrayList<TerminoAcademico> getTerminosAcademicos() {
        return terminosAcademicos;
    }

    public void setTerminosAcademicos(ArrayList<TerminoAcademico> terminosAcademicos) {
        this.terminosAcademicos = terminosAcademicos;
    }

    public TerminoAcademico getTerminoAcademicoJuego() {
        return terminoAcademicoJuego;
    }

    public void setTerminoAcademicoJuego(TerminoAcademico terminoAcademicoJuego) {
        this.terminoAcademicoJuego = terminoAcademicoJuego;
    }

    public Materia getMateriaJuego() {
        return materiaJuego;
    }

    public void setMateriaJuego(Materia materiaJuego) {
        this.materiaJuego = materiaJuego;
    }

    public Estudiante getEstudianteJuego() {
        return estudianteJuego;
    }

    public void setEstudianteJuego(Estudiante estudianteJuego) {
        this.estudianteJuego = estudianteJuego;
    }
    
    // Método para obtener los años de los térmions académicos en lista en año
    public ArrayList<Integer> obtenerNumeroTerminos(int año){
        // Crear un ArrayList añosTerminos
        ArrayList<Integer> numeroTerminos = new ArrayList<Integer>(); 
        // Recorrer terminosAcademicos
        for (TerminoAcademico terminoIte: this.terminosAcademicos){
            if (terminoIte.getAñoTermino() == año){
                // Agregar numTermino a numeroTerminos
                numeroTerminos.add(terminoIte.getNumTermino());
            }

        }
        // Retornar añosTerminos
        return numeroTerminos;
    }
    
    // Método para ingresar un término académico
    public void ingresarTerminoAcademico(){
        
        // Pedir al usuario año de término académico
        System.out.println("Ingresar el año del término académico");
        int añoTermino = sc.nextInt();
        sc.nextLine();     
        
        
        // Verificar que año no sea menor al actual
        while (añoTermino < this.añoActual){
            // Volver a pedir
            System.out.println("Ingresar un año que no sea menor al actual");
            añoTermino = sc.nextInt();
            sc.nextLine();     
        } 
        
        // Pedir al usuario el número del término académico
        System.out.println("Ingresar el número del término académico");
        int numeroTermino = sc.nextInt();
        sc.nextLine();
        

        
        
        // Validar que el número no esté repetido para ese año
        for (TerminoAcademico terminoIte: this.terminosAcademicos){
            
        }
    }
    
   
}
