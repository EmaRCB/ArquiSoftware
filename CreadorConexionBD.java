import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreadorConexionBD {
    public CreadorConexionBD() {
        realizarConexion();
    }
    public static void realizarConexion(){
        String url = ConfiguradorBD.url;
        String username = ConfiguradorBD.usuario;
        String password = ConfiguradorBD.password;

        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            ConexionBD.setConnection(connection);

            Statement statement = connection.createStatement();
            ConexionBD.setStatement(statement);


            /*ResultSet resultSet = statement.executeQuery("SELECT * FROM Articulos");

            while(resultSet.next()){
                System.out.println(resultSet.getString("ClvArt") + "|" + resultSet.getString("DescArt") + "|" + resultSet.getString("TipoArt"));
            }
            connection.close();
            statement.close();
            resultSet.close();

             */


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
