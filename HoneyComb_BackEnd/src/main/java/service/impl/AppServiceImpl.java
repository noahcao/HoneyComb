package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;
import service.AppService;

import java.util.List;

public class AppServiceImpl implements AppService {

    private UserDao userDao;

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

    public User getUserById(int id) {
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

    public User queryUser(String name, String pwd) {
        return userDao.queryUser(name, pwd);
    }

}
