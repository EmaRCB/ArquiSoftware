import org.apache.log4j.Logger;

public class Bitacora {
    public static Logger log;
    public Bitacora(Class name) {
        log = CreadorBitacora.getLogger(name);

    }
}
