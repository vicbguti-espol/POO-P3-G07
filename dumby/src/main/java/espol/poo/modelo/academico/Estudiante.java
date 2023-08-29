package espol.poo.modelo.academico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Omen
 */
public class Estudiante implements Serializable{
  private static final long serialVersionUID = 1;
  private int matricula;
  private String correo;
  private String nombre;

    /**
     *
     */
    public static ArrayList <Estudiante> estudiantes = new ArrayList<>();
  
    /**
     *
     * @param matricula
     * @param correo
     * @param nombre
     */
    public Estudiante(int matricula, String correo, String nombre) {
        this.matricula = matricula;
        this.correo = correo;
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public int getMatricula() {
        return matricula;
    }

    /**
     *
     * @param matricula
     */
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    /**
     *
     * @return
     */
    public String getCorreo() {
        return correo;
    }

    /**
     *
     * @param correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString(){
        return "Estudiante {matricula: " + matricula + ", nombre: " + nombre 
                + ", correo: " + correo + "}";
    }

    /**
     *
     * @param path
     * @return
     */
    public static ArrayList<Estudiante> cargarEstudiantes (String path){
        ArrayList<Estudiante> lfinal=new ArrayList<>();
        try(BufferedReader lectura=new BufferedReader(new FileReader(path))){
            String line="";
            while((line=lectura.readLine())!=null){
                String[]elementos=line.split(",");
                lfinal.add(new Estudiante(Integer.valueOf(elementos[0]), elementos[1], elementos[2]));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return lfinal;
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
        final Estudiante other = (Estudiante) obj;
        return this.matricula == other.matricula;
    }
    
    

}
