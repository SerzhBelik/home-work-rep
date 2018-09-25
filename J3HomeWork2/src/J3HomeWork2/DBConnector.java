package J3HomeWork2;

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


    public DBConnector() {
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        String prefix = JDBC.PREFIX;

    }

    public String getName(String login, String password){
        String name = null;
        try {

            connection = DriverManager.getConnection(prefix + "D:\\test.db");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM chat_users WHERE login = '"
                    + login + "' AND password = '" + password + "';");
            name = resultSet.getString(4);
//            System.out.println(name);

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
        String name = null;
        int id;
        try {
//            System.out.println(newName);
            connection = DriverManager.getConnection(prefix + "D:\\test.db");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT name FROM chat_users");
            while (resultSet.next())
            {
//                System.out.println(resultSet.getString(1));
                if(resultSet.getString(1).equals(newName)) return null;

            }
//            if(resultSet.wasNull()) return null;
//            name = resultSet.getString(4);
//            System.out.println(name);
            id = statement.executeQuery("SELECT id FROM chat_users WHERE name = '"+ oldName + "';").getInt(1);
//            System.out.println(id);
            int update = statement.executeUpdate("UPDATE chat_users SET name = '" + newName + "' WHERE id = " + id + ";");
//            System.out.println(update);

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

        return newName;//FIXME
    }

}

