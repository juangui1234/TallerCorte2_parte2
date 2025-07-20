import javax.swing.*;
import vista.MenuPrincipal;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal ventana = new MenuPrincipal();
            ventana.setVisible(true);
        });
    }
}

/*public class Main {
    public static void main(String[] args) {
        System.out.println(IDGenerator.generateHuespedId());     // HUE-1
        System.out.println(IDGenerator.generateHabitacionId());  // HAB-1
        System.out.println(IDGenerator.generateServicioId());    // SER-1
        System.out.println(IDGenerator.generateReservaId());     // RES-1
    }
}



        /* Llamadas al metodo estático
        String id1 = dto.IDGeneratorDTO.generateReservaId();
        String id2 = dto.IDGeneratorDTO.generateReservaId();
        String id3 = dto.IDGeneratorDTO.generateReservaId();

        // Mostramos los IDs generados
        System.out.println("ID 1: " + id1); // RES-1
        System.out.println("ID 2: " + id2); // RES-2
        System.out.println("ID 3: " + id3); // RES-3
    }*/


