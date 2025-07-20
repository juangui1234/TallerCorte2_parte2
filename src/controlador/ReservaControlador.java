package controlador;

import dao.HabitacionDAO;
import dao.HuespedDAO;
import dao.ReservaDAO;
import dto.ReservaDTO;
import excepciones.DatoInvalidoException;
import excepciones.FechaInvalidaException;
import excepciones.HabitacionNoDisponibleException;
import modelo.Habitacion;
import modelo.Huesped;
import modelo.Reserva;
import util.IDGenerator;

import java.time.LocalDate;
import java.util.List;

public class ReservaControlador {

    private ReservaDAO reservaDAO;
    private HuespedDAO huespedDAO;
    private HabitacionDAO habitacionDAO;

    public ReservaControlador(HuespedDAO huespedDAO, HabitacionDAO habitacionDAO) {
        this.reservaDAO = new ReservaDAO();
        this.huespedDAO = huespedDAO;
        this.habitacionDAO = habitacionDAO;
    }

    public void registrarReserva(ReservaDTO dto) throws DatoInvalidoException, FechaInvalidaException, HabitacionNoDisponibleException {
        // Validar fechas
        if (dto.getFechaEntrada() == null || dto.getFechaSalida() == null) {
            throw new DatoInvalidoException("Las fechas no pueden estar vacías.");
        }
        if (dto.getFechaEntrada().isBefore(LocalDate.now())) {
            throw new FechaInvalidaException("La fecha de entrada no puede ser anterior a hoy.");
        }
        if (!dto.getFechaSalida().isAfter(dto.getFechaEntrada())) {
            throw new FechaInvalidaException("La fecha de salida debe ser posterior a la de entrada.");
        }

        // Validar huésped
        Huesped huesped = huespedDAO.buscarPorDocumento(dto.getDocumentoHuesped());
        if (huesped == null) {
            throw new DatoInvalidoException("El huésped no está registrado.");
        }

        // Validar habitación
        Habitacion habitacion = habitacionDAO.buscarPorNumero(dto.getNumeroHabitacion());
        if (habitacion == null) {
            throw new DatoInvalidoException("La habitación no existe.");
        }

        // Validar disponibilidad
        List<Reserva> reservasHabitacion = reservaDAO.buscarPorNumeroHabitacion(dto.getNumeroHabitacion());
        for (Reserva r : reservasHabitacion) {
            boolean fechasSeCruzan = !(dto.getFechaSalida().isBefore(r.getFechaEntrada()) ||
                    dto.getFechaEntrada().isAfter(r.getFechaSalida()));
            if (fechasSeCruzan) {
                throw new HabitacionNoDisponibleException("La habitación ya está reservada en ese rango de fechas.");
            }
        }

        // Generar ID único
        String id = IDGenerator.generateReservaId();

        // Crear reserva
        Reserva nueva = new Reserva(id, dto.getFechaEntrada(), dto.getFechaSalida(), huesped, habitacion);
        reservaDAO.agregarReserva(nueva);
    }

    public List<Reserva> obtenerTodas() {
        return reservaDAO.obtenerTodas();
    }

    public boolean cancelarReserva(String idReserva) {
        return reservaDAO.eliminarReserva(idReserva);
    }

}
