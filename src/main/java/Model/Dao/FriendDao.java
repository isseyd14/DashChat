package Model.Dao;

import Model.Account;
import java.sql.*;
import java.util.ArrayList;


public class FriendDao {

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
    public Account ReturnAccount(String id) throws SQLException {
        String sql = "SELECT * FROM webchat.account WHERE AccountID = ?";
        String username = "";
        String Fname = "";
        String Lname = "";
        String password  = "";
        String Email  = "";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                username = rs.getString("username");
                Fname = rs.getString("FirstName");
                Lname = rs.getString("LastName");
                password = rs.getString("Password");
                Email = rs.getString("Email");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return new Account(id,username,password,Email, Fname,Lname);
    }

    public ArrayList<Account> ReturnFriends(Account Mainaccount) throws SQLException {
        ArrayList<Account> friends = new ArrayList<>();
        String sql = "SELECT account2_id FROM webchat.friendships WHERE account1_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, Mainaccount.getAccountId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                friends.add(ReturnAccount(rs.getString("account2_id")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return friends;
    }
    // id1 is the current user logged in while id2 is the user that is being sent the request
    public boolean checkFriend(String id_1, String id_2) {
        String sql = "SELECT COUNT(*) FROM webchat.friendships WHERE (account1_id = ? AND account2_id = ?) OR (account1_id = ? AND account2_id = ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id_1);
            ps.setString(2, id_2);
            ps.setString(3, id_2);
            ps.setString(4, id_1);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // id1 is the current user logged in while id2 is the user that is being sent the request
    public void sendFriendRequest(String id_1, String id_2){
        String sql = "INSERT INTO webchat.friendrequest (FR1_id, FR2_id) VALUES (?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id_1);
            ps.setString(2, id_2);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //remove a Friend
    public void RemoveFriend(String MainID, String removedID) throws SQLException{
        String sql = "DELETE FROM webchat.friendships WHERE account1_id = ? AND account2_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, MainID);
            ps.setString(2, removedID);
            ps.executeUpdate();
            ps.clearParameters();
            ps.setString(1, removedID);
            ps.setString(2, MainID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            }
        }
}
