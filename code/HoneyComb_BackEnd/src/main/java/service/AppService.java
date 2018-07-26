package service;

import model.Author;
import model.Paper;
import model.PaperSmall;
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

    List<PaperSmall> getPaperSmallByTitle(String name);

    List<Paper> getPaperByTitle(String title);

    List<PaperSmall> getPaperSmallLikeTitle(String title);

    Author getAuthorById(Long id);

}
