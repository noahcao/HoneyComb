package service;

import model.Book;
import model.Order;
import model.User;

import java.util.List;

/**
 * @author seniyuting
 * @version 1.0
 */
public interface AppService {

    /**
     * book
     */
    public Integer addBook(Book book);

    public void deleteBook(Book book);

    public void updateBook(Book book);

    public Book getBookById(int id);

    public List<Book> getAllBooks();

    /**
     * order
     */
    public Integer addOrder(Order order);

    public void deleteOrder(Order order);

    public void updateOrder(Order order);

    public Order getOrderById(int id);

    public List<Order> getAllOrders();

    /**
     * user
     */
    public Integer addUser(User user);

    public void deleteUser(User user);

    public void updateUser(User user);

    public User getUserById(int id);

    public User getUserByName(String name);

    public List<User> getAllUsers();

    public User queryUser(String name, String pwd);

}
