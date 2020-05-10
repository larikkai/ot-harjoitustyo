package algoritmittehtavageneraattori.dao;

import java.util.List;
import algoritmittehtavageneraattori.domain.User;
/**
 * Interface for users
 */
public interface UserDao {
    /** Method to add user
     * 
     * @param user A user to add
     * @return A user representing added user
     * @throws Exception Throw exception
     */
    User create(User user) throws Exception;
    /** Method to find user with specific username
     * 
     * @param username A string to search for a user
     * @return The user representing search result 
     */
    User findByUsername(String username);
    /** Gets all users
     * 
     * @return A list representing all users
     */
    List<User> getAll();
    /** Save user points to users file
     * 
     * @param user user to update
     * @return updated user
     */
    User savePoints(User user);
    
}
