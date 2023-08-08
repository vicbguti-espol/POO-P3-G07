package modelo.academico;

import java.util.ArrayList;

public class Estudiante {
  private int matricula;
  private String correo;
  private String nombre;

  public static ArrayList <Estudiante> estudiantes = new ArrayList<Estudiante>();
  
    public Estudiante(int matricula, String correo, String nombre) {
        this.matricula = matricula;
        this.correo = correo;
        this.nombre = nombre;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString(){
        return "Estudiante {matricula: " + matricula + ", nombre: " + nombre 
                + ", correo: " + correo + "}";
    }

}
