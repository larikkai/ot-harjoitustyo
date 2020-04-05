package algoritmittehtavageneraattori.domain;

import algoritmittehtavageneraattori.dao.UserDao;
import algoritmittehtavageneraattori.dao.TaskDao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class AlgoritmitehtavageneraattoriService {
    
    private User loggedIn;
    private UserDao userDao;
    private TaskDao taskDao;
    
    public AlgoritmitehtavageneraattoriService(UserDao userDao, TaskDao taskDao) {
        this.userDao = userDao;
        this.taskDao = taskDao;
    }
    
    public boolean createUser(String username, String password)  {   
        if (userDao.findByUsername(username) != null) {
            return false;
        }
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(11));
        User user = new User(username, hashedPassword);
        try {
            userDao.create(user);
        } catch(Exception e) {
            return false;
        }

        return true;
    }
    
    public boolean login(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            return false;
        }
        
        loggedIn = user;
        
        return true;
    }
    
    public void logout() {
        loggedIn = null;  
    }
    
    public User getLoggedUser(){
        return loggedIn;
    }
    
    public boolean createTask(String title, String description, String result, int difficulty, int gategoryId, String input){
        String hashedResult = BCrypt.hashpw(result, BCrypt.gensalt(11));
        Task task = new Task(title, description, result, difficulty, taskDao.getAll().size()+1, gategoryId, input);
        try {
            taskDao.create(task);
        } catch(Exception ex) {
            return false;
        }
        return true;
    }
    
    public List<Task> getTasks(){
        if(loggedIn == null){
            return new ArrayList<>();
        }
        return taskDao.getAll();
    }
    
}
