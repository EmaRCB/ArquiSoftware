import exceptions.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

@SuppressWarnings({"unchecked", "RedundantCast"})
public class ConfiguradorMVC {

    private static ConfiguradorMVC instancia;
    private final List<Transaccion> transacciones = new ArrayList<>();

    private ConfiguradorMVC(){
        leerArchivoConfiguracion();
    }

    public static ConfiguradorMVC getInstancia() {
        if (instancia == null) instancia = new ConfiguradorMVC();
        return instancia;
    }

    private void leerArchivoConfiguracion() {
        try {
            Object parse = new JSONParser().parse(new FileReader("configuracionCompMVC.json"));
            JSONArray arrayTransacciones = (JSONArray) parse;

            //En este ciclo se obtienen las claves de las transacciones
            for (JSONObject transaction : (Iterable<JSONObject>) arrayTransacciones) {


                String nombreTransaccion = (String) transaction.get("nombre-transaccion");
                if (nombreTransaccion == null) throw new MVCInexistente("ERROR EN ARCHIVO DE CONFIGURACIÓN: \"nombre-transaccion\" no existe en el archivo de configuración 'configuracionCompMVC.json'.");

                JSONArray vistas = (JSONArray) transaction.get("vistas");
                if (vistas == null) throw new MVCInexistente("ERROR EN ARCHIVO DE CONFIGURACIÓN: la clave \"vistas\" para la transacción \""+nombreTransaccion+"\" no existe en el archivo de configuración 'configuracionCompMVC.json'.");

                JSONObject controlador = (JSONObject) transaction.get("controlador");
                if (controlador == null) throw new MVCInexistente("ERROR EN ARCHIVO DE CONFIGURACIÓN: la clave \"controlador\" para la transacción \""+nombreTransaccion+"\" no existe en el archivo de configuración 'configuracionCompMVC.json'.");

                JSONArray modelos = (JSONArray) transaction.get("modelos");
                if (modelos == null) throw new MVCInexistente("ERROR EN ARCHIVO DE CONFIGURACIÓN: la clave \"modelos\" para la transacción \""+nombreTransaccion+"\" no existe en el archivo de configuración 'configuracionCompMVC.json'.");

                //Se revisa que la transacción sea única
                if (yaExisteTransaccion(nombreTransaccion) != null) throw new EstructuraIncorrecta("ERROR: La transacción \"" + nombreTransaccion + "\" existe más de una vez en el archivo de configuración 'configuracionCompMVC.json'.");
                else {
                    TransaccionJSON transaccionJSON = new TransaccionJSON(nombreTransaccion,vistas,controlador,modelos);
                    transacciones.add(new Transaccion(nombreTransaccion, transaccionJSON));
                }

            }

        } catch (FileNotFoundException f) {
            throw new ArchivoInexistente("ERROR: No se encontró el archivo de configuración para el componente MVC.\n***El archivo de configuración del componente MVC debe ser guardado en la carpeta que contiene a 'src' con el nombre 'configuracionCompMVC.json'***");
        } catch (ParseException | IOException e){
            e.printStackTrace();
        } catch (MVCInexistente e){
            e.printStackTrace();
            System.exit(1);
        }



    }

    public Transaccion cargarTransaccion(String nombreTransaccion){
        Transaccion transaccion = yaExisteTransaccion(nombreTransaccion);
        if (transaccion != null) {
            try {
                Hashtable<String, TipoVista> vistas = transaccion.getVistasIntanciadas();
                Set<String> keySet = vistas.keySet();

                JSONObject controlador = transaccion.getTransaccionJSON().getControlador();
                JSONArray controllerModelNames = (JSONArray) controlador.get("modelosControlador");
                JSONArray models = transaccion.getTransaccionJSON().getModelos();
                String controllerName = (String) controlador.get("nombreControlador");

                for (String key : keySet) {
                    String tipoVista = vistas.get(key).getType();

                    Object instanciaDeVista = vistas.get(key).getView();
                    Object instanciaControlador = transaccion.getControladorIntanciado();

                    if (tipoVista.equals("emisora") || tipoVista.equals("emisora-receptora")) {

                        Method setControllerMethod = vistas.get(key).getView().getClass().getMethod("setController", Controlador.class);

                        setControllerMethod.invoke(instanciaDeVista, (Controlador) instanciaControlador);

                    }


                }

                for (JSONObject model : (Iterable<JSONObject>) models) {
                    String modelName = (String) model.get("nombreModelo");

                    JSONArray modelViewsNames = (JSONArray) model.get("vistasModelo");
                    for (Object viewName : modelViewsNames) {
                        if (vistas.containsKey((String) viewName)) {
                            String viewType = vistas.get((String) viewName).getType();

                            if (viewType.equals("receptora") || viewType.equals("emisora-receptora")) {
                                Object viewInstance = vistas.get((String) viewName).getView();
                                Object modelInstance = transaccion.getModelosIntanciados().get(modelName);

                                Method addViewMethod = modelInstance.getClass().getMethod("addView", Vista.class);
                                addViewMethod.invoke(modelInstance, (Vista) viewInstance);
                            }
                        } else
                            throw new VinculoInvalido("ERROR: View \"" + viewName + "\" of model \"" + modelName + "\" is not linked with any view defined in transaccion-vistas of mvc-config file.");
                    }

                }

                for (Object modelName : controllerModelNames){
                    if (transaccion.getModelosIntanciados().containsKey((String)modelName)){
                        Object controllerInstance = transaccion.getControladorIntanciado();
                        Object modelInstance = transaccion.getModelosIntanciados().get(modelName);

                        Method addModelMethod = controllerInstance.getClass().getMethod("addModel", Modelo.class);
                        addModelMethod.invoke(controllerInstance, (Modelo) modelInstance);

                    } else
                        throw new VinculoInvalido("ERROR: Model \"" + modelName + "\" of controlador \"" + controllerName + "\" is not linked with any model defined in transaccion-models of mvc-config file.");
                }

            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            return transaccion;
        }

        else throw new TransaccionInexistente("ERROR: Transaction \"" + nombreTransaccion + "\" not found. Please check transaccion names in mvc-config file");

    }

    private Transaccion yaExisteTransaccion(String transactionName){
        for (Transaccion tInArray : transacciones){
            if (tInArray.getNombre().equals(transactionName)) return tInArray;
        }
        return null;
    }
}
