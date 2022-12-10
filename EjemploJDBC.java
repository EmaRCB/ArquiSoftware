import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class EjemploJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/adquisiciones?serverTimezone=UTC";
        String username = "root";
        String password = "Jamart123";
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM Articulos");

            while(resultSet.next()){
                System.out.println(resultSet.getString("ClvArt") + "|" + resultSet.getString("DescArt") + "|" + resultSet.getString("TipoArt"));
            }
            connection.close();
            statement.close();
            resultSet.close();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
