package vista;

import controlador.HabitacionControlador;
import dto.HabitacionDTO;
import excepciones.DatoInvalidoException;
import modelo.Habitacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PanelHabitacion extends JPanel {
    private JTextField txtNumero;
    private JComboBox<String> cbTipo;
    private JTextField txtCapacidad;
    private JComboBox<String> cbEstado;
    private JButton btnRegistrar;
    private JTable tabla;
    private HabitacionControlador controlador;

    public PanelHabitacion(HabitacionControlador controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout());
        initComponents();
        cargarHabitaciones();
    }

    private void initComponents() {
        // Panel de formulario con campos
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Registrar Habitación"));

        txtNumero = new JTextField();
        cbTipo = new JComboBox<>(new String[]{"estándar", "suite ecológica", "familiar"});
        txtCapacidad = new JTextField();
        cbEstado = new JComboBox<>(new String[]{"libre", "ocupada", "mantenimiento"});

        formPanel.add(new JLabel("Número:"));
        formPanel.add(txtNumero);
        formPanel.add(new JLabel("Tipo:"));
        formPanel.add(cbTipo);
        formPanel.add(new JLabel("Capacidad:"));
        formPanel.add(txtCapacidad);
        formPanel.add(new JLabel("Estado:"));
        formPanel.add(cbEstado);

        // Panel de botones con acciones
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));

        btnRegistrar = new JButton("Registrar Habitación");
        btnRegistrar.addActionListener(this::registrarHabitacion);
        botonesPanel.add(btnRegistrar);

        JButton btnBuscar = new JButton("Buscar por número");
        btnBuscar.addActionListener(this::buscarHabitacion);
        botonesPanel.add(btnBuscar);

        JButton btnEditar = new JButton("Editar habitación");
        btnEditar.addActionListener(this::editarHabitacion);
        botonesPanel.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar habitación");
        btnEliminar.addActionListener(this::eliminarHabitacion);
        botonesPanel.add(btnEliminar);

        // Panel contenedor superior
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(formPanel, BorderLayout.CENTER);
        panelSuperior.add(botonesPanel, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);

        // Tabla de habitaciones
        tabla = new JTable();
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    private void limpiarCampos() {
        txtNumero.setText("");
        txtCapacidad.setText("");
        cbTipo.setSelectedIndex(0);
        cbEstado.setSelectedIndex(0);
    }

    private void registrarHabitacion(ActionEvent e) {
        try {
            HabitacionDTO dto = new HabitacionDTO(
                    Integer.parseInt(txtNumero.getText()),
                    cbTipo.getSelectedItem().toString(),
                    Integer.parseInt(txtCapacidad.getText()),
                    cbEstado.getSelectedItem().toString()
            );
            controlador.registrarHabitacion(dto);
            JOptionPane.showMessageDialog(this, "Habitación registrada exitosamente.");
            cargarHabitaciones();
        } catch (DatoInvalidoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error general: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarHabitacion(ActionEvent e) {
        try {
            int numero = Integer.parseInt(txtNumero.getText());
            Habitacion h = controlador.buscarPorNumero(numero);
            if (h != null) {
                cbTipo.setSelectedItem(h.getTipo());
                txtCapacidad.setText(String.valueOf(h.getCapacidad()));
                cbEstado.setSelectedItem(h.getEstado());
                JOptionPane.showMessageDialog(this, "Habitación encontrada.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró la habitación.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Número inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarHabitacion(ActionEvent e) {
        try {
            HabitacionDTO dto = new HabitacionDTO(
                    Integer.parseInt(txtNumero.getText()),
                    cbTipo.getSelectedItem().toString(),
                    Integer.parseInt(txtCapacidad.getText()),
                    cbEstado.getSelectedItem().toString()
            );

            boolean actualizado = controlador.editarHabitacion(dto);
            if (actualizado) {
                JOptionPane.showMessageDialog(this, "Habitación actualizada exitosamente.");
                cargarHabitaciones();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró la habitación para actualizar.");
            }
        } catch (DatoInvalidoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error general: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarHabitacion(ActionEvent e) {
        try {
            int numero = Integer.parseInt(txtNumero.getText());

            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Estás seguro de eliminar la habitación número " + numero + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (opcion == JOptionPane.YES_OPTION) {
                boolean eliminada = controlador.eliminarHabitacion(numero);
                if (eliminada) {
                    JOptionPane.showMessageDialog(this, "Habitación eliminada con éxito.");
                    cargarHabitaciones();
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró la habitación.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Número inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarHabitaciones() {
        List<Habitacion> habitaciones = controlador.obtenerTodas();
        String[] columnas = {"ID", "Número", "Tipo", "Capacidad", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        if (habitaciones.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay habitaciones registradas.");
        }
        for (Habitacion h : habitaciones) {
            Object[] fila = {
                    h.getIdHabitacion(),
                    h.getNumero(),
                    h.getTipo(),
                    h.getCapacidad(),
                    h.getEstado()
            };
            modelo.addRow(fila);
        }
        limpiarCampos();
        tabla.setModel(modelo);
    }

}
