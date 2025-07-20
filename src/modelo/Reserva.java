package modelo;
import util.IDGenerator;
import java.time.LocalDate;

public class Reserva {

    private String idReserva;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private Huesped huesped;
    private Habitacion habitacion;

    public Reserva(LocalDate fechaEntrada, LocalDate fechaSalida, Huesped huesped, Habitacion habitacion) {
        this.idReserva = IDGenerator.generateReservaId();
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.huesped = huesped;
        this.habitacion = habitacion;

    }

    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        if (huesped == null) {
            throw new IllegalArgumentException("Debe seleccionar un huésped válido.");
        }
        this.huesped = huesped;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        if (habitacion == null) {
            throw new IllegalArgumentException("Debe seleccionar una habitación válida.");
        }
        this.habitacion = habitacion;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        if (fechaSalida == null || (this.fechaEntrada != null && fechaSalida.isBefore(this.fechaEntrada))) {
            throw new IllegalArgumentException("La fecha de salida debe ser posterior a la fecha de entrada.");
        }
        this.fechaSalida = fechaSalida;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }
//validacion fecha entrada
    public void setFechaEntrada(LocalDate fechaEntrada) {
        if (fechaEntrada == null || fechaEntrada.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de entrada no puede ser anterior a la fecha actual.");
        }
        this.fechaEntrada = fechaEntrada;
    }
        @Override
    public String toString() {
        return "Reserva #" + idReserva + " - " + huesped.getNombre() + " (" + fechaEntrada + " a " + fechaSalida + ")";
    }

}
