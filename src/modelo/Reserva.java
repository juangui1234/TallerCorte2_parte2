package modelo;

import java.time.LocalDate;

public class Reserva {

    private int idReserva;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private Huesped huesped;
    private Habitacion habitacion;

    public Reserva(int idReserva, LocalDate fechaEntrada, LocalDate fechaSalida, Huesped huesped, Habitacion habitacion) {
        this.idReserva = idReserva;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.huesped = huesped;
        this.habitacion = habitacion;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    @Override
    public String toString() {
        return "Reserva #" + idReserva + " - " + huesped.getNombre() + " (" + fechaEntrada + " a " + fechaSalida + ")";
    }

}
