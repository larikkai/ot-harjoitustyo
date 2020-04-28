package algoritmittehtavageneraattori.domain;
/**
 * Represents users
 */
public class User {
    
    private final String username;
    private final String password;
    private int points;
    /** Creates a user with specific username, password and points
     * 
     * @param username The user's username
     * @param password The user's password
     * @param points The user's points
     */
    public User(String username, String password, int points) {
        this.username = username;
        this.password = password;
        this.points = points;
    }
    /** Gets user's username
     * 
     * @return A string representing the user's username
     */
    public String getUsername() {
        return this.username;
    }
    /** Gets user's password
     * 
     * @return A string representing user's password
     */
    public String getPassword() {
        return this.password;
    }
    /** Gets users's points
     * 
     * @return An int representing user's points
     */
    public int getPoints() {
        return this.points;
    }
    /** Sets user's points by increasing points by specific value
     * 
     * @param value An int representing points to add to user's 
     * existing points
     */
    public void setPoints(int value) {
        this.points += value;
    }
    /** Compares whether the object is an instance of the user
     * 
     * @param o An object representing object compared with user object
     * @return A boolean represents whether the object is 
     * an instance of the user
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) {
            return false;
        }
        User other = (User) o;
        return username.equals(other.username);
    }
    
}
