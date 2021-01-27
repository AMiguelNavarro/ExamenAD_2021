package DAO;

import Beans.Equipo;
import Beans.Partido;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class PartidosDAO {

    private final String NOMBRE_DB = "liga_futbol";
    private final String COLLECTION = "partidos";

    private MongoClient cliente;
    private MongoDatabase db;
    private MongoCollection<Partido> collection;

    public void conectar() {
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        cliente = new MongoClient("localhost",
                MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        db = cliente.getDatabase(NOMBRE_DB);
    }

    public ArrayList<Partido> getPartidos()  {

        MongoCollection<Partido> collection = db.getCollection(COLLECTION , Partido.class);
        ArrayList<Partido> partidos = collection.find().into(new ArrayList<>());

        return partidos;

    }

    public void insertarPartido(Partido partido) {

        collection = db.getCollection(COLLECTION, Partido.class);
        collection.insertOne(partido);

    }

    public void eliminarPartido(Partido partido)  {

        collection = db.getCollection(COLLECTION, Partido.class);
        collection.deleteOne(eq("_id" , partido.getId()));

    }

}
