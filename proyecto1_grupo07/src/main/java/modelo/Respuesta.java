/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Omen
 */
public class Respuesta {
    private String texto;
    private TipoRespuesta tipo;

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
    
    public Respuesta(String texto, TipoRespuesta tipo){
        this.texto = texto;
        this.tipo = tipo;
    }
}
