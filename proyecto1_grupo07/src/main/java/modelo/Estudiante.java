package modelo;

import java.util.ArrayList;

public class Estudiante {
  private String matricula;
  private String correo;
  private String nombre;

  public static ArrayList <Estudiante> estudiantes = new ArrayList<Estudiante>();
  
  public Estudiante(String matriculaString, String correoString, String nombreString ) {
    this.matricula=matriculaString;
    this.correo=correoString;
    this.nombre=nombreString;
  }

  public String getMatricula(){
    return matricula;
}
}
