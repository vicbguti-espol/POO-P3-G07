module espol.poo.proyectojar {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens espol.poo.proyectojar to javafx.fxml;
    opens modelo.academico to javafx.base;
    opens modelo.juego to javafx.base;
    exports espol.poo.proyectojar;
}
