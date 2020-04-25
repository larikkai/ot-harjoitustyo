package algoritmittehtavageneraattori.dao;

import java.util.List;
import java.util.ArrayList;
import algoritmittehtavageneraattori.domain.Task;
import algoritmittehtavageneraattori.domain.User;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class FileTaskDao implements TaskDao {
    
    private List<Task> tasks;
    private String file;
    private UserDao users;
    
    public FileTaskDao(String file, UserDao users) {
        tasks = new ArrayList<>();
        this.file = file;
        this.users = users;
        load();
    }
    
    public void load() {
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if(!line.trim().isEmpty()) {
                    String[] parts = line.split(";");
                    int difficulty = Integer.valueOf(parts[3]);
                    int categoryId = Integer.valueOf(parts[6]);
                    int id = tasks.size() + 1;
                    String input = parts[7];
                    User taskUser = users.findByUsername(parts[8]);
                    //User taskUser = users.getAll().stream().filter(user -> user.getUsername().equals(parts[8])).findFirst().orElse(null);
                    Task t = new Task(parts[0], parts[1], parts[2], difficulty, id, categoryId, input, taskUser);
                    if (Boolean.valueOf(parts[5])) {
                        t.setDone();
                }
                    tasks.add(t);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void save() {
        try {
            FileWriter writer = new FileWriter(new File(file)); 
            for (Task task : tasks) {
                writer.write(task.getTitle() + ";" + task.getDescription() + ";" + task.getResult() + ";"
                        + task.getDifficulty() + ";" + task.getId() + ";" + task.getDone() + ";" + task.getCategoryId()
                        + ";" + task.getInput() + ";" + task.getUser().getUsername() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Task> getAll() {
        return tasks;
    }

    @Override
    public Task create(Task task) {
        tasks.add(task);
        save();
        return task;
    }
    
    @Override
    public void loadNewTasks() {
        tasks.clear();
        load();
    }
    
    @Override
    public void addNewTasks() {
        load();
        save();
    }
    
    @Override
    public void setDone(int id) throws Exception {
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setDone();
            }
        }
        save();
    }

}
