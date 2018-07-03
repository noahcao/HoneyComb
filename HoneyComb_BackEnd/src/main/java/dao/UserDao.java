package dao;

import model.User;

import java.util.List;

public interface UserDao {

	Integer save(User user);

	void delete(User user);

	void update(User user);

	User getUserById(int id);

	User UserAuthorize(String name, String pwd);

	List<User> getAllUsers();

	User getUserByName(String name);

	User getUserByEmail(String email);

	List<User> queryUser(String name);
}