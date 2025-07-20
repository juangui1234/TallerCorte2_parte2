package modelo;
import java.util.ArrayList;
import java.util.List;
import util.IDGenerator;

public class Huesped {

    private String nombre;
    private String documento;
    private String correo;
    private String telefono;
    // Relación: 1 a muchos con Reserva
    private List <Reserva> reservas;
    private String idHuesped;

    //constructor
    public Huesped(String nombre, String documento, String correo, String telefono) {
        this.idHuesped = IDGenerator.generateHuespedId(); // ✅ ID automático
        this.nombre = nombre;
        this.documento = documento;
        setCorreo(correo); //validacion correo
        this.telefono = telefono;
        this.reservas = new ArrayList<>();
    }
    //getters y setters

    public String getIdHuesped() {
        return idHuesped;
    }

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
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
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
        if (telefono == null || !telefono.matches("\\d+")) {
            throw new IllegalArgumentException("El teléfono debe contener solo números.");
        }
        this.telefono = telefono;
    }

    // Para mostrar en JComboBox o en tablas
    @Override
    public String toString() {
        return nombre + " (" + documento + ")";
    }

}
