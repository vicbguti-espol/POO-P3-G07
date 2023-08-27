/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.poo.proyectojar;

import static espol.poo.proyectojar.visualizarMateriasController.materiaEditable;
import espol.poo.modelo.academico.*;
import espol.poo.modelo.juego.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import espol.poo.modelo.academico.TerminoAcademico;

/**
 *
 * @author joshz
 */
public class ConfPreguntasController implements Initializable{
    @FXML
    private BorderPane bpCentral;
    @FXML
    private TableView<Pregunta> tvPreguntas=new TableView<Pregunta>();;
    @FXML
    private ComboBox cmbMateria;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnRespuestas;
    
    static Pregunta preguntaEditable;
    ObservableList<Pregunta> listaPreguntas =FXCollections.observableArrayList(Pregunta.preguntas);
    
    @FXML
    private void switchToPrimary(ActionEvent event) throws IOException{
        App.setRoot("primary");
    }
    @FXML
    private void switchToRespuestas(ActionEvent event) throws IOException{
        Pregunta m = preguntaEditable;
        if (m != null) {
            App.setRoot("respuestasMenu");
        } else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setContentText("Seleccione una pregunta");
                alert.showAndWait();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        cargarComboBox();
        tvPreguntas.setOnMouseClicked(e->{
            Pregunta selectedPregunta = tvPreguntas.getSelectionModel().getSelectedItem();
            if (selectedPregunta != null) {
                preguntaEditable = selectedPregunta;
            }
            System.out.println(preguntaEditable.getTexto());
        });
        btnEditar.setOnMouseClicked(e->{
            Pregunta m = preguntaEditable;
            if (m != null) {
                preguntaEditable=m;
                try{
                App.setRoot("preguntaMenu");
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setContentText("Seleccione una pregunta");
                alert.showAndWait();
            }
        });
        
        btnAgregar.setOnMouseClicked(e->{
            try{
                preguntaEditable=null;
                App.setRoot("preguntaMenu");
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });

        
        bpCentral.setCenter(llenarTableViewPreguntas());
    }
    
    
    @FXML
    private TableView<Pregunta> llenarTableViewPreguntas() {
        TableColumn<Pregunta,Integer> tcCodigo=new TableColumn<>("Nivel");
        tcCodigo.setCellValueFactory(new PropertyValueFactory<>("nivel"));
        
        TableColumn<Pregunta,String> tcTexto=new TableColumn<>("Pregunta");
        tcTexto.setCellValueFactory(new PropertyValueFactory<>("texto"));
        
        TableColumn<Pregunta,String> tcMateria=new TableColumn<>("Materia");
        tcMateria.setCellValueFactory(new PropertyValueFactory<>("materia"));
        
        tvPreguntas.getColumns().addAll(tcCodigo,tcTexto,tcMateria);
        tvPreguntas.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.SINGLE);
        tvPreguntas.setItems(listaPreguntas);
        
        return tvPreguntas;
    }
    @FXML
    private void eliminarPregunta(ActionEvent event) {
        Pregunta m=preguntaEditable;
        if (m != null) {
            //IMPLEMENTAR CON SERIALIZABLE
            Pregunta.eliminarPregunta(m);
            tvPreguntas.setItems(FXCollections.observableArrayList(Pregunta.preguntas));
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Seleccione una materia");
            alert.showAndWait();
        }
    }
    public void cargarComboBox(){
        cmbMateria.getItems().setAll(Materia.materias);
    }
    
    
}
