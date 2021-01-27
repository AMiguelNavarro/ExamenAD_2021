package DAO;

import Beans.Equipo;
import Utilidades.R;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class EquiposDAO {

    private final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";

    private Connection conexion;

    public void conectar() throws IOException, ClassNotFoundException, SQLException {
        Properties configuracion = new Properties();
        configuracion.load(R.getProperties("bd"));

        String host = configuracion.getProperty("host");
        String port = configuracion.getProperty("port");
        String namedb = configuracion.getProperty("namedb");
        String user = configuracion.getProperty("user");
        String password = configuracion.getProperty("password");

        Class.forName(CONTROLADOR);
        conexion = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + namedb + "?serverTimezone=UTC", user , password);

        System.out.println("CONECTADO A LA BASE DE DATOS");
    }



    public ArrayList<Equipo> getEquipos() throws SQLException {

        ArrayList<Equipo> equipos = new ArrayList<>();

        final String SQL_FINDALL = "SELECT * FROM equipos WHERE 1=1";

        PreparedStatement ps = conexion.prepareStatement(SQL_FINDALL);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Equipo equipo = new Equipo();

            equipo.setIdEquipo(rs.getInt(1));
            equipo.setNombre(rs.getString(2));
            equipo.setPatrocinador(rs.getString(3));
            equipo.setCategoría(rs.getString(4));

            equipos.add(equipo);
        }

        return equipos;

    }

    public void insertarEquipo(Equipo equipo) throws SQLException {

        String sql_insert = "INSERT INTO equipos (nombre, patrocinador, categoria) VALUES (?,?,?)";

        PreparedStatement ps = conexion.prepareStatement(sql_insert);
        ps.setString(1, equipo.getNombre());
        ps.setString(2, equipo.getPatrocinador());
        ps.setString(3, equipo.getCategoría());

        ps.execute();

    }

    public void eliminarEquipo(Equipo equipo) throws SQLException {

        String sql_delete = "DELETE FROM equipos WHERE nombre = ? AND patrocinador = ? AND categoria = ?";

        PreparedStatement ps = conexion.prepareStatement(sql_delete);
        ps.setString(1, equipo.getNombre());
        ps.setString(2, equipo.getPatrocinador());
        ps.setString(3, equipo.getCategoría());

        ps.execute();

    }
}
