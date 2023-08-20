/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo.proyectojar;

import modelo.academico.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
/**
 * FXML Controller class
 *
 * @author Anthony
 */
public class visualizarMateriasController implements Initializable {
    @FXML
    private Button btnSalir;
    @FXML
    private Button btnagregarMateria;
    @FXML
    private Button btneditarMateria;
    @FXML
    private BorderPane bpCentral;
    @FXML
    private TableView<Materia> tvMaterias=new TableView<Materia>();;
    
    static Materia materiaEditable;
    
    ObservableList<Materia> listaMaterias=FXCollections.observableArrayList(Materia.materias);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        tvMaterias.setOnMouseClicked(e->{
            Materia selectedMateria = tvMaterias.getSelectionModel().getSelectedItem();
            if (selectedMateria != null) {
                materiaEditable = selectedMateria;
            }
        });
        btneditarMateria.setOnMouseClicked(e->{
            //Materia m = (Materia) tvMaterias.getSelectionModel().getSelectedItem();
            Materia m = materiaEditable;
            if (m != null) {
                materiaEditable=m;
                try{
                App.setRoot("confMaterias");
                }catch(Exception ex){
                    ex.printStackTrace();
                }   
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error");
                alert.setContentText("Seleccione una materia");
                alert.showAndWait();
            }
        });
        btnagregarMateria.setOnMouseClicked(e->{
            try{
                materiaEditable=null;
                App.setRoot("confMaterias");
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
        btnSalir.setOnMouseClicked(e ->{
          try{
            App.setRoot("primary");
          }    
          catch(Exception ex){
            ex.printStackTrace();
          }
        });
                
        bpCentral.setPadding(new javafx.geometry.Insets(10));
        bpCentral.setCenter(llenarTableViewMaterias());
        
    }
    @FXML
    private TableView<Materia> llenarTableViewMaterias() {
        TableColumn<Materia,String> tcCodigo=new TableColumn<>("Codigo");
        tcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        
        TableColumn<Materia,String> tcNombre=new TableColumn<>("Nombre de Materia");
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        
        TableColumn<Materia,String> tcNiveles=new TableColumn<>("N° de niveles");
        tcNiveles.setCellValueFactory(new PropertyValueFactory<>("cantNiveles"));
        
        tvMaterias.getColumns().addAll(tcCodigo,tcNombre,tcNiveles);
        tvMaterias.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.SINGLE);
        tvMaterias.setItems(listaMaterias);
        
        return tvMaterias;
    }
        
    @FXML
    private void eliminarMateria(ActionEvent event) {
        //Materia m = (Materia) tvMaterias.getSelectionModel().getSelectedItem();
        Materia m=materiaEditable;
        if (m != null) {
            //Materia.materias.remove(m);
            //IMPLEMENTAR CON SERIALIZABLE
            Materia.eliminarMateria(m);
            tvMaterias.setItems(FXCollections.observableArrayList(Materia.materias));
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Seleccione una materia");
            alert.showAndWait();
        }
    }
}
