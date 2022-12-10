
public class ControladorVotos extends Controlador {
    @Override
    public void handleEvent(Object dato) {
        models.get("modeloVotos").serviceNotification(dato);
    }
}
