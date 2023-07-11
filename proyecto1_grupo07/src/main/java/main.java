/**
 *
 * @author joshz
 */
import modelo.*;
import java.util.Scanner;
public class main {
    public static int menu(){
        String menu = "Menú\n1) Configuraciones\n2) Nuevo Juego\n3) Reporte\n4) Salir";
        Scanner sc = new Scanner(System.in);
        int opcionmenu;
        do{
            System.out.println(menu);
            System.out.println("Escoga una opción");
            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido del 1 al 4.");
                sc.next();
            }
            opcionmenu = sc.nextInt();
            if (opcionmenu <1 ||opcionmenu>4){
                System.out.println("Ingrese un número válido.");
            }
        }while(opcionmenu <1 ||opcionmenu>4);
        return opcionmenu;
    }
    public static int menuconfig(){
        String menuconfig = "Menú\n1) Administrar términos académicos\n2) Administrar materias y paralelos\n3) Administrar preguntas\n4) Salir";
        Scanner sc = new Scanner(System.in);
        int opcionconfiguracion;
        do{
            System.out.println(menuconfig);
            System.out.println("Escoga una opción");
            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido del 1 al 4.");
                sc.next();
            }
            opcionconfiguracion = sc.nextInt();
            if (opcionconfiguracion <1 ||opcionconfiguracion>4){
                System.out.println("Ingrese un número válido.");
            }
        }while(opcionconfiguracion <1 ||opcionconfiguracion>4);
        return opcionconfiguracion;
    }
    public static int menuterminos(){
        String menuterminos = "Menú\n1) Ingresar término\n2) Editar Termino\n3) Configurar termino para el juego";
        Scanner sc = new Scanner(System.in);
        int opcionterminos;
        do{
            System.out.println(menuterminos);
            System.out.println("Escoga una opción");
            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido del 1 al 3.");
                sc.next();
            }
            opcionterminos = sc.nextInt();
            if (opcionterminos <1 ||opcionterminos>3){
                System.out.println("Ingrese un número válido.");
            }
        }while(opcionterminos <1 ||opcionterminos>3);
        return opcionterminos;
    }
    public static int menumaterias(){
        String menumaterias = "Menú\n1) Ingresar materia\n2) Editar materia\n3) Agregar paralelo\n4) Eliminar paralelo";
        Scanner sc = new Scanner(System.in);
        int opcionmaterias;
        do{
            System.out.println(menumaterias);
            System.out.println("Escoga una opción");
            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido del 1 al 4.");
                sc.next();
            }
            opcionmaterias = sc.nextInt();
            if (opcionmaterias <1 ||opcionmaterias>4){
                System.out.println("Ingrese un número válido.");
            }
        }while(opcionmaterias <1 ||opcionmaterias>4);
        return opcionmaterias;
    }
    public static int menupreguntas(){
        String menupreguntas = "Menú\n1) Visualizar preguntas\n2) Agregar pregunta\n3) Eliminar pregunta";
        Scanner sc = new Scanner(System.in);
        int opcionpreguntas;
        do{
            System.out.println(menupreguntas);
            System.out.println("Escoga una opción");
            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido del 1 al 4.");
                sc.next();
            }
            opcionpreguntas = sc.nextInt();
            if (opcionpreguntas <1 ||opcionpreguntas>3){
                System.out.println("Ingrese un número válido.");
            }
        }while(opcionpreguntas <1 ||opcionpreguntas>3);
        return opcionpreguntas;
    }
    public static void main(String[] args) {
        int opcionprincipal=0;
        while (opcionprincipal!=4){
            opcionprincipal=menu();
            if (opcionprincipal==1){
                int opcion1 = menuconfig();
                //CODIGO PARA LAS CONFIGURACIONES
                if (opcion1==1){
                    // Codigo para administrar términos académicos
                    System.out.println("Escogio la opcion "+opcion1);
                    int opcionterminos=menuterminos();
                    if (opcionterminos==1){
                        //Código para Ingresar terminos academicos
                    System.out.println("Eligió Ingresar Término Académico");
                    }else if(opcionterminos==2){
                        //Código para Ingresar editar academicos
                    System.out.println("Eligió Editar Término Académico");
                    }else if(opcionterminos==3){
                        //Código para configurar terminos academicos
                    System.out.println("Eligió Configurar Término para el Juego");
                    }
                }else if(opcion1==2){
                    // Codigo para administrar materias y  paralelos
                    System.out.println("Escogio la opcion "+opcion1);
                    int opcionmaterias=menuterminos();
                    if (opcionmaterias==1){
                        //Código para Ingresar MATERIA
                    System.out.println("Eligió Ingresar Materia");
                    }else if(opcionmaterias==2){
                        //Código para Ingresar Editar MATERIA
                    System.out.println("Eligió Editar Materia");
                    }else if(opcionmaterias==3){
                        //Código para Agregar PARALELO
                    System.out.println("Eligió Configurar Término para el Juego");
                    }else if(opcionmaterias==4){
                        //Código para Eliminar PARALELO
                    System.out.println("Eligió Configurar Término para el Juego");
                    }
                }else if(opcion1==3){
                    // Codigo para administrar preguntas
                    System.out.println("Escogio la opcion "+opcion1);
                    int opcionpreguntas=menupreguntas();
                    if (opcionpreguntas==1){
                        //Código para Visualizar Preguntas
                    System.out.println("Eligió Visualizar Preguntas");
                    }else if(opcionpreguntas==2){
                        //Código para Agregar Preguntas
                    System.out.println("Eligió Agregar pregunta");
                    }else if(opcionpreguntas==3){
                        //Código para Eliminar pregunta
                    System.out.println("Eligió Eliminar Pregunta");
                    }
                }else if(opcion1==4){
                opcionprincipal=0;
                }
            }
        }
    }
}