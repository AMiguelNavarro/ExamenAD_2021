package Beans;

public class Jugador {

    private String nombre, apellidos, dorsal;
    private int idEquipo;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getDorsal() {
        return dorsal;
    }
    public void setDorsal(String dorsal) {
        this.dorsal = dorsal;
    }
    public int getIdEquipo() {
        return idEquipo;
    }
    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }


    @Override
    public String toString() {
        return "Nombre -> " + nombre + "    --    Equipo -> ";
    }
}
