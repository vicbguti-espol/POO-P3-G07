import java.util.ArrayList;
import java.util.Iterator;
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
    static ArrayList<Juego> juegos = new ArrayList<>();
    
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
     * Pedir datos para obtener una nueva materia
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
    static void setMateriaConsole(ArrayList<Materia> materias){
        Map<Integer, Materia> mIndexadas = getMateriasIndexadas(materias);
        
        // Obtener una lista de codigos de las materias
        ArrayList<String> codigos = new ArrayList<>();
        for (Materia m: materias){
            codigos.add(m.getCodigo());
        }
        
        // Mostrar materias por índice
        separador();
        for (Map.Entry<Integer, Materia> entry: mIndexadas.entrySet()){
            System.out.println((entry.getKey() + 1) + ". " + entry.getValue());
        }
        separador();
        
        
        System.out.println("Ingrese el codigo o indice de la materia a editar");

        // Acoger el índice de la materia recibido
        if(sc.hasNextInt()){
            Materia m1 = mIndexadas.get(sc.nextInt()-1);
            sc.nextLine();

            Materia m2 = materias.get(materias.indexOf(m1));
            System.out.println("Ingrese el nuevo nombre de la materia");
            m2.setNombre(sc.nextLine());

            System.out.println("Ingrese la nueva cantidad de niveles de "
                    + "la materia");
            m2.setCantNiveles(sc.nextInt());
            sc.nextLine();
        }
        else if(sc.hasNextLine()){
            // Acoger el código
            String codigo = sc.nextLine();
            
            // Validar la existencia del código 
            while(!codigos.contains(codigo)){
                System.out.println("Ingrese un código existente");
                codigo = sc.nextLine();
            }
            
            // Obtener el índice de la materia en codigos
            int indCodigo = codigos.indexOf(codigo);
            Materia m = materias.get(indCodigo);
            
            // Modificar nombre y cantidad de niveles de la materia
            System.out.println("Ingrese el nuevo nombre de la materia");
            m.setNombre(sc.nextLine());

            System.out.println("Ingrese la nueva cantidad de niveles de la materia");
            m.setCantNiveles(sc.nextInt());
            sc.nextLine();
        }
        
    }
    /**
     * Obtener una materia por consola entre materias disponibles
     * @param materias
     * @return 
     */
    static Materia getMateriaConsole(ArrayList<Materia> materias){
        // Dar opciones de materias existentes
        visualizarMaterias(materias);
        // Pedir al usuario ingresar el índice de la materia
        System.out.println("Seleccionar el índice de la materia (e.g 0)");
        int indMateria = sc.nextInt();
        sc.nextLine();
        // Obtener la materia por indexación
        return materias.get(indMateria);
    }
    
    /**
     * Métodos de visualización
     */
    
    /**
     * Mostrar las materias disponibles
     * @param materias 
     */
    static void visualizarMaterias(ArrayList<Materia> materias){
        separador();
        // Mostrar las materias indexadas
        System.out.println("Materias disponibles");
        int i;
        for (i = 0; i < materias.size(); i++){
            System.out.println(i + ". " + materias.get(i));
        }
        separador();
    }
    /**
     * Mostrar los términos académicos disponibles
     * @param paralelos 
     */
    static void visualizarTerminosAcademicos(ArrayList<TerminoAcademico> 
            terminosAcademicos){
        separador();
        System.out.println("Términos académicos disponibles");
        // Mostrar las materias indexadas
        int i;
        for (i = 0; i < terminosAcademicos.size(); i++){
            System.out.println(i + ". " + terminosAcademicos.get(i));
        }
        separador();
    }
    /**
     * Mostrar los paralelos disponibles
     * @param paralelos 
     */
    static void visualizarParalelos(ArrayList<Paralelo> paralelos){
        separador();
        System.out.println("Paralelos disponibles");
        // Mostrar las materias indexadas
        int i;
        for (i = 0; i < paralelos.size(); i++){
            System.out.println(i + ". " + paralelos.get(i));
        }
        separador();
        
    }
    /**
     * Mostrar preguntas por materia
     * @param preguntas
     * @param m 
     */
    static void visualizarPreguntas(ArrayList<Pregunta> preguntasMateria){
        separador();
        // Indicar la materia de las preguntas
        System.out.println("Preguntas de " + 
                preguntasMateria.get(0).getMateria());
        
        // Mostrar preguntas indexadas
        for (int i = 0; i < preguntasMateria.size(); i++){
            System.out.println( "Índice: "  + (i+1) + "\n" +  
                    preguntasMateria.get(i));
        }
        separador();
        
    }
    
    /**
     * Métodos para configuración de paralelos
     */
    
    /**
     * Pedir datos para obtener un paralelo por consola
     * @param materias
     * @param terminosAcademicos
     * @return 
     */
    static Paralelo getParaleloConsole(ArrayList<Materia> materias, 
            ArrayList<TerminoAcademico> terminosAcademicos){
        // Obtener una materia bajo indexación
        Materia m = getMateriaConsole(materias);
        
        // Dar opciones de paralelos existentes
        visualizarTerminosAcademicos(terminosAcademicos);
        // Pedir al usuario el índice del paralelo
        System.out.println("Seleccionar el índice del término académico (e.g 1)");
        TerminoAcademico t = terminosAcademicos.get(sc.nextInt());
        sc.nextLine();
        
        // Pedir al usuario el número de paralelo
        System.out.println("Ingresar el número de paralelo");
        int n = sc.nextInt();
        sc.nextLine();
        
        // Devolver el paralelo
        return new Paralelo(n, m, t, estudiantesP3);
    }
    /**
     * Obtener paralelo a partir de paralelos existentes
     * @param paralelos
     * @return 
     */
    static Paralelo getParaleloConsole(ArrayList<Paralelo> paralelos){
        // Mostrar los paralelos disponibles
        visualizarParalelos(paralelos);
        // Pedir al usuario el paralelo a su elección
        System.out.println("Ingresar el índice del paralelo (e.g. 0)");
        return paralelos.get(sc.nextInt());
    }
    
    /**
     * Métodos para administración de preguntas
     */
    /**
     * Obtener una pregunta a partir de una entrada del usuario
     * @param materias
     * @return 
     */
    public static Pregunta getPreguntaConsole(ArrayList<Materia> materias){
        // Obtener materia por consola
        Materia mat = getMateriaConsole(materias);
        // Obtener el nivel máximo de la materia
        int lvlMax = mat.getCantNiveles();
        // Declarar el nivel de la pregunta
        int n = 0;
        // Declarar el ArrayList de las respuestas
        ArrayList<Respuesta> respuestas = new ArrayList<>();
        // Pedir al usuario un nivel apropiado al rango de cero al nivel máximo
        do{
            System.out.println("Ingresar el nivel de dificultad de 0 a " + 
                    lvlMax);
            n = sc.nextInt();
            sc.nextLine();
        }while(n < 1 || n > lvlMax);
        // Pedir al usuario el ingreso de la pregunta
        System.out.println("Ingresar el enunciado de la pregunta");
        String pregunta = sc.nextLine();
        // Pedir al usuario el ingreso de la respuesta correcta
        System.out.println("Ingresar la respuesta correcta");
        // Agregar respuesta correcta
        respuestas.add(new Respuesta(sc.nextLine(), 
                TipoRespuesta.CORRECTA));
        // Pedir las tres posibles respuestas restantes
        for (int i = 1; i < 4; i++){
            System.out.println("Ingresar la respuesta posible " + i);
            // Agregar la i-ésima respuesta posible
            respuestas.add(new Respuesta(sc.nextLine(), 
                TipoRespuesta.INCORRECTA));
        }
        return new Pregunta(pregunta,n,mat, respuestas);
    }
    /**
     * Obtener una pregunta a partir de seleccionar en preguntas existentes
     * @param materias
     * @param preguntas
     * @return 
     */
    public static Pregunta getPreguntaConsole(ArrayList<Materia> materias, 
        ArrayList<Pregunta> preguntas){
            // Obtener materia por consola
            Materia m = getMateriaConsole(materias);
            // Obtener preguntas por materia
            ArrayList<Pregunta> preguntasMateria = Pregunta.getPreguntasMateria(preguntas, m);
            // Mostrar las preguntas de la materia
            visualizarPreguntas(preguntasMateria);
            // Pedir al usuario el índice de la materia a eliminar
            System.out.println("Indicar el índice de la pregunta");
            int indPregunta = sc.nextInt() - 1;
          return preguntasMateria.get(indPregunta);
      }
    
    /**
     * Métodos agregados para estudiante 
     * @param p
     * @param tp
     * @return 
     */
    public static int getMatriculaConsole(Paralelo p, TipoEstudiante tp){
        // Obtener el arreglo de estudiantes del paralelo
        ArrayList<Estudiante> estudiantes = p.getEstudiantes();
        separador();
        // Mostrar el paralelo
        System.out.println(p);
        // Mostrar los estudiantes del paralelo
        for (Estudiante e: estudiantes){
            System.out.println(e);
        }
        separador();
        // Pedir al usuario la manera de obtner el estudiante
        System.out.println("Ingresar la matrícula del estudiante " + tp + " o "
                + "cero (0) en caso de escoger a un "
                + "estudiante aleatoriamente");
        int matricula = sc.nextInt();
        sc.nextLine();
        return matricula;
    }
    
    /**
     * Métodos de juego
     */
    
    /**
     * Pedir al usuario la cantidad de preguntas por nivel adecuado a las 
     * condiciones
     * @param preguntas
     * @return 
     */
    public static int getPreguntasPerLvlConsole(ArrayList<Pregunta> preguntas){
        // Obtener la cantidad máxima alcanzable en todos los niveles
        int maxPreguntas = Juego.getMaxSize(preguntas);
        int preguntasPerLvl = 0;

        // Pedir cantidad de preguntas por nivel
        System.out.println("Ingresar la cantidad de preguntas por "
                + "nivel entre 1 y " + maxPreguntas);
        do{
            preguntasPerLvl = sc.nextInt();
        } while((preguntasPerLvl < 1) || (preguntasPerLvl 
                > maxPreguntas));
        
        return preguntasPerLvl;
    }
    /**
     * Obtener la fecha mediante pedido al usuario
     * @return 
     */
    public static String getFechaConsole(){
        // Pedir al usuario ingresar la fecha
        System.out.println("Ingresar fecha en formato dd-mm-yy");
        return sc.nextLine();
    }
    /**
     * Obtener juego mediante pedido de datos al usuario
     * @param materias
     * @param paralelos
     * @param preguntas
     * @return 
     */
    public static Juego getJuegoConsole(ArrayList<Materia> materias, 
        ArrayList<Paralelo> paralelos, ArrayList<Pregunta> preguntas){
        
        // Realizar el juego con materias que tengan preguntas
        // Pedir al usuario la materia
        Materia m = getMateriaConsole(materias);
        // Pedir al usuario el paralelo
        Paralelo p = getParaleloConsole(paralelos);
        // Obtener las preguntas por materia
        ArrayList<Pregunta> preguntasMateria = 
                Juego.getPreguntasMateria(m, preguntas);
        
        while (preguntasMateria.size() == 0){
            System.out.println("Probar con alguna otra materia o "
                    + "paralelo que cuente con preguntas disponibles");
            m = getMateriaConsole(materias);
            p = getParaleloConsole(paralelos);
            preguntasMateria = Juego.getPreguntasMateria(m, preguntas);
        }
        
        
        // Obtener preguntas por nivel pidiendole al usuario
        int preguntasPerLvl = getPreguntasPerLvlConsole(preguntasMateria);
        // Obtener matrícula del participante pidiéndole al usuario
        System.out.println("Ingresar estudiante participante");
        int matriculaParticipante = getMatriculaConsole(p, 
                TipoEstudiante.PARTICIPANTE);
        // Obtener matrícula del estudiante de apoyo pidiéndole al usuario
        int matriculaApoyo = getMatriculaConsole(p, TipoEstudiante.APOYO);
        // Obtener fecha pidiéndole al usuario
        String fecha = getFechaConsole();
        
        // Obtener matrícula del participante
        return new Juego(m, preguntasMateria, p, 
                matriculaParticipante, matriculaApoyo,
                preguntasPerLvl, fecha);
    }
    
    /**
     * Métodos de submenús
     */
    
    /**
     * Manu Main que retorna la opción escogida
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
    /**
     * Muestra el menú de las configuraciones
     * @return 
     */
    public static int menuconfig(){
        String menuconfig = "Menú\n1) Administrar términos académicos\n2) "
                + "Administrar materias y paralelos\n"
                + "3) Administrar preguntas\n4) Volver al menú principal";
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
    /**
     * Menú de preguntas que retorna la opción escogida
     * @return 
     */
    public static int menupreguntas(){
        String menupreguntas = "Menú\n1) Visualizar preguntas\n"
                + "2) Agregar pregunta\n3) Eliminar pregunta\n"
                + "4) Volver al menú principal";
        int opcionpreguntas;
        do{
            System.out.println(menupreguntas);
            System.out.println("Escoga una opción");
            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, ingrese un"
                        + " número válido del 1 al 4.");
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
     * Métodos utilitarios
     */
    /**
     * Separador de texto a base de asteriscos
     */
    static void separador(){
        System.out.println("*".repeat(20));
    }
    
    /**
     * Método main principal para mostra al usuario
     * @param args 
     */
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

        
        materias.add(new Materia("CCPG1052", "PROGRAMACIÓN ORIENTADA A OBJETOS",2));
        materias.add(new Materia("CCPG1000", "ALGEBRA LINEAL",2));
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
        //Pregunta 3 de prueba
        ArrayList<Respuesta> respuestas3 = new ArrayList<>();
        respuestas3.add(new Respuesta("0",TipoRespuesta.INCORRECTA));
        respuestas3.add(new Respuesta("13",TipoRespuesta.CORRECTA));
        respuestas3.add(new Respuesta("7",TipoRespuesta.INCORRECTA));
        respuestas3.add(new Respuesta("112",TipoRespuesta.INCORRECTA));
        //pREGUNTA 4 DE PRUEBA
        ArrayList<Respuesta> respuestas4 = new ArrayList<>();
        respuestas4.add(new Respuesta("0",TipoRespuesta.INCORRECTA));
        respuestas4.add(new Respuesta("2",TipoRespuesta.CORRECTA));
        respuestas4.add(new Respuesta("7",TipoRespuesta.INCORRECTA));
        respuestas4.add(new Respuesta("112",TipoRespuesta.INCORRECTA));
        
        Pregunta p1= new Pregunta("Cuanto es 2+2?",2, 
                materias.get(0), respuestas1);
        Pregunta p2= new Pregunta("Cuanto es 10-12?",2,
                materias.get(0), respuestas2);
        Pregunta p3= new Pregunta("Cuanto es 1+12",3,
                materias.get(0), respuestas3);
        Pregunta p4= new Pregunta("Cuanto es 1+1",3,
                materias.get(0), respuestas4);
        preguntas.add(p1);
        preguntas.add(p2);
        preguntas.add(p3);
        preguntas.add(p4);
        
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
                    // Mostrar materias y paralelos
                    visualizarMaterias(materias);
                    visualizarParalelos(paralelos);
                    
                    int opcionmaterias = menuMaterias();
                    
                    if (opcionmaterias==1){
                        //Código para Ingresar MATERIA
                        System.out.println("Eligió Ingresar Materia");
                        Materia m = getMateriaConsole();
                        materias.add(m);
                    }else if(opcionmaterias==2){
                        //Código para Ingresar Editar MATERIA
                        System.out.println("Eligió Editar Materia");
                        setMateriaConsole(materias);
                    }else if(opcionmaterias==3){
                        //Código para Agregar PARALELO
                        System.out.println("Eligió Agregar Paralelo");
                        Paralelo p = getParaleloConsole(materias, 
                                terminosAcademicos);
                        paralelos.add(p);
                    }else if(opcionmaterias==4){
                        //Código para Eliminar PARALELO
                        System.out.println("Eligió eliminar paralelo");
                        Paralelo p = getParaleloConsole(paralelos);
                        paralelos.remove(p);
                        
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
                        // Obtener materia por consola
                        Materia m = getMateriaConsole(materias);
                        // Obtener preguntas por materia
                        ArrayList<Pregunta> preguntasMateria = 
                                Pregunta.getPreguntasMateria(preguntas,m);
                        // Mostrar las preguntas de la materia
                        visualizarPreguntas(preguntasMateria);
                    }else if(opcionpreguntas==2){
                        //Código para Agregar Preguntas
                        System.out.println("Eligió Agregar pregunta");
                        Pregunta p = getPreguntaConsole(materias);
                        preguntas.add(p);
                    }else if(opcionpreguntas==3){
                        //Código para Eliminar pregunta
                        System.out.println("Eligió Eliminar Pregunta");
                        Pregunta p = getPreguntaConsole(materias, preguntas);
                        preguntas.remove(p);
                    }else if(opcionpreguntas==4){
                        opcionprincipal = 0;
                    }
                }else if(opcion1==4){
                opcionprincipal=0;
                }
            }else if(opcionprincipal==2){
                System.out.println("Escogió iniciar Juego, Éxitos!");
                // Declarar el objeto Juego
                Juego j = getJuegoConsole(materias, paralelos, preguntas);
                separador();
                // Empezar el juego
                j.visualizarPreguntas();
                // Agregar juego a juegos para el reporte
                juegos.add(j);
            } else if(opcionprincipal==3){
                Juego.generarReporte(juegos);
            }
        }
    }
}