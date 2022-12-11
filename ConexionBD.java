import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConexionBD {
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        ConexionBD.connection = connection;
    }

    public static Statement getStatement() {
        return statement;
    }

    public static void setStatement(Statement statement) {
        ConexionBD.statement = statement;
    }

    public static ResultSet getResultSet() {
        return resultSet;
    }

    public static void setResultSet(ResultSet resultSet) {
        ConexionBD.resultSet = resultSet;
    }

    public static void update(String query){
        try{
            statement.executeUpdate(query);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public static void delete(String query){
        try{
            statement.executeUpdate(query);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void create(String query){
        try{
            statement.executeUpdate(query);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public static void read(String query){
        try{
            resultSet = statement.executeQuery(query);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
