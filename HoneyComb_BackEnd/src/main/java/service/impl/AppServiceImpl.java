package service.impl;

import dao.PaperDao;
import dao.UserDao;
import model.Paper;
import model.User;
import service.AppService;

import java.util.List;

public class AppServiceImpl implements AppService {

    private UserDao userDao;
    private PaperDao paperDao;

    public void setPaperDao(PaperDao paperDao) {
        this.paperDao = paperDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * user
     */
    public Integer addUser(User user) {
        return userDao.save(user);
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }

    public void updateUser(User user) {
        userDao.update(user);
    }

    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User UserAuthorize(String name, String pwd) {
        return userDao.UserAuthorize(name, pwd);
    }

    @Override
    public List<User> queryUser(String name) {
        return userDao.queryUser(name);
    }

    /**
     * paper
     */
    @Override
    public Paper getPaperById(Integer id) {
        return paperDao.getPaperById(id);
    }

    @Override
    public List<Paper> queryPaper(String name) {
        return paperDao.queryPapers(name);
    }
}
