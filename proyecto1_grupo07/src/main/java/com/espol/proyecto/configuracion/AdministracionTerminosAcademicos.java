package com.espol.proyecto.configuracion;

import java.util.ArrayList;
import java.util.Scanner;

public class AdministracionTerminosAcademicos {
    // Atributos
    protected static ArrayList<TerminoAcademico> terminosAcademicos;
    protected int añoActual;
    
    // Scanner
    Scanner sc = new Scanner(System.in);
    
    // Constructor
    public AdministracionTerminosAcademicos(){
        this.terminosAcademicos = new ArrayList<TerminoAcademico>();
        añoActual = 2023;
    }
    

    
   
}
