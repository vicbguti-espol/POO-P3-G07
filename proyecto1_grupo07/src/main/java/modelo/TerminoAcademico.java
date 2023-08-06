package modelo;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
// Importar clases
import java.util.ArrayList;


public class TerminoAcademico implements Serializable {
    // Atributos 
    private static final long serialVersionUID = 1;
    private int numTermino;
    private int añoTermino;
    //public static ArrayList<TerminoAcademico> terminosAcademicos = new ArrayList<>();
    public static ArrayList<TerminoAcademico> terminosAcademicos = cargarTerminosAcademicos();
    private static final String path="Proyecto\\POO-P3-G07\\POO-P3-G07\\proyecto1_grupo07\\archivo\\terminos.ser";
    
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
    public static void main(String[] args) {
        eliminarTerminosAcademicos(new TerminoAcademico(1,2020));
        for(TerminoAcademico t: terminosAcademicos){
            System.out.println(t.toString());
        }
    }
    /*public static void subirArchivo(){
        terminosAcademicos.add(new TerminoAcademico(1, 2023));
        try(ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(path))){
            out.writeObject(terminosAcademicos);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/
    public static ArrayList<TerminoAcademico>cargarTerminosAcademicos(){
        ArrayList<TerminoAcademico> terminoscargados = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            terminoscargados= (ArrayList<TerminoAcademico>) in.readObject();
        } catch (EOFException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return terminoscargados;
    }
    public static void agregarTerminosAcademicos(TerminoAcademico t){
        if(!terminosAcademicos.contains(t)){
            terminosAcademicos.add(t);
        }
        try(ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(path))){
            out.writeObject(terminosAcademicos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void eliminarTerminosAcademicos(TerminoAcademico t){
        terminosAcademicos.remove(t);
        try(ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(path))){
            out.writeObject(terminosAcademicos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
