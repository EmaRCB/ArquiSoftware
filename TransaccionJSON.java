import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



public class TransaccionJSON {
    private final String nombre;
    private final JSONArray vistas;
    private final JSONObject controlador;
    private final JSONArray modelos;

    public TransaccionJSON(String nombre, JSONArray vistas, JSONObject controlador, JSONArray modelos) {
        this.nombre = nombre;
        this.vistas = vistas;
        this.controlador = controlador;
        this.modelos = modelos;
    }

    public String getNombre() {
        return nombre;
    }

    public JSONArray getVistas() {
        return vistas;
    }

    public JSONObject getControlador() {
        return controlador;
    }

    public JSONArray getModelos() {
        return modelos;
    }
}
