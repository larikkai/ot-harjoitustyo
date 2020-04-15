package algoritmittehtavageneraattori.domain;

import algoritmittehtavageneraattori.dao.UserDao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class FakeUserDao implements UserDao {
    
    private List<User> users;
    
    public FakeUserDao() {
        users = new ArrayList<>();
        String hashedPassword = BCrypt.hashpw("test", BCrypt.gensalt(11));
    users.add(new User("student", hashedPassword));
    }

    @Override
    public User create(User user) {
        users.add(user);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        for(User user : users) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return users;
    }
    
}
