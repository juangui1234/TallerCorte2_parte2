package modelo;

public class ServicioAdicional {

    private int idServicio;
    private String nombre;
    private double precio;

    public ServicioAdicional(int idServicio, String nombre, double precio) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getIdServicio() {
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
