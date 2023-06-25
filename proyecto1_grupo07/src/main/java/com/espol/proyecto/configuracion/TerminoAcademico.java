package com.espol.proyecto.configuracion;

// Importar clases
import java.util.ArrayList;


public class TerminoAcademico {
    // Atributos 
    private int numTermino;
    private int añoTermino;
    private ArrayList<Materia> materias;
    private ArrayList<Estudiante> estudiantes;
    
    // Getters y setters

    public int getNumTermino() {
        return numTermino;
    }

    public void setNumTermino(int numTermino) {
        this.numTermino = numTermino;
    }

    public int getAñoTermino() {
        return añoTermino;
    }

    public void setAñoTermino(int añoTermino) {
        this.añoTermino = añoTermino;
    }
    
    @Override
    public String toString(){
        return "numTermino=" + numTermino + ", añoTermino="  + añoTermino;
    }
    
    // Metodo ingresarMateria()
    
    
    // Método ingresarMateria()
    public void ingresarMateria(String codigo, String nombre, int cantNiveles){
        this.materias.add(new Materia(codigo, nombre, cantNiveles));
    }
    
    public boolean buscarMateria(Materia materia){
        // Recorrer la lista de terminosAcademicos
        for (Materia materiaIte: this.materias){
            // De encontrarse el término académico true
            if (materiaIte.equals(materia)){
                return true;
            }
        }
        return false;
    }
    
    // Método modificarNombre()
    public void modificarNombre(Materia materia){
        if (buscarMateria(materia)){
            materia.
        }
    }
}
