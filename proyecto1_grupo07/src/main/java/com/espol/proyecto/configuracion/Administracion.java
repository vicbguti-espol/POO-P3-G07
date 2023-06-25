package com.espol.proyecto.configuracion;

import java.util.ArrayList;

public class Administracion {
    // Atributos
    private ArrayList<TerminoAcademico> terminosAcademicos;
    
    // Atributos de selección
    protected TerminoAcademico terminoAcademicoJuego;

    private Materia materiaJuego;
    private Estudiante estudianteJuego;
    
    // Constructor
    public Administracion(){
        this.terminosAcademicos = new ArrayList<TerminoAcademico>();
    }
    
    // Getter

    public TerminoAcademico getTerminoAcademicoJuego() {
        return terminoAcademicoJuego;
    }
    
    // Setters
    
    public void setTerminoAcademicoJuego(TerminoAcademico terminoAcademicoJuego) {
        this.terminoAcademicoJuego = terminoAcademicoJuego;
    }

    public void setMateriaJuego(Materia materiaJuego) {
        this.materiaJuego = materiaJuego;
    }
    
    
    // TÉRMINOS ACADÉMICOS
    
    // Método para ingresar un término académico
    public void ingresarTermino(TerminoAcademico termino){
        this.terminosAcademicos.add(termino);
    }
    
    // Método para buscar término académico buscarTermino()
    public boolean buscarTerminoAcademico(TerminoAcademico terminoBuscar){
        // Recorrer la lista de terminosAcademicos
        for (TerminoAcademico termino: this.terminosAcademicos){
            // De encontrarse el término académico true
            if (termino.equals(terminoBuscar)){
                return true;
            }
        }
        return false;
    }
    
    
    // Método para modificar el año de un término académico de terminosAcademicos
    public void modificarAño(TerminoAcademico termino, int año){
        // Verificar la existencia del término académico en terminosAcademicos
        if (this.terminosAcademicos.contains(termino)){
            // Obtener el índice de termino en terminosAcademicos
           int indexTermino = this.terminosAcademicos.indexOf(termino);
           // Obtener el terminoAcademico
           TerminoAcademico t = this.terminosAcademicos.get(indexTermino);
           // Modificar el año del término académico
           t.setAñoTermino(año);
            
        }
    }
    
    // Método para modificar el número de un término académico de terminosAcademicos
    public void modificarNumero(TerminoAcademico termino, int numero){
    // Verificar la existencia del término académico en terminosAcademicos
        if (this.terminosAcademicos.contains(termino)){
            // Obtener el índice de termino en terminosAcademicos
           int indexTermino = this.terminosAcademicos.indexOf(termino);
           // Obtener el terminoAcademico
           TerminoAcademico t = this.terminosAcademicos.get(indexTermino);
           // Modificar el número del término académico
           t.setAñoTermino(numero);
            
        }
    }
    
}
