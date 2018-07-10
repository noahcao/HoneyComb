package service;

import model.Paper;
import model.User;

import java.util.List;

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
    Paper getPaperById(java.lang.Long id);

    List<Paper> queryPaper(String name);

}
