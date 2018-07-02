package dao;

import model.Order;

import java.util.List;

public interface OrderDao {

	public Integer save(Order order);

	public void delete(Order order);

	public void update(Order order);

	public Order getOrderById(int id);

	public List<Order> getAllOrders();

}