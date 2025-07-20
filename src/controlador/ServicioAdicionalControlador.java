package controlador;

import dao.ServicioAdicionalDAO;
import dto.ServicioAdicionalDTO;
import excepciones.DatoInvalidoException;
import modelo.ServicioAdicional;

import java.util.List;

public class ServicioAdicionalControlador {
    private ServicioAdicionalDAO servicioDAO;

    public ServicioAdicionalControlador() {
        this.servicioDAO = new ServicioAdicionalDAO();
    }

    public void registrarServicio(ServicioAdicionalDTO dto) throws DatoInvalidoException {
        if (dto.getNombre() == null || dto.getNombre().trim().isEmpty()) {
            throw new DatoInvalidoException("El nombre del servicio no puede estar vacío.");
        }
        if (dto.getPrecio() < 0) {
            throw new DatoInvalidoException("El precio no puede ser negativo.");
        }

        ServicioAdicional nuevo = new ServicioAdicional(dto.getNombre(), dto.getPrecio());
        servicioDAO.agregarServicio(nuevo);
    }

    public List<ServicioAdicional> obtenerTodos() {
        return servicioDAO.obtenerTodos();
    }

    public ServicioAdicional buscarPorNombre(String nombre) {
        return servicioDAO.buscarPorNombre(nombre);
    }
    public boolean eliminarServicio(String id) {
        return servicioDAO.eliminarServicio(id);
    }
}
