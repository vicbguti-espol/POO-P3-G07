package com.espol.proyecto.configuracion;

import java.util.ArrayList;
import java.util.Scanner;

public class AdministracionTerminosAcademicos {
    // Atributos
    protected static ArrayList<TerminoAcademico> terminosAcademicos;
    protected int añoActual;
    private TerminoAcademico terminoJuego;
    
    // Scanner
    Scanner sc = new Scanner(System.in);
    
    // Constructor
    public AdministracionTerminosAcademicos(){
        this.terminosAcademicos = new ArrayList<TerminoAcademico>();
        añoActual = 2023;
    }
    
    // Pedir datos de un término académico para obtener el índice del término académico
    public int pedirDatos(){
        // Indice del termino académico
        int indTer = -1;
        
        do{
            // Pedir al usuario el año del término académico
            System.out.println("Ingresar el año del término académico");
            int añoTer = sc.nextInt();
            sc.nextLine();

            // Pedir al usuario el número del término académico
            System.out.println("Ingresar el número del término académico");
            int numTer = sc.nextInt();
            sc.nextLine();
            
            // Hallar el índice de el término académico
            indTer = terminosAcademicos.indexOf(new TerminoAcademico(numTer, añoTer));
            
            if (numTer == -1){
                // Pedir al usuario una entrada
                System.out.println("¿Desea realizar una entrada? (si/no)");
                String in = sc.nextLine();
                
                if (in.equalsIgnoreCase("no")){
                    indTer = -2;
                }
            }
        }while(indTer == -1);
        
        return indTer;
    }
    
    // Método para seleccionar el término de juego
    public void solicitarTermino(){
        // Pedir los datos del término para buscar su índice en la lista
        int indTermino = this.pedirDatos();
        // Asignar el término para el juego
        this.terminoJuego = terminosAcademicos.get(indTermino);
        
    }
    
   
}
