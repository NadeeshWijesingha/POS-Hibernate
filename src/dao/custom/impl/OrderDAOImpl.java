package dao.custom.impl;

import java.util.List;

import org.hibernate.Session;

import dao.custom.OrderDAO;
import entity.Order;

public class OrderDAOImpl implements OrderDAO {

  private Session session;

  @Override
  public void setSession(Session session) {
    this.session = session;
  }

  public String getLastOrderId() throws Exception {
    return (String) session.createNativeQuery("SELECT * FROM `Order` ORDER BY id DESC LIMIT 1").uniqueResult();
  }

  @Override
  public List<Order> findAll() throws Exception {
    return session.createQuery("FROM Order", Order.class).list();
  }

  @Override
  public Order find(String key) throws Exception {
    return session.get(Order.class, key);
  }

  @Override
  public void save(Order order) throws Exception {
    session.save(order);
  }

  @Override
  public void update(Order order) throws Exception {
    session.update(order);
  }

  @Override
  public void delete(String key) throws Exception {
    session.delete(session.load(Order.class, key));
  }
}
