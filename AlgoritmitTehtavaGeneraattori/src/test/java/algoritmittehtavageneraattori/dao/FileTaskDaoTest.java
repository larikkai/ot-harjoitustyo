package algoritmittehtavageneraattori.dao;

import algoritmittehtavageneraattori.domain.Task;
import algoritmittehtavageneraattori.domain.User;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class FileTaskDaoTest {
    
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    
    File taskFile;
    File userFile;
    File taskFileToAdd;
    UserDao userDao;
    TaskDao taskDao;
    
    @Before
    public void setUp() throws IOException {
        taskFile = folder.newFile("test_tasks.txt");
        userFile = folder.newFile("test_users.txt");
        taskFileToAdd = folder.newFile("load.txt");
        loadUser(userFile);
        userDao = new FileUserDao(userFile.getAbsolutePath());
        loadTasks(taskFile);
        taskDao = new FileTaskDao(taskFile.getAbsolutePath(), userDao);
    }
    
    @After
    public void tearDown() {
        userFile.delete();
        taskFile.delete();
        taskFileToAdd.delete();
    }
    
    @Test
    public void tasksLoadedAtStart() {
        assertEquals(2, taskDao.getAll().size());
    }
    
    @Test
    public void newTaskSavedToFile() throws Exception {
        User user = userDao.findByUsername("testuser");
        Task task = new Task("test", "test", "test", 666, 3, 5, "test", user);
        taskDao.create(task);
        ArrayList<Task> tasks = load();
        assertEquals(3, tasks.size());
    }
    
    @Test
    public void doneTaskSavedToFile() throws Exception {
        User user = userDao.findByUsername("testuser");
        taskDao.setDone(1);
        ArrayList<Task> tasks = load();
        assertEquals(true, tasks.get(0).getDone());
    }
    
    @Test
    public void taskCanBeAddedFromFile() {
        loadTasks(taskFileToAdd);
        taskDao.addNewTasks(taskFileToAdd);
        assertEquals(4, taskDao.getAll().size());
    }
    
    private void loadUser(File file) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("testuser" + ";" + "testpassword" + ";" + 0 + "\n");
            writer.close();
        } catch (IOException e) {
        }
    }
    private void loadTasks(File file) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("title" + ";" + "some description" +";" + "2020" + ";" + 1 + ";" + 1 + ";" +"false" + ";" + 1 + ";" + 1 + ";" + "testuser" + "\n");
            writer.write("title" + ";" + "some description" +";" + "2020" + ";" + 1 + ";" + 2 + ";" +"done" + ";" + 2 + ";" + 1 + ";" + "testuser" + "\n");
            writer.close();
        } catch (IOException e) {
        }
    }
    
    private void loadManyTasks(File file) {
        try {
            FileWriter writer = new FileWriter(file);
            for(int i = 0; i < 101; i++) {
                System.out.println("id "+i);
                writer.write("title" + ";" + "some description" +";" + "2020" + ";" + 1 + ";" + (i+1) + ";" +"false" + ";" + 1 + ";" + 1 + ";" + "testuser" + "\n");
            }
            writer.close();
        } catch (IOException e) {
        }
    }
    
    private ArrayList<Task> load() {
        ArrayList tasks = new ArrayList<>();
        try {
            Scanner reader = new Scanner(taskFile);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (!line.isEmpty()) {
                    String[] parts = line.split(";");
                    int difficulty = Integer.valueOf(parts[3]);
                    int id = tasks.size() + 1;
                    int categoryId = Integer.valueOf(parts[6]);
                    Task t = new Task(parts[0], parts[1], parts[2], difficulty, id, categoryId, parts[7], userDao.findByUsername(parts[8]));
                    if (Boolean.valueOf(parts[5])) {
                        t.setDone();
                    }
                    tasks.add(t);
                }
            }
        } catch (Exception e) {
        }
        return tasks;
    }
    
}
