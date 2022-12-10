import exceptions.ArchivoInexistente;
import exceptions.EstructuraIncorrecta;
import exceptions.MVCInexistente;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfiguradorBitacora {
    public String nombreArchivo;
    public String pesoArchivo;
    public String maxArchivos;

    public ConfiguradorBitacora(){
        leerArchivoConfiguracion();
        //CreadorBitacora.setPesoArchivo("10MB");
        //CreadorBitacora.setMaxArchivos(5);
    }

    private void leerArchivoConfiguracion() {
        try {
            Object parse = new JSONParser().parse(new FileReader("configuracionBitacora.json"));
            JSONObject contenidoJson = (JSONObject) parse;

            JSONObject configBitacora = (JSONObject) contenidoJson.get("configuracion");
            nombreArchivo = (String) configBitacora.get("nombreArchivo");
            System.out.println(nombreArchivo);
            pesoArchivo = (String) configBitacora.get("limiteArchivo");
            System.out.println(pesoArchivo);
            maxArchivos = (String) configBitacora.get("maxArchivos");
            System.out.println(maxArchivos);

            /*CreadorBitacora.setNombreArchivo(nombreArchivo);
            CreadorBitacora.setPesoArchivo(pesoArchivo);
            CreadorBitacora.setMaxArchivos(Integer.parseInt(maxArchivos));*/



        } catch (FileNotFoundException f) {
            throw new ArchivoInexistente("ERROR: No se encontró el archivo de configuración para el componente MVC.\n***El archivo de configuración del componente MVC debe ser guardado en la carpeta que contiene a 'src' con el nombre 'configuracionCompMVC.json'***");
        } catch (ParseException | IOException e){
            e.printStackTrace();
        } catch (MVCInexistente e){
            e.printStackTrace();
            System.exit(1);
        }
    }
}
