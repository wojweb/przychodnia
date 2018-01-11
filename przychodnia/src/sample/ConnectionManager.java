package sample;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    Connection connection;

    void Connect() throws SQLException{
        Connection conn = null;

        try(FileInputStream f = new FileInputStream("/home/pan/Studia/Semestr3/Bazy/Lista4/przychodnia/db.properties")){

            Properties pros = new Properties();
            pros.load(f);

            String url = pros.getProperty("url");
            String user = pros.getProperty("user");
            String password = pros.getProperty("password");

            conn = DriverManager.getConnection(url, user, password);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        connection = conn;
    }

    public static void main(String[] args) {
        try{
            ConnectionManager manager = new ConnectionManager();
            manager.Connect();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
