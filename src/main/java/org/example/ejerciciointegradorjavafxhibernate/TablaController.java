package org.example.ejerciciointegradorjavafxhibernate;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import org.example.ejerciciointegradorjavafxhibernate.models.Equipo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.net.URL;
import java.util.ResourceBundle;

public class TablaController implements Initializable {

    private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    @FXML
    private TableView<Equipo> table;

    @FXML
    private TableColumn<Equipo, String> cId;

    @FXML
    private TableColumn<Equipo, String> cName;

    @FXML
    private TableColumn<Equipo, String> cCategory;

    @FXML
    private TableColumn<Equipo, String> cAvailability;

    private final ObservableList<Equipo> equipos = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cId.setCellValueFactory((TableColumn.CellDataFeatures<Equipo, String> row) ->
                new SimpleStringProperty(String.valueOf(row.getValue().getId())));
        cName.setCellValueFactory((TableColumn.CellDataFeatures<Equipo, String> row) ->
                new SimpleStringProperty(row.getValue().getNombre()));
        cCategory.setCellValueFactory((TableColumn.CellDataFeatures<Equipo, String> row) ->
                new SimpleStringProperty(row.getValue().getCategoria()));
        cAvailability.setCellValueFactory((TableColumn.CellDataFeatures<Equipo, String> row) ->
                new SimpleStringProperty(Boolean.TRUE.equals(row.getValue().getDisponible()) ? "Disponible" : "No disponible"));

        table.setItems(equipos);

        try (Session session = SESSION_FACTORY.openSession()) {
            equipos.setAll(session.createQuery("From Equipo", Equipo.class).getResultList());
        }
    }
}
