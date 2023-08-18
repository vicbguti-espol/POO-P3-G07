package modelo.academico;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class Paralelo implements Serializable {
    private int numero;
    private Materia materia;
    private TerminoAcademico termino;
    private ArrayList<Estudiante> estudiantes;
    private String rutaEstudiantes;
    //public static ArrayList<Paralelo> paralelos = new ArrayList<>(Arrays.asList(new Paralelo(3, Materia.materias.get(0), TerminoAcademico.terminosAcademicos.get(0), Estudiante.cargarEstudiantes("archivo\\CCPG1052-3-2023-1.txt"))));
    public static ArrayList<Paralelo> paralelos = cargarParalelos();
    private static final long serialVersionUID = 1;
    private static final String path="archivo\\paralelos.ser";

    public Paralelo(int numero, Materia materia, TerminoAcademico termino, ArrayList<Estudiante> estudiantes) {
        this.numero = numero;
        this.materia = materia;
        this.termino = termino;
        this.estudiantes = estudiantes;
    }
    public Paralelo(int numero, Materia materia, TerminoAcademico termino, String rutaEstudiantes) {
        this.numero = numero;
        this.materia = materia;
        this.termino = termino;
        this.rutaEstudiantes=rutaEstudiantes;
    }

    public int getnumero() {
        return numero;
    }

    public TerminoAcademico getTerminoAcademico() {
        return termino;
    }

    public Materia getMateria() {
        return materia;
    }
    
    public ArrayList <Estudiante> getEstudiantes(){
        return estudiantes;
    }
    public String getrutaEstudiantes(){
        return rutaEstudiantes;
    }

    @ Override
    public String toString(){
        return "Paralelo {materia: " + materia.getNombre() + ", paralelo: " + numero + ", termino: " + termino + "}";
    }

    public static void main(String[] args) {
        //subirArchivo();
        for(Paralelo p: paralelos){
            System.out.println(p.toString());
            for(Estudiante e:p.getEstudiantes()){
                System.out.println(e.toString());
            }
        }
    }
    public static void subirArchivo(){
        ArrayList<Paralelo> paralelostest = new ArrayList<>(Arrays.asList(new Paralelo(3, Materia.materias.get(0), TerminoAcademico.terminosAcademicos.get(0), Estudiante.cargarEstudiantes("archivo\\CCPG1052-3-2023-1.txt"))));
        try(ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(path))){
            out.writeObject(paralelostest);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static ArrayList<Paralelo>cargarParalelos(){
        ArrayList<Paralelo> paraleloscargados = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            paraleloscargados= (ArrayList<Paralelo>) in.readObject();
        } catch (EOFException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return paraleloscargados;
    }
    public static void agregarParalelos(Paralelo p){
        if(!paralelos.contains(p)){
            paralelos.add(p);
        }
        try(ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(path))){
            out.writeObject(paralelos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void eliminarParalelos(Paralelo p){
        paralelos.remove(p);
        try(ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream(path))){
            out.writeObject(paralelos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
