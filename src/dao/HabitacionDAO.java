package dao;

import modelo.Habitacion;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAO {

    private List<Habitacion> habitaciones;

    public HabitacionDAO() {
        this.habitaciones = new ArrayList<>();
    }
    //crear
    public void agregarHabitacion(Habitacion habitacion) {
        habitaciones.add(habitacion);
    }
    //leer
    public List<Habitacion> obtenerTodas() {
        return new ArrayList<>(habitaciones); // Retorna copia para proteger la lista original
    }
    //buscar por número de habitacion
    public Habitacion buscarPorNumero(int numero) {
        for (Habitacion h : habitaciones) {
            if (h.getNumero() == numero) {
                return h;
            }
        }
        return null;
    }
    //actualizar
    public boolean actualizarHabitacion(Habitacion habitacionActualizada) {
        for (int i = 0; i < habitaciones.size(); i++) {
            if (habitaciones.get(i).getNumero() == habitacionActualizada.getNumero()) {
                habitaciones.set(i, habitacionActualizada);
                return true;
            }
        }
        return false;
    }
    //eliminar
    public boolean eliminarHabitacion(int numero) {
        return habitaciones.removeIf(h -> h.getNumero() == numero);
    }
}
