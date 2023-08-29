package espol.poo.modelo.academico;

import espol.poo.excepciones.TerminoExistenteException;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
// Importar clases
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Omen
 */
public class TerminoAcademico implements Serializable {
    // Atributos 
    private static final long serialVersionUID = 1;
    private int numTermino;
    private int añoTermino;
    //public static ArrayList<TerminoAcademico> terminosAcademicos = new ArrayList<>();

    /**
     *
     */
    public static ArrayList<TerminoAcademico> terminosAcademicos = cargarTerminosAcademicos();
    // public static ArrayList<TerminoAcademico> terminosAcademicos = new ArrayList<>(Arrays.asList(new TerminoAcademico(1, 2023)));
    private static final String path="archivo\\terminos.ser";
    
    // Constructor

    /**
     *
     * @param numTermino
     * @param añoTermino
     */
    public TerminoAcademico(int numTermino, int añoTermino){
        this.numTermino = numTermino;
        this.añoTermino = añoTermino;
    }
    
    /**
     *
     */
    public TerminoAcademico(){
        
    }
    
    // Getters

    /**
     *
     * @return
     */
    public int getNumTermino(){
        return this.numTermino;
    }
    
    /**
     *
     * @return
     */
    public int getAñoTermino(){
        return this.añoTermino;
    }
    
    // Setters

    /**
     *
     * @param numTermino
     */
    public void setNumTermino(int numTermino) {
        this.numTermino = numTermino;
    }

    /**
     *
     * @param añoTermino
     */
    public void setAñoTermino(int añoTermino) {
        this.añoTermino = añoTermino;
    }
    
    
    
//    public static void main(String[] args) {
//        subirArchivo();
//    }

    /**
     *
     */
    public static void subirArchivo(){
        terminosAcademicos.add(new TerminoAcademico(1, 2023));
        try(ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(path))){
            out.writeObject(terminosAcademicos);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @return
     */
    public static ArrayList<TerminoAcademico>cargarTerminosAcademicos(){
        ArrayList<TerminoAcademico> terminoscargados = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            terminoscargados= (ArrayList<TerminoAcademico>) in.readObject();
        } catch (EOFException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return terminoscargados;
    }
    
    /**
     * Escribir la lista de términos una vez es modificada
     */
    private static void escribirLista() throws IOException{
        try(ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(path))){
            out.writeObject(terminosAcademicos);
        }
    }
    
    /**
     * Agregar un término académico
     * @param t
     * @throws java.io.IOException
     * @throws espol.poo.excepciones.TerminoExistenteException
     */
    public static void agregarTerminosAcademicos(TerminoAcademico t) throws IOException, TerminoExistenteException{
        if(!terminosAcademicos.contains(t)){
            terminosAcademicos.add(t);
            escribirLista();
        } else{
            throw new TerminoExistenteException();
        }
    }
    
    /**
     * Eliminar un término académico de la lista de términos académicos
     * @param t 
     */
    public static void eliminarTerminosAcademicos(TerminoAcademico t){
        if (terminosAcademicos.contains(t)){
            terminosAcademicos.remove(t);
            try{
                escribirLista();
            } catch(IOException i){
                System.out.println("Error en escritura una vez eliminado un "
                        + "objeto");
            } catch(Exception e){
                System.out.println(e);
            }
            
        }
        
    }
    
    /**
     * Comprobar que dos términos académicos son iguales
     * @param o
     * @return 
     */
    public boolean equals(Object o){
        if (o == this){
            return true;
        }
        if (o != null && getClass() == o.getClass()){
            TerminoAcademico ter = (TerminoAcademico) o;
            return this.numTermino == ter.getNumTermino() && this.añoTermino == ter.getAñoTermino();
        } else{
           return false;
        }
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return añoTermino + "-" + numTermino;
    }
}
