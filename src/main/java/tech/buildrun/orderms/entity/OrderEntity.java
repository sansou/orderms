package tech.buildrun.orderms.entity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "tb_orders")
public class OrderEntity {


  @MongoId
  private Long orderId;

  @Indexed(name = "cutomerId_index" )
  private Long customerId;
  
  @Field(targetType = FieldType.DECIMAL128)
  private BigDecimal orderAmount;
  
  private List<OrderItem> itens;
  
  public OrderEntity() {
  }

  public Long getOrderId() {
    return orderId;
  }
  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }
  public Long getCustomerId() {
    return customerId;
  }
  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }
  public BigDecimal getOrderAmount() {
    return orderAmount;
  }
  public void setOrderAmount(BigDecimal orderAmount) {
    this.orderAmount = orderAmount;
  }
  public List<OrderItem> getItem() {
    return itens;
  }
  public void setItem(List<OrderItem> itens) {
    this.itens = itens;
  }

  

}
