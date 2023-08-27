/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo.proyectojar;

import espol.poo.modelo.academico.Estudiante;
import espol.poo.modelo.academico.Materia;
import espol.poo.modelo.academico.Paralelo;
import espol.poo.modelo.academico.TerminoAcademico;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
     * @param url
     * @param rb
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
    private void agregarParalelo(ActionEvent event) throws IOException{
        Materia matParalelo=null;
        TerminoAcademico tParalelo=null;
        int numeroParalelo=0;
        if(cmbMateria.getValue()==null||cmbNParalelo.getValue()==null||cmbTermino.getValue()==null){
            Alert faltaatributos=new Alert(AlertType.ERROR);
            faltaatributos.setTitle("Error de Usuario");
            faltaatributos.setContentText("Ha dejado campos vacios");
            faltaatributos.showAndWait();
        }else if(archivoCarga==null&&"".equals(txtPath.getText())){
            Alert faltaest=new Alert(AlertType.ERROR);
            faltaest.setTitle("Error en la carga de estudiantes");
            faltaest.setContentText("No selecciono ningún método de carga. Intente nuevamente");
            faltaest.showAndWait();
        }
        else{
            numeroParalelo=Integer.parseInt(cmbNParalelo.getValue());
            for(Materia m: Materia.materias){
                if(m.getNombre().equals(cmbMateria.getValue())){
                    matParalelo=m;
                }
            }
            for(TerminoAcademico t:TerminoAcademico.terminosAcademicos) {
                String[] elementos= cmbTermino.getValue().split("-");
                TerminoAcademico tprueba= new TerminoAcademico(Integer.parseInt(elementos[0].trim()),Integer.parseInt(elementos[1].trim()));
                if(tprueba.equals(tprueba)){
                    tParalelo=t;
                }
            }
            for (Paralelo p:Paralelo.paralelos){
                if(p.getnumero()==numeroParalelo&&p.getMateria().equals(matParalelo)&&p.getTerminoAcademico().equals(tParalelo)){
                    Alert error=new Alert(AlertType.ERROR);    
                    error.setTitle("Paralelo Existente");
                    error.setContentText("El paralelo ya existe. Por favor ingrese valores válidos");
                    error.showAndWait();
                }else if(archivoCarga==null){
                    try{
                        Paralelo.agregarParalelos(new Paralelo(numeroParalelo,matParalelo,tParalelo,Estudiante.cargarEstudiantes(txtPath.getText())));             
                        Alert faltaest=new Alert(AlertType.INFORMATION);
                        faltaest.setTitle("Guardado");
                        faltaest.setContentText("Paralelo guardado exitosamente");
                        faltaest.showAndWait();
                        App.setRoot("visualizarParalelos");
                    }
                    catch(FileNotFoundException e){
                        Alert rutanovalida=new Alert(AlertType.ERROR);    
                        rutanovalida.setTitle("Error de archivo");
                        rutanovalida.setContentText("La ruta no contiene un archivo o no estan en el formato válido");
                        rutanovalida.showAndWait();
                    }
                }else if(archivoCarga!=null){
                    Paralelo.agregarParalelos(new Paralelo(numeroParalelo,matParalelo,tParalelo,Estudiante.cargarEstudiantes(archivoCarga.getPath())));
                    Alert faltaest=new Alert(AlertType.INFORMATION);
                    faltaest.setTitle("Guardado");
                    faltaest.setContentText("Paralelo guardado exitosamente");
                    faltaest.showAndWait();
                    App.setRoot("visualizarParalelos");
                }
            }
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
