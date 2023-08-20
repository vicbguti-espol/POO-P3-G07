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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button guardarTermino;
    
    private Integer metodo;
    
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
        
        // Escoger el método entre editar y agregar según el click del botón
        btnEditar.setOnMouseClicked(e -> 
            {
                metodo = 0;
                editarTermino();
            });
        btnAgregar.setOnMouseClicked(e -> 
            {
                metodo = 1;
                editarTermino();
            });
        
        // Manejar el evento para guardar término del juego 
        guardarTermino.setOnMouseClicked(e -> guardarTerminoJuego());
        
        // Ubicar valor por default del término del juego 
        cmbTerminos.setValue(App.terminoJuego);
    }
    
    private void guardarTerminoJuego(){
        App.terminoJuego =(TerminoAcademico) cmbTerminos.getValue();
        // Mostrar alerta de agregación exitosa
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Resultado de la operación");
            alert.setContentText("Término de juego guardado exitosamente");
            
            alert.showAndWait();
    }
    
    private void editarTermino() {
        // Cargar fxml
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("datosTer.fxml"));
        DatosTerController ct = new DatosTerController();
        fxmlLoader.setController(ct);//se asigna el controlador
        
        BorderPane root = new BorderPane();
        try{
            root = (BorderPane) fxmlLoader.load();
        } catch(Exception e){
            System.out.println(e);
        }
        
        
        if (metodo.equals(0)){
            // Obtener la selección del tableView
            TerminoAcademico t = (TerminoAcademico) tvTerminosAcademicos.
                    getSelectionModel().getSelectedItem();
            ct.setDefault(t);
        }
        
        if (metodo.equals(1)){
            ct.setDefault();
        }
        
        ct.llenarCombos();
        App.changeRoot(root); }
    
}
