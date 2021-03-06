package algoritmittehtavageneraattori.dao;

import java.util.List;
import java.util.ArrayList;
import algoritmittehtavageneraattori.domain.Task;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

/**
 * repsenting file taskdao by implementing taskdao
 */
public class FileTaskDao implements TaskDao {
    
    private List<Task> tasks;
    private String file;
    private UserDao users;
    
    /** Creates file taskdao
     * 
     * @param file representing file to save data
     * @param users representing users
     */
    public FileTaskDao(String file, UserDao users) {
        tasks = new ArrayList<>();
        this.file = file;
        this.users = users;
        load(new File(file));
    }
    /** Method to read datafile
     * 
     * @param file datafile
     */
    public void load(File file) {
        try {
            Scanner reader = new Scanner(file);
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
    /** Method to save data to file
     * 
     */
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
    /** Method to retrieve all tasks
     * 
     * @return list representing all tasks
     */
    @Override
    public List<Task> getAll() {
        return tasks;
    }
    /** Method to add task to file and task-list
     * 
     * @param task representing task to add
     * @return added task
     */
    @Override
    public Task create(Task task) {
        tasks.add(task);
        save();
        return task;
    }
    /** Method to load new task-list
     * 
     * @param file where to read tasks
     */
    @Override
    public void loadNewTasks(File file) {
        tasks.clear();
        load(file);
        save();
    }
    /** Method to add new tasks to existing list
     * 
     * @param file where to read new tasks
     */
    @Override
    public void addNewTasks(File file) {
        load(file);
        save();
    }
    
    /** Method to set task done
     * 
     * @param id representing tasks id to set done
     * @throws Exception throw if exception
     */
    @Override
    public void setDone(int id) throws Exception {
        tasks.stream().filter((t) -> (t.getId() == id)).forEachOrdered((t) -> {
            t.setDone();
        });
        save();
    }

}
