package algoritmittehtavageneraattori.dao;

import algoritmittehtavageneraattori.domain.Task;
import algoritmittehtavageneraattori.domain.User;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class DBTaskDaoTest {
    
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    
    UserDao userDao;
    TaskDao taskDao;
    
    @Before
    public void setUp() {
        userDao = new DBUserDao("test");
        taskDao = new DBTaskDao("test", userDao);
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
    public void taskCanBeCreatedAndSetDone() {
        User user = userDao.findByUsername("test");
        Task task = new Task("test", "test", "test", 666, 3, 5, "test", user);
        try {
            taskDao.create(task);
        } catch (Exception e) {
        }
        assertEquals(1, taskDao.getAll().size());
        try {
            taskDao.setDone(1);
        } catch (Exception e) {
        }
        assertEquals(true, taskDao.getAll().get(0).getDone());
    }
    
    @Test
    public void tasksCanBeAddedFromFile() throws IOException {
        File taskFile = folder.newFile("test_tasks.txt");
        loadTasksToFile(taskFile);
        assertEquals(0, taskDao.getAll().size());
        taskDao.addNewTasks(taskFile);
        assertEquals(2, taskDao.getAll().size());
    }
    
    private void load() {
        try {
            User user = new User("test", "pass", 0);
            userDao.create(user);
        } catch (Exception e) {
        }
    }
    
    private void loadTasksToFile(File file) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("title" + ";" + "some description" +";" + "2020" + ";" + 1 + ";" + 1 + ";" +"false" + ";" + 1 + ";" + 1 + ";" + "test" + "\n");
            writer.write("title" + ";" + "some description" +";" + "2020" + ";" + 1 + ";" + 2 + ";" +"done" + ";" + 2 + ";" + 1 + ";" + "test" + "\n");
            writer.close();
        } catch (IOException e) {
        }
    }
}
