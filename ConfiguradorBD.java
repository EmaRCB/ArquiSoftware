import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class ConfiguradorBD {
    public static String bd;
    public static String url;
    public static String usuario;
    public static String password;
    public ConfiguradorBD() {
        leerArchivoConfiguracion();
    }

    public static void leerArchivoConfiguracion() {
        try {
            Object parse = new JSONParser().parse(new FileReader("configuracion/configuracionBD.json"));
            JSONArray arrayBD = (JSONArray) parse;


            for (JSONObject basedatos : (Iterable<JSONObject>) arrayBD) {


                bd = (String) basedatos.get("BD");
                //if (nombreTransaccion == null) throw new MVCInexistente("ERROR EN ARCHIVO DE CONFIGURACIÓN: \"nombre-transaccion\" no existe en el archivo de configuración 'configuracionCompMVC.json'.");

                url = (String) basedatos.get("url");
                //if (vistas == null) throw new MVCInexistente("ERROR EN ARCHIVO DE CONFIGURACIÓN: la clave \"vistas\" para la transacción \""+nombreTransaccion+"\" no existe en el archivo de configuración 'configuracionCompMVC.json'.");

                usuario = (String) basedatos.get("usuario");
                //if (controlador == null) throw new MVCInexistente("ERROR EN ARCHIVO DE CONFIGURACIÓN: la clave \"controlador\" para la transacción \""+nombreTransaccion+"\" no existe en el archivo de configuración 'configuracionCompMVC.json'.");

                password = (String) basedatos.get("password");
                //if (modelos == null) throw new MVCInexistente("ERROR EN ARCHIVO DE CONFIGURACIÓN: la clave \"modelos\" para la transacción \""+nombreTransaccion+"\" no existe en el archivo de configuración 'configuracionCompMVC.json'.");

                System.out.println(bd + url + usuario + password);
            }

        } catch (FileNotFoundException f) {
            //throw new ArchivoInexistente("ERROR: No se encontró el archivo de configuración para el componente MVC.\n***El archivo de configuración del componente MVC debe ser guardado en la carpeta que contiene a 'src' con el nombre 'configuracionCompMVC.json'***");
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) throws SQLException {
        ConfiguradorBD nuevaconfig = new ConfiguradorBD();
        CreadorConexionBD nuevaBD = new CreadorConexionBD();
        ConexionBD.read("SELECT * FROM Articulos");

        pedir();
        VistaVotos vista = new VistaVotos();
        PoolObserver nuevow = new PoolObserver();




    }

    public static void pedir() throws SQLException {
        ConexionBD.update("update Articulos set DescArt='HOLAMUNdito' where ClvArt='A00001'");
        ConexionBD.read("SELECT * FROM Articulos");
        while(ConexionBD.resultSet.next()){
            System.out.println(ConexionBD.resultSet.getString("ClvArt") + "|" + ConexionBD.resultSet.getString("DescArt") + "|" + ConexionBD.resultSet.getString("TipoArt"));
        }
        ConexionBD.connection.close();
        ConexionBD.statement.close();
        ConexionBD.resultSet.close();
    }

}
