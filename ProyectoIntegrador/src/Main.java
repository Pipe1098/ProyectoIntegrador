import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        // Creamos fecha y hora de entrega
        LocalDateTime horaEntrega = LocalDateTime.of(2023, 2, 16, 12, 30);

        // Creamos un cliente
       Cliente cliente = new Cliente(1234567890, "Juan", "Pérez", "1234567890", "juanperez@gmail.com", "Cra 10 # 20-30", "Bogotá");

        // Creamos un empleado
        Usuario empleado = new Empleado(1274467880, "María", "Gómez", "3007654321", "mariagomez@gmail.com", "Cra 20 # 30-40", "Medellín", 3, "AB+", TipoEmpleado.CONDUCTOR);

        // Creamos un paquete
        Paquete paquete = new Paquete(TipoPaquete.LIVIANO, 1.5, 100000);

        // Creamos un envío
        Envio envio = new Envio (cliente, "Bogotá", "Medellín", "Cra 30 # 40-50", "Juan Gómez", 300654321, horaEntrega, EstadoEnvio.EN_RUTA, 200000, paquete);


        // Imprimir los datos del envío
     System.out.println("*******************************************");
        System.out.println("Datos del envío:");
        System.out.println("Número de guía: " + envio.getNumeroGuia());
        System.out.println("Cliente: " + envio.getCliente().getNombre() + " " + envio.getCliente().getApellidos());
        System.out.println("Ciudad origen: " + envio.getCiudadOrigen());
        System.out.println("Ciudad destino: " + envio.getCiudadDestino());
        System.out.println("Dirección destino: " + envio.getDirDestino());
        System.out.println("Nombre persona que recibe: " + envio.getNombreReceptor());
        System.out.println("Número celular persona que recibe: " + envio.getCelReceptor());
        System.out.println("Estado del envío: " + envio.getEstadoEnvio());
        System.out.println("Valor del envío: $" + envio.getValorEnvio());
        System.out.println("Tipo de paquete: " + envio.getPaquete().getTipoPaquete());
        System.out.println("Peso del paquete: " + envio.getPaquete().getPeso()+"Kg");
        System.out.println("ID del paquete: " + envio.getPaquete().getIdPaquete());
        System.out.println("Valor declarado del paquete: $" + envio.getPaquete().getValorDeclarado());
     System.out.println("Empleado encargado de la gestión: " + empleado.getNombre()+" "+empleado.getApellidos());
     System.out.println("**********************************************");

    }

}
