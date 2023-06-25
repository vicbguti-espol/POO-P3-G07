package com.espol.proyecto.configuracion;

public class Materia {
    // Atributos
    private String codigo;
    private String nombre;
    private int cantNiveles;

    // Constructor
    public Materia(String codigo, String nombre, int cantNiveles) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantNiveles = cantNiveles;
    }
    
    // Getters y setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCantNiveles() {
        return cantNiveles;
    }

    public void setCantNiveles(int cantNiveles) {
        this.cantNiveles = cantNiveles;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
