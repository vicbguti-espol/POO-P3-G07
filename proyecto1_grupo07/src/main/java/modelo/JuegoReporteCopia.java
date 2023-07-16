package main.java.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Juego {
    private ArrayList<Pregunta> preguntas;
    private Estudiante participante;
    private Estudiante apoyo;
    private int nPreguntasPerLvl;
    private String fecha;
    private int lvlMax;
    private int nPreguntasContestadas;
    private ArrayList<Comodin> comodinesUtilizados;
    private int segundos=0;
    static ArrayList <Juego> juegos=new ArrayList<Juego>();
    
    // Instanciar scanner
    static Scanner sc = new Scanner(System.in);
    
    /**
     * Constructor de clase
     * @param materia
     * @param paralelo
     * @param matriculaParticipante
     * @param matriculaApoyo
     * @param nPreguntasPerLvl 
     */
    public Juego(Materia materia, ArrayList<Pregunta> preguntasMateria, 
            Paralelo paralelo, int matriculaParticipante,
            int matriculaApoyo, int n, String f,ArrayList<Comodin>ComodinesUtilizados){
        preguntas = preguntasMateria; // Asignar preguntas por materia
        setEstudiante(paralelo, matriculaParticipante,
                TipoEstudiante.PARTICIPANTE); // Asignar participante
        setEstudiante(paralelo, matriculaParticipante,
                TipoEstudiante.APOYO); // Asignar support
        fecha = f; // Asignar fecha
        nPreguntasPerLvl = n; // Set n preguntas por nivel
        comodinesUtilizados=new ArrayList<Comodin>();
    }
    
    /**
     * Setting de preguntas a partir de una materia
     * @param materia 
     */
    public static ArrayList<Pregunta> getPreguntasMateria(Materia materia, 
            ArrayList<Pregunta> pregs){
        ArrayList<Pregunta> preguntasMaterias = new ArrayList<>();
        // Iterar la lista de preguntas estáticas
        for (Pregunta p: pregs){ 
            // Verificar materias iguales
            if (p.getMateria().equals(materia)){
                preguntasMaterias.add(p); // Agregar pregunta a preguntas
            }
        }
        return preguntasMaterias;
    }
    
    /**
     * Establecer el valor de participante o apoyo entre estudiantes de un mismo
     * paralelo y a partir de la matrícula que este tenga. (0) random
     * @param paralelo
     * @param matricula
     * @param tipo 
     */
    private void setEstudiante(Paralelo paralelo, int matricula,
            TipoEstudiante tipo){
        // Obtener lista de estudiantes en el paralelo
        ArrayList<Estudiante> estudiantes = paralelo.getEstudiantes();
        // Obtener un índice random
        int random_index = (int)(Math.random() * estudiantes.size());
        
        Estudiante lastStudent = null;
        TipoEstudiante tp = TipoEstudiante.PARTICIPANTE;
        TipoEstudiante ts = TipoEstudiante.APOYO;
        
        if (matricula != 0){
            // Matrícula variable al iterar
            int matIte = -1;

            // Definir iterator para el ArrayList estudiantes
            Iterator<Estudiante> it = estudiantes.iterator();

            // Asignar la siguiente matrícula en lista a matIte
            while ((matIte != matricula) && (it.hasNext())){
                lastStudent = it.next();
                matIte = lastStudent.getMatricula();
            }
            
            if (!(matricula == matIte)){
                matricula = 0; // De no encontrar matricula pasar a random
            } else{ // Asignar participante o apoyo en caso de existir matrícula
                if (tipo.equals(tp)){
                    participante = lastStudent; 
                } else if(tipo.equals(ts)){
                    apoyo = lastStudent;
                }
            }
        }
        
        
        if ((matricula == 0) && (tipo.equals(tp))){
            // Asignar participante random de no tener matrícula
            participante = estudiantes.get(random_index);
        } else if ((matricula == 0) && (tipo.equals(ts))){
            // Asignar apoyo de tener matrícula
            apoyo = estudiantes.get(random_index); 
        }
    }
    
    /**
     * Obtener preguntas organizadas por nivel en un map
     * @return 
     */
    public static Map<Integer, ArrayList<Pregunta>> getPreguntasByLevel(
            ArrayList<Pregunta> preguntas){
        // Crear un hashmap con contadores por niveles
        Map<Integer, ArrayList<Pregunta>> m = new TreeMap<>();
        
        for (Pregunta p: preguntas){ // Iterar las preguntas de juego
            int lvl = p.getNivel(); 
            if (m.containsKey(lvl)){
                m.get(lvl).add(p); // Agregar la pregunta según el nivel 
            } else{
                m.put(lvl, new ArrayList<>());
                m.get(lvl).add(p);
            }
        }
        
        return m;
    }
    
    /**
     * Devolver n preguntas por nivel
     * @param n
     * @return 
     */
    private Map<Integer, ArrayList<Pregunta>> getPreguntasByLevel(int n){
        Map<Integer, ArrayList<Pregunta>> chosen = new TreeMap<>();
        // Obtener map de preguntasPorNivel
        Map<Integer, ArrayList<Pregunta>> preguntasPerLvl = 
                getPreguntasByLevel(preguntas);
        
        
        for (Map.Entry<Integer, ArrayList<Pregunta>> entry: preguntasPerLvl.entrySet()){
            ArrayList<Pregunta> nPreguntasNivel = new ArrayList<Pregunta>();    
            // Obtener la lista de preguntas del nivel 
            ArrayList<Pregunta> preguntasNivel = entry.getValue();
            Collections.shuffle(preguntasNivel); // Shuffle de preguntas
            
            for (int i = 0; i < n; i++){
                // Agregar pregunta a nPreguntasNivel
                nPreguntasNivel.add(preguntasNivel.get(i));
            }
            chosen.put(n, nPreguntasNivel);
        
        }
        return chosen;
    }
    
    /**
     * Método para obtener los tamaños de los arreglos de preguntas por nivel
     * @param preguntas
     * @return 
     */
    public static Map<Integer, Integer> getSizesByLvl(ArrayList<Pregunta> 
            preguntas){
        // Obtener Map de preguntas por nivel
        Map<Integer, ArrayList<Pregunta>> pregByLvl = 
                Juego.getPreguntasByLevel(preguntas);
        // Decalar map de tamañanos de listas de preguntas por nivel
        Map<Integer, Integer> sizesByLvl = new TreeMap<>();
        // Iterar el map de preguntas por nivel
        for (Map.Entry<Integer, ArrayList<Pregunta>> entry: 
                pregByLvl.entrySet()){
            // Agregar el tamaño del arreglo de preguntas por nivel
            sizesByLvl.put(entry.getKey(),entry.getValue().size());
        }
        return sizesByLvl;
    }
    

    public static int getMaxSize(ArrayList<Pregunta> preguntas){
        // Obtener únicamente los tamaños de arreglos 
        ArrayList<Integer> sizesByLvl = new ArrayList<>(
                getSizesByLvl(preguntas).values());
        // Ordenar los valores de tamaños
        Collections.sort(sizesByLvl);
        // Retornar el máximo de preguntas por nivel para el juego
        return sizesByLvl.get(0);   
    }
    
    /**
     * Método que retorna el literal (S) de la respuesta correcta
     * @param opcionPregunta
     * @return 
     */
    private String obtenerKeyRespuestaCorrecta(Map<String, Respuesta> opcionPregunta){
        for (Map.Entry<String, Respuesta> mapEntry: opcionPregunta.entrySet()){
            Respuesta value = mapEntry.getValue(); // Obtener valor de map
            if (value != null && value.getTipo().equals(TipoRespuesta.CORRECTA)){
                return mapEntry.getKey();
            }
        }
        return null;
    }
    
    /**
     * Método encargado de la funcionalidad principal del juego
     */
    public void visualizarPreguntas(){
        
        // Obtener preguntas por nivel
        Map<Integer, ArrayList<Pregunta>> preguntasPerLvl = getPreguntasByLevel(nPreguntasPerLvl);
       // Decalar input para recibir respuesta 
       String input = "";
       // Decalar el último literalCorrecto seleccionado
       String literalCorrecto = "";
       
       Iterator<Map.Entry<Integer, ArrayList<Pregunta>>> it = preguntasPerLvl.entrySet().iterator();
        // Iterar el map de preguntas por nivel
        while ((it.hasNext()) &&  (input.equalsIgnoreCase(literalCorrecto))){
            Map.Entry<Integer, ArrayList<Pregunta>> entry = (Map.Entry) it;
            // Obtener las preguntas del nivel
            ArrayList<Pregunta> preguntas = entry.getValue(); 
            // Shuffle el ArrayList de las preguntas
            Collections.shuffle(preguntas); 
            // Nivel de pregunta
            lvlMax = entry.getKey();
            // Mostrar por pantalla el nivel en que se encuentra
            System.out.println("Nivel" + lvlMax);
            
            do{
                for (Pregunta p: preguntas){
                // Obtener las respuestas de la pregunta
                ArrayList<Respuesta> respuestas = p.getRespuestas();
                
                Collections.shuffle(respuestas); // Shuffle de respuestas
                
                // Asignar un key value de opción a cada respuesta
                Map<String, Respuesta> opcionPregunta = new TreeMap<>();
                opcionPregunta.put("a", respuestas.get(0));
                opcionPregunta.put("b", respuestas.get(1));
                opcionPregunta.put("c", respuestas.get(2));
                opcionPregunta.put("d", respuestas.get(3));

                // Muestra por consola la pregunta
                System.out.println(preguntas.indexOf(p) + ". " + p.getTexto());
                
                // Iterar el map
                for (Map.Entry<String, Respuesta> mapEntry: opcionPregunta.entrySet()){
                        // Visualizar pregunta
                        System.out.println(mapEntry.getKey() + ". " 
                                + mapEntry.getValue().getTexto()); 
                }
                
                // Solicitar al usuario su respuesta a la pregunta
                System.out.println("Ingresar opcion válida (S)");
                input = sc.nextLine();
                
                String comodin = "";
                
                // Ingresar comodín en caso de requerirlo
                if (input.equals("*")){
                    System.out.println("Ingresar comodín "
                            + "(cincuenta/companero/salon)");
                    comodin = sc.nextLine();
                    
                    // Solicitar al usuario su respuesta a la pregunta
                    System.out.println("Ingresar opcion válida (S)");
                    input = sc.nextLine();
                }
                
                // Agregar comodines utilizados en caso de ser requerido
                switch(comodin){
                    case "cincuenta" -> comodinesUtilizados.add(Comodin.CINCUENTA);
                    case "companero" -> comodinesUtilizados.add(Comodin.COMPANERO);
                    case "salon" -> comodinesUtilizados.add(Comodin.SALON);
                    default -> {
                    }
                }
                
                // Obtener literal correcto
                literalCorrecto = obtenerKeyRespuestaCorrecta(opcionPregunta);
                }
                if (input.equalsIgnoreCase(literalCorrecto)){
                    nPreguntasContestadas++; // Aumentar de responder correctamente
                }
            }while(input.equalsIgnoreCase(literalCorrecto));
        }
    }
    public Materia getMateria(){
        return materia;
    }
    public Estudiante getParticipante(){
        return participante;
    }
    public String getFecha(){
        return fecha;
    }
    public int getMaxlvl(){
        return lvlMax;
    }
    public String getPremio(){
        return "x";
    }
    public ArrayList<Pregunta> getPreguntas(){
        return preguntas;
    }
    public int getNPreguntasContestadas(){
        return nPreguntasContestadas;
    }
    public ArrayList<Comodin> getComodines(){
        return comodinesUtilizados;
    }
    public int getSegundos(){
        return segundos;
    }
    // Ordenar reporte en funcion de la fecha
    public static ArrayList<Juego> ordenarReporte(ArrayList<Juego> juegos){
        int indicecomparar=0;
        // Bubble sort entre listas y fechas
        for (int i = 0; i < juegos.size() - 1; i++) {
            for (int j = 0; j < juegos.size() - i - 1; j++) {
                //Separación de listas
                String[] fecha1 = juegos.get(j).getFecha().split("-");
                String[] fecha2 = juegos.get(j + 1).getFecha().split("-");
                int d1 = Integer.parseInt(fecha1[0]);
                int m1 = Integer.parseInt(fecha1[1]);
                int a1 = Integer.parseInt(fecha1[2]);
                int d2 = Integer.parseInt(fecha2[0]);
                int m2 = Integer.parseInt(fecha2[1]);
                int a2 = Integer.parseInt(fecha2[2]);
                //Comparacion 
                    if (a1 != a2) {
                        indicecomparar=Integer.compare(a1,a2);
                    } else if (m1 != m2) {
                        indicecomparar=Integer.compare(m1,m2);
                    } else {
                        indicecomparar=Integer.compare(d1,d2);
                    }
                if (indicecomparar > 0) {
                    // Swap the positions of the elements
                    Juego temp = juegos.get(j);
                    juegos.set(j, juegos.get(j+1));
                    juegos.set(j+1, temp);
                }
            }
        }
        return juegos;
    }
    public static void main(String[] args) {
        ArrayList<Paralelo> paralelos = new ArrayList();
        ArrayList<TerminoAcademico> terminosAcademicos = new ArrayList<>();
        ArrayList<Materia> materias = new ArrayList<>();
        final ArrayList<Estudiante> estudiantesP3 = new ArrayList<>();
        
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
        Pregunta p1= new Pregunta("Cuanto es 2+2?",1, 
                materias.get(0), respuestas1);
        Pregunta p2= new Pregunta("Cuanto es 10-12?",2,
                materias.get(0), respuestas2);
        preguntas.add(p1);
        preguntas.add(p2);
        
        terminosAcademicos.add(new TerminoAcademico(1,2023));
        
        
        paralelos.add(new Paralelo(3, materias.get(0), terminosAcademicos.get(0), estudiantesP3));
        juegos.add(new Juego(materias.get(0), preguntas, paralelos.get(0), 202211355, 202211306, 5, "05-06-2023",new ArrayList<Comodin>()));
        juegos.get(0).comodinesUtilizados.add(Comodin.COMPANERO);
        juegos.add(new Juego(materias.get(0), preguntas, paralelos.get(0), 202111928, 202211306, 5, "05-05-2023",new ArrayList<Comodin>()));
        juegos.get(1).comodinesUtilizados.add(Comodin.SALON);
        juegos.add(new Juego(materias.get(0), preguntas, paralelos.get(0), 202104816, 202211306, 5, "05-06-2028",new ArrayList<Comodin>()));
        juegos.get(2).comodinesUtilizados.add(Comodin.CINCUENTA);
        generarReporte();
    }
    public static void generarReporte(){
        // Sort de todos los juegos registrados
        juegos=ordenarReporte(juegos);
        System.out.println("Reporte de Juegos:");
        for(Juego juego:juegos){
            //Obtencion de atributos
            String nombreEstudiante=juego.getParticipante().getNombre();
            //Format de la impresion de juegos
            System.out.println(juegos.indexOf(juego)+1+". "+nombreEstudiante+", el dia "+ juego.getFecha() +" jugó por "+juego.getSegundos()+" segundos y contesto "+juego.getNPreguntasContestadas()+" preguntas correctamente");
            System.out.println("Nivel Máximo alcanzado: "+juego.getMaxlvl()+" \nPremio: "+juego.getPremio());
            if(juego.getComodines()!=null){
                for (Comodin c: juego.getComodines())
                System.out.println(c.name()); 
            }
            
        }
        
    }
}

    