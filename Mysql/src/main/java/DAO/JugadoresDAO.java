package DAO;

import Utilidades.R;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JugadoresDAO {

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

}
