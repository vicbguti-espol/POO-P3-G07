package modelo;

import java.util.*;

public class Paralelo {
    private Materia materia;
    private TerminoAcademico termino;
    private int numero;
    private String ruta;
    private static ArrayList<Estudiante> estudiantes=Estudiante.estudiantes;


    public static ArrayList<Paralelo>paralelos=new ArrayList<Paralelo>(); 

    Scanner sc = new Scanner(System.in);

    public Paralelo(Materia materia, TerminoAcademico termino, int numero, String ruta){
        this.materia=materia;
        this.termino=termino;
        this.numero=numero;
        this.ruta=ruta;
    }
    public Paralelo(Materia materia, TerminoAcademico termino, int numero, ArrayList<Estudiante> estudiantes){
        this.materia=materia;
        this.termino=termino;
        this.numero=numero;
        this.estudiantes=estudiantes;
    }

    public static void ingresarParalelo(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingresar el codigo de la materia");
        String codigo=sc.nextLine();
        Materia materia=Materia.verificarExistencia(codigo);
        
        System.out.println("Ingresar el año del termino de la materia");
        int terminoa = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingresar el numero del termino de la materia");
        int terminon = sc.nextInt();
        sc.nextLine();

        TerminoAcademico termino=new TerminoAcademico(terminoa, terminon);
        
        System.out.println("Ingresar numero de paralelo:");
        int numparalelo = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingresar ruta de archivo:");
        String rutaarchivo = sc.nextLine();

        //proceso de agregar estudiantes -- codigomateria-paralelo-año-termino.csv
        paralelos.add(new Paralelo(materia, termino ,numparalelo, estudiantes));

        
    }

    public String toString(){
        return "Paralelo "+numero+ " de la materia "+materia.getNombre()+" en el termino "+termino.getNumTermino()+"-"+termino.getAñoTermino();
    }

    public static void eliminarParalelo(){
        Scanner sc= new Scanner(System.in);
        for(Paralelo p: paralelos){
            System.out.println(paralelos.indexOf(p)+1+". "+p.toString());
        }
        System.out.println("Ingrese indice de paralelo a eliminar");
        paralelos.remove(sc.nextInt()-1);
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
    public static void main(String[] args) {
        ArrayList <Estudiante> test=new ArrayList<Estudiante>();
        test.add(new Estudiante("201411265","lokistaz","Kimberly Lopez"));
        test.add(new Estudiante("201896545","lerinero","Armando Gerundio"));
        test.add(new Estudiante("202212364","otmotiel","Rosa Paredes"));
        test.add(new Estudiante("201956238","seterine","Stephanie Fuentes"));
        test.add(new Estudiante("201245561","kiltopan","Lana del Rey"));

        Materia.materias.add(new Materia("0001","FP",3));
        Materia.materias.add(new Materia("0002","FEM",2));
        Materia.materias.add(new Materia("0003","CYS",4));
        Materia.materias.add(new Materia("0004","ARP",1));
        Materia.materias.add(new Materia("0005","CVV",2));
        Materia.materias.add(new Materia("0006","FM",5));

        paralelos.add(new Paralelo(Materia.verificarExistencia("0002"), new TerminoAcademico(2023, 2), 8, test));
        paralelos.add(new Paralelo(Materia.verificarExistencia("0006"), new TerminoAcademico(2012, 1), 6, test));

        for (Paralelo p: paralelos){
            System.out.println(p.toString());
        }

    }


}
