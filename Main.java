public class Main {
    public static void main(String[] args) {
        ConfiguradorBitacora nuevaConfig = new ConfiguradorBitacora();
        CreadorBitacora nuevaBitacora = new CreadorBitacora(nuevaConfig);

        ConfiguradorMVC nuevaConfiguracion = ConfiguradorMVC.getInstancia();


        Transaccion votos = nuevaConfiguracion.cargarTransaccion("VotacionCandidatos");
        CreadorMVC.getInstancia().ejecutarTransaccion(votos);




    }
}
