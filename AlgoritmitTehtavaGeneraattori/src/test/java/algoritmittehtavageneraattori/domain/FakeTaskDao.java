package algoritmittehtavageneraattori.domain;

import algoritmittehtavageneraattori.dao.TaskDao;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FakeTaskDao implements TaskDao {
    
    List<Task> tasks = new ArrayList<>();

    @Override
    public Task create(Task task) {
        tasks.add(task);
        return task;
    }

    @Override
    public List<Task> getAll() {
        return tasks;
    }

    @Override
    public void loadNewTasks(File file) {
        List<Task> newTasks = new ArrayList<>();
        newTasks.add(new Task(null, null, null, 1,1,1, null, null));
        newTasks.add(new Task(null, null, null, 1,2,1, null, null));
        newTasks.add(new Task(null, null, null, 1,3,1, null, null));
        tasks = newTasks;
    }

    @Override
    public void addNewTasks(File file) {
        tasks.add(new Task(null, null, null, 1,1,1, null, null));
    }

    @Override
    public void setDone(int id) {
        for(Task task : tasks) {
            if(task.getId() == id) {
                task.setDone();
            }
        }
    }
}
