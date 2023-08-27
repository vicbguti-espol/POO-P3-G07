package espol.poo.proyectojar;

import espol.poo.excepciones.TerminoExistenteException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import espol.poo.modelo.academico.TerminoAcademico;

/**
 *
 * @author Omen
 */
public class DatosTerController{
    @FXML
    ComboBox cmbAño;
    @FXML
    ComboBox cmbTermino;
    @FXML
    Label lblEdTer;
    
    Integer metodo;
    TerminoAcademico terminoDefault;
    ArrayList<TerminoAcademico> terminos;
    
    @FXML
    private void initialize(){
        terminos = TerminoAcademico.terminosAcademicos;
    }
    
    /**
     * Volver a la pantalla inicial de términos
     * @param event
     * @throws IOException 
     */
    @FXML
    private void switchToTerminos(ActionEvent event) throws IOException{
        App.setRoot("terminos");
    }
    
    /**
     * Llenar los combos de año y término
     */
    public void llenarCombos(){
        // Indicar el título de selección
        lblEdTer.setText("Editar Término Académico");
        
        // Crear una lista de años de 1958 (fundación espol) a el año actual
        List<Integer> años = new ArrayList<>();
        for (int i = 1958; i < App.añoActual + 1; i++){
            años.add(i);
        }
        
        // Crear una lista de terminos 0 - PAE, 1 - 1PAO, 2 - 2PAO
        List<Integer> terminosAc = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            terminosAc.add(i);
        }
        
        // Llenar el combo de año
        cmbAño.getItems().setAll(años);
        // Llenar el combo de terminos
        cmbTermino.getItems().setAll(terminosAc);
    }
    
    /**
     * Llenar los combos con los datos de un término
     * @param t
     */
    public void setDefault(TerminoAcademico t){
        terminoDefault = t;
        // Set de los valores de los comboBox
        cmbAño.setValue(t.getAñoTermino());
        cmbTermino.setValue(t.getNumTermino());
    }
    
    /**
     *
     */
    public void setDefault(){
        cmbAño.setValue(App.añoActual);
        cmbAño.setDisable(true);
    }
            
    /**
     * Guardar término
     */        
    public void guardarTermino(){
        System.out.println("Guardando término");
        
        // Obtener los datos seleccionados en los comboBox
        Integer selAño = (Integer) cmbAño.getValue();
        Integer selTermino = (Integer) cmbTermino.getValue();
        
        // Instanciar un nuevo término académico con los datos del comboBox
        TerminoAcademico t1 = new 
            TerminoAcademico(selTermino, selAño);
        
        // Remover el termino seleccionado en edición de término académico
        TerminoAcademico.eliminarTerminosAcademicos(terminoDefault);
        
        // Agregar un término académico en caso de no existir
        try{
            TerminoAcademico.agregarTerminosAcademicos(t1);
            
            // Mostrar alerta de agregación exitosa
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Resultado de la operación");
            alert.setContentText("Nuevo término agregado exitosamente");
            
            alert.showAndWait();
            // Cambiar a la pantalla main de términos
            App.setRoot("terminos");

            
        } catch(IOException i){
            System.out.println(i);
        } catch (TerminoExistenteException t){
            // Mostrar alerta de agregación exitosa
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Resultado de la operación");
            alert.setContentText("Término actualmente en existencia");
            
            alert.showAndWait();
            
        }
        
    }
    
    
}
