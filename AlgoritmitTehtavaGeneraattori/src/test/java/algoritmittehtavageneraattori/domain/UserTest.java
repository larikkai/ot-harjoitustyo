package algoritmittehtavageneraattori.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class UserTest {
    
    User user;
    User user2;
    User user3;
    String password = BCrypt.hashpw("test", BCrypt.gensalt(11));
    
    @Before
    public void setUp(){
        user = new User("user", password, 0);
        user2 = new User("user", password, 0);
        user3 = new User("notUser", password, 0);
    }
    
    @Test
    public void userWithSameUsernameAreEqual() {
        assertTrue(user.equals(user2));
    }
    
    @Test
    public void differentUsernameAreNotEqual() {
        assertFalse(user2.equals(user3));
    }
    
    @Test
    public void passwordIsNotPlainText(){
        System.out.print(user.getPassword());
        assertFalse("test" == user.getPassword());
    }
    
    @Test
    public void methodReturnsCorrectPassword(){
        assertTrue(BCrypt.checkpw("test", user.getPassword()));
    }
    
    @Test
    public void methodReturnsCorrectUsername(){
        assertEquals("user", user.getUsername());
    }
    
    @Test
    public void nonEqualObjectReturnFalse () {
        Object o = new Object();
        assertFalse(user.equals(o));
    }
    
    @Test
    public void newUserHasZeroPoints(){
        assertEquals(0, user.getPoints());
    }
    
    @Test
    public void pointsCanIncrease(){
        user.setPoints(1);
        assertEquals(1, user.getPoints());
    }
}