package vista;

import controlador.HuespedControlador;
import dto.HuespedDTO;
import excepciones.DatoInvalidoException;
import modelo.Huesped;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PanelHuesped extends JPanel {
    private JTextField txtNombre;
    private JTextField txtDocumento;
    private JTextField txtCorreo;
    private JTextField txtTelefono;
    private JButton btnRegistrar;
    private JTable tabla;
    private HuespedControlador controlador;

    public PanelHuesped(HuespedControlador controlador) {
        this.controlador = controlador;
        setLayout(new BorderLayout());
        initComponents();
        cargarHuespedes();
    }

    private void initComponents() {
        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        formPanel.setBorder(BorderFactory.createTitledBorder("Registrar Huésped"));

        txtNombre = new JTextField();
        txtDocumento = new JTextField();
        txtCorreo = new JTextField();
        txtTelefono = new JTextField();

        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(txtNombre);
        formPanel.add(new JLabel("Documento:"));
        formPanel.add(txtDocumento);
        formPanel.add(new JLabel("Correo electrónico:"));
        formPanel.add(txtCorreo);
        formPanel.add(new JLabel("Teléfono:"));
        formPanel.add(txtTelefono);

        btnRegistrar = new JButton("Registrar Huésped");
        btnRegistrar.addActionListener(this::registrarHuesped);
        formPanel.add(btnRegistrar);

        add(formPanel, BorderLayout.NORTH);

        tabla = new JTable();
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    private void registrarHuesped(ActionEvent e) {
        try {
            HuespedDTO dto = new HuespedDTO(
                    txtNombre.getText(),
                    txtDocumento.getText(),
                    txtCorreo.getText(),
                    txtTelefono.getText()
            );
            controlador.registrarHuesped(dto);
            JOptionPane.showMessageDialog(this, "Huésped registrado exitosamente.");
            cargarHuespedes();
        } catch (DatoInvalidoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error general: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarHuespedes() {
        List<Huesped> huespedes = controlador.obtenerTodos();
        String[] columnas = {"Nombre", "Documento", "Correo", "Teléfono"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Huesped h : huespedes) {
            Object[] fila = {
                    h.getNombre(),
                    h.getDocumento(),
                    h.getCorreo(),
                    h.getTelefono()
            };
            modelo.addRow(fila);
        }

        tabla.setModel(modelo);
    }
}