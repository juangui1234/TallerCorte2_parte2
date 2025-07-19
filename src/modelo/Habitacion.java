package modelo;

public class Habitacion {

    int numero;
    String tipo;   // (estándar, suite ecológica, familiar)
    private int capacidad;
    private String estado;  //(libre, ocupada, mantenimiento)

    public Habitacion(int numero, String tipo, int capacidad, String estado) {
        this.numero = numero;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.estado = estado;
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
}


    // 🔗 Relación: 1 a muchos con dto.Reserva.