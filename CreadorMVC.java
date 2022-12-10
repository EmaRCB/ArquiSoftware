import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Set;

public class CreadorMVC {
    private static CreadorMVC instancia;

    private CreadorMVC(){
    }

    public static CreadorMVC getInstancia() {
        if (instancia == null) instancia = new CreadorMVC();
        return instancia;
    }

    public void ejecutarTransaccion(Transaccion transaction){
        Hashtable<String, TipoVista> views = transaction.getVistasIntanciadas();
        Set<String> keySet = views.keySet();
        try {
            for (String key : keySet) {
                Object viewInstance = views.get(key).getView();
                Method initViewMethod = viewInstance.getClass().getDeclaredMethod("initView");
                initViewMethod.invoke(viewInstance);
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
