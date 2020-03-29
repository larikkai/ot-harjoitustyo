package algoritmittehtavageneraattori.domain;

import algoritmittehtavageneraattori.dao.UserDao;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class AlgoritmitehtavageneraattoriService {
    
    private User loggedIn;
    private UserDao userDao;
    
    public AlgoritmitehtavageneraattoriService(UserDao userDao) {
        this.userDao = userDao;
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
    
}
