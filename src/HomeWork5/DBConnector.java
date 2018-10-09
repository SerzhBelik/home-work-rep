package HomeWork5;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.sqlite.JDBC;


public class DBConnector {
    Connection connection = null;
    ResultSet resultSet = null;
    Statement statement = null;
    String prefix = JDBC.PREFIX;


    public String getName(String login, String password){
        String name = null;
        try {

            connection = DriverManager.getConnection(prefix + "D:\\test.db");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM chat_users WHERE login = '"
                    + login + "' AND password = '" + password + "';");
            name = resultSet.getString(4);

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return name;
    }

    public String renameUser (String oldName, String newName){

        try {
            connection = DriverManager.getConnection(prefix + "D:\\test.db");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT name FROM chat_users");
            while (resultSet.next())
            {
                if(resultSet.getString(1).equals(newName)) return null;
            }

            int id = statement.executeQuery("SELECT id FROM chat_users WHERE name = '"+ oldName + "';").getInt(1);
            statement.executeUpdate("UPDATE chat_users SET name = '" + newName + "' WHERE id = " + id + ";");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return newName;
    }

}
