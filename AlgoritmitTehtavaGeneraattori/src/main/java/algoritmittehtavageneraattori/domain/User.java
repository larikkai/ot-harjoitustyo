
package algoritmittehtavageneraattori.domain;

public class User {
    
    private String username;
    
    public User(String username){
        this.username = username;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    @Override
    public boolean equals(Object o) {
        if(!(o instanceof User)) return false;
        
        User other = (User) o;
        return username.equals(other.username);
    }
    
}
