package excepciones;

/**
 * Excepción personalizada para cuando no se encuentra una entidad esperada
 * como un huésped, habitación o reserva.
 */
public class EntidadNoEncontradaException extends Exception {

    public EntidadNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
