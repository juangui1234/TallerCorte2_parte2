package vista;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    private JDesktopPane desktopPane;

    public MenuPrincipal() {
        setTitle("Sistema de Gestión EcoHotel rural");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        desktopPane = new JDesktopPane();
        add(desktopPane, BorderLayout.CENTER);

        crearMenu();
        JOptionPane.showMessageDialog(
                this,
                "👋 Bienvenido al Sistema de Gestión Ecohotel Rural realizado por Juan Guillermo Salazar",
                "Bienvenido",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    private void crearMenu() {
        JMenuBar menuBar = new JMenuBar();

        // Menú Archivo
        JMenu menuArchivo = getJMenu();
        menuBar.add(menuArchivo);
        setJMenuBar(menuBar);
    }



    private JMenu getJMenu() {
        JMenu menuArchivo = new JMenu("Archivo");
        return menuArchivo;
    }
/*        //menu huesped
        JMenuItem itemPropietarios = new JMenuItem("Registrar Propietario");
        itemPropietarios.addActionListener(_ -> {
            PanelGestionPropietarios panel = new PanelGestionPropietarios(crudPropietarios);
            desktopPane.add(panel);
            panel.setVisible(true);
        });
        menuArchivo.add(itemPropietarios);

        JMenuItem itemNuevo = new JMenuItem("Menu habitaciones");
        itemNuevo.addActionListener(_ -> {
            FormularioPaciente form = new FormularioPaciente(crudMascotas, crudPropietarios);
            desktopPane.add(form);
            form.setVisible(true);
        });


        // Menu reservas
        JMenuItem itemRegistrarConsulta = new JMenuItem("Registrar Consulta");
        itemRegistrarConsulta.addActionListener(_ -> {
            PanelRegistrarConsulta panel = new PanelRegistrarConsulta(crudMascotas);
            desktopPane.add(panel);
            panel.setVisible(true);
        });
        menuArchivo.add(itemRegistrarConsulta);

        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(_ -> {
            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Estas seguro que deseas salir?",
                    "Confirma tu salida",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (opcion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        menuArchivo.add(itemNuevo);
        menuArchivo.addSeparator();
        menuArchivo.add(itemSalir);
        return menuArchivo;
    }*/

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal ventana = new MenuPrincipal();
            ventana.setVisible(true);
        });
    }
}