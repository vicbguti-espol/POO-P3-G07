/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo.proyectojar;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import modelo.academico.*;
/**
 * FXML Controller class
 *
 * @author Omen
 */
public class TerminosController{
    @FXML
    private TableView<TerminoAcademico> tvTerminosAcademicos;
    @FXML
    private TableColumn<TerminoAcademico, Integer> colAño;
    @FXML
    private TableColumn<TerminoAcademico, Integer> colTermino;
    @FXML
    private ComboBox cmbTerminos;
    
    //List<TerminoAcademico> terminos = TerminoAcademico.
    //            cargarTerminosAcademicos(App.pathTer);
    
    @FXML
    private void switchToPrimary(ActionEvent event) throws IOException{
        App.setRoot("primary");
    }
    
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private void initialize(){
        System.out.println(TerminoAcademico.terminosAcademicos);
        //cargar combobox
        cmbTerminos.getItems().setAll(TerminoAcademico.terminosAcademicos);
        
        //asignar a cada columna el atributo del objeto correspondiente
        colAño.setCellValueFactory(new PropertyValueFactory<>("añoTermino"));
        colTermino.setCellValueFactory(new PropertyValueFactory<>("numTermino"));
        
        //datos en tableview
        tvTerminosAcademicos.getItems().setAll(TerminoAcademico.terminosAcademicos);
    }
    
    
    
    @FXML
    private void editarTermino() throws IOException {
        TerminoAcademico t = (TerminoAcademico) tvTerminosAcademicos.getSelectionModel().getSelectedItem();
        
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("datosTer.fxml"));
        DatosTerController ct = new DatosTerController();
        fxmlLoader.setController(ct);//se asigna el controlador

        BorderPane root = (BorderPane) fxmlLoader.load();
        
        ct.llenarCombos();
        ct.setDefault(t);
        App.changeRoot(root);
        
    }
}
