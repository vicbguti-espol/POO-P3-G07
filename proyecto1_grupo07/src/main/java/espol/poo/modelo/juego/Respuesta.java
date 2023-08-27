package espol.poo.modelo.juego;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Omen
 */
public class Respuesta implements Serializable{
    private static final long serialVersionUID = 1;
    private String texto;
    private TipoRespuesta tipo;
    
    /**
     *
     */
    public Respuesta(){
        tipo = TipoRespuesta.CORRECTA;
    }
    
    /**
     *
     * @param texto
     * @param tipo
     */
    public Respuesta(String texto, TipoRespuesta tipo){
        this.texto = texto;
        this.tipo = tipo;
    }
    
    /**
     * Constructor para generar una copia de respuesta
     * @param r 
     */
    public Respuesta(Respuesta r){
        texto = r.texto;
        tipo = r.tipo;
    }

    /**
     *
     * @return
     */
    public String getTexto() {
        return texto;
    }

    /**
     *
     * @param texto
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     *
     * @return
     */
    public TipoRespuesta getTipo() {
        return tipo;
    }

    /**
     *
     * @param tipo
     */
    public void setTipo(TipoRespuesta tipo) {
        this.tipo = tipo;
    }
    
    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Respuesta other = (Respuesta) obj;
        if (!Objects.equals(this.texto, other.texto)) {
            return false;
        }
        return this.tipo == other.tipo;
    }
   
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return texto;
    }
    
    
}
