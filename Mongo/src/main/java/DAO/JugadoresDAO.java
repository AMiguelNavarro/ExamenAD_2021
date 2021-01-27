package DAO;

import Beans.Jugador;
import Utilidades.R;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JugadoresDAO {

    private final String NOMBRE_DB = "liga_futbol";
    private final String COLLECTION = "jugadores";

    private MongoClient cliente;
    private MongoDatabase db;
    private MongoCollection<Jugador> collection;

    public void conectar() {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        cliente = new MongoClient("localhost",
                MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        db = cliente.getDatabase(NOMBRE_DB);
    }

}
