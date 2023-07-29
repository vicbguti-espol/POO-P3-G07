module vicbguti.proyecto1_grupo07 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens basemaven to javafx.fxml;
    exports basemaven;
}
