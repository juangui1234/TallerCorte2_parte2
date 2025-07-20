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
        formPanel.setBorder(BorderFactory.createTitledBorder("Gestión de Huéspedes"));

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

        // Panel de botones
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(this::registrarHuesped);
        botonesPanel.add(btnRegistrar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(this::buscarHuesped);
        botonesPanel.add(btnBuscar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(this::editarHuesped);
        botonesPanel.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this::eliminarHuesped);
        botonesPanel.add(btnEliminar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(_ -> limpiarCampos());
        botonesPanel.add(btnLimpiar);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(formPanel, BorderLayout.CENTER);
        panelSuperior.add(botonesPanel, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);

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
            limpiarCampos();
        } catch (DatoInvalidoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de validación", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarHuesped(ActionEvent e) {
        String documento = txtDocumento.getText();
        if (documento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el documento para buscar.");
            return;
        }
        Huesped h = controlador.buscarPorDocumento(documento);
        if (h != null) {
            txtNombre.setText(h.getNombre());
            txtCorreo.setText(h.getCorreo());
            txtTelefono.setText(h.getTelefono());
            JOptionPane.showMessageDialog(this, "Huésped encontrado.");
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el huésped.");
        }
    }

    private void editarHuesped(ActionEvent e) {
        try {
            String documento = txtDocumento.getText();
            if (documento.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el documento del huésped que desea editar.");
                return;
            }

            Huesped encontrado = controlador.buscarPorDocumento(documento);
            if (encontrado == null) {
                JOptionPane.showMessageDialog(this, "No se encontró un huésped con ese documento.");
                return;
            }

            // Creamos uno nuevo con los datos actualizados
            Huesped nuevo = new Huesped(
                    txtNombre.getText(),
                    documento,
                    txtCorreo.getText(),
                    txtTelefono.getText()
            );

            boolean actualizado = controlador.getDao().actualizarHuesped(nuevo);
            if (actualizado) {
                JOptionPane.showMessageDialog(this, "Huésped actualizado exitosamente.");
                cargarHuespedes();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar huésped.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarHuesped(ActionEvent e) {
        String documento = txtDocumento.getText();
        if (documento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar el documento del huésped a eliminar.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de eliminar el huésped con documento " + documento + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean eliminado = controlador.eliminarHuesped(documento);
            if (eliminado) {
                JOptionPane.showMessageDialog(this, "Huésped eliminado.");
                cargarHuespedes();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el huésped.");
            }
        }
    }

    private void cargarHuespedes() {
        List<Huesped> huespedes = controlador.obtenerTodos();
        String[] columnas = {"ID", "Nombre", "Documento", "Correo", "Teléfono"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Huesped h : huespedes) {
            Object[] fila = {
                    h.getIdHuesped(),
                    h.getNombre(),
                    h.getDocumento(),
                    h.getCorreo(),
                    h.getTelefono()
            };
            modelo.addRow(fila);
        }

        tabla.setModel(modelo);
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtDocumento.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
    }
}
