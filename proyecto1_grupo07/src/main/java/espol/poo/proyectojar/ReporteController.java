/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo.proyectojar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import modelo.academico.Materia;
import modelo.academico.TerminoAcademico;
/**
 * FXML Controller class
 *
 * @author Omen
 */
public class ReporteController implements Initializable {


    @FXML
    private ComboBox cmbTermino;
    @FXML
    private ComboBox cmbParalelo;
    @FXML
    private ComboBox cmbMateria;
    @FXML
    private TableView tvReporte;
    @FXML
    private Button btnRegresar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarComboBox();
    }    
    
    
    /**
     * Cargar combos
     */
    public void cargarComboBox(){
        cmbTermino.getItems().setAll(TerminoAcademico.terminosAcademicos);
        cmbMateria.getItems().setAll(Materia.materias);
        cmbParalelo.getItems().setAll(TerminoAcademico.terminosAcademicos);
    }
    
}
