package Controlador;

import Beans.Equipo;
import Beans.Partido;
import DAO.EquiposDAO;
import DAO.PartidosDAO;
import Utilidades.Alertas;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AppControlador implements Initializable {

    public TextField tfNombreEquipo, tfPatrocinadorEquipo, tfCategoriaEquipo, tfArbitro, tfCampo, tfIncidencias;
    public Button btInsertarEquipo, btEliminarEquipo, btInsertarPartido, btEliminarPartido;
    public ListView lvEquipos, lvPartidos;

    private PartidosDAO partidosDAO;
    private EquiposDAO equiposDAO;

    private Equipo equipoSeleccionado;
    private Partido partidoSeleccionado;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        partidosDAO = new PartidosDAO();
        equiposDAO = new EquiposDAO();
        partidosDAO.conectar();
        equiposDAO.conectar();
        cargarDatos();
    }

    private void cargarDatos() {

        lvEquipos.getItems().clear();

        ArrayList<Equipo> equipos = null;
        equipos = equiposDAO.getEquipos();
        lvEquipos.setItems(FXCollections.observableList(equipos));

        ArrayList<Partido> partidos = null;
        partidos = partidosDAO.getPartidos();
        lvPartidos.setItems(FXCollections.observableList(partidos));

        limpiarCajasEquipos();
        limpiarCajasPartidos();

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
    public void insertarPartido(Event event) {

        String arbitro = tfArbitro.getText();
        String campo = tfCampo.getText();
        String incidencia = tfIncidencias.getText();

        if (arbitro == null || arbitro.isEmpty() || arbitro.isBlank() || campo == null || campo.isEmpty() || campo.isBlank()) {
            Alertas.mostrarError("ERROR", "Debes rellenar el arbitro y el campo como mínimo");
            return;
        }

        Partido partido = new Partido();
        partido.setArbitro(arbitro);
        partido.setCampo(campo);
        partido.setIncidencias(incidencia);

        partidosDAO.insertarPartido(partido);

        cargarDatos();
        limpiarCajasPartidos();


    }

    @FXML
    public void eliminarPartido(Event event) {

        Partido partido = partidoSeleccionado;

        if (partido == null) {
            Alertas.mostrarError("ERROR", "Debes seleccionar un partido para poder borrar");
        }

        partidosDAO.eliminarPartido(partido);

        cargarDatos();
        limpiarCajasPartidos();

    }


    @FXML
    public void getEquipoSeleccionado(Event event) {

        equipoSeleccionado = (Equipo) lvEquipos.getSelectionModel().getSelectedItem();

        if (equipoSeleccionado == null) {
            Alertas.mostrarError("ERROR", "No has seleccionado ningún equipo");
        }

        cargarEquipoSeleccionado(equipoSeleccionado);

    } // OK

    @FXML
    public void getPartidoSeleccionado(Event event) {

        partidoSeleccionado = (Partido) lvPartidos.getSelectionModel().getSelectedItem();

        if (partidoSeleccionado == null) {
            Alertas.mostrarError("ERROR", "No has seleccionado ningún equipo");
        }

        cargarPartidoSeleccionado(partidoSeleccionado);

    }


    public void limpiarCajasEquipos() {
        tfNombreEquipo.setText("");
        tfCategoriaEquipo.setText("");
        tfPatrocinadorEquipo.setText("");
    }

    public void limpiarCajasPartidos() {
        tfArbitro.setText("");
        tfCampo.setText("");
        tfIncidencias.setText("");
    }

    public void cargarEquipoSeleccionado(Equipo equipo) {
        tfNombreEquipo.setText(equipo.getNombre());
        tfPatrocinadorEquipo.setText(equipo.getPatrocinador());
        tfCategoriaEquipo.setText(equipo.getCategoría());
    }

    public void cargarPartidoSeleccionado(Partido partido) {
        tfArbitro.setText(partido.getArbitro());
        tfCampo.setText(partido.getCampo());
        tfIncidencias.setText(partido.getIncidencias());
    }


}
