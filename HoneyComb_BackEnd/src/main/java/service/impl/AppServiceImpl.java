package service.impl;

import dao.BookDao;
import dao.OrderDao;
import dao.UserDao;
import model.Book;
import model.Order;
import model.User;
import service.AppService;

import java.util.List;

public class AppServiceImpl implements AppService {

    private BookDao bookDao;
    private OrderDao orderDao;
    private UserDao userDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * book
     */
    public Integer addBook(Book book) {
        return bookDao.save(book);
    }

    public void deleteBook(Book book) {
        bookDao.delete(book);
    }

    public void updateBook(Book book) {
        bookDao.update(book);
    }

    public Book getBookById(int id) {
        return bookDao.getBookById(id);
    }

    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    /**
     * order
     */
    public Integer addOrder(Order order) {
        return orderDao.save(order);
    }

    public void deleteOrder(Order order) {
        orderDao.delete(order);
    }

    public void updateOrder(Order order) {
        orderDao.update(order);
    }

    public Order getOrderById(int id) {
        return orderDao.getOrderById(id);
    }

    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
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
