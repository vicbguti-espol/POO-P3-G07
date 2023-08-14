package modelo.academico;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import java.util.*;

public class Paralelo {
    private int numero;
    private Materia materia;
    private TerminoAcademico termino;
    private ArrayList<Estudiante> estudiantes;

    public Paralelo(int numero, Materia materia, TerminoAcademico termino, 
            ArrayList<Estudiante> estudiantes) {
        this.numero = numero;
        this.materia = materia;
        this.termino = termino;
        this.estudiantes = estudiantes;
    }
    
    /**
     * Obtener un arreglo de paralelos a partir de una ruta de archivo
     * @param pathParalelos
     * @return 
     */
    public static ArrayList<Paralelo> cargarParalelos(String pathParalelos){
        ArrayList<Paralelo> paraleloscargados = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(pathParalelos))) {
            paraleloscargados= (ArrayList<Paralelo>) in.readObject();
        } catch (EOFException e) {

        } catch (Exception e) {
            System.out.println(e);
        }
        return paraleloscargados;
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
        return String.valueOf(numero);
    }


}
