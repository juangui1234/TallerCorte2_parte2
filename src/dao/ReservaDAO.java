package dao;

import modelo.Reserva;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    private List<Reserva> reservas;

    public ReservaDAO() {
        this.reservas = new ArrayList<>();
    }
    //crear
    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }
    //leer todas
    public List<Reserva> obtenerTodas() {
        return new ArrayList<>(reservas); // Retorna copia para proteger la lista original
    }
    //buscar por ID
    public Reserva buscarPorId(String idReserva) {
        for (Reserva r : reservas) {
            if (r.getIdReserva().equals(idReserva)) {
                return r;
            }
        }
        return null;
    }
    //eliminar por ID
    public boolean eliminarReserva(String idReserva) {
        return reservas.removeIf(r -> r.getIdReserva().equals(idReserva));
    }
    //buscar reservas por número de habitación (para validar disponibilidad)
    public List<Reserva> buscarPorNumeroHabitacion(int numeroHabitacion) {
        List<Reserva> resultado = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getHabitacion().getNumero() == numeroHabitacion) {
                resultado.add(r);
            }
        }
        return resultado;
    }
    //buscar reservas por documento de huésped
    public List<Reserva> buscarPorDocumentoHuesped(String documento) {
        List<Reserva> resultado = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getHuesped().getDocumento().equalsIgnoreCase(documento)) {
                resultado.add(r);
            }
        }
        return resultado;
    }
}
