package modelo;
import java.util.*;

public class Materia {
    private String codigo;
    private String nombre;
    private int cantNiveles;
    
    public Materia(String codigo, String nombre, int cantidad){
        this.codigo=codigo;
        this.nombre=nombre;
        this.cantNiveles=cantidad;
    }

    public String getCodigo(){
        return codigo;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String n){
        this.nombre=n;
    }
    public void setCodigo(String c){
        this.codigo=c;
    }
    public void setCantNiveles(int c){
        this.cantNiveles=c;
    }
    public int getCantNiveles(){
        return cantNiveles;
    }

    public static void eliminarMateria(ArrayList<Materia> materias){
        Scanner sc = new Scanner(System.in);

        for (Materia m: materias){
            System.out.println((materias.indexOf(m)+1)+". "+m.toString());
        }

        System.out.println("Ingrese codigo o indice de materia a eliminar");
        if(sc.hasNextInt()){
            int indice=sc.nextInt();
            materias.remove(indice-1);
        }

        else if(sc.hasNextLine()){
            String cmateriaeliminar=sc.nextLine();
            Materia i=verificarExistencia(cmateriaeliminar, materias);
            materias.remove(materias.indexOf(i));
        }
    }

    public static Materia verificarExistencia(String c, ArrayList<Materia> materias){
        for(Materia i: materias){
            if (i.getCodigo().equals(c)){
                return i;
            }
        }
        System.out.println("Materia no encontrada");
        return null;
    }
    
    public static void editarMateria(ArrayList<Materia> materias){
        Scanner sc = new Scanner(System.in);

        for (Materia m: materias){
            System.out.println((materias.indexOf(m)+1)+". "+m.getCodigo()+" "+m.getNombre());
        }

        System.out.println("Ingrese el codigo o indice de la materia a editar");

        if(sc.hasNextInt()){
            Materia m = materias.get(sc.nextInt()-1);
            sc.nextLine();

            System.out.println("Ingrese el nuevo nombre de la materia");
            m.setNombre(sc.nextLine());

            System.out.println("Ingrese la nueva cantidad de niveles de la materia");
            m.setCantNiveles(sc.nextInt());
            sc.nextLine();
        }
        else if(sc.hasNextLine()){
            Materia m = verificarExistencia(sc.nextLine(), materias);
            System.out.println("Ingrese el nuevo nombre de la materia");
            m.setNombre(sc.nextLine());

            System.out.println("Ingrese la nueva cantidad de niveles de la materia");
            m.setCantNiveles(sc.nextInt());
            sc.nextLine();
        }
        
    }
    public String toString(){
        return "Materia con codigo "+codigo+", nombre "+nombre+", tiene "+cantNiveles+" niveles.";

    }

    //Implementar equals pedido por Victor
}
