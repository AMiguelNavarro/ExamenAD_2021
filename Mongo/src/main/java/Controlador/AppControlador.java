package Controlador;

import Beans.Equipo;
import Beans.Jugador;
import DAO.EquiposDAO;
import DAO.JugadoresDAO;
import Utilidades.Alertas;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AppControlador implements Initializable {

    public TextField tfNombreEquipo, tfPatrocinadorEquipo, tfCategoriaEquipo, tfNombreJugador, tfApellidoJugador, tfDorsalJugador;
    public Button btInsertarEquipo, btEliminarEquipo, btInsertarJugador, btModificarJugador, btCancelar;
    public ListView lvEquipos, lvJugadores;
    public ComboBox<String> cbEquipo;

    private JugadoresDAO jugadoresDAO;
    private EquiposDAO equiposDAO;

    private Equipo equipoSeleccionado;
    private Jugador jugadorSeleccionado;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        jugadoresDAO = new JugadoresDAO();
        equiposDAO = new EquiposDAO();
        jugadoresDAO.conectar();
        equiposDAO.conectar();
        cargarDatos();
    }

    private void cargarDatos() {

        lvEquipos.getItems().clear();

        ArrayList<Equipo> equipos = null;
        equipos = equiposDAO.getEquipos();
        lvEquipos.setItems(FXCollections.observableList(equipos));

        ArrayList<String> nombreEquipos = new ArrayList<>();

        for (Equipo equipo : equipos) {
            nombreEquipos.add(equipo.getNombre());
        }

        cbEquipo.setItems(FXCollections.observableList(nombreEquipos));

        limpiarCajasEquipos();

    }

    @FXML
    public void insertarEquipo(Event event) {

        String nombre = tfNombreEquipo.getText();
        String patrocinador = tfPatrocinadorEquipo.getText();
        String categoria = tfCategoriaEquipo.getText();

        if (nombre == null || nombre.isEmpty() || nombre.isBlank()) {
            Alertas.mostrarError("ERROR AL AÑADIR EQUIPO", "Debes rellenar el campo nombre");
            return;
        }

        Equipo equipo = new Equipo();
        equipo.setNombre(nombre);
        equipo.setPatrocinador(patrocinador);
        equipo.setCategoría(categoria);


        equiposDAO.insertarEquipo(equipo);

        cargarDatos();

        limpiarCajasEquipos();

    } // OK

    @FXML
    public void eliminarEquipo(Event event) {

        Equipo equipo = equipoSeleccionado;

        if (equipo == null) {
            Alertas.mostrarError("ERROR", "Debes seleccionar un equipo para poder borrar");
        }

        equiposDAO.eliminarEquipo(equipo);

        cargarDatos();

    } // OK

    @FXML
    public void insertarJugador(Event event) {

        String nombre = tfNombreJugador.getText();
        String apellidos = tfApellidoJugador.getText();
        String dorsal = tfDorsalJugador.getText();
        String equipo = cbEquipo.getSelectionModel().getSelectedItem();

        if (equipo == null || equipo.isBlank() || equipo.isEmpty()) {
            Alertas.mostrarError("ERROR", "Debes asignarle un equipo");
            return;
        }

        Jugador jugador = new Jugador();

    }

    @FXML
    public void modificarJugador(Event event) {}

    @FXML
    public void cancelar(Event event) {}

    @FXML
    public void getEquipoSeleccionado(Event event) {

        equipoSeleccionado = (Equipo) lvEquipos.getSelectionModel().getSelectedItem();

        if (equipoSeleccionado == null) {
            Alertas.mostrarError("ERROR", "No has seleccionado ningún equipo");
        }

        cargarEquipoSeleccionado(equipoSeleccionado);

    } // OK

    @FXML
    public void getJugadorSeleccionado(Event event) {}


    public void limpiarCajasEquipos() {
        tfNombreEquipo.setText("");
        tfCategoriaEquipo.setText("");
        tfPatrocinadorEquipo.setText("");
    }

    public void cargarEquipoSeleccionado(Equipo equipo) {
        tfNombreEquipo.setText(equipo.getNombre());
        tfPatrocinadorEquipo.setText(equipo.getPatrocinador());
        tfCategoriaEquipo.setText(equipo.getCategoría());
    }


}
