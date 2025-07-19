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

    //constructor
    public Habitacion(int numero, String tipo, int capacidad, String estado) {
        setNumero(numero);
        setTipo(tipo);
        setCapacidad(capacidad);
        setEstado(estado);
        this.reservas = new ArrayList<>();
    }
    //Getters y Setters
    public List<Reserva> getReservas() {
        return reservas;
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public int getNumero() {
        return numero;
    }
//validacion habitacion
    public void setNumero(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("El número de habitación debe ser mayor que cero.");
        }
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }
//validacion tipo habitacion
    public void setTipo(String tipo) {
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de habitación no puede estar vacío.");
        }
        if (!tipo.equalsIgnoreCase("estándar") &&
                !tipo.equalsIgnoreCase("suite ecológica") &&
                !tipo.equalsIgnoreCase("familiar")) {
            throw new IllegalArgumentException("Tipo de habitación inválido. Debe ser: estándar, suite ecológica o familiar.");
        }
        this.tipo = tipo;
    }

    public int getCapacidad() {
                return capacidad;
    }
//validacion capacidad
    public void setCapacidad(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
    }

        public String getEstado() {
        return estado;
    }
//validacion estado
    public void setEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado no puede estar vacío.");
        }
        if (!estado.equalsIgnoreCase("libre") &&
                !estado.equalsIgnoreCase("ocupada") &&
                !estado.equalsIgnoreCase("mantenimiento")) {
            throw new IllegalArgumentException("Estado inválido. Debe ser: libre, ocupada o mantenimiento.");
        }
        this.estado = estado.toLowerCase(); //consistencia
    }


    @Override
    public String toString() {
        return "Habitación " + numero + " - " + tipo + " (" + estado + ")";
    }


}


