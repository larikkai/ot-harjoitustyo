package algoritmittehtavageneraattori.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    User user;
    User user2;
    User user3;
    
    @Before
    public void setUp(){
        user = new User("user");
        user2 = new User("user");
        user3 = new User("notUser");
    }
    
    @Test
    public void userWithSameUsernameAreEqual() {
        assertTrue(user.equals(user2));
    }
    
    @Test
    public void userWithoutSameUsernameAreNotEqual() {
        assertFalse(user2.equals(user3));
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
}