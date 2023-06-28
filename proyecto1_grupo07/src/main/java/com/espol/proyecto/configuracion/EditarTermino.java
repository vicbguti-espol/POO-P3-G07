package com.espol.proyecto.configuracion;

public class EditarTermino extends AdministracionTerminosAcademicos {
    
    // Modificar año de un términoAcadémico
    public void modificarAño(){
        // Pedir datos para obtener índice
        int indTer = this.pedirDatos();
        
        // Modificar el año de ser posible
        if (indTer > -1){
            // Pedir al usuario el año por el cual se va modificar
            System.out.println("Cambiar año a");
            int añoNew = sc.nextInt();
            sc.nextLine();
            
            // Modificar el año
            terminosAcademicos.get(indTer).setAñoTermino(añoNew);
        }

    }
    
    // Modificar el número del término
    public void modificarNumero(){
        // Pedir datos para obtener índice
        int indTer = this.pedirDatos();
        
        
        // Modificar el número de ser posible
        if(indTer > -1){
            // Pedir al usuario el número por el cual se va modificar
            System.out.println("Cambiar número a");
            int numNew = sc.nextInt();
            sc.nextLine();
            
            // Modificar el número
            terminosAcademicos.get(indTer).setNumTermino(numNew);
        }
    }
    
    
}
