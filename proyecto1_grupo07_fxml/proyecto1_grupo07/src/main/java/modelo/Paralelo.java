package modelo;

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
