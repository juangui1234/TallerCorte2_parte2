package vista;

import controlador.ReservaControlador;
import dto.ReservaDTO;
import excepciones.DatoInvalidoException;
import excepciones.FechaInvalidaException;
import excepciones.HabitacionNoDisponibleException;
import modelo.Reserva;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.List;

public class PanelReserva extends JPanel {
    private JTextField txtDocumento;
    private JTextField txtHabitacion;
    private JTextField txtEntrada;
    private JTextField txtSalida;
    private JButton btnRegistrar;
    private JTable tabla;
    private ReservaControlador controlador;

    public PanelReserva(ReservaControlador controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout());
        initComponents();
        cargarReservas();
    }

    private void initComponents() {
        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        formPanel.setBorder(BorderFactory.createTitledBorder("Registrar Reserva"));

        txtDocumento = new JTextField();
        txtHabitacion = new JTextField();
        txtEntrada = new JTextField("2025-07-21");
        txtSalida = new JTextField("2025-07-22");

        formPanel.add(new JLabel("Documento Huésped:"));
        formPanel.add(txtDocumento);
        formPanel.add(new JLabel("Número de Habitación:"));
        formPanel.add(txtHabitacion);
        formPanel.add(new JLabel("Fecha Entrada (YYYY-MM-DD):"));
        formPanel.add(txtEntrada);
        formPanel.add(new JLabel("Fecha Salida (YYYY-MM-DD):"));
        formPanel.add(txtSalida);

        btnRegistrar = new JButton("Registrar Reserva");
        btnRegistrar.addActionListener(this::registrarReserva);
        formPanel.add(btnRegistrar);

        add(formPanel, BorderLayout.NORTH);

        tabla = new JTable();
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    private void registrarReserva(ActionEvent e) {
        try {
            String documento = txtDocumento.getText();
            int numeroHabitacion = Integer.parseInt(txtHabitacion.getText());
            LocalDate entrada = LocalDate.parse(txtEntrada.getText());
            LocalDate salida = LocalDate.parse(txtSalida.getText());

            ReservaDTO dto = new ReservaDTO("", entrada, salida, "", documento, numeroHabitacion, "");
            controlador.registrarReserva(dto);

            JOptionPane.showMessageDialog(this, "Reserva registrada exitosamente.");
            cargarReservas();
        } catch (DatoInvalidoException | FechaInvalidaException | HabitacionNoDisponibleException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error general: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarReservas() {
        List<Reserva> reservas = controlador.obtenerTodas();
        String[] columnas = {"ID", "Huésped", "Habitación", "Entrada", "Salida"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Reserva r : reservas) {
            Object[] fila = {
                    r.getIdReserva(),
                    r.getHuesped().getNombre(),
                    r.getHabitacion().getNumero(),
                    r.getFechaEntrada(),
                    r.getFechaSalida()
            };
            modelo.addRow(fila);
        }

        tabla.setModel(modelo);
    }
}