/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo.proyectojar;

import java.io.IOException;
import modelo.academico.*;

import java.util.Random;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import modelo.juego.Juego;
import modelo.juego.Pregunta;
/**
 * FXML Controller class
 *
 * @author Anthony
 */
public class NuevoJuegoController implements Initializable {
    @FXML
    private Label lblTitulo;
    @FXML
    private ComboBox<String> cmbPregNivel;
    @FXML
    private TextField txtMatPart;
    @FXML
    private Button btnRandom1;
    @FXML
    private ComboBox<String> cmbMateria;
    @FXML
    private ComboBox<String> cmbParalelo;
    @FXML
    private TextField txtMatApoyo;
    @FXML
    private Button btnRandom2;
    @FXML
    private Button btnnuevoJuego;
    
    private Materia matSeleccionada=null;
    private Paralelo parSeleccionado=null;
    static int cantPregNivSeleccionado=0;
    private ArrayList <Estudiante> cursoEstudiantes=null;
    private Estudiante partSeleccionado=null;
    static Estudiante apoyoSeleccionado=null;
    private Alert error=new Alert(AlertType.WARNING);
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(Materia m:Materia.materias){
            cmbMateria.getItems().add(m.getNombre());
        }
        cmbParalelo.setDisable(true);
        cmbPregNivel.setDisable(true);
        txtMatPart.setDisable(true);
        txtMatApoyo.setDisable(true);
        btnRandom1.setDisable(true);
        btnRandom2.setDisable(true);
    } 
    @FXML
    private void materiaSeleccionada(ActionEvent event){
        Alert error=new Alert(AlertType.ERROR);
        try{
           for(Materia m: Materia.materias){
                if(m.getNombre().equals(cmbMateria.getValue())){
                    matSeleccionada=m;
                }
            }
           cmbParalelo.getItems().clear();
           for(Paralelo p:Paralelo.paralelos){
                if(p.getMateria().getNombre().equals(cmbMateria.getValue()) && p.getTerminoAcademico().equals(App.terminoJuego)){
                    cmbParalelo.getItems().add(String.valueOf(p.getnumero()));
                }
            } 
           cmbParalelo.setDisable(false);
        }
        catch(NullPointerException e){
            error.setTitle("No se ha cargado una materia");
            error.setContentText("Ha ocurrido un error extraordinario");
            error.showAndWait();
        }
        catch(Exception e){
            e.printStackTrace();
            error.setTitle("Ha ocurrido un error externo");
            error.setContentText("Ha ocurrido un error extraordinario. Intente nuevamente");
            error.showAndWait();
        }
    }
    @FXML
    private void paraleloSeleccionado(ActionEvent event) {
        try{
            txtMatPart.clear();
            txtMatApoyo.clear();
            int numSeleccionado=0;
            if(cmbParalelo.getValue()!= null){
                numSeleccionado=Integer.parseInt(cmbParalelo.getValue());
            }
            for(Paralelo p: Paralelo.paralelos){
                if(p.getnumero()==numSeleccionado&&p.getMateria().equals(matSeleccionada)&&p.getTerminoAcademico().equals(App.terminoJuego)){
                    parSeleccionado=p;
                    cursoEstudiantes=p.getEstudiantes();
                }
            }
            for(int i=1;i<matSeleccionada.getCantNiveles()+1;i++){
                cmbPregNivel.getItems().add(String.valueOf(i));
            }
            cmbPregNivel.setDisable(false);
        }catch(Exception e){
            e.printStackTrace();
            error.setTitle("Ha ocurrido un error externo");
            error.setContentText("Ha ocurrido un error extraordinario. Intente nuevamente");
            error.showAndWait();
        }
    }
    @FXML
    private void nivelSeleccionado(ActionEvent event) {
        try{
            cantPregNivSeleccionado=Integer.parseInt(cmbPregNivel.getValue());
            txtMatPart.setDisable(false);
            btnRandom1.setDisable(false);
            btnRandom1.setOnMouseClicked(ev->{
                int indiceRandom=new Random().nextInt(cursoEstudiantes.size());
                partSeleccionado=cursoEstudiantes.get(indiceRandom);
                txtMatPart.setText(String.valueOf(partSeleccionado.getMatricula()));
                txtMatPart.setDisable(true);
            });
            txtMatApoyo.setDisable(false);
            btnRandom2.setDisable(false);
            btnRandom2.setOnMouseClicked(e->{
                do{
                    int indiceRandom2=new Random().nextInt(cursoEstudiantes.size());
                    apoyoSeleccionado=cursoEstudiantes.get(indiceRandom2); 
                }while(apoyoSeleccionado.equals(partSeleccionado));
                txtMatApoyo.setText(String.valueOf(apoyoSeleccionado.getMatricula()));
                txtMatApoyo.setDisable(true);
                btnnuevoJuego.setDisable(false);
            });
        }catch(Exception e){
            e.printStackTrace();
            error.setAlertType(AlertType.ERROR);
            error.setContentText("Ha ocurrido un error externo. Intente nuevamente");
            error.setTitle("Error externo");
        }
    }
    @FXML
    private void partSeleccionado(ActionEvent event) {
        if(partSeleccionado==null){
            for(Estudiante e: cursoEstudiantes){
                if(Integer.parseInt(txtMatPart.getText())==e.getMatricula()){
                    partSeleccionado=e;
                }
            }
        }
    }
    @FXML
    private void apoyoSeleccionado(ActionEvent event) {
        if(txtMatApoyo.getText()!=null){
            for(Estudiante e: cursoEstudiantes){
                if(Integer.parseInt(txtMatApoyo.getText())==e.getMatricula()){
                    apoyoSeleccionado=e;
                }
            } 
            btnnuevoJuego.setDisable(false);
        }
    }
    
    @FXML
    private void iniciarJuego(ActionEvent event) throws IOException{
        if(apoyoSeleccionado==null){
            if(Integer.parseInt(txtMatApoyo.getText())==partSeleccionado.getMatricula()){
                Alert partRedudante=new Alert(AlertType.ERROR);
                partRedudante.setTitle("Participante redudante");
                partRedudante.setContentText("La matrícula ingresada ya está asignada a participante. Ingrese otra matrícula válida");
                partRedudante.showAndWait();
            }else{
                Alert partNoEncontrado=new Alert(AlertType.ERROR);
                partNoEncontrado.setTitle("Participante de apoyo no encontrado");
                partNoEncontrado.setContentText("Ingrese una matrícula válida");
                partNoEncontrado.showAndWait(); 
            }
        }
        if(partSeleccionado==null){
            Alert partNoEncontrado=new Alert(AlertType.ERROR);
            partNoEncontrado.setTitle("Participante no encontrado");
            partNoEncontrado.setContentText("Ingrese una matrícula válida");
            partNoEncontrado.showAndWait();
        }
        System.out.println(matSeleccionada.toString());
        System.out.println(parSeleccionado.toString());
        System.out.println(cantPregNivSeleccionado);
        System.out.println(partSeleccionado.toString()+", con apoyo:"+ apoyoSeleccionado.toString());
        //INICIAR JUEGO CON LAS VARIABLES DEFINIDAS
        ArrayList<Pregunta> preguntasMateria = 
                Juego.getPreguntasMateria(matSeleccionada, 
                        Pregunta.preguntas);
        
        App.juego = new Juego(matSeleccionada, preguntasMateria, 
                parSeleccionado,
                partSeleccionado.getMatricula(), 
                apoyoSeleccionado.getMatricula(),
                cantPregNivSeleccionado, 
                java.time.LocalDate.now());
        
        
        // Cargar FXML con el controlador
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("juego.fxml"));
        JuegoController ct = new JuegoController();
        fxmlLoader.setController(ct);
        
        BorderPane root = new BorderPane();
        try{
            root = (BorderPane) fxmlLoader.load();
        } catch(Exception e){
            System.out.println(e);
        }
        
        App.changeRoot(root); 
    }
   
    @FXML
    private void switchToPrimary(ActionEvent event) throws IOException{
        App.setRoot("primary");
        
    }

}
