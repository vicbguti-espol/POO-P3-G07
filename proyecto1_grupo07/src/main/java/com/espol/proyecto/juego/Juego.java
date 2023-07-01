package com.espol.proyecto.juego;

import com.espol.proyecto.configuracion.AdministracionTerminosAcademicos;

public class Juego {
    // Atributos
    private Materia materiaJuego;
    private Paralelo paraleloJuego;
    private int nPreguntasPerLvl;
    private Estudiante participante;
    private Estudiante support;
    private TerminoAcademico terminoJuego;
    
    // Constructor
    public Juego(Materia mJuego, Paralelo pJuego, int nPerLvl, Estudiante part, Estudiante sup){
        this.materiaJuego  = mJuego;
        this.paraleloJuego = pJuego;
        this.nPreguntasPerLvl = nPerLvl;
        this.participante = part;
        this.support = sup;
        this.terminoJuego = AdministracionTerminosAcademicos.getTerminoJuego();
    }
}
