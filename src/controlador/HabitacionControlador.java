package controlador;

import dao.HabitacionDAO;
import dto.HabitacionDTO;
import excepciones.DatoInvalidoException;
import modelo.Habitacion;

import java.util.List;

public class HabitacionControlador {

    private HabitacionDAO habitacionDAO;

    public HabitacionControlador() {
        this.habitacionDAO = new HabitacionDAO();
    }

    public HabitacionControlador(HabitacionDAO dao) {
        this.habitacionDAO = dao;
    }

    public HabitacionDAO getDao() {
        return habitacionDAO;
    }


    // Registrar nueva habitación
    public void registrarHabitacion(HabitacionDTO dto) throws DatoInvalidoException {
        // Validaciones
        if (dto.getNumero() <= 0) {
            throw new DatoInvalidoException("El número de habitación debe ser mayor que cero.");
        }
        if (dto.getTipo() == null || dto.getTipo().trim().isEmpty()) {
            throw new DatoInvalidoException("El tipo de habitación no puede estar vacío.");
        }
        if (!dto.getTipo().equalsIgnoreCase("estándar") &&
                !dto.getTipo().equalsIgnoreCase("suite ecológica") &&
                !dto.getTipo().equalsIgnoreCase("familiar")) {
            throw new DatoInvalidoException("Tipo de habitación inválido.");
        }
        if (dto.getCapacidad() <= 0) {
            throw new DatoInvalidoException("La capacidad debe ser mayor que cero.");
        }
        if (dto.getEstado() == null || dto.getEstado().trim().isEmpty()) {
            throw new DatoInvalidoException("El estado no puede estar vacío.");
        }
        if (!dto.getEstado().equalsIgnoreCase("libre") &&
                !dto.getEstado().equalsIgnoreCase("ocupada") &&
                !dto.getEstado().equalsIgnoreCase("mantenimiento")) {
            throw new DatoInvalidoException("Estado inválido.");
        }

        // Verificar si ya existe una habitación con ese número
        if (habitacionDAO.buscarPorNumero(dto.getNumero()) != null) {
            throw new DatoInvalidoException("Ya existe una habitación con ese número.");
        }

        // Crear objeto del modelo
        Habitacion nueva = new Habitacion(
                dto.getNumero(),
                dto.getTipo(),
                dto.getCapacidad(),
                dto.getEstado()
        );

        // Guardar en DAO
        habitacionDAO.agregarHabitacion(nueva);
    }

    // Obtener todas las habitaciones
    public List<Habitacion> obtenerTodas() {
        return habitacionDAO.obtenerTodas();
    }

    // Buscar por número
    public Habitacion buscarPorNumero(int numero) {
        return habitacionDAO.buscarPorNumero(numero);
    }

    // Eliminar habitación
    public boolean eliminarHabitacion(int numero) {
        return habitacionDAO.eliminarHabitacion(numero);
    }
    public boolean editarHabitacion(HabitacionDTO dto) throws DatoInvalidoException {
        // Validaciones similares a registrar
        if (dto.getNumero() <= 0) {
            throw new DatoInvalidoException("El número de habitación debe ser mayor que cero.");
        }
        if (dto.getTipo() == null || dto.getTipo().trim().isEmpty()) {
            throw new DatoInvalidoException("El tipo de habitación no puede estar vacío.");
        }
        if (!dto.getTipo().equalsIgnoreCase("estándar") &&
                !dto.getTipo().equalsIgnoreCase("suite ecológica") &&
                !dto.getTipo().equalsIgnoreCase("familiar")) {
            throw new DatoInvalidoException("Tipo de habitación inválido.");
        }
        if (dto.getCapacidad() <= 0) {
            throw new DatoInvalidoException("La capacidad debe ser mayor que cero.");
        }
        if (dto.getEstado() == null || dto.getEstado().trim().isEmpty()) {
            throw new DatoInvalidoException("El estado no puede estar vacío.");
        }
        if (!dto.getEstado().equalsIgnoreCase("libre") &&
                !dto.getEstado().equalsIgnoreCase("ocupada") &&
                !dto.getEstado().equalsIgnoreCase("mantenimiento")) {
            throw new DatoInvalidoException("Estado inválido.");
        }

        // Crear nuevo objeto y actualizarlo
        Habitacion editada = new Habitacion(
                dto.getNumero(),
                dto.getTipo(),
                dto.getCapacidad(),
                dto.getEstado()
        );
        return habitacionDAO.actualizarHabitacion(editada);
    }

}
