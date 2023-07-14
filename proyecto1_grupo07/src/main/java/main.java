/**
 *
 * @author joshz
 */
import java.util.ArrayList;
import modelo.*;
import java.util.Scanner;

public class main {    
    static int añoActual = 2023;
    static TerminoAcademico terminoJuego;
    
    /**
     * Pedir datos para obtener un término académico
     * @return 
     */
    public static TerminoAcademico getTerminoConsole(){
        // Pedir al usuario el año del término académico
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresar el año del término académico (yyyy)");
        int añoTer = sc.nextInt();
        sc.nextLine();

        // Pedir al usuario el número del término académico
        System.out.println("Ingresar el número del término académico (e.g. 1)");
        int numTer = sc.nextInt();
        sc.nextLine();
        
        return new TerminoAcademico(numTer, añoTer);
    }
    /**
     * Pedir datos para obtener un índice de término académico existente
     * @return 
     */
    public static int setIndiceConsole(){
        // Indice del termino académico
        int indTer = -1;
        do{
            // Hallar el índice de el término académico
            indTer = terminosAcademicos.indexOf(getTerminoConsole());
            
            if (indTer == -1){
                // Pedir al usuario una entrada de no existir el término académico
                System.out.println("Ingresar un término académico existente");
            }
        }while(indTer == -1);
        
        return indTer;
    }
    /**
     * Pedir datos para modificar número o año de término académico
     */
    public static void setAtributosConsole(){
        // Pedir datos para obtener índice
        Scanner sc = new Scanner(System.in);
        int indTer = setIndiceConsole();
        
        // Pedir al usuario qué dato desea modificar
        System.out.println("¿Qué desea modificar? (numero/año)");
        String input = sc.nextLine();

        // Modificar de acuerdo sea el caso
        switch(input){
            case "numero" -> {
                // Pedir al usuario el número por el cual se va modificar
                System.out.println("Indicar el número (e.g. 1)");
                int numNew = sc.nextInt();
                sc.nextLine();
                // Modificar el número
                terminosAcademicos.get(indTer).setNumTermino(numNew);
            }
            case "año" -> {
                // Pedir el año por el cual se va modificar
                System.out.println("Indicar el año (yyyy)");
                int añoNew = sc.nextInt();
                sc.nextLine();
                // Modificar el año
                terminosAcademicos.get(indTer).setAñoTermino(añoNew);
            }
                
        }

    }
    /**
     * Pedir datos para asignar el termino académico de un nuevo juego
     */
    public void setTerminoJuegoConsole(){        
        // Pedir los datos del término para buscar su índice en la lista
        int indTermino = setIndiceConsole();
        // Asignar el término para el juego
        terminoJuego = terminosAcademicos.get(indTermino); 
    }
    public static void ingresarMateria(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el codigo de la materia");
        String codigo=sc.nextLine();

        System.out.println("Ingrese el nombre de la materia");
        String nombre=sc.nextLine();

        System.out.println("Ingrese la cantidad de niveles de la materia");
        int cantNiveles=sc.nextInt();
        sc.nextLine();
        Materia m =new Materia(codigo,nombre,cantNiveles);
        materias.add(m);
        
    }
    public static Pregunta ingresarPregunta(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Ingresar el codigo de la materia");
    Materia mat = Materia.verificarExistencia(sc.nextLine());
    int n;
    do{
        System.out.println("Ingresar el nivel de dificultad");
        n = sc.nextInt();
        sc.nextLine();
    }while(n < 1 || n > mat.getCantNiveles());
    System.out.println("Ingresar el texto de la pregunta");
    String pregunta = sc.nextLine();
    return new Pregunta(pregunta,n,mat);
}
    public static int eliminarPregunta(){
      Scanner sc = new Scanner(System.in);
      System.out.println("Ingrese el numero de pregunta que desea eliminar");
      return sc.nextInt()-1);
  }
    public static int menu(){
        String menu = "Menú\n1) Configuraciones\n2) Nuevo Juego\n3) Reporte\n4) Salir";
        Scanner sc = new Scanner(System.in);
        int opcionmenu;
        do{
            System.out.println(menu);
            System.out.println("Escoga una opción");
            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido del 1 al 4.");
                sc.next();
            }
            opcionmenu = sc.nextInt();
            if (opcionmenu <1 ||opcionmenu>4){
                System.out.println("Ingrese un número válido.");
            }
        }while(opcionmenu <1 ||opcionmenu>4);
        return opcionmenu;
    }
    public static int menuconfig(){
        String menuconfig = "Menú\n1) Administrar términos académicos\n2) Administrar materias y paralelos\n3) Administrar preguntas\n4) Salir";
        Scanner sc = new Scanner(System.in);
        int opcionconfiguracion;
        do{
            System.out.println(menuconfig);
            System.out.println("Escoga una opción");
            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido del 1 al 4.");
                sc.next();
            }
            opcionconfiguracion = sc.nextInt();
            if (opcionconfiguracion <1 ||opcionconfiguracion>4){
                System.out.println("Ingrese un número válido.");
            }
        }while(opcionconfiguracion <1 ||opcionconfiguracion>4);
        return opcionconfiguracion;
    }
    public static int menuterminos(){
        String menuterminos = "Menú\n1) Ingresar término\n2) Editar Termino\n3) Configurar termino para el juego";
        Scanner sc = new Scanner(System.in);
        int opcionterminos;
        do{
            System.out.println(menuterminos);
            System.out.println("Escoga una opción");
            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido del 1 al 3.");
                sc.next();
            }
            opcionterminos = sc.nextInt();
            if (opcionterminos <1 ||opcionterminos>3){
                System.out.println("Ingrese un número válido.");
            }
        }while(opcionterminos <1 ||opcionterminos>3);
        return opcionterminos;
    }
    public static int menumaterias(){
        String menumaterias = "Menú\n1) Ingresar materia\n2) Editar materia\n3) Agregar paralelo\n4) Eliminar paralelo";
        Scanner sc = new Scanner(System.in);
        int opcionmaterias;
        do{
            System.out.println(menumaterias);
            System.out.println("Escoga una opción");
            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido del 1 al 4.");
                sc.next();
            }
            opcionmaterias = sc.nextInt();
            if (opcionmaterias <1 ||opcionmaterias>4){
                System.out.println("Ingrese un número válido.");
            }
        }while(opcionmaterias <1 ||opcionmaterias>4);
        return opcionmaterias;
    }
    public static int menupreguntas(){
        String menupreguntas = "Menú\n1) Visualizar preguntas\n2) Agregar pregunta\n3) Eliminar pregunta";
        Scanner sc = new Scanner(System.in);
        int opcionpreguntas;
        do{
            System.out.println(menupreguntas);
            System.out.println("Escoga una opción");
            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido del 1 al 4.");
                sc.next();
            }
            opcionpreguntas = sc.nextInt();
            if (opcionpreguntas <1 ||opcionpreguntas>3){
                System.out.println("Ingrese un número válido.");
            }
        }while(opcionpreguntas <1 ||opcionpreguntas>3);
        return opcionpreguntas;
    }
    public static void main(String[] args) {
        ArrayList<TerminoAcademico> terminosAcademicos = new ArrayList<>();
        ArrayList<Materia> materias = new ArrayList<>();
        // Elementos default para probar el codigo
        materias.add(new Materia("0001","FP",3));
        materias.add(new Materia("0002","FEM",2));
        materias.add(new Materia("0003","CYS",4));
        materias.add(new Materia("0004","ARP",1));
        materias.add(new Materia("0005","CVV",2));
        materias.add(new Materia("0006","FM",5));
        //Lista de preguntas
        ArrayList<Pregunta> preguntas= new ArrayList<>();
        //Pregunta 1 de prueba
        ArrayList<Respuesta> respuestas1 = new ArrayList<>();
        respuestas1.add(new Respuesta("4",TipoRespuesta.CORRECTA));
        respuestas1.add(new Respuesta("5",TipoRespuesta.INCORRECTA));
        respuestas1.add(new Respuesta("0",TipoRespuesta.INCORRECTA));
        respuestas1.add(new Respuesta("22",TipoRespuesta.INCORRECTA));
        //Pregunta 2 de prueba
        ArrayList<Respuesta> respuestas2 = new ArrayList<>();
        respuestas2.add(new Respuesta("4",TipoRespuesta.INCORRECTA));
        respuestas2.add(new Respuesta("-2",TipoRespuesta.CORRECTA));
        respuestas2.add(new Respuesta("0",TipoRespuesta.INCORRECTA));
        respuestas2.add(new Respuesta("22",TipoRespuesta.INCORRECTA));
        Pregunta p1= new Pregunta("Cuanto es 2+2?",1,Materia.verificarExistencia("0006"));
        Pregunta p2= new Pregunta("Cuanto es 10-12?",2,Materia.verificarExistencia("0006"));
        p1.setRespuestas(respuestas1);
        p2.setRespuestas(respuestas2);
        preguntas.add(p1);
        preguntas.add(p2);
        ////////////////////////////////
        terminosAcademicos.add(new TerminoAcademico(1,añoActual));
        
        int opcionprincipal=0;
        while (opcionprincipal!=4){
            opcionprincipal=menu();
            if (opcionprincipal==1){
                int opcion1 = menuconfig();
                //CODIGO PARA LAS CONFIGURACIONES
                if (opcion1==1){
                    // Codigo para administrar términos académicos
                    System.out.println("Escogio la opcion "+opcion1);
                    int opcionterminos=menuterminos();
                    if (opcionterminos==1){
                        //Código para Ingresar terminos academicos
                    System.out.println("Eligió Ingresar Término Académico");
                    }else if(opcionterminos==2){
                        //Código para Ingresar editar academicos
                    System.out.println("Eligió Editar Término Académico");
                    }else if(opcionterminos==3){
                        //Código para configurar terminos academicos
                    System.out.println("Eligió Configurar Término para el Juego");
                    }
                }else if(opcion1==2){
                    // Codigo para administrar materias y  paralelos
                    System.out.println("Escogio la opcion "+opcion1);
                    int opcionmaterias=menuterminos();
                    if (opcionmaterias==1){
                        //Código para Ingresar MATERIA
                    System.out.println("Eligió Ingresar Materia");
                    }else if(opcionmaterias==2){
                        //Código para Ingresar Editar MATERIA
                    System.out.println("Eligió Editar Materia");
                    }else if(opcionmaterias==3){
                        //Código para Agregar PARALELO
                    System.out.println("Eligió Configurar Término para el Juego");
                    }else if(opcionmaterias==4){
                        //Código para Eliminar PARALELO
                    System.out.println("Eligió Configurar Término para el Juego");
                    }
                }else if(opcion1==3){
                    // Codigo para administrar preguntas
                    System.out.println("Escogio la opcion "+opcion1);
                    int opcionpreguntas=menupreguntas();
                    if (opcionpreguntas==1){
                        //Código para Visualizar Preguntas
                    System.out.println("Eligió Visualizar Preguntas");
                    }else if(opcionpreguntas==2){
                        //Código para Agregar Preguntas
                    System.out.println("Eligió Agregar pregunta");
                    
                    Pregunta p = ingresarPregunta();
                    p.ingresarRespuestas();
                    
                    }else if(opcionpreguntas==3){
                        //Código para Eliminar pregunta
                    System.out.println("Eligió Eliminar Pregunta");
                    }
                }else if(opcion1==4){
                opcionprincipal=0;
                }
            }
        }
    }
}