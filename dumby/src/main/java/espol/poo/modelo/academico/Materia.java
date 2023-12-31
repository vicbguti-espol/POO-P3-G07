package espol.poo.modelo.academico;
import java.io.*;
import java.util.*;

/**
 *
 * @author Omen
 */
public class Materia implements Serializable{
    private static final String path="archivo\\materias.ser";
    private static final long serialVersionUID = 1;
    private String codigo;
    private String nombre;
    private int cantNiveles;

    /**
     *
     */
    public static ArrayList<Materia> materias = cargarMaterias();
    //public static ArrayList<Materia> materias = new ArrayList<>(Arrays.asList(new Materia("CCPG1052", "PROGRAMACIÓN ORIENTADA A OBJETOS",2),new Materia("CCPG1000", "ALGEBRA LINEAL",2)));
    
    /**
     *
     * @param codigo
     * @param nombre
     * @param cantidad
     */
    public Materia(String codigo, String nombre, int cantidad){
        this.codigo=codigo;
        this.nombre=nombre;
        this.cantNiveles=cantidad;
    }

    /**
     *
     * @return
     */
    public String getCodigo(){
        return codigo;
    }

    /**
     *
     * @return
     */
    public String getNombre(){
        return nombre;
    }

    /**
     *
     * @param n
     */
    public void setNombre(String n){
        this.nombre=n;
    }

    /**
     *
     * @param c
     */
    public void setCodigo(String c){
        this.codigo=c;
    }

    /**
     *
     * @param c
     */
    public void setCantNiveles(int c){
        this.cantNiveles=c;
    }

    /**
     *
     * @return
     */
    public int getCantNiveles(){
        return cantNiveles;
    }
    
    /**
     *
     * @return
     */
    @ Override
    public String toString(){
//        return "Materia {codigo: " + codigo +", nombre: " + nombre +
//                ", niveles: " + cantNiveles + "}";
        return String.valueOf(codigo);

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
        final Materia other = (Materia) obj;
        if (this.cantNiveles != other.cantNiveles) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return Objects.equals(this.nombre, other.nombre);
    }

    
//    public static void main(String[]args){
//        subirArchivo();
//    }

    /**
     *
     */
    public static void subirArchivo(){
      try(ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(path))){
            out.writeObject(new ArrayList<Materia>(
                    Arrays.asList(new Materia("CCPG1052",
                            "PROGRAMACIÓN ORIENTADA A OBJETOS",3),
                            new Materia("CCPG1000", "ALGEBRA LINEAL",
                                    2))));
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }

    /**
     *
     * @return
     */
    public static ArrayList<Materia> cargarMaterias() {
        //subirArchivo();
        ArrayList<Materia> materiascargadas = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            materiascargadas= (ArrayList<Materia>) in.readObject();
        } catch (EOFException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return materiascargadas;
    }
    // Para editar una materia seria necesario eliminarla y agregarla nuevamente en tiempo de compilacion.

    /**
     *
     * @param m
     */
    public static void agregarMateria(Materia m){
        if(!materias.contains(m)){
            materias.add(m);
        }
        try(ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(path))){
            out.writeObject(materias);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param mantigua
     * @param nombre
     * @param cantNiveles
     */
    public static void editarMateria(Materia mantigua, String nombre, int cantNiveles){
        if(materias.contains(mantigua)){
            materias.remove(materias.indexOf(mantigua));
        }
        mantigua.setNombre(nombre);
        mantigua.setCantNiveles(cantNiveles);
        materias.add(mantigua);
        try(ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(path))){
            out.writeObject(materias);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param m
     */
    public static void eliminarMateria(Materia m){
        materias.remove(m);
        try(ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(path))){
            out.writeObject(materias);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
