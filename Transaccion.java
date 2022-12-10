import exceptions.MVCInexistente;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;


public class Transaccion {

    private final String nombre;
    private final TransaccionJSON transaccionJSON;
    private final Hashtable<String, TipoVista> vistasIntanciadas = new Hashtable<>();
    private Object controladorIntanciado;
    private final Hashtable<String,Object> modelosIntanciados = new Hashtable<>();

    public Transaccion(String nombre, TransaccionJSON transaccionJSON) {
        this.nombre = nombre;
        this.transaccionJSON = transaccionJSON;
        JSONArray views = transaccionJSON.getVistas();
        JSONObject controller = transaccionJSON.getControlador();
        JSONArray models = transaccionJSON.getModelos();
        try {

        String controllerName = (String) controller.get("nombreControlador");
        Object controllerModels =  controller.get("modelosControlador");

        if (controllerName == null) throw new MVCInexistente("CONFIG FILE ERROR: \"nombreControlador\" key for transaction \""+this.nombre +"\" does not exist in mvc-config file.");
        if (controllerModels == null) throw new MVCInexistente("CONFIG FILE ERROR: \"modelosControlador\" key for transaction \""+this.nombre +"\" does not exist in mvc-config file.");

            Class<?> controllerClass = Class.forName(controllerName);
        this.controladorIntanciado = controllerClass.getConstructor().newInstance();

        for (JSONObject view : (Iterable<JSONObject>) views) {
            String viewName = (String) view.get("nombreVista");
            if (viewName == null) throw new MVCInexistente("CONFIG FILE ERROR: \"nombreVista\" key for some view of transaction \""+this.nombre +"\" does not exist in mvc-config file.");

            String viewType = (String) view.get("tipoVista");
            if (viewType == null) throw new MVCInexistente("CONFIG FILE ERROR: \"tipoVista\" key for view \""+viewName+"\" of transaction \""+this.nombre +"\" does not exist in mvc-config file.");

                Class<?> viewClass = Class.forName(viewName);
                Object viewInstance = viewClass.getConstructor().newInstance();

                this.vistasIntanciadas.put(viewName,new TipoVista(viewType,viewInstance));

        }

        for (JSONObject model : (Iterable<JSONObject>) models) {
            String modelName = (String) model.get("nombreModelo");
            Object modelViews = model.get("vistasModelo");
            if (modelName == null) throw new MVCInexistente("ERROR DE ARCHIVO: la clave \"nombreModelo\" de la transacción \""+this.nombre +"\" no existe en el archivo de configuración 'configuracionCompMVC.json'.");
            if (modelViews == null) throw new MVCInexistente("ERROR DE ARCHIVO: la clave \"vistasModelo\" del modelo \""+modelName+"\" de la transaccion \""+this.nombre +"\" no existe en el archivo de configuración 'configuracionCompMVC.json'.");


            Class<?> modelClass = Class.forName(modelName);
            Object modelInstance = modelClass.getConstructor().newInstance();
            this.modelosIntanciados.put(modelName,modelInstance);
        }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("La clase" + e.getMessage() + " no existe");
            System.exit(1);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        } catch (MVCInexistente e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public TransaccionJSON getTransaccionJSON() {
        return transaccionJSON;
    }

    public Hashtable<String, TipoVista> getVistasIntanciadas() {
        return vistasIntanciadas;
    }

    public Object getControladorIntanciado() {
        return controladorIntanciado;
    }

    public Hashtable<String, Object> getModelosIntanciados() {
        return modelosIntanciados;
    }
}