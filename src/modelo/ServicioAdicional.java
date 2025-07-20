package modelo;
import util.IDGenerator;


public class ServicioAdicional {

    private String idServicio;
    private String nombre;
    private double precio;

    public ServicioAdicional(String nombre, double precio) {
        this.idServicio = IDGenerator.generateServicioId();
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getIdServicio() {
        return idServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return nombre + " ($" + precio + ")";
    }
}
