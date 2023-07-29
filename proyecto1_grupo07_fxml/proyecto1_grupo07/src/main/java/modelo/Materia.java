package modelo;
import java.util.*;

public class Materia {
    private String codigo;
    private String nombre;
    private int cantNiveles;
    
    public Materia(String codigo, String nombre, int cantidad){
        this.codigo=codigo;
        this.nombre=nombre;
        this.cantNiveles=cantidad;
    }

    public String getCodigo(){
        return codigo;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String n){
        this.nombre=n;
    }
    public void setCodigo(String c){
        this.codigo=c;
    }
    public void setCantNiveles(int c){
        this.cantNiveles=c;
    }
    public int getCantNiveles(){
        return cantNiveles;
    }
    
    @ Override
    public String toString(){
        return "Materia {codigo: " + codigo +", nombre: " + nombre +
                ", niveles: " + cantNiveles + "}";

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
        final Materia other = (Materia) obj;
        if (this.cantNiveles != other.cantNiveles) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return Objects.equals(this.nombre, other.nombre);
    }
    
    
}
