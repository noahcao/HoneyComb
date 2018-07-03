package dao.impl;

import dao.UserDao;
import model.User;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	public Integer save(User user) {
		return (Integer) getHibernateTemplate().save(user);
	}

	public void delete(User user) {
		getHibernateTemplate().delete(user);
	}

	public void update(User user) {
		getHibernateTemplate().merge(user);
	}

	public User getUserById(int id) {
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) getHibernateTemplate().find(
				"from User as u where u.id=?", id);
		User user = users.size() > 0 ? users.get(0) : null;
		return user;
	}

	public User UserAuthorize(String name, String pwd) {
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) getHibernateTemplate().find(
				"from User as u where u.name=? and u.pwd=?", name, pwd);
		User user = users.size() > 0 ? users.get(0) : null;
		return user;
	}

	public List<User> getAllUsers() {
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) getHibernateTemplate()
				.find("from User");
		return users;
	}

	public User getUserByName(String name) {
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) getHibernateTemplate().find(
				"from User as u where u.name=?", name);
		User user = users.size() > 0 ? users.get(0) : null;
		return user;
	}

	public User getUserByEmail(String email) {
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) getHibernateTemplate().find(
				"from User as u where u.email=?", email);
		User user = users.size() > 0 ? users.get(0) : null;
		return user;
	}

	@Override
	public List<User> queryUser(String name) {
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) getHibernateTemplate().find(
				"from User as u where u.name like ?",
				"%" + name + "%");
		return users;
	}
}
