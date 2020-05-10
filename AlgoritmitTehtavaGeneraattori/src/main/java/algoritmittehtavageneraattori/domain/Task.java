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

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getResult() {
        return this.result;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
 
    public int getId() {
        return this.id;
    }

    public boolean getDone() {
        return this.done;
    }

    public void setDone() {
        this.done = true;
    }

    public int getCategoryId() {
        return this.categoryId;
    }
 
    public String getInput() {
        return this.input;
    }

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
