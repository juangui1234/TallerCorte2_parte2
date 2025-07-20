package vista;

import controlador.ReservaControlador;
import dto.ReservaDTO;
import excepciones.DatoInvalidoException;
import excepciones.FechaInvalidaException;
import excepciones.HabitacionNoDisponibleException;
import modelo.Habitacion;
import modelo.Reserva;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.List;

public class PanelReserva extends JPanel {
    private JTextField txtDocumento;
    private JComboBox<Habitacion> comboHabitacion;
    private JTextField txtEntrada;
    private JTextField txtSalida;
    private JButton btnRegistrar, btnEliminar, btnBuscar;
    private JTable tabla;
    private ReservaControlador controlador;

    public PanelReserva(ReservaControlador controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout());
        initComponents();
        cargarHabitacionesLibres();
        cargarReservas();
    }

    private void initComponents() {
        // Panel de formulario superior
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Registrar Reserva"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtDocumento = new JTextField(15);
        comboHabitacion = new JComboBox<>();
        txtEntrada = new JTextField("2025-07-21", 10);
        txtSalida = new JTextField("2025-07-22", 10);

        btnRegistrar = new JButton("Registrar Reserva");
        btnRegistrar.addActionListener(this::registrarReserva);

        btnEliminar = new JButton("Eliminar Reserva");
        btnEliminar.addActionListener(this::eliminarReserva);

        btnBuscar = new JButton("Buscar por Documento");
        btnBuscar.addActionListener(this::buscarPorDocumento);

        // Fila 1: Documento
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Documento Huésped:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtDocumento, gbc);

        // Fila 2: Habitación
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Habitación Disponible:"), gbc);
        gbc.gridx = 1;
        formPanel.add(comboHabitacion, gbc);

        // Fila 3: Fecha entrada
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Fecha Entrada (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtEntrada, gbc);

        // Fila 4: Fecha salida
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Fecha Salida (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtSalida, gbc);

        // Fila 5: Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        formPanel.add(panelBotones, gbc);

        add(formPanel, BorderLayout.NORTH);

        // Tabla
        tabla = new JTable();
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    private void cargarHabitacionesLibres() {
        comboHabitacion.removeAllItems();
        for (Habitacion h : controlador.obtenerHabitacionesLibres()) {
            comboHabitacion.addItem(h);
        }
    }

    private void registrarReserva(ActionEvent e) {
        try {
            String documento = txtDocumento.getText();
            Habitacion habitacionSeleccionada = (Habitacion) comboHabitacion.getSelectedItem();
            if (habitacionSeleccionada == null) {
                throw new DatoInvalidoException("Debe seleccionar una habitación disponible.");
            }

            LocalDate entrada = LocalDate.parse(txtEntrada.getText());
            LocalDate salida = LocalDate.parse(txtSalida.getText());

            ReservaDTO dto = new ReservaDTO("", entrada, salida, "", documento, habitacionSeleccionada.getNumero(), "");
            controlador.registrarReserva(dto);

            JOptionPane.showMessageDialog(this, "Reserva registrada exitosamente.");
            cargarReservas();
            cargarHabitacionesLibres();
        } catch (DatoInvalidoException | FechaInvalidaException | HabitacionNoDisponibleException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error general: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarReserva(ActionEvent e) {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una reserva para eliminar.");
            return;
        }

        String id = tabla.getValueAt(fila, 0).toString();
        int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar la reserva #" + id + "?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            if (controlador.cancelarReserva(id)) {
                JOptionPane.showMessageDialog(this, "Reserva eliminada correctamente.");
                cargarReservas();
                cargarHabitacionesLibres();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar la reserva.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void buscarPorDocumento(ActionEvent e) {
        String documento = JOptionPane.showInputDialog(this, "Ingrese el documento del huésped:");
        if (documento != null && !documento.trim().isEmpty()) {
            List<Reserva> reservas = controlador.buscarPorDocumentoHuesped(documento);
            DefaultTableModel modelo = new DefaultTableModel(new String[]{"ID", "Huésped", "Habitación", "Tipo", "Entrada", "Salida"}, 0);
            for (Reserva r : reservas) {
                modelo.addRow(new Object[]{
                        r.getIdReserva(),
                        r.getHuesped().getNombre(),
                        r.getHabitacion().getNumero(),
                        r.getHabitacion().getTipo(),
                        r.getFechaEntrada(),
                        r.getFechaSalida()
                });
            }
            tabla.setModel(modelo);
        }
    }

    private void cargarReservas() {
        List<Reserva> reservas = controlador.obtenerTodas();
        DefaultTableModel modelo = new DefaultTableModel(new String[]{"ID", "Huésped", "Habitación", "Tipo", "Entrada", "Salida"}, 0);
        for (Reserva r : reservas) {
            modelo.addRow(new Object[]{
                    r.getIdReserva(),
                    r.getHuesped().getNombre(),
                    r.getHabitacion().getNumero(),
                    r.getHabitacion().getTipo(),
                    r.getFechaEntrada(),
                    r.getFechaSalida()
            });
        }
        tabla.setModel(modelo);
    }
}