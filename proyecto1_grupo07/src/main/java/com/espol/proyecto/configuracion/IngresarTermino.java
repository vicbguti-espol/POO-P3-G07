package com.espol.proyecto.configuracion;

import java.util.ArrayList;

public class IngresarTermino extends AdministracionTerminosAcademicos {
    // Método para obtener los numeros de los térmions académicos en lista por año
    private ArrayList<Integer> obtenerNumeroTerminos(int año){
        // Crear un ArrayList añosTerminos
        ArrayList<Integer> numeroTerminos = new ArrayList<Integer>(); 
        // Recorrer terminosAcademicos
        for (TerminoAcademico terminoIte: super.terminosAcademicos){
            if (terminoIte.getAñoTermino() == año){
                // Agregar numTermino a numeroTerminos
                numeroTerminos.add(terminoIte.getNumTermino());
            }

        }
        // Retornar añosTerminos
        return numeroTerminos;
    }
    
    // Método para obtener los años de los términos académicos en lista
    private ArrayList<Integer> obtenerAñosTerminos(){
        // ArrayList de años
        ArrayList<Integer> años =  new ArrayList<Integer>();
        // Recorrer la lista de terminosAcademicos
        for (TerminoAcademico terminoIte: super.terminosAcademicos){
            // Agregar el año al ArrayList
            años.add(terminoIte.getAñoTermino());
        }
        // Retornar la lista de años
        return años;
    }
    
    // Método para pedirAño
    private int pedirAño(){
        /**
         * Método para pedir un año bajo condiciones
         */
        // Obtener la lista de años
        ArrayList<Integer> años = obtenerAñosTerminos();
        // Pedir al usuario año de término académico
        System.out.println("Ingresar el año del término académico");
        int añoTermino = sc.nextInt();
        sc.nextLine();     
        
        // Variables booleanas
        boolean esMenor = añoTermino < super.añoActual;
        boolean repetido = años.contains(añoTermino);
        
        // Verificar que año no sea menor al actual o se encuentre repetido
        while (esMenor || repetido){
            // Caso en el que el año el menor al actual
            if (esMenor){
                System.out.println("Ingresar un año que no sea menor al actual");
                añoTermino = sc.nextInt();
                sc.nextLine();     
            }
            // Caso en el que el año está repetido
            else if (repetido){
                System.out.println("Ingresar un año no repetido");
                añoTermino = sc.nextInt();
                sc.nextLine();
            }
        } 
        return añoTermino;
    }
    
    // Método para pedir un número de término
    private int pedirNumeroTermino(int año){
        // Pedir al usuario el número del término académico
        System.out.println("Ingresar el número del término académico");
        int numeroTermino = sc.nextInt();
        sc.nextLine();
        
        // Obtener lista de número de términos
        ArrayList<Integer> nthTerminos = obtenerNumeroTerminos(año);
        
        // Validar que no este repetido el n-ésimo término
        while (nthTerminos.contains(numeroTermino)){
            System.out.println("Ingresar un término que no esté repetido");
            numeroTermino = sc.nextInt();
            sc.nextLine();
        }
        return numeroTermino;
    }

    // Método para ingresar un término académico
    public void ingresarTerminoAcademico(){
        // Pedir año al usuario
        int año = pedirAño();
        
        // Pedir numero de término para el año al usuario
        int numeroTermino = pedirNumeroTermino(año);

        super.terminosAcademicos.add(new TerminoAcademico(numeroTermino, año));
    }
}
