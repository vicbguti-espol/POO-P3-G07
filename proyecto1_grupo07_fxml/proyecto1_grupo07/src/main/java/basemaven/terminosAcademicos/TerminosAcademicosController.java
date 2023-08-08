/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package basemaven.terminosAcademicos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import modelo.academico.TerminoAcademico;
import basemaven.App;



/**
 * FXML Controller class
 *
 * @author Omen
 */
public class TerminosAcademicosController implements Initializable {


    @FXML
    private TableView<TerminoAcademico> tvTerminosAcademicos;
    @FXML
    private TableColumn<TerminoAcademico, Integer> colAño;
    @FXML
    private TableColumn<TerminoAcademico, Integer> colTermino;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //asignar a cada columna el atributo del objeto correspondiente
        colAño.setCellValueFactory(new PropertyValueFactory<>("añoTermino"));
        colTermino.setCellValueFactory(new PropertyValueFactory<>("numTermino"));
        
        //datos en tableview
        llenarTableView();
    }    
    
    
    public void llenarTableView() {
        tvTerminosAcademicos.getItems().setAll(TerminoAcademico.cargarTerminos(App.pathTerminos));
    }
    
}
