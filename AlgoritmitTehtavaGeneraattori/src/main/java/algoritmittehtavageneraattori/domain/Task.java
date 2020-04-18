package algoritmittehtavageneraattori.domain;

public class Task implements Comparable<Task> {
    
    private final String title;
    private final String description;
    private final String result;
    private int difficulty;
    private final int id;
    private boolean done;
    private int gategoryId;
    private final String input;
    private User user;
    
    public Task(String title, String description, String result, int difficulty, int id, int gategoryId, String input, User user) {
        this.title = title;
        this.description = description;
        this.result = result;
        this.difficulty = difficulty;
        this.id = id;
        this.done = false;
        this.gategoryId = gategoryId;
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
    
    public int getGategoryId() {
        return this.gategoryId;
    }
    
    public String getInput() {
        return this.input;
    }
    
    public User getUser() {
        return this.user;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Task)) {
            return false;
        }
        Task o2 = (Task) o;
        return this.id == o2.id;
    }

    @Override
    public int compareTo(Task other) {
        if (this.gategoryId - other.gategoryId == 0) {
            return this.difficulty - other.difficulty;
        }
        return this.gategoryId - other.gategoryId;
    }
    
}
