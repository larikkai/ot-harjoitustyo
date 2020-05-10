package algoritmittehtavageneraattori.dao;

import algoritmittehtavageneraattori.domain.Task;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * Represents tasks databasedao by implementing taskdao
 * 
 */

public class DBTaskDao implements TaskDao {
    
    private List<Task> tasks;
    private Statement stmt;
    private UserDao userDao;
    private String dbName;
    
    /** Creates tasktable to database
     * 
     * @param name representing database name
     * @param userDao representing users
     */
    public DBTaskDao(String name, UserDao userDao) {
        this.dbName = name;
        this.userDao = userDao;
        createDB();
    }
    
    /**
     * creates tasktable with id, title, description, result, difficulty,
     * categoryId, input, done and username colums
     */
    private void createDB() {
        String sql = "CREATE TABLE TASK " +
                "(id bigint auto_increment PRIMARY KEY, " +
                " title VARCHAR(255) not NULL, " +
                " description VARCHAR(255) not NULL, " +
                " result VARCHAR(255) not NULL, " +
                " difficulty INTEGER not NULL, " +
                " categoryId INTEGER not NULL, " +
                " input VARCHAR(255) not NULL, " +
                " done BOOLEAN not NULL, " +
                " username VARCHAR(255) not NULL)";
        connect("createTable", sql);
    }
    
    /** Adds task to database
     * 
     * @param task to add to database
     * @return crated task
     * @throws Exception throws if exception
     */
    @Override
    public Task create(Task task) throws Exception {
        String sql = "INSERT INTO Task (title, description, result, difficulty, categoryId, input, done, username) VALUES ('"
                + task.getTitle() + "', '" + task.getDescription() + "', '" + task.getResult() + "', '" + task.getDifficulty() + "', '" 
                + task.getCategoryId() + "', '" + task.getInput() + "', '" + task.getDone() + "', '" + task.getUser().getUsername() + "')";
        connect("createTask", sql);
        return task;
    }
    /**
     * 
     * @return all tasks from database
     */
    @Override
    public List<Task> getAll() {
        String sql = "SELECT * FROM Task";
        connect("getAll", sql);
        return tasks;
    }
    /** Loads new tasks from file to database
     * 
     * @param file file where to read tasks
     */
    @Override
    public void loadNewTasks(File file) {
        String sql = "DELETE FROM Task";
        connect("loadNew", sql);
        tasks.clear();
        load(file);
    }
    /** Adds new task to database
     * 
     * @param file file where to read tasks
     */
    @Override
    public void addNewTasks(File file) {
        tasks.clear();
        load(file);
    }
    /** Method to set task done in databasde
     * 
     * @param id representing tasks id to update
     * @throws Exception throws if exception
     */
    @Override
    public void setDone(int id) throws Exception {
        String sql = "UPDATE Task SET done = TRUE WHERE id = " + id;
        connect("setDone", sql);
    }
    
    /** Method to load file
     * 
     * @param file to read
     */
    public void load(File file) {
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (!line.isEmpty()) {
                    String[] parts = line.split(";");
                    int difficulty = Integer.valueOf(parts[3]);
                    int id = tasks.size() + 1;
                    int categoryId = Integer.valueOf(parts[6]);
                    Task t = new Task(parts[0], parts[1], parts[2], difficulty, id, categoryId, parts[7], userDao.findByUsername(parts[8]));
                    if (Boolean.valueOf(parts[5])) {
                        t.setDone();
                    }
                    tasks.add(create(t));
                }
            }
        } catch (Exception e) {
        }
    }
    /** Method to execute database command to get all tasks
     * 
     * @param sql command to execute
     * @throws SQLException throws if exception
     */
    private void commandGetAll(String sql) throws SQLException {
        tasks = new ArrayList<>();
        ResultSet rs = stmt.executeQuery(sql); 
        while (rs.next()) {
            Task task = new Task(rs.getString("title"), rs.getString("description"), rs.getString("result"), rs.getInt("difficulty"), rs.getInt("id"), rs.getInt("categoryId"), rs.getString("input"), userDao.findByUsername(rs.getString("username")));
            if (rs.getBoolean("done")) {
                task.setDone();
            }
            tasks.add(task);
        }
    }
    /** Method to connect to database
     * 
     * @param command representing what should be done
     * @param sql representing execute command
     */
    private void connect(String command, String sql) {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:./" + dbName, "sa", "")) {
            stmt = conn.createStatement();
            if (command.equals("loadNew")) {
                stmt.executeUpdate(sql); 
            } else if (command.equals("createTask")) {
                stmt.executeUpdate(sql); 
            } else if (command.equals("createTable")) {
                stmt.executeUpdate(sql); 
            } else if (command.equals("setDone")) {
                stmt.executeUpdate(sql);
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
