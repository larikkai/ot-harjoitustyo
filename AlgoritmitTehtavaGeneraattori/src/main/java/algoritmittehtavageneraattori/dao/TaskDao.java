package algoritmittehtavageneraattori.dao;

import java.util.List;
import algoritmittehtavageneraattori.domain.Task;
import java.io.File;
/**
 * Interface for tasks
 *
 */       
public interface TaskDao {
    /** Method to add task and create new record to task file
     * 
     * @param task A Task representing task to add
     * @return A task representing added task to pass it to service
     * @throws Exception throw exception
     */
    Task create(Task task) throws Exception;
    /** Method to get all tasks
     * 
     * @return A list representing all tasks
     */
    List<Task> getAll();
    /**
     * Method to load new tasks list
     * @param file where to load new task-list
     */
    void loadNewTasks(File file);
    /**
     * Method to add new tasks to current task list
     * @param file where to add tasks
     */
    void addNewTasks(File file);
    /** Method to set task done
     * 
     * @param id An int representing specific task id
     * @throws Exception throws exception
     */
    void setDone(int id) throws Exception;
}
