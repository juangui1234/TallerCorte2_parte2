package dto;

public class ServicioAdicionalDTO {
    private String nombre;
    private double precio;

    public ServicioAdicionalDTO(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}
