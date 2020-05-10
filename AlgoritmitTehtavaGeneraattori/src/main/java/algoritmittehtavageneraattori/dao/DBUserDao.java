package algoritmittehtavageneraattori.dao;

import algoritmittehtavageneraattori.domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBUserDao implements UserDao {
    private List<User> users;
    private User u;
    private Statement stmt;
    private String dbName;
    
    public DBUserDao(String name) {
        this.dbName = name;
        createDB();
    }

    private void createDB() {
        String sql = "CREATE TABLE USER " +
                    "(id bigint auto_increment PRIMARY KEY, " +
                    " username VARCHAR(255) not NULL, " +
                    " password VARCHAR(255) not NULL, " +
                    " points INTEGER)";
        connect("createTable", sql);
    }
    
    @Override
    public User create(User user) throws Exception {
        String sql = "INSERT INTO User (username, password, points) VALUES ('" + user.getUsername() + "', '" + user.getPassword() + "', '" + user.getPoints() + "')";
        connect("createUser", sql);
        return user;
    }

    @Override
    public User findByUsername(String usernameToFind) {
        String sql = "SELECT * FROM User WHERE username = '" + usernameToFind + "'";
        connect("findByUsername", sql);
        return u;
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM User";
        connect("getAll", sql);
        return users;
    }
    
    @Override
    public User savePoints(User user) {
        String sql = "UPDATE User SET points = '" + user.getPoints() + "' WHERE username = '" + user.getUsername() + "'";
        connect("savePoints", sql);
        return user;
    }
    
    private void commandFindByUsername(String sql) throws SQLException {
        ResultSet rs = stmt.executeQuery(sql); 
        if (rs.next()) {
            u = new User(rs.getString("username"), rs.getString("password"), rs.getInt("points"));
        } else {
            u = null;
        }
    }
    
    private void commandGetAll(String sql) throws SQLException {
        users = new ArrayList<>();
        ResultSet rs = stmt.executeQuery(sql); 
        while (rs.next()) {
            User user = new User(rs.getString("username"), rs.getString("password"), rs.getInt("points"));
            users.add(user);
        }
    }
    
    private void connect(String command, String sql) {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:./" + dbName, "sa", "")) {
            stmt = conn.createStatement();
            if (command.equals("savePoints")) {
                stmt.executeUpdate(sql); 
            } else if (command.equals("createUser")) {
                stmt.executeUpdate(sql); 
            } else if (command.equals("createTable")) {
                stmt.executeUpdate(sql); 
            } else if (command.equals("findByUsername")) {
                commandFindByUsername(sql);
            } else if (command.equals("getAll")) {
                commandGetAll(sql);
            }
            
            stmt.close();
            conn.close();
        } catch (SQLException se) {
        } catch (Exception e) {
        }
    }
}