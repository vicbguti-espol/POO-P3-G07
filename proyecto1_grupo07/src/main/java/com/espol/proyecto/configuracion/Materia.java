package main.java.com.espol.proyecto.configuracion;
import java.util.*;

public class Materia {
    private String codigo;
    private String nombre;
    private int cantNiveles;

    static ArrayList<Materia> materias=new ArrayList<Materia>();

    Scanner sc = new Scanner(System.in);

    Materia(String c, String n, int cantidad){
        this.codigo=c;
        this.nombre=n;
        this.cantNiveles=cantidad;
    }

    public void ingresarMateria(String c, String n, int cantidad){
        System.out.println("Ingrese el codigo de la materia");
        codigo=sc.nextLine();

        System.out.println("Ingrese el nombre de la materia");
        nombre=sc.nextLine();

        System.out.println("Ingrese la cantidad de niveles de la materia");
        cantNiveles=sc.nextInt();
        sc.nextLine();

        materias.add(new Materia(codigo,nombre,cantNiveles));
         
    }

    public String getCodigo(){
        return codigo;
    }
    public String getNombre(){
        return nombre;
    }
    public int getCantNiveles(){
        return cantNiveles;
    }

    public static Materia verificarExistencia(String c){
        for(Materia i: Materia.materias){
        if (i.getCodigo().equals(c)){
            return i;
        }
        else{
            System.out.println("Materia no encontrada");
        }
        }
        return null;
    }
    
    public void eliminarMateria(Materia m){
        System.out.println("Ingrese el codigo de la materia a eliminar");
        String cmateriaeliminar=sc.nextLine();
        for(Materia i:materias){
            if (cmateriaeliminar.equals(i.getCodigo())){
                materias.remove(i);
            }
        }
    }

}
