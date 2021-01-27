package DAO;

import Beans.Equipo;
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
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import static com.mongodb.client.model.Filters.eq;

public class EquiposDAO {

    private final String NOMBRE_DB = "liga_futbol";
    private final String COLLECTION = "equipos";

    private MongoClient cliente;
    private MongoDatabase db;
    private MongoCollection<Equipo> collection;

    public void conectar() {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        cliente = new MongoClient("localhost",
                MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        db = cliente.getDatabase(NOMBRE_DB);

        System.out.println("Conextion con MONGO OK");
    }



    public ArrayList<Equipo> getEquipos()  {

        MongoCollection<Equipo> collection = db.getCollection(COLLECTION , Equipo.class);
        ArrayList<Equipo> equipos = collection.find().into(new ArrayList<>());

        return equipos;

    }

    public void insertarEquipo(Equipo equipo) {

        collection = db.getCollection(COLLECTION, Equipo.class);
        collection.insertOne(equipo);

    }

    public void eliminarEquipo(Equipo equipo)  {

        collection = db.getCollection(COLLECTION, Equipo.class);
        collection.deleteOne(eq("_id" , equipo.getIdEquipo()));

    }
}
