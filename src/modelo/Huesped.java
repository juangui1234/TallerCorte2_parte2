package modelo;
import java.util.ArrayList;
import java.util.List;

public class Huesped {

    private String nombre;
    private String documento;
    private String correo;
    private String telefono;
    // Relación: 1 a muchos con Reserva
    private List <Reserva> reservas;

    //constructor
    public Huesped(String nombre, String documento, String correo, String telefono) {
        this.nombre = nombre;
        this.documento = documento;
        setCorreo(correo); //validacion correo
        this.telefono = telefono;
        this.reservas = new ArrayList<>();
    }
    //getters y setters

    public List <Reserva> getReservas() {
        return reservas;
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo != null && correo.contains("@")) {
            this.correo = correo;
        } else {
            throw new IllegalArgumentException("Correo inválido: debe contener '@'");
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Para mostrar en JComboBox o en tablas
    @Override
    public String toString() {
        return nombre + " (" + documento + ")";
    }

}
