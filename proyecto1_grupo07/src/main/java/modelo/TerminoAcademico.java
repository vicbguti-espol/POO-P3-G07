package modelo;

// Importar clases
import java.util.ArrayList;
import java.util.Scanner;


public class TerminoAcademico {
    // Atributos 
    private int numTermino;
    private int añoTermino;
    
    // Constructor
    public TerminoAcademico(int numTermino, int añoTermino){
        this.numTermino = numTermino;
        this.añoTermino = añoTermino;
    }
    
    // Getters
    public int getNumTermino(){
        return this.numTermino;
    }
    
    public int getAñoTermino(){
        return this.añoTermino;
    }
    
    // Setters
    public void setNumTermino(int numTermino) {
        this.numTermino = numTermino;
    }

    public void setAñoTermino(int añoTermino) {
        this.añoTermino = añoTermino;
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
            return this.numTermino == ter.getNumTermino() && this.añoTermino == ter.getAñoTermino();
        } else{
           return false;
        }
    }
}
