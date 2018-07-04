package service;

import model.Paper;
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
    Integer addUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    User getUserById(Integer id);

    User getUserByEmail(String email);

    User getUserByName(String name);

    List<User> getAllUsers();

    User UserAuthorize(String name, String pwd);

    List<User> queryUser(String name);

    /**
     * paper
     */
    Paper getPaperById(Integer id);

    List<Paper> queryPaper(String name);

}
