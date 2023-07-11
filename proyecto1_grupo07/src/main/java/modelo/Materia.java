package modelo;
import java.util.*;

public class Materia {
    private String codigo;
    private String nombre;
    private int cantNiveles;

    public static ArrayList<Materia> materias=new ArrayList<Materia>();

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
    public setNombre(String n){
        this.nombre=n;
    }
    public setCantNiveles(int c){
        this.cantNiveles=c;
    }
    public int getCantNiveles(){
        return cantNiveles;
    }

    public void eliminarMateria(){
        for (Materia m: materias){
            System.out.println((materias.indexOf(m)+1)+". "+m.getCodigo()+" "+m.getNombre());
        }
        System.out.println("Ingrese codigo o indice de materia a eliminar");
        if(sc.hasNextInt()){
            int indice=sc.nextInt();
            materias.remove(indice-1);
        }
        else if(sc.hasNextLine()){
            String cmateriaeliminar=sc.nextLine();
            Materia i=verificarExistencia(cmateriaeliminar);
            materias.remove(i);
        }else{
            System.out.println("Ingreso invalido");
        }
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
    
    public void editarMateria(Materia x){
        for (Materia m: materias){
            System.out.println((materias.indexOf(m)+1)+". "+m.getCodigo()+" "+m.getNombre());
        }
        System.out.println("Ingrese el codigo o indice de la materia a editar");
        if(sc.hasNextInt()){
            System.out.println("Ingrese el nuevo nombre de la materia");
            materias.get(sc.nextInt()).setNombre(sc.nextLine());

            System.out.println("Ingrese la nueva cantidad de niveles de la materia");
            materias.get(sc.nextInt()).setCantNiveles(sc.nextInt());
        }
        else if(sc.hasNextLine()){
            Materia m=sc.nextLine().verificarExistencia();
            System.out.println("Ingrese el nuevo nombre de la materia");
            m.setNombre(sc.nextLine());

            System.out.println("Ingrese la nueva cantidad de niveles de la materia");
            m.setCantNiveles(sc.nextInt());
        }else{
            System.out.println("Ingreso invalido");
        }
        
    }
    public boolean equals(Object o){
        if (o != null && getClass()==o.getClass()){
            return super.equals(o) && o==other.o;
        }
        else{
            return false;
        }
    }
}
