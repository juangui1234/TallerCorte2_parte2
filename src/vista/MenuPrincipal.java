package vista;

import controlador.HabitacionControlador;
import controlador.HuespedControlador;
import controlador.ReservaControlador;

import javax.swing.*;

public class MenuPrincipal extends JFrame {
    private JDesktopPane escritorio;

    // Controladores compartidos
    private HuespedControlador huespedControlador;
    private HabitacionControlador habitacionControlador;
    private ReservaControlador reservaControlador;

    public MenuPrincipal() {
        setTitle("Sistema de Gestión EcoHotel Rural");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Inicializar controladores
        huespedControlador = new HuespedControlador();
        habitacionControlador = new HabitacionControlador();
        reservaControlador = new ReservaControlador(
                huespedControlador.getDao(),
                habitacionControlador.getDao()
        );

        escritorio = new JDesktopPane();
        setContentPane(escritorio);

        crearBarraMenu();

        // Mensaje de bienvenida
        JOptionPane.showMessageDialog(
                this,
                "👋 Bienvenido al Sistema de Gestión Ecohotel Rural realizado por Juan Guillermo Salazar",
                "Bienvenido",
                JOptionPane.INFORMATION_MESSAGE
        );

        setVisible(true);
    }

    private void crearBarraMenu() {
        JMenuBar barra = new JMenuBar();

        JMenu menuHuesped = new JMenu("Huésped");
        JMenuItem itemHuesped = new JMenuItem("Registrar/Búsqueda");
        itemHuesped.addActionListener(_ -> abrirPanelHuesped());
        menuHuesped.add(itemHuesped);

        JMenu menuHabitacion = new JMenu("Habitación");
        JMenuItem itemHabitacion = new JMenuItem("Registrar/Búsqueda");
        itemHabitacion.addActionListener(_ -> abrirPanelHabitacion());
        menuHabitacion.add(itemHabitacion);

        JMenu menuReserva = new JMenu("Reserva");
        JMenuItem itemReserva = new JMenuItem("Nueva Reserva");
        itemReserva.addActionListener(_ -> abrirPanelReserva());
        menuReserva.add(itemReserva);

        JMenu menuSalir = new JMenu("Salir");
        JMenuItem itemSalir = new JMenuItem("Salir del sistema");
        itemSalir.addActionListener(_ -> {
            int opcion = JOptionPane.showConfirmDialog(this, "¿Deseas salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        menuSalir.add(itemSalir);

        barra.add(menuHuesped);
        barra.add(menuHabitacion);
        barra.add(menuReserva);
        barra.add(menuSalir);

        setJMenuBar(barra);
    }

    private void abrirPanelHuesped() {
        PanelHuesped panel = new PanelHuesped(huespedControlador);
        mostrarPanel(panel, "Gestión de Huéspedes");
    }

    private void abrirPanelHabitacion() {
        PanelHabitacion panel = new PanelHabitacion(habitacionControlador);
        mostrarPanel(panel, "Gestión de Habitaciones");
    }

    private void abrirPanelReserva() {
        PanelReserva panel = new PanelReserva(reservaControlador);
        mostrarPanel(panel, "Gestión de Reservas");
    }

    private void mostrarPanel(JPanel panel, String titulo) {
        JInternalFrame ventana = new JInternalFrame(titulo, true, true, true, true);
        ventana.setContentPane(panel);
        ventana.pack();
        ventana.setVisible(true);
        escritorio.add(ventana);
        try {
            ventana.setSelected(true);
        } catch (Exception ignored) {}
    }
}



/*package vista;

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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal ventana = new MenuPrincipal();
            ventana.setVisible(true);
        });
    }
}*/

/*package vista;

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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal ventana = new MenuPrincipal();
            ventana.setVisible(true);
        });
    }
}*/