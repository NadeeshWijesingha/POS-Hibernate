package dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import dao.CrudUtil;
import dao.custom.ItemDAO;
import entity.Item;

public class ItemDAOImpl implements ItemDAO {

  @Override
  public void setSession(Session session) {

  }

  public String getLastItemCode() throws Exception {
    ResultSet rst = CrudUtil.execute("SELECT * FROM Item ORDER BY code DESC LIMIT 1");
    if (!rst.next()) {
      return null;
    } else {
      return rst.getString(1);
    }
  }

  @Override
  public List<Item> findAll() throws Exception {
    ResultSet rst = CrudUtil.execute("SELECT * FROM Item");
    List<Item> items = new ArrayList<>();
    while (rst.next()) {
      items.add(new Item(rst.getString(1),
          rst.getString(2),
          rst.getBigDecimal(3),
          rst.getInt(4)));
    }
    return items;
  }

  @Override
  public Item find(String key) throws Exception {
    ResultSet rst = CrudUtil.execute("SELECT * FROM Item WHERE code=?", key);
    if (rst.next()) {
      return new Item(rst.getString(1),
          rst.getString(2),
          rst.getBigDecimal(3),
          rst.getInt(4));
    }
    return null;
  }

  @Override
  public void save(Item item) throws Exception {
    return CrudUtil.execute("INSERT INTO Item VALUES (?,?,?,?)", item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand());
  }

  @Override
  public void update(Item item) throws Exception {
    return CrudUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?", item.getDescription(), item.getUnitPrice(), item.getQtyOnHand(), item.getCode());
  }

  @Override
  public void delete(String key) throws Exception {
    return CrudUtil.execute("DELETE FROM Item WHERE code=?", key);
  }
}
