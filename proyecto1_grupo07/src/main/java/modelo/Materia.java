package modelo;
import java.io.*;
import java.util.*;

public class Materia implements Serializable{
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
    
    @ Override
    public String toString(){
        return "Materia {codigo: " + codigo +", nombre: " + nombre +
                ", niveles: " + cantNiveles + "}";

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Materia other = (Materia) obj;
        if (this.cantNiveles != other.cantNiveles) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return Objects.equals(this.nombre, other.nombre);
    }
    public static void main(String[] args) {
        for(Materia m: cargarMateriasseria("archivo/Materia.type")){
            System.out.print(m.toString());
            
        }
    }
    public static ArrayList<Materia> cargarMateriasarchi(String path){
        ArrayList<Materia> lfinal=new ArrayList<>();
        try{
            BufferedWriter writer=new BufferedWriter(new FileWriter(path,true));
            writer.write("CCPG1000,ALGEBRA LINEAL,2");
            writer.newLine();
            writer.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            BufferedReader reader=new BufferedReader(new FileReader(path));
            String line="";
            while((line=reader.readLine())!=null){
                String[] elementos=line.split(",");
                lfinal.add(new Materia(elementos[0], elementos[1], Integer.valueOf(elementos[2])));
            }
            reader.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return lfinal;
    }
    public static ArrayList<Materia> cargarMateriasseria(String path){
        ArrayList<Materia> lfinal=new ArrayList<>();
        try{
            ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(path,true));
            out.writeObject(new Materia("CCPG1000", "ALGEBRA LINEAL",2));
            out.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            ObjectInputStream in=new ObjectInputStream(new FileInputStream(path));
            Object o=in.readObject();
            Materia m=(Materia)o;
            lfinal.add(m);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return lfinal;
    }
    
    
}
