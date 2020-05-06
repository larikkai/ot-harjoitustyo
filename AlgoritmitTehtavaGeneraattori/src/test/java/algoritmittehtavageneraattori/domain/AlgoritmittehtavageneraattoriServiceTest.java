package algoritmittehtavageneraattori.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.security.crypto.bcrypt.BCrypt;


public class AlgoritmittehtavageneraattoriServiceTest {
    
    FakeUserDao userDao;
    FakeTaskDao taskDao;
    AlgoritmitehtavageneraattoriService algoritmitehtavageneraattoriService;

    @Before
    public void setUp() {
        userDao = new FakeUserDao();
        taskDao = new FakeTaskDao();
        algoritmitehtavageneraattoriService = new AlgoritmitehtavageneraattoriService(userDao, taskDao);
        algoritmitehtavageneraattoriService.createUser("testuser", "test");
    }
    
    public void ifUsernameTakenCreatingNewUserFail() {
        assertTrue(algoritmitehtavageneraattoriService.createUser("testuser2", "test"));
        assertFalse(algoritmitehtavageneraattoriService.createUser("testuser2", "test"));
    }
    
    @Test
    public void newCreatedUserCanLogIn() {
        assertTrue(algoritmitehtavageneraattoriService.createUser("testuser2", "test"));
        assertTrue(algoritmitehtavageneraattoriService.login("testuser2", "test"));
    }
    
    @Test
    public void loggingWithInvalidPasswordFail() {
        assertFalse(algoritmitehtavageneraattoriService.login("testuser", "tester"));
    }
    
    @Test
    public void loggingWithInvalidUsernameFail() {
        assertFalse(algoritmitehtavageneraattoriService.login("tester", "test"));
    }
    
    @Test
    public void atFirstNoLoggedUsers() {
        assertTrue(algoritmitehtavageneraattoriService.getLoggedUser() == null);
    }
    
    @Test
    public void loggedInUserCanLogout() {
        algoritmitehtavageneraattoriService.login("testuser", "test");
        assertTrue(algoritmitehtavageneraattoriService.getLoggedUser() != null);
        algoritmitehtavageneraattoriService.logout();
        assertTrue(algoritmitehtavageneraattoriService.getLoggedUser() == null);
    }
    
    @Test
    public void ifNotLoggedInCannotCreateTasks() {
        assertFalse(algoritmitehtavageneraattoriService.createTask(null, null, null, 0, 0, null));
    }
    
    @Test
    public void LoggedInUserCanCreateTasks() {
        algoritmitehtavageneraattoriService.login("testuser", "test");
        assertTrue(algoritmitehtavageneraattoriService.createTask(null, null, null, 0, 0, null));
    }
    
    @Test
    public void ifNotLoggedInCannotGetTasks() {
        assertTrue(algoritmitehtavageneraattoriService.getTasks().isEmpty());
    }
    
    @Test
    public void loggedInUserCanGetTasks() {
        loginUserAndCreateTask();
        assertTrue(algoritmitehtavageneraattoriService.getTasks().size() == 1);
    }
    
    @Test
    public void taskCanBeMarkedAsDone() {
        loginUserAndCreateTask();
        algoritmitehtavageneraattoriService.markSolved(1);
        Task task = algoritmitehtavageneraattoriService.getTasks().get(0);
        assertTrue(task.getDone() == true);
    }
    
    @Test
    public void correctResultReturnsTrue() {
        loginUserAndCreateTask();
        Task task = algoritmitehtavageneraattoriService.getTasks().get(0);
        assertTrue(algoritmitehtavageneraattoriService.compareResults(null, task));
    }
    
    @Test
    public void wrongResultReturnsFalse() {
        loginUserAndCreateTask();
        Task task = algoritmitehtavageneraattoriService.getTasks().get(0);
        assertFalse(algoritmitehtavageneraattoriService.compareResults("1", task));
    }
    
    @Test
    public void task() {
        loginUserAndCreateTask();
        algoritmitehtavageneraattoriService.markSolved(1);
        Task task = algoritmitehtavageneraattoriService.getTasks().get(0);
        try {
            algoritmitehtavageneraattoriService.markSolved(999);
        } catch (AssertionError exception) {
        }
    }
    
    public void loginUserAndCreateTask() {
        algoritmitehtavageneraattoriService.login("testuser", "test");
        algoritmitehtavageneraattoriService.createTask(null, null, null, 0, 0, null);
    }

}
