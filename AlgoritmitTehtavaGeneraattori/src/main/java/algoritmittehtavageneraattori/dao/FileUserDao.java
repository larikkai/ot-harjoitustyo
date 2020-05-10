package algoritmittehtavageneraattori.dao;

import java.util.List;
import java.util.ArrayList;
import algoritmittehtavageneraattori.domain.User;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * representing file userdao by implementing userdao
 */
public class FileUserDao implements UserDao {
    private List<User> users;
    private String file;
    
    /** Creates file userdao
     * 
     * @param file file where to save data
     */
    public FileUserDao(String file) {
        users = new ArrayList<>();
        this.file = file;
        load();   
    }
    /** Method to load data to app from file
     * 
     */
    private void load() {
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (!line.trim().isEmpty()) {
                    String[] parts = line.split(";");
                    int points = Integer.valueOf(parts[2]);
                    User u = new User(parts[0], parts[1], points);
                    users.add(u);
                }
            }
        } catch (FileNotFoundException | NumberFormatException e) {
        }
    }
    /** Method to save data from app to file
     * 
     */
    private void save() {
        try {
            FileWriter writer = new FileWriter(new File(file));
            for (User user : users) {
                writer.write(user.getUsername() + ";" + user.getPassword() + ";" + user.getPoints() + "\n");
            }
            writer.close();
        } catch (IOException e) {
        }
    }
    /** Method to retrieve all users
     * 
     * @return list representing users
     */
    @Override
    public List<User> getAll() {
        return users;
    }
    /** Method to find user by username
     * 
     * @param username to seach
     * @return return user or null
     */
    @Override
    public User findByUsername(String username) {
        return users.stream().filter(u->u.getUsername().equals(username)).findFirst().orElse(null);
    }
    /** Method to add user to users-list and datafile
     * 
     * @param user to add
     * @return added user
     */
    @Override
    public User create(User user) {
        users.add(user);
        save();
        return user;
    }
    /** Method to save users points to datafile
     * 
     * @param user representing user to save
     * @return saved user
     */
    @Override
    public User savePoints(User user) {
        save();
        return user;
    }
}
