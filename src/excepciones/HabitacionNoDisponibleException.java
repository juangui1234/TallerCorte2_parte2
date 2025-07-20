package excepciones;

/**
 * Excepción personalizada para indicar que una habitación no está disponible
 * en el rango de fechas solicitado para una reserva.
 */
public class HabitacionNoDisponibleException extends Exception {

    public HabitacionNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
