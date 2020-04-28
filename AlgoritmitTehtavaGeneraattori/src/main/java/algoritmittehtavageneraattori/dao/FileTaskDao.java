package algoritmittehtavageneraattori.dao;

import java.util.List;
import java.util.ArrayList;
import algoritmittehtavageneraattori.domain.Task;
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
                if (!line.isEmpty()) {
                    String[] parts = line.split(";");
                    int difficulty = Integer.valueOf(parts[3]);
                    int id = tasks.size() + 1;
                    int categoryId = Integer.valueOf(parts[6]);
                    Task t = new Task(parts[0], parts[1], parts[2], difficulty, id, categoryId, parts[7], users.findByUsername(parts[8]));
                    if (Boolean.valueOf(parts[5])) {
                        t.setDone();
                    }
                    tasks.add(t);
                }
            }
        } catch (Exception e) {
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
        save();
    }
    
    @Override
    public void addNewTasks() {
        load();
        save();
    }
    
    @Override
    public void setDone(int id) throws Exception {
        tasks.stream().filter((t) -> (t.getId() == id)).forEachOrdered((t) -> {
            t.setDone();
        });
        save();
    }

}
