package espol.poo.proyectojar;

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
import javafx.scene.control.RadioButton;
import modelo.academico.TerminoAcademico;

public class DatosTerController{
    @FXML
    ComboBox cmbAño;
    @FXML
    ComboBox cmbTermino;
    
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
        // Crear una lista de años de 1958 (fundación espol) a el año actual
        List<Integer> años = new ArrayList<>();
        for (int i = 1958; i < App.añoActual + 1; i++){
            años.add(i);
        }
        
        // Crear una lista de terminos 0 - PAE, 1 - 1PAO, 2 - 2PAO
        List<Integer> terminos = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            terminos.add(i);
        }
        
        // Llenar el combo de año
        cmbAño.getItems().setAll(años);
        // Llenar el combo de terminos
        cmbTermino.getItems().setAll(terminos);
    }
    
    /**
     * Llenar los combos con los datos de un término
     */
    public void setDefault(TerminoAcademico t){
        // Set de los valores de los comboBox
        cmbAño.setValue(t.getAñoTermino());
        cmbTermino.setValue(t.getNumTermino());
    }
    
    public void guardarTermino(){
        ArrayList<TerminoAcademico> terminos = TerminoAcademico.terminosAcademicos;
        System.out.println("Guardando término");
        /*
        RadioButton selectedRadioButton = (RadioButton) genero.getSelectedToggle();
        String genero = selectedRadioButton.getText();
        System.out.println(genero);
        
        Empleado e = new Empleado(txtCedula.getText(), 
                                  txtNombre.getText(), 
                                  (Departamento) cmbDepartamento.getValue(),
                                   Genero.valueOf(genero.toUpperCase()));

        if (empleados.contains(e)){
            int ind = empleados.indexOf(e);
            empleados.set(ind, e);
        }else{
            empleados.add(e);//agregar empleado a la lista
        }
        System.out.println("Nuevo Empleado:" + e);
        
        //serializar la lista
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(App.pathEmpleados))){
            out.writeObject(empleados);
            out.flush();

            //mostrar informacion
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Resultado de la operación");
            alert.setContentText("Nueva persona agregada exitosamente");

            alert.showAndWait();
            App.setRoot("primary");

        } catch (IOException ex) {
            System.out.println("IOException:" + ex.getMessage());
        } 
*/
    }
    
}
