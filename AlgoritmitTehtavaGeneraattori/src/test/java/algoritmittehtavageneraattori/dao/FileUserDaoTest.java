package algoritmittehtavageneraattori.dao;

import algoritmittehtavageneraattori.domain.User;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class FileUserDaoTest {
    
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    
    File userFile;
    UserDao userDao;
    UserDao missingUserDao;
    
    @Before
    public void setUp() throws IOException {
        userFile = folder.newFile("test_users.txt");
        load(userFile);
        userDao = new FileUserDao(userFile.getAbsolutePath());
    }
    
    @Test
    public void returnCorrectUserCountAtStart() {
        assertEquals(1, userDao.getAll().size());
    }
    
    @Test
    public void returnCreatedUser() {
        User user = userDao.findByUsername("testuser");
        assertTrue(user != null);
    }
    
    @Test
    public void returnNullIfUserNotFound() {
        assertEquals(null, userDao.findByUsername("testaaja"));
    }
    
    @Test
    public void userCanBeCreated() throws Exception {
        User user = new User("test", "password", 0);
        User createdUser = userDao.create(user);
        assertEquals(2, userDao.getAll().size());
        assertEquals(user.getUsername(), createdUser.getUsername());
    }
    
    @After
    public void tearDown() {
        userFile.delete();
    }
    
    private void load(File file) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("testuser" + ";" + "testpassword" + ";" + 0 + "\n");
            writer.close();
        } catch (IOException e) {
        }
    }
}
