package dao.custom.impl;

import java.util.List;

import org.hibernate.Session;

import dao.custom.OrderDetailDAO;
import entity.OrderDetail;
import entity.OrderDetailPK;

public class OrderDetailDAOImpl implements OrderDetailDAO {

  private Session session;

  @Override
  public void setSession(Session session) {
    this.session = session;
  }

  @Override
  public List<OrderDetail> findAll() throws Exception {
    return session.createQuery("FROM OrderDetail", OrderDetail.class).list();
  }

  @Override
  public OrderDetail find(OrderDetailPK key) throws Exception {
    return session.find(OrderDetail.class, key);
  }

  @Override
  public void save(OrderDetail orderDetail) throws Exception {
    session.save(orderDetail);
  }

  @Override
  public void update(OrderDetail orderDetail) throws Exception {
    session.update(orderDetail);
  }

  @Override
  public void delete(OrderDetailPK orderDetailPK) throws Exception {
    session.delete(session.load(OrderDetail.class, orderDetailPK));
  }
}
