package modelo;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Juego implements Serializable, Comparable<Juego>{
    private static final long serialVersionUID=1;
    private static final String path="archivo\\reporte.ser";
    public static ArrayList<Juego> juegos = new ArrayList<>();
    private Materia materia;
    private ArrayList<Pregunta> preguntas;
    private Estudiante participante;
    private Estudiante apoyo;
    private int nPreguntasPerLvl;
    private String fecha;
    private int lvlMax;
    private int nPreguntasContestadas;
    private ArrayList<Comodin> comodinesUtilizados;
    private String premio;
    
    // Instanciar scanner
    static Scanner sc = new Scanner(System.in);
    
    /**
     * Constructor de clase
     * @param materia
     * @param preguntasMateria
     * @param paralelo
     * @param matriculaParticipante
     * @param matriculaApoyo
     * @param n
     * @param f 
     * @param ComodinesUtilizados 
     */
    public Juego(Materia materia, ArrayList<Pregunta> preguntasMateria, 
            Paralelo paralelo, int matriculaParticipante,int matriculaApoyo,
            int n, String f){
        this.materia = materia;
        preguntas = preguntasMateria; // Asignar preguntas por materia
        setEstudiante(paralelo, matriculaParticipante,
                TipoEstudiante.PARTICIPANTE); // Asignar participante
        setEstudiante(paralelo, matriculaParticipante,
                TipoEstudiante.APOYO); // Asignar support
        fecha = f; // Asignar fecha
        nPreguntasPerLvl = n; // Set n preguntas por nivel
        comodinesUtilizados=new ArrayList<Comodin>();
    }
    
    public ArrayList<Pregunta> getPreguntasdeJuego(){
    return preguntas;
    }
    public Materia getMateriaJuego(){
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
        return premio;
    }
    public int getNPreguntasContestadas(){
        return nPreguntasContestadas;
    }
    public ArrayList<Comodin> getComodines(){
        return comodinesUtilizados;
    }
    
    /**
     * Setting de preguntas a partir de una materia
     * @param materia 
     * @param pregs 
     * @return ArrayList
     */
    public static ArrayList<Pregunta> getPreguntasMateria(Materia materia, ArrayList<Pregunta> pregs){
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
            chosen.put(entry.getKey(), nPreguntasNivel);
        
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
     * Método encargado de la funcionalidad principal del juego
     */
    public void visualizarPreguntas(){
        // Obtener preguntas por nivel
        Map<Integer, ArrayList<Pregunta>> preguntasPerLvl =
                getPreguntasByLevel(nPreguntasPerLvl);
        // Declarar input para recibir respuesta 
        String input;
        Iterator<Map.Entry<Integer, ArrayList<Pregunta>>> it = 
                preguntasPerLvl.entrySet().iterator();
        
        // Declarar la última respuesta escogida
        Respuesta r = new Respuesta();
        
        // Iterar el map de preguntas por nivel
        while (it.hasNext() &&  (r.getTipo().equals(TipoRespuesta.CORRECTA))){
            Map.Entry<Integer, ArrayList<Pregunta>> entry = it.next();
            
            if (lvlMax > 0){
                System.out.println("Ingresar el premio que el estudiante "
                        + "ha obtenido al superar el nivel");
                premio = sc.nextLine();
            }
            // Obtener las preguntas del nivel
            ArrayList<Pregunta> lpreguntas = entry.getValue(); 
            
            // Shuffle el ArrayList de las preguntas
            Collections.shuffle(lpreguntas); 
            
            // Crear un iterador con lpreguntas
            Iterator<Pregunta> pit = lpreguntas.iterator();
            while ((pit.hasNext() && 
                    (r.getTipo().equals(TipoRespuesta.CORRECTA)))){
                
                Pregunta pOriginal = pit.next();
                Pregunta p = Pregunta.copy(pOriginal);
                int indRespuesta = -1;
                // Muestra por consola la pregunta
                System.out.println(p);
                // Solicitar al usuario su respuesta a la pregunta
                System.out.println("Ingresar opcion válida (A,B,C,D)");
                System.out.println("Ingresar (*) de requerir comodín");
                input = sc.nextLine();
                String comodin = "";

                if (input.equals("*")){
                    System.out.println("Ingresar comodín " +
                                "(cincuenta/companero/salon)");
                        comodin = sc.nextLine();
                        // Agregar comodines utilizados en caso de ser requerido
                        switch(comodin.toLowerCase()){
                            case "cincuenta" -> { comodinesUtilizados.
                                    add(Comodin.CINCUENTA);
                                    p.removeRespuestasIncorrectas(2);
                                    break;    
                            }
                            
                            case "companero" -> {
                                comodinesUtilizados.add(Comodin.COMPANERO);
                                System.out.println("Consultar a " + 
                                        apoyo.getNombre());
                                break;
                            }
                                    
                            case "salon" -> {
                                comodinesUtilizados.add(Comodin.SALON);
                                System.out.println("¿Qué dice el salón?");
                                break;
                            }
                            default -> {}
                        }
                        // Muestra por consola la pregunta
                        System.out.println(p);
                        // Solicitar al usuario su respuesta a la pregunta
                        System.out.println("Ingresar opcion válida (A,B,C,D)");
                        input = sc.nextLine();
                        
                }

                switch (input.toUpperCase()){
                    case "A" -> indRespuesta = 0;
                    case "B" -> indRespuesta = 1;
                    case "C" -> indRespuesta = 2;
                    case "D" -> indRespuesta = 3;
                    default -> {
                     }
                }
                // Asignar la última respuesta escogida
                r = p.getRespuestas().get(indRespuesta);
                
                if (r.getTipo().equals(TipoRespuesta.CORRECTA)){
                    System.out.println("*".repeat(20));
                    System.out.println("Respuesta Correcta!");
                    nPreguntasContestadas++; 
                }else{
                    System.out.println("*".repeat(20));
                    System.out.println("Respuesta Incorrecta :(");
                }
            }
            if (r.getTipo().equals(TipoRespuesta.CORRECTA)){
                lvlMax++;
            }     
        }
        
        if (lvlMax == materia.getCantNiveles()){
            System.out.println("Asignar el premio para el estudiante ganador. "
                    + "FELICITACIONES!");
            premio = sc.nextLine();
        }
    }
    public void setPreguntasParaJuego(ArrayList<Pregunta> pr){
        this.preguntas=pr;
    }

    @Override
    public int compareTo(Juego j) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date1 = dateFormat.parse(this.getFecha());
            Date date2 = dateFormat.parse(j.getFecha());
            return date2.compareTo(date1); // Compare in reverse order for newest to oldest
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle parsing exception as needed
        }
        return 0; // Default value if unable to compare
    }
    // Ordenar reporte en funcion de la fecha
    public ArrayList<Juego> ordenarReporte(ArrayList<Juego> juegos){
        Collections.sort(juegos);
        return juegos;
    }
    public static ArrayList<Juego> cargarJuegos(){
        ArrayList<Juego> juegoscargados = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            juegoscargados= (ArrayList<Juego>) in.readObject();
        } catch (EOFException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return juegoscargados;
    }
    public static void agregarJuego(Juego j){
            juegos.add(j);
        try(ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(path))){
            out.writeObject(juegos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
