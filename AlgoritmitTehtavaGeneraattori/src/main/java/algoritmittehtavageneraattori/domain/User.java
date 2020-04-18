
package algoritmittehtavageneraattori.domain;

public class User {
    
    private final String username;
    private final String password;
    private int points;
    
    public User(String username, String password, int points) {
        this.username = username;
        this.password = password;
        this.points = points;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public int getPoints() {
        return this.points;
    }
    
    public void setPoints(int value) {
        this.points += value;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) {
            return false;
        }
        User other = (User) o;
        return username.equals(other.username);
    }
    
}
