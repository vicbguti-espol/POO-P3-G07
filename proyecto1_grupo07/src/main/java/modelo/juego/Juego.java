package modelo.juego;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import modelo.juego.Pregunta;
import modelo.juego.Respuesta;
import modelo.juego.TipoEstudiante;
import modelo.juego.TipoRespuesta;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import modelo.academico.Estudiante;
import modelo.academico.Materia;
import modelo.academico.Paralelo;

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
    private String premio;

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public void setPreguntas(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public void setParticipante(Estudiante participante) {
        this.participante = participante;
    }

    public void setApoyo(Estudiante apoyo) {
        this.apoyo = apoyo;
    }

    public void setnPreguntasPerLvl(int nPreguntasPerLvl) {
        this.nPreguntasPerLvl = nPreguntasPerLvl;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setLvlMax(int lvlMax) {
        this.lvlMax = lvlMax;
    }

    public void setnPreguntasContestadas(int nPreguntasContestadas) {
        this.nPreguntasContestadas = nPreguntasContestadas;
    }

    public void setComodinesUtilizados(ArrayList<Comodin> comodinesUtilizados) {
        this.comodinesUtilizados = comodinesUtilizados;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }

    public Materia getMateria() {
        return materia;
    }

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    public Estudiante getApoyo() {
        return apoyo;
    }

    public int getnPreguntasPerLvl() {
        return nPreguntasPerLvl;
    }

    public int getLvlMax() {
        return lvlMax;
    }

    public int getnPreguntasContestadas() {
        return nPreguntasContestadas;
    }

    public ArrayList<Comodin> getComodinesUtilizados() {
        return comodinesUtilizados;
    }


    
    
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
    

    /**
     * Obtener un arreglo de tipo Juego a partir de una ruta de archivo
     * @param pathJuegos
     * @return 
     */
    public static ArrayList<Juego> cargarJuegos(String pathJuegos){
        ArrayList<Juego> juegoscargados = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(pathJuegos))) {
            juegoscargados= (ArrayList<Juego>) in.readObject();
        } catch (EOFException e) {

        } catch (Exception e) {
            System.out.println(e);
        }
        return juegoscargados;
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
    public Map<Integer, ArrayList<Pregunta>> getPreguntasByLevel(int n){
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
    
    public Map<Integer, ArrayList<Pregunta>> getPreguntasByLevel(){
        Map<Integer, ArrayList<Pregunta>> chosen = new TreeMap<>();
        // Obtener map de preguntasPorNivel
        Map<Integer, ArrayList<Pregunta>> preguntasPerLvl = 
                getPreguntasByLevel(preguntas);
        
        for (Map.Entry<Integer, ArrayList<Pregunta>> entry: preguntasPerLvl.entrySet()){
            ArrayList<Pregunta> nPreguntasNivel = new ArrayList<Pregunta>();    
            // Obtener la lista de preguntas del nivel 
            ArrayList<Pregunta> preguntasNivel = entry.getValue();
            Collections.shuffle(preguntasNivel); // Shuffle de preguntas
            
            for (int i = 0; i < nPreguntasPerLvl; i++){
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
    
    
    public void setPreguntasParaJuego(ArrayList<Pregunta> pr){
        this.preguntas=pr;
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
    public static void generarReporte(ArrayList<Juego> juegos){
        // Sort de todos los juegos registrados
        juegos=ordenarReporte(juegos);
        System.out.println("Reporte de Juegos:");
        for(Juego juego:juegos){
            //Obtencion de atributos
            String nombreEstudiante=juego.getParticipante().getNombre();
            //Format de la impresion de juegos
            System.out.println(juegos.indexOf(juego)+1+". "+nombreEstudiante+", el dia "+ juego.getFecha() + " y contesto "+ juego.getNPreguntasContestadas()+" preguntas correctamente");
            System.out.println("Nivel Máximo alcanzado: "+juego.getMaxlvl()+" \nPremio: "+juego.getPremio());
            if(juego.getComodines()!=null){
                System.out.println("Comodines utilizados:");
                for (Comodin c: juego.getComodines())
                System.out.println(c.name()); 
            }
            
        }
        
    }
}
