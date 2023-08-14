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
    private TableView<Materia> tvMaterias;
    
    static Materia materiaEditable;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        btneditarMateria.setOnMouseClicked(e->{
        tvMaterias.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            String editCodigo = newSelection.getCodigo();
            materiaEditable=Materia.materias.get(Materia.materias.indexOf(editCodigo));
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
        });});
        btnagregarMateria.setOnMouseClicked(e->{
            try{
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
        ObservableList<Materia> listaMaterias=FXCollections.observableArrayList(Materia.materias);
        
        TableColumn<Materia,String> tcCodigo=new TableColumn<>("Codigo");
        tcCodigo.setCellValueFactory(new PropertyValueFactory<>("Código"));
        
        TableColumn<Materia,String> tcNombre=new TableColumn<>("Nombre de Materia");
        tcCodigo.setCellValueFactory(new PropertyValueFactory<>("Nombre de Materia"));
        
        TableColumn<Materia,String> tcNiveles=new TableColumn<>("N° de niveles");
        tcCodigo.setCellValueFactory(new PropertyValueFactory<>("N° de niveles"));
        
        tvMaterias=new TableView<Materia>();
        tvMaterias.getColumns().addAll(tcCodigo,tcNombre,tcNiveles);
        tvMaterias.setColumnResizePolicy(tvMaterias.CONSTRAINED_RESIZE_POLICY);
        tvMaterias.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.SINGLE);
        tvMaterias.setItems(listaMaterias);
        
        return tvMaterias;
    }
        
    @FXML
    private void eliminarMateria(ActionEvent event) {
        tvMaterias.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            String elimCodigo = newSelection.getCodigo();
            Materia.eliminarMateria(Materia.materias.get(Materia.materias.indexOf(elimCodigo)));
            llenarTableViewMaterias();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Seleccione una materia");
            alert.showAndWait();
        }
        });
    }
}
