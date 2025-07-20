package dto;

import java.time.LocalDate;

public class ReservaDTO {

    private String idReserva;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private String nombreHuesped;
    private String documentoHuesped;
    private int numeroHabitacion;
    private String tipoHabitacion;

    //constructor
    public ReservaDTO(String idReserva, LocalDate fechaEntrada, LocalDate fechaSalida,
                      String nombreHuesped, String documentoHuesped,
                      int numeroHabitacion, String tipoHabitacion
    ) {
        this.idReserva = idReserva;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.nombreHuesped = nombreHuesped;
        this.documentoHuesped = documentoHuesped;
        this.numeroHabitacion = numeroHabitacion;
        this.tipoHabitacion = tipoHabitacion;

    }
    //metodos gettes y setters
    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

    public String getDocumentoHuesped() {
        return documentoHuesped;
    }

    public void setDocumentoHuesped(String documentoHuesped) {
        this.documentoHuesped = documentoHuesped;
    }

    public String getNombreHuesped() {
        return nombreHuesped;
    }

    public void setNombreHuesped(String nombreHuesped) {
        this.nombreHuesped = nombreHuesped;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
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
    //metodo de sobreescritura para mostrar en las tablas
    @Override
    public String toString() {
        return "Reserva #" + idReserva + " | " + nombreHuesped + " (" + documentoHuesped + ") | Habitación " +
                numeroHabitacion + " - " + tipoHabitacion + " | " + fechaEntrada + " a " + fechaSalida;
    }

}