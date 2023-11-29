package Model.Dao;

import Model.Account;
import Model.FriendShip;
import java.sql.*;
import java.util.ArrayList;


public class FriendRequestDAO {

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
    //return friendrequests for a specifc account
    public ArrayList<Account> ReturnFriendRequests(Account Mainaccount) throws SQLException {
        ArrayList<Account> friends = new ArrayList<>();
        String sql = "SELECT FR1_id FROM webchat.friendrequest WHERE FR2_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, Mainaccount.getAccountId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                friends.add(ReturnAccount(rs.getString("FR1_id")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return friends;
    }
    //add a friend
    public void AddFriend(String id_1, String id_2) throws SQLException {
        String sql = "INSERT INTO webchat.friendships (account2_id, account1_id) VALUES (?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id_1);
            ps.setString(2, id_2);
            ps.executeUpdate();
            ps.clearParameters();
            ps.setString(1, id_2);
            ps.setString(2, id_1);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //accept a friend request where id1 should belong to the user logged in and id 2 is the pending request
    public void AcceptFriendRequest( String id_1, String id_2) throws SQLException {
        String sql = "DELETE FROM webchat.friendrequest WHERE (FR1_id = ? AND FR2_id = ?) or (FR1_id = ? AND FR2_id = ?)";
        AddFriend(id_1,id_2);
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1,id_1);
            ps.setString(2,id_2);
            ps.setString(3,id_2);
            ps.setString(4,id_1);
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void RejectFriendRequest( String id_1, String id_2) throws SQLException {
        String sql = "DELETE FROM webchat.friendrequest WHERE FR1_id = ? AND FR2_id = ?";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1,id_1);
            ps.setString(2,id_2);
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean checkFR(String id_1, String id_2) {
        String sql = "SELECT COUNT(*) FROM webchat.friendrequest WHERE (FR1_id= ? AND FR2_id = ?) OR (FR1_id = ? AND FR2_id = ?)";
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


}