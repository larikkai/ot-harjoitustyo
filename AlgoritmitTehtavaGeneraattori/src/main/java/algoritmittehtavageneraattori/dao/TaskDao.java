package algoritmittehtavageneraattori.dao;

import java.util.List;
import algoritmittehtavageneraattori.domain.Task;
        
public interface TaskDao {
    
    Task create(Task task) throws Exception;
    List<Task> getAll();
    
}
