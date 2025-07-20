package util;

public class IDGenerator {
    private static int contadorReserva = 1;
    private static int contadorHuesped = 1;
    private static int contadorHabitacion = 1;
    private static int contadorServicio = 1;

    public static String generateReservaId() {
        return "RES-" + contadorReserva++;
    }

    public static String generateHuespedId() {
        return "HUE-" + contadorHuesped++;
    }

    public static String generateHabitacionId() {
        return "HAB-" + contadorHabitacion++;
    }

    public static String generateServicioId() {
        return "SER-" + contadorServicio++;
    }
}