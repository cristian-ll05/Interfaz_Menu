module interfaz.menu {
    requires javafx.controls;
    requires javafx.fxml;

    opens interfaz.menu to javafx.fxml;
    exports interfaz.menu;
}
