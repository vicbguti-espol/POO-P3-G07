/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo.proyectojar;

import modelo.academico.*;
import java.util.Random;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
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
    
    Materia matSeleccionada=null;
    Paralelo parSeleccionado=null;
    int cantPregNivSeleccionado=0;
    ArrayList <Estudiante> cursoEstudiantes=null;
    Estudiante partSeleccionado=null;
    Estudiante apoyoSeleccionado=null;
    
    //TEMPORAL
    static TerminoAcademico terminoSeleccionado=null;
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
    private void materiaSeleccionada(ActionEvent event) {
        if(cmbMateria.getValue()!=null){
            for(Paralelo p:Paralelo.paralelos){
                if(p.getMateria().getNombre().equals(cmbMateria.getValue()) && p.getTerminoAcademico().equals(terminoSeleccionado)){
                    cmbParalelo.getItems().add(String.valueOf(p.getnumero()));
                }
            }
            cmbParalelo.setDisable(false);
        }
    }
    @FXML
    private void paraleloSeleccionado(ActionEvent event) {
        if(cmbParalelo.getValue()!=null){
            matSeleccionada=Materia.materias.get(Materia.materias.indexOf(cmbMateria.getValue()));
            for(int i=1;i<matSeleccionada.getCantNiveles()+1;i++){
                cmbPregNivel.getItems().add(String.valueOf(i));
            }
            cmbPregNivel.setDisable(false);
        }
    }
    @FXML
    private void nivelSeleccionado(ActionEvent event) {
        Paralelo temp1 = new Paralelo(Integer.parseInt(cmbParalelo.getValue()),matSeleccionada,terminoSeleccionado,"temp");
        for(Paralelo p:Paralelo.paralelos){
            Paralelo temp2 = new Paralelo(p.getnumero(),p.getMateria(),p.getTerminoAcademico(),"temp");
            if(temp1.equals(temp2)){
                cursoEstudiantes=p.getEstudiantes();
                parSeleccionado=p;
            }   
        }
        if(cmbPregNivel.getValue()!=null){
            txtMatPart.setDisable(false);
            btnRandom1.setDisable(false);
            btnRandom1.setOnMouseClicked(e->{
                int indiceRandom=new Random().nextInt(cursoEstudiantes.size());
                partSeleccionado=cursoEstudiantes.get(indiceRandom);
                txtMatPart.setText(String.valueOf(partSeleccionado.getMatricula()));
                txtMatPart.setDisable(true);
                
            });
            cantPregNivSeleccionado=Integer.parseInt(cmbPregNivel.getValue());
        }
    }
    @FXML
    private void partSeleccionado(ActionEvent event) {
        if(txtMatPart.getText()!=null){
            for(Estudiante e: cursoEstudiantes){
                if(Integer.parseInt(txtMatPart.getText())==e.getMatricula()){
                    partSeleccionado=e;
                }
            }
            if(partSeleccionado==null){
                Alert partNoEncontrado=new Alert(AlertType.ERROR);
                partNoEncontrado.setTitle("Participante no encontrado");
                partNoEncontrado.setContentText("Ingrese una matrícula válida");
                partNoEncontrado.showAndWait();
            }
            txtMatApoyo.setDisable(false);
            btnRandom2.setDisable(false);
            btnRandom2.setOnMouseClicked(e->{
                do{
                    int indiceRandom=new Random().nextInt(cursoEstudiantes.size());
                    apoyoSeleccionado=cursoEstudiantes.get(indiceRandom); 
                }while(apoyoSeleccionado.equals(partSeleccionado));
                txtMatApoyo.setText(String.valueOf(apoyoSeleccionado.getMatricula()));
                txtMatApoyo.setDisable(true);
                btnnuevoJuego.setDisable(false);
                
            });
        }
    }
    @FXML
    private void apoyoSeleccionado(ActionEvent event) {
        if(txtMatApoyo.getText()!=null){
            if(Integer.parseInt(txtMatApoyo.getText())==partSeleccionado.getMatricula()){
                Alert partRedudante=new Alert(AlertType.ERROR);
                partRedudante.setTitle("Participante redudante");
                partRedudante.setContentText("La matrícula ingresada ya está asignada a participante. Ingrese otra matrícula válida");
                partRedudante.showAndWait();
            }
            for(Estudiante e: cursoEstudiantes){
                if(Integer.parseInt(txtMatApoyo.getText())==e.getMatricula()){
                    apoyoSeleccionado=e;
                }
            }
            if(partSeleccionado==null){
                Alert partNoEncontrado=new Alert(AlertType.ERROR);
                partNoEncontrado.setTitle("Participante no encontrado");
                partNoEncontrado.setContentText("Ingrese una matrícula válida");
                partNoEncontrado.showAndWait();
            }
            btnnuevoJuego.setDisable(false);
        }
    }
    
    @FXML
    private void iniciarJuego(ActionEvent event) {
        //INICIAR JUEGO CON LAS VARIABLES DEFINIDAS
        //App.setRoot("Juego");
    }

}
