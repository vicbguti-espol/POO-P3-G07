package modelo.academico;

// Importar clases
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
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
    
    /**
     * Obtener un arreglo de tipo TerminoAcademico a partir de una ruta de archivo
     * @param pathTerminos
     * @return 
     */
    public static ArrayList<TerminoAcademico> cargarTerminosAcademicos(String pathTerminos){
        ArrayList<TerminoAcademico> terminoscargados = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(pathTerminos))) {
            terminoscargados = (ArrayList<TerminoAcademico>) in.readObject();
        } catch (EOFException e) {

        } catch (Exception e) {
            System.out.println(e);
        }
        return terminoscargados;
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
        return añoTermino + "-" + numTermino;
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
