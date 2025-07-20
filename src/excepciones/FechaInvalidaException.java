package excepciones;

/**
 * Excepción personalizada para fechas inválidas en reservas.
 * Se lanza cuando la fecha de entrada es anterior a hoy o la salida es antes de la entrada.
 */
public class FechaInvalidaException extends Exception {

    public FechaInvalidaException(String mensaje) {
        super(mensaje);
    }
}
