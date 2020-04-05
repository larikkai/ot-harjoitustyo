package algoritmittehtavageneraattori.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TaskTest {
    
    Task task;
    Task task2;
    Task task3;
    Task task4;
    
    @Before
    public void setUp() {
        task = new Task("Lukujen keskiarvo", "Toteuta algoritmi joka ratkaisee lukujen 2,2,2 summan keskiarvon", "2", 1, 1, 1, "222");
        task2 = new Task("Lukujen keskiarvo", "Toteuta algoritmi joka ratkaisee lukujen 2,2,2 summan keskiarvon", "2", 1, 1, 1, "222");
        task3 = new Task("testTitle", "testDescription", "2", 1, 1, 3, "222");
        task4 = new Task("testTitle", "testDescription", "2", 2, 1, 3, "222");
    }
    
    @Test
    public void taskHasCorrectTitle(){
        assertEquals("Lukujen keskiarvo", task.getTitle());
    }
    
    @Test
    public void taskHasCorrectDescription(){
        assertEquals("Toteuta algoritmi joka ratkaisee lukujen 2,2,2 summan keskiarvon", task.getDescription());
    }
    
    @Test
    public void taskHasCorrectResult(){
        assertTrue(task.getResult().equals("2"));
    }
    
    @Test 
    public void taskHasCorrectDifficulty(){
        assertTrue(task.getDifficulty() == 1);
    }
    
    @Test
    public void taskHasCorrectId(){
        assertTrue(task.getId() == 1);
    }
    
    @Test public void taskHasCorrectInput(){
        assertEquals("222", task.getInput());
    }
    
    @Test 
    public void taskDifficultyCanChange(){
        task.setDifficulty(2);
        assertTrue(task.getDifficulty() == 2);
    }
    
    @Test
    public void taskHasCorrectGategoryId(){
        assertTrue(task.getGategoryId() == 1);
    }
    
    @Test
    public void taskIsNotDoneAtStart(){
        assertTrue(task.getDone() == false);
    }
    @Test
    public void taskIsDoneWhenCompleted(){
        task.setDone();
        assertTrue(task.getDone() == true);
    }
    
    @Test
    public void nonEqualObjectReturnFalse(){
        Object o = new Object();
        assertFalse(task.equals(o));
    }
    
    @Test
    public void returnTrueWhenSameId() {
        Task t1 = new Task(null, null, null, 1,1,1, null);
        Task t2 = new Task(null, null, null, 1,1,1, null);
        assertTrue(t1.equals(t2));
    }
    
    @Test
    public void ObjectWithSameGategoryCompareDifficulty(){
        assertTrue(task.compareTo(task3) == -2);
    }
    
    @Test
    public void ObjectWithDifferentGategoryCompare(){
        assertTrue(task3.compareTo(task4) == -1);
    }
}
