package algoritmittehtavageneraattori.dao;

import java.util.List;
import java.util.ArrayList;
import algoritmittehtavageneraattori.domain.User;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class FileUserDao implements UserDao {
    private List<User> users;
    private String file;

    public FileUserDao(String file) {
        users = new ArrayList<>();
        this.file = file;
        load();   
    }

    private void load() {
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                int points = Integer.valueOf(parts[2]);
                User u = new User(parts[0], parts[1], points);
                users.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void save() {
        try {
            FileWriter writer = new FileWriter(new File(file));
            for (User user : users) {
                writer.write(user.getUsername() + ";" + user.getPassword() + ";" + user.getPoints() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<User> getAll() {
        return users;
    }
    
    @Override
    public User findByUsername(String username) {
        return users.stream().filter(u->u.getUsername().equals(username)).findFirst().orElse(null);
    }
    
    @Override
    public User create(User user) {
        users.add(user);
        save();
        return user;
    }    
}
