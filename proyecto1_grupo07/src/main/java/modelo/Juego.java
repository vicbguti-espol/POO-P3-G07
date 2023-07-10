package modelo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Omen
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

public class Juego {
    private ArrayList<Pregunta> preguntas;
    private Estudiante participante;
    private Estudiante apoyo;
    private int nPreguntasPerLvl;
    private String fecha;
    private int lvlMax;
    private String tiempo;
    private int nPreguntasContestadas;
    private ArrayList<Comodin> comodinesUtilizados;
    
    public Juego(Materia materia, Paralelo paralelo, int matriculaParticipante, int matriculaApoyo, int nPreguntasPerLvl){
        asignarPreguntas(materia);
        asignarEstudiante(paralelo, matriculaParticipante, TipoEstudiante.PARTICIPANTE);
        asignarEstudiante(paralelo, matriculaParticipante, TipoEstudiante.APOYO);
        fecha = "dd-mm-yyyy";
        this.nPreguntasPerLvl = nPreguntasPerLvl;
    }
    
    public void asignarPreguntas(Materia materia){
        // Iterar la lista de preguntas estáticas
        for (Pregunta p: Pregunta.getPreguntas()){ 
            // Verificar materias iguales
            if (p.getMateria().equals(materia)){
                preguntas.add(p); // Agregar pregunta a preguntas
            }
        }
    }
    
    public void asignarEstudiante(Paralelo paralelo, int matricula, TipoEstudiante tipo){
        // Obtener lista de estudiantes en el paralelo
        ArrayList<Estudiante> estudiantes = paralelo.getEstudiantes();
            
        if (matricula != 0){
            for (Estudiante e: estudiantes){
                int mat = e.getMatricula();
                if (mat == matricula){
                    if (tipo.equals(TipoEstudiante.PARTICIPANTE)){
                        // Asignar participante de tener matrícula
                        participante = e; 
                    }
                    else if (tipo.equals(TipoEstudiante.APOYO)){
                        // Asignar apoyo de tener matrícula
                        apoyo = e; 
                    }
                    break;
                }
            }
        } else{
            // Obtener un índice random
            int random_index = (int)(Math.random() * estudiantes.size());
            if (tipo.equals(TipoEstudiante.PARTICIPANTE)){
                        // Asignar participante random de no tener matrícula
                        participante = estudiantes.get(random_index); 
            }
            
            else if (tipo.equals(TipoEstudiante.APOYO)){
                        // Asignar apoyo de tener matrícula
                        apoyo = estudiantes.get(random_index); 
                    }
        }
    }
    
    private Map<Integer, ArrayList<Pregunta>> obtenerPreguntasPorNivel(){
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
    
    public boolean verificarNumeroPreguntasPerLevelValido(int n){
        // Obtener map de preguntasPorNivel
        Map<Integer, ArrayList<Pregunta>> preguntasPerLvl = obtenerPreguntasPorNivel();
        
        for (ArrayList<Pregunta> p: preguntasPerLvl.values()){
            if (p.size() < n){
                // Retornar falso en caso de no haber la cantidad requerida 
                // de preguntas en todos los niveles
                return false; 
            }
        }
        return true;
    }
    
    public void visualizarPreguntas(){
        // Obtener preguntas por nivel
        Map<Integer, ArrayList<Pregunta>> preguntasPerLvl = obtenerPreguntasPorNivel();
        // Iterar el map de preguntas por nivel
        for (Map.Entry<Integer, ArrayList<Pregunta>> entry: preguntasPerLvl.entrySet()){
            // Obtener las preguntas del nivel
            ArrayList<Pregunta> preguntas = entry.getValue(); 
            // Shuffle el ArrayList de las preguntas
            Collections.shuffle(preguntas); 
            // Mostrar por pantalla el nivel en que se encuentra
            System.out.println("Nivel" + entry.getKey());
            
            int stop = 0;
            for (Pregunta p: preguntas){
                // Obtener las respuestas de la pregunta
                ArrayList<Respuesta> respuestas = p.getRespuestas();
                stop++; // Incrementar stop por cada iteración
                
                Collections.shuffle(respuestas); // Shuffle de respuestas
                
                // Filling the Map in a Loop
                
                System.out.println(preguntas.indexOf(p) + ". " + p.getTexto());
                
                if (stop == nPreguntasPerLvl){
                    break;
                }
            }
        }
        
    }
}
