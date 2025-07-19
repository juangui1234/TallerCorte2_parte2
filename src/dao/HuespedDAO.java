package dao;

import modelo.Huesped;
import java.util.ArrayList;
import java.util.List;

public class HuespedDAO {

    private List<Huesped> huespedes;

    public HuespedDAO() {
        this.huespedes = new ArrayList<>();
    }

    // Crear
    public void agregarHuesped(Huesped huesped) {
        huespedes.add(huesped);
    }

    // Leer todos
    public List<Huesped> obtenerTodos() {
        return new ArrayList<>(huespedes); // Retorna una copia para proteger la lista original
    }

    // Buscar por documento
    public Huesped buscarPorDocumento(String documento) {
        for (Huesped h : huespedes) {
            if (h.getDocumento().equalsIgnoreCase(documento)) {
                return h;
            }
        }
        return null;
    }

    // Actualizar
    public boolean actualizarHuesped(Huesped huespedActualizado) {
        for (int i = 0; i < huespedes.size(); i++) {
            if (huespedes.get(i).getDocumento().equalsIgnoreCase(huespedActualizado.getDocumento())) {
                huespedes.set(i, huespedActualizado);
                return true;
            }
        }
        return false;
    }
    // Eliminar
    public boolean eliminarHuesped(String documento) {
        return huespedes.removeIf(h -> h.getDocumento().equalsIgnoreCase(documento));
    }
}
