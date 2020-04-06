
package algoritmittehtavageneraattori.domain;

public class User {
    
    private final String username;
    private final String password;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
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
