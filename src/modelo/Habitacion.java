package modelo;
import java.util.ArrayList;
import java.util.List;

public class Habitacion {

    private int numero;
    private String tipo;   // (estándar, suite ecológica, familiar)
    private int capacidad;
    private String estado;  //(libre, ocupada, mantenimiento)
    // 🔗 Relación: 1 a muchos con Reserva
    private List<Reserva> reservas;

    public Habitacion(int numero, String tipo, int capacidad, String estado) {
        this.numero = numero;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.estado = estado;
        this.reservas = new ArrayList<>();
    }
    public List<Reserva> getReservas() {

        return reservas;
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Habitación " + numero + " - " + tipo + " (" + estado + ")";
    }


}


