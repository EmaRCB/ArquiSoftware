import java.util.Hashtable;

public abstract class Controlador {
    protected Hashtable<String, Modelo> models = new Hashtable<>();

    public void addModel(Modelo modelo) {
        this.models.put(modelo.getId(), modelo);
    }

    public abstract void handleEvent(Object dato);


}
