package espol.poo.proyectojar;

import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.juego.Juego;
import modelo.juego.Pregunta;


public class DetallesController {
    
    @FXML
    private TableView<Pregunta> tvPreguntas;
    @FXML
    private TableColumn<Pregunta, String> colEnunciado;
    @FXML
    private TableColumn<Pregunta, Integer> colNivel;
    @FXML
    private TableColumn<Pregunta, String> colComodin;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblParticipante;
    @FXML
    private Label lblCompanero;
    @FXML
    private Label lblMax;
    @FXML
    private Label lblTiempo;
    @FXML
    private Label lblPremio;
    
    /**
     * Llenar los campos principales del juego con sus datos
     * @param j 
     */
    public void llenarCampos(Juego j){
        llenarLabels(j);
        asignarColumnas();
        llenarTableView(j);
        
    }
    
    /**
     * Llenar tableView con datos del juego
     * @param juego 
     */
    public void llenarTableView(Juego juego){
        List<Pregunta> preguntas;
        
        preguntas = juego.getArrayPreguntasContestadas();
        tvPreguntas.getItems().setAll(preguntas);
    }
    
    /**
     * Llenar labels del reporte con datos del juego
     * @param j 
     */
    public void llenarLabels(Juego j){
        lblFecha.setText("Fecha: " + String.valueOf(j.getFecha()));
        lblParticipante.setText("Participante: " + j.getParticipante().getNombre());
        lblCompanero.setText("Compañero: " + j.getApoyo().getNombre());
        lblMax.setText("Nivel Máximo: " + j.getLvlMax());
        lblTiempo.setText("Tiempo: " + j.getTime());
        lblPremio.setText("Premio: " + j.getPremio());
    }
    
    /**
     * Asignar los atributos de las columnas a llenar
     */
    public void asignarColumnas(){
        colEnunciado.setCellValueFactory(new PropertyValueFactory<>("texto"));
        colNivel.setCellValueFactory(new PropertyValueFactory<>("nivel"));
        colComodin.setCellValueFactory(new PropertyValueFactory<>("comodinUsados"));
    }
    
    @FXML
    private void switchToReporte(ActionEvent event) throws IOException {
        App.setRoot("reporte");
    }
}
