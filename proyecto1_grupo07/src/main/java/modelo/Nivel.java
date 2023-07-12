package modelo;
import java.util.*;

public class Nivel {
    private ArrayList<Pregunta> preguntas;
    private Materia materia;
    private TerminoAcademico termino;

    public Nivel(ArrayList pr, Materia m, TerminoAcademico t){
        this.preguntas=pr;
        this.materia=m;
        this.termino=t;
    }
}
