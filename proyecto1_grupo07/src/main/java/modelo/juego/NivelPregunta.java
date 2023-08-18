
package modelo.juego;

import java.util.ArrayList;

public class NivelPregunta {
    Integer nivel;
    ArrayList<Pregunta> preguntas;

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public NivelPregunta(Integer nivel, ArrayList<Pregunta> preguntas) {
        this.nivel = nivel;
        this.preguntas = preguntas;
    }
}
