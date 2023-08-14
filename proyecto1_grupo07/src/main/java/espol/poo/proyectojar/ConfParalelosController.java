/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo.proyectojar;

import modelo.academico.*;
import java.io.File;
import java.net.URL;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
/**
 * FXML Controller class
 *
 * @author Anthony
 */
public class ConfParalelosController implements Initializable {
    @FXML
    private ComboBox<String> cmbMateria;
    @FXML
    private ComboBox<String> cmbTermino;
    @FXML
    private ComboBox<String> cmbNParalelo;
    @FXML
    private TextField txtPath;
    @FXML
    private Button btnPath;
    
    File archivoCarga;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for(Materia m:Materia.materias){
            cmbMateria.getItems().add(m.getNombre());
        }
        for(TerminoAcademico t:TerminoAcademico.terminosAcademicos){
            cmbTermino.getItems().add(t .getAñoTermino()+" - "+t.getNumTermino());
        }        
        for(int i=1;i<25;i++)cmbNParalelo.getItems().add(String.valueOf(i));
        btnPath.setOnMouseClicked(e->{
           FileChooser archivo= new FileChooser(); 
           archivo.setTitle("Escoja archivo de estudiantes");
           Stage stage = new Stage();
           archivoCarga = archivo.showOpenDialog(stage);
        });
    }    
    
    @FXML
    private void agregarParalelo(ActionEvent event) {
        int numeroParalelo=Integer.parseInt(cmbNParalelo.getValue());
        Materia matParalelo=Materia.materias.get(Materia.materias.indexOf(cmbMateria.getValue()));
        int indice=0;
        for(TerminoAcademico t:TerminoAcademico.terminosAcademicos) {
            String[] elementos= cmbTermino.getValue().split("-");
            if(new TerminoAcademico(Integer.parseInt(elementos[0]),Integer.parseInt(elementos[1])).equals(t)){
                indice=TerminoAcademico.terminosAcademicos.indexOf(t);
            }
        }
        TerminoAcademico tParalelo=TerminoAcademico.terminosAcademicos.get(indice);
        if(archivoCarga!=null){
            Paralelo.agregarParalelos(new Paralelo(numeroParalelo,matParalelo,tParalelo,Estudiante.cargarEstudiantes(archivoCarga.getPath())));
        }else if(archivoCarga==null){
         Paralelo.agregarParalelos(new Paralelo(numeroParalelo,matParalelo,tParalelo,Estudiante.cargarEstudiantes(txtPath.getText())));
        }else{
            Alert faltaest=new Alert(AlertType.ERROR);
            faltaest.setTitle("Error en la carga de estudiantes");
            faltaest.setContentText("No selecciono ningún método de carga. Intene nuevamente");
            faltaest.showAndWait();
        }
    }

    @FXML
    private void switchtoVisualizarParalelos(ActionEvent event) {
        try{
            App.setRoot("visualizarParalelos");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
