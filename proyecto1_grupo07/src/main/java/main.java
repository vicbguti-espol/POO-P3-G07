/**
 *
 * @author joshz
 */
import java.util.ArrayList;
import java.util.Map;
import modelo.*;
import java.util.Scanner;
import java.util.TreeMap;

public class main {    
    static int añoActual = 2023;
    static TerminoAcademico terminoJuego;
    static Scanner sc = new Scanner(System.in);
    // Estudiantes ejemplares en reemplazo a archivos
    static ArrayList<Estudiante> estudiantesP3 = new ArrayList<>();
    
    /**
     * Métodos de configuración de términos académicos
     */
    
    /**
     * Pedir datos para obtener un término académico
     * @return 
     */
    static TerminoAcademico getTerminoConsole(){
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
    /**
     * Pedir datos para ingresar un término bajo validación
     * @param terminosAcademicos
     * @return 
     */
    static TerminoAcademico getTerminoValidado(ArrayList<TerminoAcademico> 
            terminosAcademicos){
        // Obtener un término académico por consola para validar
        TerminoAcademico t = getTerminoConsole();
        
        // Validar las condiciones del término académico
        while ((terminosAcademicos.indexOf(t) != -1) 
                ||(t.getAñoTermino() < añoActual)){
            /**
             * Pedir al usuario que ingrese otro termino 
             * academico de modo que este no se encuentre 
             * repetido
            **/
            System.out.println("Ingresar un término académico "
                    + "válido (que no se encuentre repetido "
                    + "y que no sea menor al año actual)");
            t = getTerminoConsole();
        }
        return t;
    }
    /**
     * Pedir datos para obtener un índice de término académico existente
     * @param terminosAcademicos
     * @return 
     */
    private static int setIndiceConsole(ArrayList<TerminoAcademico> terminosAcademicos){
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
     * @param terminosAcademicos
     */
    static void setAtributosConsole(ArrayList<TerminoAcademico> terminosAcademicos){
        // Pedir datos para obtener índice
        int indTer = setIndiceConsole(terminosAcademicos);
        
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
    static void setTerminoJuegoConsole(ArrayList<TerminoAcademico> terminosAcademicos){        
        // Pedir los datos del término para buscar su índice en la lista
        int indTermino = setIndiceConsole(terminosAcademicos);
        // Asignar el término para el juego
        terminoJuego = terminosAcademicos.get(indTermino); 
    }
    
    /**
     * Métodos de configuración de materias
     */
    
    /**
     * Pedir datos para ingresar una nueva materia
     * @param materias 
     */
    static Materia getMateriaConsole() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el codigo de la materia");
        String codigo = sc.nextLine();
        
        System.out.println("Ingrese el nombre de la materia");
        String nombre = sc.nextLine();

        System.out.println("Ingrese la cantidad de niveles de la materia");
        int cantNiveles = sc.nextInt();
        sc.nextLine();
        return new Materia(codigo,nombre,cantNiveles);
        
    }
    /**
     * Obtener las materias indexadas para listas enumeradas
     * @param materias
     * @return 
     */
    static Map<Integer, Materia> getMateriasIndexadas(ArrayList<Materia>
            materias){
        // Crear map de materias indexadas 
        Map<Integer, Materia> mIndexadas = new TreeMap<>(); 
        for (int i = 0; i < materias.size(); i++){
            mIndexadas.put(i, materias.get(i));
        }
        return mIndexadas;
    }
    /**
     * Pedir datos para modificar una materia
     * @param materias 
     */
    static void setMateria(ArrayList<Materia> materias){
        Map<Integer, Materia> mIndexadas = getMateriasIndexadas(materias);
        
        // Mostrar materias por índice
        separador();
        for (Map.Entry<Integer, Materia> entry: mIndexadas.entrySet()){
            System.out.println((entry.getKey() + 1) + ". " + entry.getValue());
        }
        separador();
        
        
        System.out.println("Ingrese el codigo o indice de la materia a editar");

        if(sc.hasNextInt()){
            Materia m1 = mIndexadas.get(sc.nextInt()-1);
            sc.nextLine();

            Materia m2 = materias.get(materias.indexOf(m1));
            System.out.println("Ingrese el nuevo nombre de la materia");
            m2.setNombre(sc.nextLine());

            System.out.println("Ingrese la nueva cantidad de niveles de la materia");
            m2.setCantNiveles(sc.nextInt());
            sc.nextLine();
        }
        else if(sc.hasNextLine()){
            Materia m = getMateriaConsole();
            // Comprobar la existencia de la materia en materias
            while (!materias.contains(m)){
                System.out.println("Ingresar una materia existente");
                m = getMateriaConsole();
            }
            
            System.out.println("Ingrese el nuevo nombre de la materia");
            m.setNombre(sc.nextLine());

            System.out.println("Ingrese la nueva cantidad de niveles de la materia");
            m.setCantNiveles(sc.nextInt());
            sc.nextLine();
        }
        
    }
    
    /**
     * Métodos para configuración de paralelos
     */
    
    static Paralelo getParaleloConsole(){
        // Obtener materia y término academico por consola
        Materia m = getMateriaConsole();    
        TerminoAcademico t = getTerminoConsole();
        // Pedir por consola el número de paralelo
        int n;
        System.out.println("Ingresar el número del paralelo");
        while(!sc.hasNextInt()){
            System.out.println("Ingresar un número entero");
            sc.next();
        }
        n = sc.nextInt();
        
        return new Paralelo(n, m, t, estudiantesP3);
    }
    
    public static Pregunta ingresarPregunta(ArrayList<Materia> materias){
        // Obtener materia por consola
        Materia mat = getMateriaConsole();
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
      return (sc.nextInt()-1);
  }
    
    /**
     * Métodos de submenús
     */
    
    /**
     * Manu main que retorna la opción escogida
     * @return 
     */
    public static int menu(){
        String menu = "Menú\n1) Configuraciones\n2) Nuevo Juego\n3) Reporte\n4) Salir";
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
    /**
     * Pedir al usuario la opción de administración terminos y retornar esta opción
     * @return 
     */
    public static int menuterminos(){
        // Declarar mensaje de menú de términos
        String menuterminos = "Menú\n1) Ingresar término\n"
                + "2) Editar Termino\n3) Configurar termino para el juego\n"
                + "4) Volver al menú";
        int opcionterminos = 0; //Inicializar variable de opcion escogida
        do{
            // Imprimir el menú para términos
            System.out.println(menuterminos);
            // Pedir al usuario una opción
            System.out.println("Escoga una opción");
            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, "
                        + "ingrese un número válido del 1 al 4.");
                sc.next();
            }
            opcionterminos = sc.nextInt();
            if (opcionterminos <1 ||opcionterminos>4){
                System.out.println("Ingrese un número válido.");
            }
        }while(opcionterminos <1 ||opcionterminos>4);
        return opcionterminos;
    }
    /**
     * Pedir al usuario la opción en el menú de materias, retornar la opción
     * @return 
     */
    public static int menuMaterias(){
        String menumaterias = "Menú\n1) Ingresar materia\n2) "
                + "Editar materia\n3) Agregar paralelo\n4) Eliminar paralelo\n"
                + "5) Volver al menú principal";
        int opcionmaterias;
        do{
            System.out.println(menumaterias);
            System.out.println("Escoga una opción");
            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, "
                        + "ingrese un número válido del 1 al 5.");
                sc.next();
            }
            opcionmaterias = sc.nextInt();
            if (opcionmaterias <1 ||opcionmaterias>5){
                System.out.println("Ingrese un número válido.");
            }
        }while(opcionmaterias <1 ||opcionmaterias>5);
        return opcionmaterias;
    }
    public static int menupreguntas(){
        String menupreguntas = "Menú\n1) Visualizar preguntas\n2) Agregar pregunta\n3) Eliminar pregunta";
        int opcionpreguntas;
        do{
            System.out.println(menupreguntas);
            System.out.println("Escoga una opción");
            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido del 1 al 4.");
                sc.next();
            }
            opcionpreguntas = sc.nextInt();
            if (opcionpreguntas <1 ||opcionpreguntas>4){
                System.out.println("Ingrese un número válido.");
            }
        }while(opcionpreguntas <1 ||opcionpreguntas>4);
        return opcionpreguntas;
    }
    
    /**
     * Funciona como separador para el menú a base de asteriscos
     */
    static void separador(){
        System.out.println("*".repeat(20));
    }
    
    public static void main(String[] args) {
        ArrayList<Paralelo> paralelos = new ArrayList();
        ArrayList<TerminoAcademico> terminosAcademicos = new ArrayList<>();
        ArrayList<Materia> materias = new ArrayList<>();
        
        estudiantesP3.add(new Estudiante(202110136,
            "maactorr@espol.edu.ec", 
            "ACELDO TORRES MARIA GRAZIA"));
        estudiantesP3.add(new Estudiante(202108643, 
                "jcaguila@espol.edu.ec", 
                "AGUILAR TINOCO JEAN CARLOS"));
        estudiantesP3.add(new Estudiante(202111928, 
                "jamorett@espol.edu.ec", 
                "AMORETTI SANCHEZ JUAN CARLOS"));
        estudiantesP3.add(new Estudiante(202105946, 
                "angbeand@espol.edu.ec", 
                "ANDRADE VELASCO ANGELLO BERNIE"));
        estudiantesP3.add(new Estudiante(202211355, 
                "dienarau@espol.edu.ec", 
                "ARAUJO ORTEGA DIEGO ENZO JAVIER"));
        estudiantesP3.add(new Estudiante(202104816, 
                "nfazu@espol.edu.ec", 
                "AZU PERLAZA NICOLE FERNANDA"));
        estudiantesP3.add(new Estudiante(202110219, 
                "ivagbald@espol.edu.ec", 
                "BALDEON BAQUE IVAN GONZALO"));
        estudiantesP3.add(new Estudiante(202113056, 
                "melesbar@espol.edu.ec", 
                "BARBERAN GALLARDO MELISSA ESTEFANIA"));
        estudiantesP3.add(new Estudiante(202109328, 
                "dmbasili@espol.edu.ec", 
                "BASILIO ACEBO DANIELA MILENA"));
        estudiantesP3.add(new Estudiante(202210712, 
                "vicbguti@espol.edu.ec", 
                "BORBOR GUTIERREZ VICTOR DANIEL"));

        
        materias.add(new Materia("CCPG1052", 
                "PROGRAMACIÓN ORIENTADA A OBJETOS",2));
        
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
        // Pregunta p1= new Pregunta("Cuanto es 2+2?",1, Materia.verificarExistencia("0006",materias));
        // Pregunta p2= new Pregunta("Cuanto es 10-12?",2, Materia.verificarExistencia("0006",materias));
        // p1.setRespuestas(respuestas1);
        // p2.setRespuestas(respuestas2);
        // preguntas.add(p1);
        // preguntas.add(p2);
        
        terminosAcademicos.add(new TerminoAcademico(1,2023));
        
        
        paralelos.add(new Paralelo(3, materias.get(0), 
                terminosAcademicos.get(0), estudiantesP3));
        ////////////////////////////////
        
        int opcionprincipal=0;
        while (opcionprincipal!=4){
            opcionprincipal=menu();
            if (opcionprincipal==1){
                int opcion1 = menuconfig();
                //CODIGO PARA LAS CONFIGURACIONES
                if (opcion1==1){
                    // Mostrar los términos academicos disponibles
                    System.out.println("Escogio la opcion "+opcion1);
                    separador();
                    System.out.println("Términos académicos disponibles");
                    // Imprimir el listado de términos académicos
                    for (TerminoAcademico t: terminosAcademicos){
                        System.out.println(t);
                    }
                    separador();
                    int opcionterminos=menuterminos();
                    if (opcionterminos==1){
                        //Código para Ingresar terminos academicos
                        System.out.println("Eligió Ingresar Término Académico");
                        TerminoAcademico t = getTerminoValidado(terminosAcademicos);
                        // Agregar el término académico validado
                        terminosAcademicos.add(t);
                    }else if(opcionterminos==2){
                        //Código para Ingresar editar academicos
                        System.out.println("Eligió Editar Término Académico");
                        setAtributosConsole(terminosAcademicos);
                    }else if(opcionterminos==3){
                        //Código para configurar terminos academicos
                        System.out.println("Eligió Configurar Término "
                                + "para el Juego");
                        setTerminoJuegoConsole(terminosAcademicos);
                    } else if(opcionterminos==4){
                        //Código para configurar terminos academicos
                        System.out.println("Eligió retornar al menú principal");
                    }
                }else if(opcion1==2){
                    // Codigo para administrar materias y  paralelos
                    System.out.println("Escogio la opcion "+opcion1);
                    // Imprimir las materias dictadas
                    separador();
                    System.out.println("Materias dictadas");
                    for (Materia m: materias){
                        System.out.println(m);
                    }
                    // Imprimir paralelos dictados
                    separador();
                    System.out.println("Paralelos dictados");
                    for (Paralelo p: paralelos){
                        System.out.println(p);
                    }
                    separador();
                    
                    int opcionmaterias=menuMaterias();
                    if (opcionmaterias==1){
                        //Código para Ingresar MATERIA
                        System.out.println("Eligió Ingresar Materia");
                        Materia m = getMateriaConsole();
                        materias.add(m);
                    }else if(opcionmaterias==2){
                        //Código para Ingresar Editar MATERIA
                        System.out.println("Eligió Editar Materia");
                        setMateria(materias);
                    }else if(opcionmaterias==3){
                        //Código para Agregar PARALELO
                        System.out.println("Eligió Agregar Paralelo");
                        Paralelo p = getParaleloConsole();
                        
                        // Volver a pedir siempre y cuando no exista la materia 
                        // o el término académico
                        while ((!materias.contains(p.getMateria()) || 
                                (!terminosAcademicos.
                                        contains(p.getTerminoAcademico())))){
                            System.out.println("Ingresar una materia "
                                    + "o término académico existente");
                            p = getParaleloConsole();
                        }
                        
                        paralelos.add(p);

                    }else if(opcionmaterias==4){
                        //Código para Eliminar PARALELO
                    System.out.println("Eligió Configurar "
                            + "Término para el Juego");
                    }else if(opcionmaterias==5){
                        System.out.println("Eligió retornar al "
                                + "menú principal");
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
                    
                    Pregunta p = ingresarPregunta(materias);
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