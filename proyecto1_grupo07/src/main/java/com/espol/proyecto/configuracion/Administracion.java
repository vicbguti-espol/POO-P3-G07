package com.espol.proyecto.configuracion;

import java.util.ArrayList;
import java.util.Scanner;

public class Administracion {
    // Atributos
    protected static ArrayList<TerminoAcademico> terminosAcademicos;
    protected int añoActual;
    
    // Scanner
    Scanner sc = new Scanner(System.in);
    
    // Constructor
    public Administracion(){
        this.terminosAcademicos = new ArrayList<TerminoAcademico>();
        añoActual = 2023;
    }
    

    
   
}
