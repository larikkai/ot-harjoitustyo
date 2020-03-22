package algoritmittehtavageneraattori.domain;

import algoritmittehtavageneraattori.dao.UserDao;

public class AlgoritmitehtavageneraattoriService {
    
    private User loggedIn;
    private UserDao userDao;
    
    public AlgoritmitehtavageneraattoriService(UserDao userDao) {
        this.userDao = userDao;
    }
    
    public boolean createUser(String username)  {   
        if (userDao.findByUsername(username) != null) {
            return false;
        }
        User user = new User(username);
        try {
            userDao.create(user);
        } catch(Exception e) {
            return false;
        }

        return true;
    }
    
    public boolean login(String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return false;
        }
        
        loggedIn = user;
        
        return true;
    }
    
    public void logout() {
        loggedIn = null;  
    }
    
}
