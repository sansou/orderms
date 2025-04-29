package tech.buildrun.orderms.entity;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;


public class OrderItem {

  private String product;

  private int quantity;

  @Field(targetType = FieldType.DECIMAL128)
  private BigDecimal price;

  
  public OrderItem() {
  }

  public String getProduct() {
    return product;
  }
  public int getQuantity() {
    return quantity;
  }
  public BigDecimal getPrice() {
    return price;
  }
  public void setProduct(String product) {
    this.product = product;
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  public void setPrice(BigDecimal price) {
    this.price = price;
  }
  

}
