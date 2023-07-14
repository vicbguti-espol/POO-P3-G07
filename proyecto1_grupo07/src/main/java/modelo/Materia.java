package modelo;
import java.util.*;

public class Materia {
    private String codigo;
    private String nombre;
    private int cantNiveles;
    public static ArrayList<Materia> materias;



    public Materia(String codigo, String nombre, int cantidad){
        this.codigo=codigo;
        this.nombre=nombre;
        this.cantNiveles=cantidad;
    }

    public static void ingresarMateria(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el codigo de la materia");
        String codigo=sc.nextLine();

        System.out.println("Ingrese el nombre de la materia");
        String nombre=sc.nextLine();

        System.out.println("Ingrese la cantidad de niveles de la materia");
        int cantNiveles=sc.nextInt();
        sc.nextLine();

        materias.add(new Materia(codigo,nombre,cantNiveles));
        
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
    public void setCantNiveles(int c){
        this.cantNiveles=c;
    }
    public int getCantNiveles(){
        return cantNiveles;
    }

    public static void eliminarMateria(){
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
            Materia i=verificarExistencia(cmateriaeliminar);
            materias.remove(materias.indexOf(i));
        }
    }

    public static Materia verificarExistencia(String c){
        for(Materia i: Materia.materias){
            if (i.getCodigo().equals(c)){
                return i;
            }
        }
        System.out.println("Materia no encontrada");
        return null;
    }
    
    public static void editarMateria(){
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
            Materia m=verificarExistencia(sc.nextLine());

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
    //test, borrar
    public static void main(String[] args) {
        materias.add(new Materia("0001","FP",3));
        materias.add(new Materia("0002","FEM",2));
        materias.add(new Materia("0003","CYS",4));
        materias.add(new Materia("0004","ARP",1));
        materias.add(new Materia("0005","CVV",2));
        materias.add(new Materia("0006","FM",5));
        //ingresarMateria();
        //eliminarMateria();
        //System.out.println(verificarExistencia(sc.nextLine()).toString());
        for(Materia m: materias){
            System.out.println(m.toString());
        }

    }
    //Implementar equals pedido por Victor
}
