package algoritmittehtavageneraattori.domain;

import algoritmittehtavageneraattori.dao.UserDao;
import algoritmittehtavageneraattori.dao.TaskDao;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * Represents service
 */
public class AlgoritmitehtavageneraattoriService {
    
    private User loggedIn;
    private final UserDao userDao;
    private final TaskDao taskDao;
    
    /** Creates service with specific interfaces
     * 
     * @param userDao The user interface
     * @param taskDao The task interface
     */
    public AlgoritmitehtavageneraattoriService(UserDao userDao, TaskDao taskDao) {
        this.userDao = userDao;
        this.taskDao = taskDao;
    }
    /** Creates new user with specific username and hashed password
     * 
     * @param username A string representing users input username
     * @param password A string representing users input password
     * @return A boolean representing whether the operation was 
     * completed successfully
     */
    public boolean createUser(String username, String password)  {   
        if (userDao.findByUsername(username) != null) {
            return false;
        }
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(11));
        User user = new User(username, hashedPassword, 0);
        try {
            userDao.create(user);
        } catch (Exception e1) {
            return false;
        }

        return true;
    }
    /** Checks whether the login is possible with the parameters 
     * entered by the user
     * 
     * @param username A String representing user input username
     * @param password A String representing user input password
     * @return A boolean representing whether login was successful
     */
    public boolean login(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            return false;
        }
        
        loggedIn = user;
        
        return true;
    }
    
    /** Logs current user out
     * 
     */
    public void logout() {
        loggedIn = null;  
    }
    /** Gets logged user
     * 
     * @return The user representing current logged user
     */
    public User getLoggedUser() {
        return loggedIn;
    }
    
    /** Compares the user input to the result of the task
     * 
     * @param userInputResult A String representing user's input
     * @param task A task that represents a task to which the user's 
     * input is compared
     * @return A boolean represents the result of the comparison
     */
    public boolean compareResults(String userInputResult, Task task) {
        if (!BCrypt.checkpw(userInputResult, task.getResult())) {
            return false;
        }
        if (!task.getDone()) {
            loggedIn.setPoints(task.getDifficulty());
            markSolved(task.getId());
        }
        return true;
    }
    /** creates the task and connects it to the logged in user
     * 
     * @param title A string representing user input for task's title
     * @param description A String representing user input for task's description
     * @param result A String representing user input for task's result
     * @param difficulty An int representing user input for task's difficulty
     * @param categoryId An int representing user input for task's categoryId
     * @param input A string representing user input for task's input
     * @return A boolean representing whether creating new task was successful
     */
    public boolean createTask(String title, String description, String result, int difficulty, int categoryId, String input) {
        if (loggedIn == null) {
            return false;
        }
        String hashedResult = BCrypt.hashpw(result, BCrypt.gensalt(11));
        Task task = new Task(title, description, hashedResult, difficulty, taskDao.getAll().size() + 1, categoryId, input, loggedIn);
        try {
            taskDao.create(task);
        } catch (Exception e2) {
            return false;
        }
        return true;
    }
    /** Gets all logged in user's tasks
     * 
     * @return A List representing tasks
     */
    public List<Task> getTasks() {
        if (loggedIn == null) {
            return new ArrayList<>();
        }
        ArrayList<Task> loggedInTasks = new ArrayList<>();
        taskDao.getAll().stream().filter((task) -> (task.getUser().equals(loggedIn))).forEachOrdered((task) -> {
            loggedInTasks.add(task);
        });
        return loggedInTasks;
    }
    /**
     * Loads new tasks
     */
    public void loadTasks(File file) throws IOException {
        taskDao.loadNewTasks(file);
    }
    /**
     * Adds new tasks
     */
    public void addTasks(File file) throws IOException {
        taskDao.addNewTasks(file);
    }
    /** Set task's with specific id solved
     * 
     * @param id An int value representing task's id
     */
    public void markSolved(int id) {
        try {
            taskDao.setDone(id);
            userDao.savePoints(loggedIn);
        } catch (Exception e3) {
        }
    }
    
}
