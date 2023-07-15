package modelo;

public class Respuesta {
    private String texto;
    private TipoRespuesta tipo;
    
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
    public String toString(){
        return texto;
    }
}
