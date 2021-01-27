package Beans;

public class Equipo {

    private String nombre, patrocinador, categoría;
    private int idEquipo;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPatrocinador() {
        return patrocinador;
    }
    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }
    public String getCategoría() {
        return categoría;
    }
    public void setCategoría(String categoría) {
        this.categoría = categoría;
    }
    public int getIdEquipo() {
        return idEquipo;
    }
    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    @Override
    public String toString() {
        return "NOMBRE -> " + nombre + "   --   PATROCINADOR -> " + patrocinador;
    }
}
