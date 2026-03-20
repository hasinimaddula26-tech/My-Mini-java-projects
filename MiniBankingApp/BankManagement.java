import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class BankManagement {

    static Connection con = DBConnection.getConnection();

    // Create Account
    public static boolean createAccount(String name, int passCode) {
        try {
            String sql = "INSERT INTO customer(cname, balance, pass_code) VALUES (?, 1000, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, passCode);

            int rows = ps.executeUpdate();

            if (rows == 1) {
                System.out.println("Account Created Successfully!");
                return true;
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Username already exists!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Login
    public static boolean loginAccount(String name, int passCode) {
        try {
            String sql = "SELECT * FROM customer WHERE cname=? AND pass_code=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, passCode);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Login Successful!");
                return true;
            } else {
                System.out.println("Invalid Login!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}