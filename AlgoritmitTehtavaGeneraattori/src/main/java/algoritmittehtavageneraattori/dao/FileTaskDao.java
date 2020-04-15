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
    
    public FileTaskDao(String file) {
        tasks = new ArrayList<>();
        this.file = file;
        load();
    }
    
    public void load() {
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                int difficulty = Integer.valueOf(parts[3]);
                int gategoryId = Integer.valueOf(parts[6]);
                int id = tasks.size() + 1;
                String input = parts[7];
                Task t = new Task(parts[0], parts[1], parts[2], difficulty, id, gategoryId, input);
                if (Boolean.valueOf(parts[5])) {
                    t.setDone();
                }
                tasks.add(t);
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
                        + task.getDifficulty() + ";" + task.getId() + ";" + task.getDone() + ";" + task.getGategoryId()
                        + ";" + task.getInput() + "\n");
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
