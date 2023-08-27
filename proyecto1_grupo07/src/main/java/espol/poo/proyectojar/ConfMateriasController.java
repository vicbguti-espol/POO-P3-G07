/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo.proyectojar;

import espol.poo.modelo.academico.Materia;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Anthony
 */
public class ConfMateriasController implements Initializable {

    @FXML
    private Label lblTitulo;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNiveles;
    @FXML
    private TextField txtNombre;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(visualizarMateriasController.materiaEditable!=null) {
            lblTitulo.setText("Editar Materia");
            txtCodigo.setText(visualizarMateriasController.materiaEditable.getCodigo());
            txtCodigo.setEditable(false);
            txtNiveles.setText(String.valueOf(visualizarMateriasController.materiaEditable.getCantNiveles()));
            txtNombre.setText(visualizarMateriasController.materiaEditable.getNombre());
        }
        else {
            lblTitulo.setText("Agregar Materia");
        }
    }   


    @FXML
    private void switchToVisualizarMaterias(ActionEvent event) {
        try {   
            App.setRoot("visualizarMaterias");
        } catch (IOException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error de carga");
            alert.setContentText("Hubo un error de FXML");
            alert.showAndWait();
            
        }
    }

    @FXML
    private void agregarMateria(ActionEvent event) throws IOException{
        if("".equals(txtCodigo.getText())||"".equals(txtNombre.getText())||"".equals(txtNiveles.getText())){
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText("Falta uno o mas atributos en la materia");
            alert.showAndWait();
            if(visualizarMateriasController.materiaEditable==null){
                Materia.agregarMateria(new Materia(txtCodigo.getText(),txtNombre.getText(),Integer.parseInt(txtNiveles.getText())));
            }
            else{
                Materia.editarMateria(visualizarMateriasController.materiaEditable,txtNombre.getText(),Integer.parseInt(txtNiveles.getText()));
            }
            Alert agregado= new Alert(AlertType.INFORMATION);
            agregado.setTitle("Agregado Exitosamente");
            agregado.setContentText("Cambios guardados exitosamente");
            agregado.showAndWait();
            App.setRoot("visualizarMaterias");
        }else{
        if(visualizarMateriasController.materiaEditable==null){
                    Materia.agregarMateria(new Materia(txtCodigo.getText(),txtNombre.getText(),Integer.parseInt(txtNiveles.getText())));
                }
                else{
                    Materia.editarMateria(visualizarMateriasController.materiaEditable,txtNombre.getText(),Integer.parseInt(txtNiveles.getText()));
                }
        Alert agregado= new Alert(AlertType.INFORMATION);
        agregado.setTitle("Agregado Exitosamente");
        agregado.setContentText("Cambios guardados exitosamente");
        agregado.showAndWait();
        App.setRoot("visualizarMaterias");
        }

    }    
}
