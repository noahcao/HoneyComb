package service;

import model.User;

import java.util.List;

/**
 * @author seniyuting
 * @version 1.0
 */
public interface AppService {

    /**
     * user
     */
    public Integer addUser(User user);

    public void deleteUser(User user);

    public void updateUser(User user);

    public User getUserById(int id);

    public User getUserByEmail(String email);

    public User getUserByName(String name);

    public List<User> getAllUsers();

    public User queryUser(String name, String pwd);

}
