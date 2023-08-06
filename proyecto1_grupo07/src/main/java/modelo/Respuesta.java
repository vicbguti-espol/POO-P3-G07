package modelo;

import java.io.Serializable;
import java.util.Objects;

public class Respuesta implements Serializable{
    private static final long serialVersionUID = 1;
    private String texto;
    private TipoRespuesta tipo;
    
    public Respuesta(){
        tipo = TipoRespuesta.CORRECTA;
    }
    
    public Respuesta(String texto, TipoRespuesta tipo){
        this.texto = texto;
        this.tipo = tipo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public TipoRespuesta getTipo() {
        return tipo;
    }

    public void setTipo(TipoRespuesta tipo) {
        this.tipo = tipo;
    }

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
   
    
    @Override
    public String toString(){
        return texto;
    }
}
