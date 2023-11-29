package Model.Dao;

import Model.Account;
import jakarta.servlet.RequestDispatcher;

import java.sql.*;

public class accountDAO {
    protected Connection getConnection() {
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/?user=root?autoReconnect=true&useSSL=false";
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, "root", "root");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database. Check the database URL, username, and password.", e);
        }
    }
    //checks if the login is valid
    public boolean LoginValid(String pass, String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM webchat.account WHERE Email = ? AND Password = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, pass);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Password exists if count is greater than 0
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Password doesn't exist or an error occurred
    }

    //check if the email exists
    public boolean emailValid(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM webchat.account WHERE Email = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 1; // Return true if the count is exactly 1
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if an error occurred or the count is not 1
    }
    //return current logged in user
    public Account ReturnAcc(String email, String pass) throws SQLException{
            String sql = "SELECT * FROM webchat.account WHERE Email = ? AND Password = ?";
            String username = "";
            String Fname = "";
            String Lname = "";
            String id = "";
        System.out.println("emailDB: ");
        try (Connection con = getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
            System.out.println(email);
            System.out.println(pass);
            ps.setString(1, email);
                ps.setString(2, pass);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Fname = rs.getString("FirstName");
                    Lname = rs.getString("LastName");
                    username = rs.getString("username");
                    id = String.valueOf(rs.getInt("AccountID"));

                    System.out.println("emailDB: " + Fname);
                    System.out.println("passwordDB: " + Lname);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
           return new Account(id,username, pass, email, Fname, Lname);
        }
    //returns account based upon username
    public Account ReturnUsername(String username) throws SQLException {
        String sql = "SELECT * FROM webchat.account WHERE username = ?";
        String email = "";
        String pass = "";
        String Fname = "";
        String Lname = "";
        String id = "";
        System.out.println("emailDB: ");
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            System.out.println(email);
            System.out.println(pass);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Fname = rs.getString("FirstName");
                Lname = rs.getString("LastName");
                email = rs.getString("Email");
                id = String.valueOf(rs.getInt("AccountID"));
                pass = rs.getString("Password");
                System.out.println("emailDB: " + Fname);
                System.out.println("passwordDB: " + Lname);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Account(id,username, pass, email, Fname, Lname);
    }

        // register an account onto the sql database
        public void Registeracc(String email, String pass, String username, String Fname, String Lname) throws SQLException {
            String sql = "INSERT INTO webchat.account (Email, Password, FirstName, LastName, username) VALUES(?,?,?,?,? )";
            try (Connection con = getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, pass);
                ps.setString(3, Fname);
                ps.setString(4, Lname);
                ps.setString(5, username);
                ps.executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    //checks if the username is already taken
    public boolean usernameValid(String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM webchat.account WHERE username = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 1; // Return true if the count is exactly 1
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if an error occurred or the count is not 1
    }
    //update the user details
    public void UpdateUserDetails ( String firstname, String lastname, String username, String accountID ) throws SQLException {
        String sql = "UPDATE webchat.account SET FirstName=?, LastName=?, username=? WHERE AccountID = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, username);
            ps.setString(4, accountID);
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void changePassword( String password, String id) throws SQLException {
        String sql = "UPDATE webchat.account SET Password = ? WHERE AccountID = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, password);
            ps.setString(2, id);
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }



}


