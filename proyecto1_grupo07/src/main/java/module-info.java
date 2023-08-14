module espol.poo.proyectojar {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens espol.poo.proyectojar to javafx.fxml;
    exports espol.poo.proyectojar;
}
