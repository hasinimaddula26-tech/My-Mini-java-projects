import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    static Connection con;

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/BANK";
            String user = "root";
            String pass = "hasini@@2006"; // change this

            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected to Database!");
        } catch (Exception e) {
            System.out.println("Connection Failed! " + e.getMessage());
        }
        return con;
    }
}