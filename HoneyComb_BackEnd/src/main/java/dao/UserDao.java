package dao;

import model.User;

import java.util.List;

public interface UserDao {

	public Integer save(User user);

	public void delete(User user);

	public void update(User user);

	public User getUserById(int id);

	public User queryUser(String name, String pwd);

	public List<User> getAllUsers();

	public User getUserByName(String name);
}