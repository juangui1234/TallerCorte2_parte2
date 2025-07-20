package vista;

import controlador.ServicioAdicionalControlador;
import dao.HabitacionDAO;
import dto.ServicioAdicionalDTO;
import excepciones.DatoInvalidoException;
import modelo.Habitacion;
import modelo.ServicioAdicional;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PanelServicioAdicional extends JPanel {

    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnRegistrar;
    private JButton btnEliminar;
    private JButton btnAsignar;
    private JTable tablaServicios;
    private JTable tablaAsignados;
    private JComboBox<Habitacion> comboHabitaciones;
    private ServicioAdicionalControlador controlador;
    private HabitacionDAO habitacionDAO;

    public PanelServicioAdicional(ServicioAdicionalControlador controlador, HabitacionDAO habitacionDAO) {
        this.controlador = controlador;
        this.habitacionDAO = habitacionDAO;
        setLayout(new BorderLayout());
        initComponents();
        cargarServicios();
        cargarHabitaciones();
    }

    private void initComponents() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Registrar Servicio Adicional"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtNombre = new JTextField();
        txtPrecio = new JTextField();
        comboHabitaciones = new JComboBox<>();

        btnRegistrar = new JButton("Registrar Servicio");
        btnRegistrar.addActionListener(this::registrarServicio);

        btnEliminar = new JButton("Eliminar Servicio");
        btnEliminar.addActionListener(this::eliminarServicio);

        btnAsignar = new JButton("Asignar a Habitación");
        btnAsignar.addActionListener(this::asignarServicio);

        // Fila 1 - Nombre del servicio
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Nombre del Servicio:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtNombre, gbc);

        // Fila 2 - Precio
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Precio:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtPrecio, gbc);

        // Fila 3 - Botones registrar y eliminar
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(btnRegistrar, gbc);
        gbc.gridx = 1;
        formPanel.add(btnEliminar, gbc);

        // Fila 4 - Combo de habitación
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Habitación:"), gbc);
        gbc.gridx = 1;
        formPanel.add(comboHabitaciones, gbc);

        // Fila 5 - Botón asignar
        gbc.gridx = 1; gbc.gridy = 4;
        formPanel.add(btnAsignar, gbc);

        add(formPanel, BorderLayout.NORTH);

        // Tablas
        tablaServicios = new JTable();
        tablaAsignados = new JTable();

        JPanel panelTablas = new JPanel(new GridLayout(2, 1));
        panelTablas.add(new JScrollPane(tablaServicios));
        panelTablas.add(new JScrollPane(tablaAsignados));

        add(panelTablas, BorderLayout.CENTER);
    }

    private void registrarServicio(ActionEvent e) {
        try {
            String nombre = txtNombre.getText();
            double precio = Double.parseDouble(txtPrecio.getText());

            ServicioAdicionalDTO dto = new ServicioAdicionalDTO(nombre, precio);
            controlador.registrarServicio(dto);

            JOptionPane.showMessageDialog(this, "Servicio registrado correctamente.");
            cargarServicios();
        } catch (DatoInvalidoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error general: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarServicio(ActionEvent e) {
        int fila = tablaServicios.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un servicio para eliminar.");
            return;
        }

        String id = tablaServicios.getValueAt(fila, 0).toString();

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de eliminar este servicio?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean eliminado = controlador.eliminarServicio(id);
            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Eliminado correctamente.");
                cargarServicios();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void asignarServicio(ActionEvent e) {
        int fila = tablaServicios.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un servicio a asignar.");
            return;
        }

        Habitacion habitacion = (Habitacion) comboHabitaciones.getSelectedItem();
        if (habitacion == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una habitación.");
            return;
        }

        String nombreServicio = tablaServicios.getValueAt(fila, 1).toString();
        ServicioAdicional servicio = controlador.buscarPorNombre(nombreServicio);

        if (servicio != null) {
            habitacion.agregarServicio(servicio);
            JOptionPane.showMessageDialog(this, "Servicio asignado correctamente.");
            cargarServiciosAsignados(habitacion);
        }
    }

    private void cargarServicios() {
        List<ServicioAdicional> servicios = controlador.obtenerTodos();
        String[] columnas = {"ID", "Nombre", "Precio"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (ServicioAdicional s : servicios) {
            modelo.addRow(new Object[]{
                    s.getIdServicio(),
                    s.getNombre(),
                    s.getPrecio()
            });
        }

        tablaServicios.setModel(modelo);
    }

    private void cargarHabitaciones() {
        comboHabitaciones.removeAllItems();
        for (Habitacion h : habitacionDAO.obtenerTodas()) {
            comboHabitaciones.addItem(h);
        }

        comboHabitaciones.addActionListener(_ -> {
            Habitacion seleccionada = (Habitacion) comboHabitaciones.getSelectedItem();
            if (seleccionada != null) {
                cargarServiciosAsignados(seleccionada);
            }
        });
    }

    private void cargarServiciosAsignados(Habitacion habitacion) {
        String[] columnas = {"Nombre", "Precio"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (ServicioAdicional s : habitacion.getServiciosAdicionales()) {
            modelo.addRow(new Object[]{
                    s.getNombre(),
                    s.getPrecio()
            });
        }

        tablaAsignados.setModel(modelo);
    }
}
