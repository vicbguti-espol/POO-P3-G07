package modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Juego {
    private Materia materia;
    private ArrayList<Pregunta> preguntas;
    private Estudiante participante;
    private Estudiante apoyo;
    private int nPreguntasPerLvl;
    private String fecha;
    private int lvlMax;
    private int nPreguntasContestadas;
    private ArrayList<Comodin> comodinesUtilizados;
    
    // Instanciar scanner
    static Scanner sc = new Scanner(System.in);
    
    public ArrayList<Pregunta> getPreguntasdeJuego(){
    return preguntas;
    }
    public Materia getMateriaJuego(){
    return materia;
    }
    /**
     * Constructor de clase
     * @param materia
     * @param preguntasMateria
     * @param paralelo
     * @param matriculaParticipante
     * @param matriculaApoyo
     * @param n
     * @param f 
     */
    public Juego(Materia materia, ArrayList<Pregunta> preguntasMateria, Paralelo paralelo, int matriculaParticipante,int matriculaApoyo, int n, String f){
        this.materia = materia;
        preguntas = preguntasMateria; // Asignar preguntas por materia
        setEstudiante(paralelo, matriculaParticipante,
                TipoEstudiante.PARTICIPANTE); // Asignar participante
        setEstudiante(paralelo, matriculaParticipante,
                TipoEstudiante.APOYO); // Asignar support
        fecha = f; // Asignar fecha
        nPreguntasPerLvl = n; // Set n preguntas por nivel
    }
    
    /**
     * Setting de preguntas a partir de una materia
     * @param materia 
     * @param pregs 
     * @return  
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
    public void visualizarPreguntasj(){
        // Obtener preguntas por nivel
        Map<Integer, ArrayList<Pregunta>> preguntasPerLvl = getPreguntasByLevel(nPreguntasPerLvl);
        // Decalar input para recibir respuesta 
        String input;
        // Decalar el último literalCorrecto seleccionado
        String literalCorrecto;
        Iterator<Map.Entry<Integer, ArrayList<Pregunta>>> it = preguntasPerLvl.entrySet().iterator();
        // Iterar el map de preguntas por nivel
        boolean continuar = true;
        while (it.hasNext() &&  continuar){
            Map.Entry<Integer, ArrayList<Pregunta>> entry = it.next(); //CORRIGIENDO
            // Obtener las preguntas del nivel
            ArrayList<Pregunta> lpreguntas = entry.getValue(); 
            
            // Shuffle el ArrayList de las preguntas
            Collections.shuffle(lpreguntas); 
            // Nivel de pregunta
            lvlMax = entry.getKey();
            // Mostrar por pantalla el nivel en que se encuentra
            System.out.println("Nivel" + lvlMax);
            boolean respuestaCorrecta=true;
            for (Pregunta p: lpreguntas){
                if (respuestaCorrecta){
                    // Obtener las respuestas de la pregunta
                    ArrayList<Respuesta> respuestas = p.getRespuestas();
                    Collections.shuffle(respuestas); // Shuffle de respuestas
                    // Asignar un key value de opción a cada respuesta
                    Map<String, Respuesta> opcionPregunta = new TreeMap<>();
                    opcionPregunta.put("A", respuestas.get(0));
                    opcionPregunta.put("B", respuestas.get(1));
                    opcionPregunta.put("C", respuestas.get(2));
                    opcionPregunta.put("D", respuestas.get(3));
                    // Muestra por consola la pregunta
                    System.out.println(lpreguntas.indexOf(p) + ". " + p.getTexto());
                    // Iterar el map
                    for (Map.Entry<String, Respuesta> mapEntry: opcionPregunta.entrySet()){
                        // Visualizar pregunta
                        System.out.println(mapEntry.getKey() + ". " + mapEntry.getValue().getTexto()); 
                    }
                    // Solicitar al usuario su respuesta a la pregunta
                    System.out.println("Ingresar opcion válida (A,B,C,D)");
                    input = sc.nextLine();
                    String comodin = "";
                    // Ingresar comodín en caso de requerirlo
                    if (input.equals("*")){
                        System.out.println("Ingresar comodín " + "(cincuenta/companero/salon)");
                        comodin = sc.nextLine();
                        // Solicitar al usuario su respuesta a la pregunta
                        System.out.println("Ingresar opcion válida (A,B,C,D)");
                        input = sc.nextLine();
                    }
                    // Agregar comodines utilizados en caso de ser requerido
                    switch(comodin){
                        case "cincuenta" -> comodinesUtilizados.add(Comodin.CINCUENTA);
                        case "companero" -> comodinesUtilizados.add(Comodin.COMPANERO);
                        case "salon" -> comodinesUtilizados.add(Comodin.SALON);
                        default -> {}
                    }
                
                    // Obtener literal correcto
                    literalCorrecto = obtenerKeyRespuestaCorrecta(opcionPregunta);
                    if (input.equalsIgnoreCase(literalCorrecto.toLowerCase())){
                        nPreguntasContestadas++; // Aumentar de responder correctamente
                    } else {
                        System.out.println("RESPUESTA INCORRECTA");
                        respuestaCorrecta=false;
                    }
                }
            }
            if (!respuestaCorrecta){
                continuar=false;
            }
        }
    }
    public void setPreguntasParaJuego(ArrayList<Pregunta> pr){
    this.preguntas=pr;
    }
}
