package algoritmittehtavageneraattori.dao;

import algoritmittehtavageneraattori.domain.User;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DBUserDaoTest {
    
    UserDao userDao;
    
    @Before
    public void setUp() {
        userDao = new DBUserDao("test");
        load();
    }
    
    @After
    public void tearDown() {
        File testDBFile = new File("test.mv.db");
        File testDBFile2 = new File("test.mv.trace");
        testDBFile.delete();
        testDBFile2.delete();
    }
    
    @Test
    public void returnCorrectUserCountAtStart() {
        assertEquals(1, userDao.getAll().size());
    }
    
    @Test
    public void returnUserCreatedAtStart() {
        User returnedUser = userDao.findByUsername("test");
        assertEquals("test", returnedUser.getUsername());
    }
    
    @Test
    public void userCanGetMorePoints() {
        User returnedUser = userDao.findByUsername("test");
        assertEquals(0, returnedUser.getPoints());
        returnedUser.setPoints(10);
        userDao.savePoints(returnedUser);
        User returnedUserWithMorePoints = userDao.findByUsername("test");
        assertEquals(10, returnedUserWithMorePoints.getPoints());
    }
    
    private void load() {
        try {
            User user = new User("test", "pass", 0);
            userDao.create(user);
        } catch (Exception e) {
        }
    }
}
