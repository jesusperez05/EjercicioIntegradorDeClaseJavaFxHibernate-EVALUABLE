module org.example.ejerciciointegradorjavafxhibernate {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejerciciointegradorjavafxhibernate to javafx.fxml;
    exports org.example.ejerciciointegradorjavafxhibernate;
}