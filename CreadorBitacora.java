import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

public class CreadorBitacora {
    private static Logger REGISTRO;
    private static String nombreArchivo;
    private static String pesoArchivo;
    private static int maxArchivos;

    public CreadorBitacora(ConfiguradorBitacora config) {
        nombreArchivo = config.nombreArchivo;
        pesoArchivo = config.pesoArchivo;
        maxArchivos = Integer.parseInt(config.maxArchivos);
    }

    public static Logger getLogger(Class name) {
        try {
            REGISTRO = Logger.getLogger(name);
            String logfile = nombreArchivo + ".";
            Date fecha = new Date();

            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            String fechaAc = formato.format(fecha);
            PatternLayout defaultLayout = new PatternLayout("%d{dd-MM-yyyy HH:mm:ss} %-5p %c{1}:%L: %m%n");

            RollingFileAppender rollingFileAppender = new RollingFileAppender();
            rollingFileAppender.setFile(logfile + fechaAc + ".log", true, false, 0);
            rollingFileAppender.setMaxFileSize(pesoArchivo);
            rollingFileAppender.setMaxBackupIndex(maxArchivos);
            rollingFileAppender.setLayout(defaultLayout);

            REGISTRO.removeAllAppenders();
            REGISTRO.addAppender(rollingFileAppender);
            REGISTRO.setAdditivity(true);

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(CreadorBitacora.class.getName()).log(Level.SEVERE, null, ex);
        }
        return REGISTRO;
    }

    public static void setNombreArchivo(String nombreArchivo) {
        CreadorBitacora.nombreArchivo = nombreArchivo;
    }

    public static void setPesoArchivo(String pesoArchivo) {
        CreadorBitacora.pesoArchivo = pesoArchivo;
    }

    public static void setMaxArchivos(int maxArchivos) {
        CreadorBitacora.maxArchivos = maxArchivos;
    }
}
