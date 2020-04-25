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
        task = new Task("Lukujen keskiarvo", "Toteuta algoritmi joka ratkaisee lukujen 2,2,2 summan keskiarvon", "2", 1, 1, 1, "222", null);
        task2 = new Task("Lukujen keskiarvo", "Toteuta algoritmi joka ratkaisee lukujen 2,2,2 summan keskiarvon", "2", 1, 1, 1, "222", null);
        task3 = new Task("testTitle", "testDescription", "2", 1, 1, 3, "222", null);
        task4 = new Task("testTitle", "testDescription", "2", 2, 1, 3, "222", null);
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
    public void taskHasCorrectCategoryId(){
        assertTrue(task.getCategoryId() == 1);
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
        Task t1 = new Task(null, null, null, 1,1,1, null, null);
        Task t2 = new Task(null, null, null, 1,1,1, null, null);
        Task t3 = new Task(null, null, null, 1,2,1, null, null);
        assertTrue(t1.equals(t2));
        assertFalse(t1.equals(t3));
    }
    
    @Test
    public void ObjectWithSameCategoryCompareDifficulty(){
        assertTrue(task.compareTo(task3) == -2);
    }
    
    @Test
    public void ObjectWithDifferentCategoryCompare(){
        assertTrue(task3.compareTo(task4) == -1);
    }
    
    @Test public void taskHasUser() {
        User user = new User("student", "test", 0);
        Task task = new Task(null, null, null, 1,2,1, null, user);
        assertTrue(task.getUser().equals(user));
    }
}
