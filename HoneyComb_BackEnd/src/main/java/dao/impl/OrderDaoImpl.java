package dao.impl;

import dao.OrderDao;
import model.Order;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class OrderDaoImpl extends HibernateDaoSupport implements OrderDao {

	public Integer save(Order order) {
		return (Integer) getHibernateTemplate().save(order);
	}

	public void delete(Order order) {
		getHibernateTemplate().delete(order);
	}

	public void update(Order order) {
		getHibernateTemplate().merge(order);
	}

	public Order getOrderById(int id) {
		@SuppressWarnings("unchecked")
		List<Order> orders = (List<Order>) getHibernateTemplate().find(
				"from Order as o where o.id=?", id);
		Order order = orders.size() > 0 ? orders.get(0) : null;
		return order;
	}

	public List<Order> getAllOrders() {
		@SuppressWarnings("unchecked")
		List<Order> orders = (List<Order>) getHibernateTemplate().find(
				"from Order");
		return orders;
	}

}
