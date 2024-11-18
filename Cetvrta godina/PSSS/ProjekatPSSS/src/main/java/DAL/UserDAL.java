package DAL;

import Data.ConnectionProvider;
import Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAL {
    public static int register(User user) {
        Connection conn = ConnectionProvider.getConnection();
        try {
            PreparedStatement preStat = conn.prepareStatement("INSERTI INTO users VALUES(NULL, ?, ?, ?)");
            preStat.setString(1, user.getUsername());
            preStat.setString(2, user.getEmail());
            preStat.setString(3, user.getPassword());

            preStat.execute();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public static int login(String email, String password) {
        Connection conn = ConnectionProvider.getConnection();
        try {
            PreparedStatement preStat = conn.prepareStatement("SELECT id FROM users WHERE email=? AND password=?");
            preStat.setString(1, email);
            preStat.setString(2, password);
            ResultSet rs = preStat.executeQuery();
            if (rs.next())
                return rs.getInt(1);
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getIdForEmail(String email) {
        Connection conn = ConnectionProvider.getConnection();
        try {
            PreparedStatement preStat = conn.prepareStatement("select id from users where email=?");
            preStat.setString(1, email);
            ResultSet rs = preStat.executeQuery();
            if (rs.next())
                return rs.getInt(1);
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
