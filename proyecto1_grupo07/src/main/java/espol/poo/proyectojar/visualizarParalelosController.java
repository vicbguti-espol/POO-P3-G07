/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo.proyectojar;

import modelo.academico.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
/**
 * FXML Controller class
 *
 * @author Anthony
 */
public class visualizarParalelosController implements Initializable {


    @FXML
    private Button btnagregarParalelo;
    @FXML
    private Button btnSalir;
    @FXML
    private TableView<Paralelo> tvParalelos=new TableView<Paralelo>();
    @FXML
    private BorderPane bpCentral;
    
    private Paralelo paraleloEliminable;
    
    ObservableList<Paralelo> listaParalelos=FXCollections.observableArrayList(Paralelo.paralelos);;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tvParalelos.setOnMouseClicked(e->{
            Paralelo selectedParalelo = tvParalelos.getSelectionModel().getSelectedItem();
            if (selectedParalelo!=null){
                paraleloEliminable=selectedParalelo;
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
        btnagregarParalelo.setOnMouseClicked(e->{
            try{
                App.setRoot("confParalelos");
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });
        bpCentral.setPadding(new javafx.geometry.Insets(10));
        bpCentral.setCenter(llenarTableViewParalelos());
    }    
    
    @FXML
    private TableView<Paralelo> llenarTableViewParalelos() {
        TableColumn<Paralelo,String> tcNumero=new TableColumn<>("Numero");
        tcNumero.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getnumero())));
        
        TableColumn<Paralelo,String> tcMateria=new TableColumn<>("Nombre de Materia");
        tcMateria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMateria().getNombre()));
        
        TableColumn<Paralelo,String> tcTermino=new TableColumn<>("Término Académico");
        tcTermino.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTerminoAcademico().toString()));
        
        tvParalelos.getColumns().addAll(tcNumero,tcMateria,tcTermino);
        tvParalelos.setColumnResizePolicy(tvParalelos.CONSTRAINED_RESIZE_POLICY);
        tvParalelos.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.SINGLE);
        tvParalelos.setItems(listaParalelos);
        
        return tvParalelos;
    }
    @FXML
    private void eliminarParalelo(ActionEvent event){
        System.out.println("ASASSASA");
        Paralelo p=paraleloEliminable;
        if (p != null) {
            Paralelo.eliminarParalelos(p);
            tvParalelos.setItems(FXCollections.observableArrayList(Paralelo.paralelos));
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Seleccione un paralelo");
            alert.showAndWait();
        }
    }

}
