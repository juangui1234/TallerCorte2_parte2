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
        JPanel formPanel = new JPanel(new GridLayout(5, 2));
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

        btnRegistrar = new JButton("Registrar Habitación");
        btnRegistrar.addActionListener(this::registrarHabitacion);
        formPanel.add(btnRegistrar);

        add(formPanel, BorderLayout.NORTH);

        tabla = new JTable();
        add(new JScrollPane(tabla), BorderLayout.CENTER);
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

    private void cargarHabitaciones() {
        List<Habitacion> habitaciones = controlador.obtenerTodas();
        String[] columnas = {"Número", "Tipo", "Capacidad", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Habitacion h : habitaciones) {
            Object[] fila = {
                    h.getNumero(),
                    h.getTipo(),
                    h.getCapacidad(),
                    h.getEstado()
            };
            modelo.addRow(fila);
        }

        tabla.setModel(modelo);
    }
}