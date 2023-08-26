/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package espol.poo.proyectojar;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import modelo.academico.Estudiante;
import modelo.academico.Materia;
import modelo.academico.Paralelo;
import modelo.academico.TerminoAcademico;
import modelo.juego.Juego;
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
    @FXML
    private TableColumn<Juego, String> colFecha;
    @FXML
    private TableColumn<Juego, String> colParticipante;
    @FXML
    private TableColumn<Juego, Integer> colNivelMax;
    @FXML
    private TableColumn<Juego, String> colTiempo;
    @FXML
    private TableColumn<Juego, Integer> colPreguntas;
    @FXML
    private TableColumn<Juego, Integer> colComodines;
    @FXML
    private TableColumn<Juego, String> colPremio;
    @FXML
    private TableColumn<Juego, Void> colOpciones;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarComboBox();
        asignarColumnas();
        System.out.println(Juego.juegos.size());
    }    
    
    
    /**
     * Cargar combos
     */
    public void cargarComboBox(){
        cmbTermino.getItems().setAll(TerminoAcademico.terminosAcademicos);
        cmbMateria.getItems().setAll(Materia.materias);
    }
    
    @FXML
    public void cargarCmbParalelos(){
        TerminoAcademico termino = (TerminoAcademico)cmbTermino.getValue();
        cmbParalelo.getItems().setAll(Paralelo.filtrarParalelos(termino));
        filtrarJuegos();
    }

    
    /**
     * Asignar atributos de columnas
     */
    public void asignarColumnas(){
        //asignar a cada columna el atributo del objeto correspondiente
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechas"));
        colParticipante.setCellValueFactory(new PropertyValueFactory<>("participantes"));
        colNivelMax.setCellValueFactory(new PropertyValueFactory<>("lvlMax"));
        colTiempo.setCellValueFactory(new PropertyValueFactory<>("time"));
        colPreguntas.setCellValueFactory(new PropertyValueFactory<>("preguntasContestadas"));
        colComodines.setCellValueFactory(new PropertyValueFactory<>("comodinesUsados"));
        colPremio.setCellValueFactory(new PropertyValueFactory<>("premio"));
        agregarOpciones();
    }
    /**
     * Filtrar juegos por comboBox
     */
    @FXML
    private void filtrarJuegos(){
        // Filtrar por comboBox y los atributos de cada juego
        List<Juego> juegoCriterios =  Juego.juegos
                .stream()
                .filter(p -> 
                        (p.getTermino().equals((TerminoAcademico)cmbTermino.getValue())) && 
                        (p.getMateria().equals((Materia)cmbMateria.getValue())) &&
                        (p.getParalelo().equals((Paralelo)cmbParalelo.getValue()))
                )
                .collect(Collectors.toList());
         // Actualizar TableView
         tvReporte.getItems().setAll(juegoCriterios);
    }
    
    private void agregarOpciones() {

        Callback<TableColumn<Juego, Void>, TableCell<Juego, Void>> cellFactory = new Callback<TableColumn<Juego, Void>, TableCell<Juego, Void>>() {
            @Override
            public TableCell<Juego, Void> call(final TableColumn<Juego, Void> param) {
                TableCell<Juego, Void> cell = new TableCell<Juego, Void>() {
                   
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            //hbox para ubicar los botones
//                            HBox hbOpciones = new HBox(5);
                            //recuperar el juego de la fila
                            Juego juego = getTableView().getItems().get(getIndex());
                            //boton detalles
                            Button btnEd = new Button("Ver detalle");
                            btnEd.setOnAction(r -> verDetalles(juego));
                       
//                            //boton eliminar
//                            Button btnEl = new Button("Eliminar");
//                            //este boton si inhabilita para genero femenino
//                            if (juego.getGenero().equals(Genero.FEMENINO))
//                                btnEl.setDisable(true);
//                            btnEl.setOnAction(e -> eliminarEmpleado2(juego));
//                            //se agregan botones al hbox
//                            hbOpciones.getChildren().addAll(btnEd,btnEl);
//                            //se ubica hbox en la celda
//                            setGraphic(hbOpciones);
                            setGraphic(btnEd);
                        }
                    }
                };
                return cell;
            }
        };

        colOpciones.setCellFactory(cellFactory);

    }
    
    public void verDetalles(Juego j){
        
    }
    
    @FXML
    private void switchToPrimary(ActionEvent event) throws IOException{
        App.setRoot("primary");
        
    }
    
}
