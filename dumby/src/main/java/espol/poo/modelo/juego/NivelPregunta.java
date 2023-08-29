
package espol.poo.modelo.juego;

import java.util.ArrayList;

/**
 *
 * @author Omen
 */
public class NivelPregunta {
    Integer nivel;
    ArrayList<Pregunta> preguntas;

    /**
     *
     * @return
     */
    public Integer getNivel() {
        return nivel;
    }

    /**
     *
     * @param nivel
     */
    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    /**
     *
     * @return
     */
    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    /**
     *
     * @param preguntas
     */
    public void setPreguntas(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    /**
     *
     * @param nivel
     * @param preguntas
     */
    public NivelPregunta(Integer nivel, ArrayList<Pregunta> preguntas) {
        this.nivel = nivel;
        this.preguntas = preguntas;
    }
}
