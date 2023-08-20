package modelo.juego;

public class PreguntaComodin {
    Pregunta pregunta;
    Comodin comodin;

    public PreguntaComodin(Pregunta pregunta, Comodin comodin) {
        this.pregunta = pregunta;
        this.comodin = comodin;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public Comodin getComodin() {
        return comodin;
    }

    public void setComodin(Comodin comodin) {
        this.comodin = comodin;
    }

    
    
    
}
