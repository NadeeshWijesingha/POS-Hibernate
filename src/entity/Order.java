package entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "orderDetails")
@Table(name = "`Order`")
@Entity
public class Order implements SuperEntity {

  @Id
  private String id;
  private Date date;
  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
  @JoinColumn(name = "customerId", referencedColumnName = "id", nullable = false)
  private Customer customer;
  @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
  private List<OrderDetail> orderDetails;

  public Order(String id, Date date, Customer customer, List<OrderDetail> orderDetails) {
    this.id = id;
    this.date = date;
    this.customer = customer;
    for (OrderDetail orderDetail : orderDetails) {
      orderDetail.setOrder(this);
    }
    this.orderDetails = orderDetails;
  }

  public void setOrderDetails(List<OrderDetail> orderDetails) {
    for (OrderDetail orderDetail : orderDetails) {
      orderDetail.setOrder(this);
    }
    this.orderDetails = orderDetails;
  }

  public void addOrderDetail(OrderDetail orderDetail){
    orderDetail.setOrder(this);
    this.getOrderDetails().add(orderDetail);
  }
}
