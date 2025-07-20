package controlador;

import dao.HuespedDAO;
import dto.HuespedDTO;
import excepciones.DatoInvalidoException;
import modelo.Huesped;

import java.util.List;

public class HuespedControlador {

    private HuespedDAO huespedDAO;

    public HuespedControlador() {
        this.huespedDAO = new HuespedDAO();
    }

    public HuespedControlador(HuespedDAO dao) {
        this.huespedDAO = dao;
    }

    public HuespedDAO getDao() {
        return huespedDAO;
    }

    // Registrar un nuevo huésped
    public void registrarHuesped(HuespedDTO dto) throws DatoInvalidoException {
        // Validaciones
        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new DatoInvalidoException("El nombre no puede estar vacío.");
        }
        if (dto.getDocumento() == null || dto.getDocumento().trim().isEmpty()) {
            throw new DatoInvalidoException("El documento no puede estar vacío.");
        }
        if (dto.getCorreo() == null || !dto.getCorreo().contains("@")) {
            throw new DatoInvalidoException("El correo debe contener '@'.");
        }
        if (dto.getTelefono() == null || dto.getTelefono().trim().isEmpty()) {
            throw new DatoInvalidoException("El teléfono no puede estar vacío.");
        }

        // Verificar si ya existe un huésped con ese documento
        if (huespedDAO.buscarPorDocumento(dto.getDocumento()) != null) {
            throw new DatoInvalidoException("Ya existe un huésped con ese documento.");
        }

        // Crear objeto del modelo
        Huesped nuevo = new Huesped(
                dto.getNombre(),
                dto.getDocumento(),
                dto.getCorreo(),
                dto.getTelefono()
        );

        // Guardar en DAO
        huespedDAO.agregarHuesped(nuevo);
    }

    // Obtener todos los huéspedes
    public List<Huesped> obtenerTodos() {
        return huespedDAO.obtenerTodos();
    }

    // Buscar huésped por documento
    public Huesped buscarPorDocumento(String documento) {
        return huespedDAO.buscarPorDocumento(documento);
    }

    // Eliminar huésped
    public boolean eliminarHuesped(String documento) {
        return huespedDAO.eliminarHuesped(documento);
    }
}
