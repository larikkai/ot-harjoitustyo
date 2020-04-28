package algoritmittehtavageneraattori.domain;
/**
 * Representing tasks
 */
public class Task implements Comparable<Task> {
    
    private final String title;
    private final String description;
    private final String result;
    private int difficulty;
    private final int id;
    private boolean done;
    private final int categoryId;
    private final String input;
    private final User user;
    /** Creates task with specific title, description, result, difficulty,
     * id, categoryId, input and user.
     * 
     * @param title The task's title
     * @param description The task's description
     * @param result The task's result
     * @param difficulty The task's difficulty
     * @param id The task's id
     * @param categoryId The task's category
     * @param input The task's input
     * @param user The task's user
     */
    public Task(String title, String description, String result, int difficulty, int id, int categoryId, String input, User user) {
        this.title = title;
        this.description = description;
        this.result = result;
        this.difficulty = difficulty;
        this.id = id;
        this.done = false;
        this.categoryId = categoryId;
        this.input = input;
        this.user = user;
    }
    /** Gets task's title
     * 
     * @return A string representing task's title
     */
    public String getTitle() {
        return this.title;
    }
    /** Gets task's description
     * 
     * @return A string representing task's description
     */
    public String getDescription() {
        return this.description;
    }
    /** Gets task's result
     * 
     * @return A string representing task's result
     */
    public String getResult() {
        return this.result;
    }
    /** Gets task's difficulty
     * 
     * @return An int representing task's difficulty
     */
    public int getDifficulty() {
        return this.difficulty;
    }
    /** Sets task's difficulty to specific value
     * 
     * @param difficulty An int representing task's new difficulty
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    /** Gets task's id
     * 
     * @return An int representing task's id
     */
    public int getId() {
        return this.id;
    }
    /** Gets task's done
     * 
     * @return A boolean representing if task's is completed
     */
    public boolean getDone() {
        return this.done;
    }
    /** Sets task's done
     * 
     * Sets task done to true
     */
    public void setDone() {
        this.done = true;
    }
    /** Gets task's categoryId
     * 
     * @return An int representing task's category
     */
    public int getCategoryId() {
        return this.categoryId;
    }
    /** Gets task's input
     * 
     * @return A String representing task's input
     */
    public String getInput() {
        return this.input;
    }
    /** Gets task's
     * 
     * @return A user representing the user added to the task
     */
    public User getUser() {
        return this.user;
    }
    /** Compares whether the object is an instance of the task
     * 
     * @param o An object representing object compared with task object
     * @return A boolean represents whether the object is 
     * an instance of the task
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Task)) {
            return false;
        }
        Task o2 = (Task) o;
        return this.id == o2.id;
    }
    /** Compare tasks so that they can be organized in a list
     * 
     * @param other A task representing task to compare with
     * @return A boolean represents the result of the comparison
     */
    @Override
    public int compareTo(Task other) {
        if (this.categoryId - other.categoryId == 0) {
            return this.difficulty - other.difficulty;
        }
        return this.categoryId - other.categoryId;
    }
    
}
