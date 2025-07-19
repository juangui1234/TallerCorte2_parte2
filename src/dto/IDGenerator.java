package modelo;


    public class IDGenerator {

        // Contador interno estático (se comparte en todas las llamadas)
        private static int contador = 1;

        // Metodo estático que genera el ID único
        public static String generateReservaId() {
            String id = "RES-" + contador;
            contador++; // Incrementamos para el siguiente ID
            return id;
        }


    // generateReservaId()
}
