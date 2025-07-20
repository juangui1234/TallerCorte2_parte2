package dao;

import modelo.ServicioAdicional;
import java.util.ArrayList;
import java.util.List;

public class ServicioAdicionalDAO {
    private List<ServicioAdicional> servicios;

    public ServicioAdicionalDAO() {
        this.servicios = new ArrayList<>();
    }

    public void agregarServicio(ServicioAdicional servicio) {
        servicios.add(servicio);
    }

    public List<ServicioAdicional> obtenerTodos() {
        return new ArrayList<>(servicios); // copia para proteger la lista original
    }

    public boolean eliminarServicio(String id) {
        return servicios.removeIf(s -> s.getIdServicio().equals(id));
    }

    public ServicioAdicional buscarPorNombre(String nombre) {
        for (ServicioAdicional s : servicios) {
            if (s.getNombre().equalsIgnoreCase(nombre)) {
                return s;
            }
        }
        return null;
    }
}
