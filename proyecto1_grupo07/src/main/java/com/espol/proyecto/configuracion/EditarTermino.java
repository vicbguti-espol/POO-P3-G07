package com.espol.proyecto.configuracion;

public class EditarTermino extends AdministracionTerminosAcademicos {
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
