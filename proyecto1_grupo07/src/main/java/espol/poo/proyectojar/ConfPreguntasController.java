/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.poo.proyectojar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    private TableView<TerminoAcademico> tvPreguntas;
    @FXML
    private TableColumn<TerminoAcademico, Integer> colNivel;
    @FXML
    private TableColumn<TerminoAcademico, Integer> colTxtPregunta;
    @FXML
    private TableColumn<TerminoAcademico, Integer> colMateria;
    @FXML
    private ComboBox cmbMateria;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnRegresar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        // Escoger el método entre editar y agregar según el click del botón
        
        btnRegresar.setOnMouseClicked(e ->{
          try{
            App.setRoot("primary");
          }    
          catch(Exception ex){
            ex.printStackTrace();
          }
        });
        
    }
    @FXML
    private void editarPregunta() {
        // Cargar fxml
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("preguntaMenu.fxml"));
        DatosTerController ct = new DatosTerController();
        fxmlLoader.setController(ct);//se asigna el controlador
        
        BorderPane root = new BorderPane();
        try{
            root = (BorderPane) fxmlLoader.load();
        } catch(Exception e){
            System.out.println(e);
        }
        ct.setDefault();
        ct.llenarCombos();
        App.changeRoot(root); }
}
