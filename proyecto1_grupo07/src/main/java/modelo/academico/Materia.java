package modelo.academico;
import java.util.*;
import java.io.*;

public class Materia implements Serializable{
    private String codigo;
    private String nombre;
    private int cantNiveles;
    
    public Materia(String codigo, String nombre, int cantidad){
        this.codigo=codigo;
        this.nombre=nombre;
        this.cantNiveles=cantidad;
    }
    
    /**
     * Obtener arreglo de materias a partir de una ruta de archivo
     * @param pathMaterias
     * @return 
     */
    public static ArrayList<Materia> cargarMaterias(String pathMaterias) {
        //subirArchivo();
        ArrayList<Materia> materiascargadas = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(pathMaterias))) {
            materiascargadas = (ArrayList<Materia>) in.readObject();
        } catch (EOFException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return materiascargadas;
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
        return nombre;

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
