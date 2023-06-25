package com.espol.proyecto.configuracion;

import java.util.ArrayList;

public class Administracion {
    // Atributos
    private ArrayList<TerminoAcademico> terminosAcademicos;
    
    // Atributos de selección
    private TerminoAcademico terminoAcademicoJuego;
    private Materia materiaJuego;
    private Estudiante estudianteJuego;
    
    // Constructor
    public Administracion(){
        this.terminosAcademicos = new ArrayList<TerminoAcademico>();
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
   
}
