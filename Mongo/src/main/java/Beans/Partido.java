package Beans;

import org.bson.types.ObjectId;

public class Partido {

    private String arbitro, campo, incidencias;
    private ObjectId id;

    public String getArbitro() {
        return arbitro;
    }
    public void setArbitro(String arbitro) {
        this.arbitro = arbitro;
    }
    public String getCampo() {
        return campo;
    }
    public void setCampo(String campo) {
        this.campo = campo;
    }
    public String getIncidencias() {
        return incidencias;
    }
    public void setIncidencias(String incidencias) {
        this.incidencias = incidencias;
    }
    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Arbitro -> " + arbitro + "    --    Campo -> " + campo;
    }
}
