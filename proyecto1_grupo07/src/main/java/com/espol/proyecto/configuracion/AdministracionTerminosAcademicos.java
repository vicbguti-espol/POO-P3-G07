package com.espol.proyecto.configuracion;

import java.util.ArrayList;
import java.util.Scanner;

public class AdministracionTerminosAcademicos {
    // Atributos
    private static ArrayList<TerminoAcademico> terminosAcademicos;
    private int añoActual;
    private TerminoAcademico terminoJuego;
    
    // Constructor
    public AdministracionTerminosAcademicos(){
        this.terminosAcademicos = new ArrayList<TerminoAcademico>();
        añoActual = 2023;
    }
    
    // Scanner
    Scanner sc = new Scanner(System.in);
    
    private TerminoAcademico ObtenerTerminoPorConsola(){
        /**
         * Pedir datos del término académico al usuario
         * Retorna un objeto de tipo TerminoAcademico
         */
        
        // Pedir al usuario el año del término académico
        System.out.println("Ingresar el año del término académico (yyyy)");
        int añoTer = sc.nextInt();
        sc.nextLine();

        // Pedir al usuario el número del término académico
        System.out.println("Ingresar el número del término académico (e.g. 1)");
        int numTer = sc.nextInt();
        sc.nextLine();
        
        return new TerminoAcademico(numTer, añoTer);
    }
    
    private int obtenerIndicePorConsola(){
        /**
         * Pedir datos de un término académico
         * Retorna el índice del término académico
         */
        
        // Indice del termino académico
        int indTer = -1;
        
        do{
            // Hallar el índice de el término académico
            indTer = terminosAcademicos.indexOf(ObtenerTerminoPorConsola());
            
            if (indTer == -1){
                // Pedir al usuario una entrada
                System.out.println("¿Desea realizar una entrada existente? (si/no)");
                String in = sc.nextLine();
                
                if (in.equalsIgnoreCase("no")){
                    indTer = -2;
                }
            }
        }while(indTer == -1);
        
        return indTer;
    }
    
    public void ingresarTerminoPorConsola(){
        /**
         * Pedir por consola los datos del término
         * Agregar objeto TerminoAcademico a terminosAcademicos
         */
        this.terminosAcademicos.add(this.ObtenerTerminoPorConsola());
    }
    
    public void modificarDatosPorConsola(){
        /**
         * Pedir datos de terminoAcademico para modificarlo
         */
        
        // Pedir datos para obtener índice
        int indTer = this.obtenerIndicePorConsola();
        
        // Modificar el año de ser posible
        if (indTer > -1){
            
            // Pedir al usuario qué dato desea modificar
            System.out.println("¿Qué desea modificar? (numero/año)");
            String input = sc.nextLine();
            
            // Modificar de acuerdo sea el caso
            if (input.equalsIgnoreCase("numero")){
                // Pedir al usuario el número por el cual se va modificar
                System.out.println("Indicar el número (e.g. 1)");
                int numNew = sc.nextInt();
                sc.nextLine();

                // Modificar el número
                terminosAcademicos.get(indTer).setNumTermino(numNew);
            } else if (input.equalsIgnoreCase("año")){
                // Pedir el año por el cual se va modificar
                System.out.println("Indicar el año (yyyy)");
                int añoNew = sc.nextInt();
                sc.nextLine();
                
                // Modificar el año
                terminosAcademicos.get(indTer).setAñoTermino(añoNew);
            } 
        }
    }
    
    public void asignarTerminoJuegoPorConsola(){
        /**
         * Seleccionar el término de juego por consola
         */
        
        // Pedir los datos del término para buscar su índice en la lista
        int indTermino = this.obtenerIndicePorConsola();
        // Asignar el término para el juego
        this.terminoJuego = terminosAcademicos.get(indTermino);
        
    }
    
   
}
