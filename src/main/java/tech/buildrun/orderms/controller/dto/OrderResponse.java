package tech.buildrun.orderms.controller.dto;

import java.math.BigDecimal;

import tech.buildrun.orderms.entity.OrderEntity;

public record OrderResponse(
  Long orderId,
  Long customerId,
  BigDecimal total ) {

  public static OrderResponse fronEntity(OrderEntity orderEntity) {
    return new OrderResponse(
      orderEntity.getOrderId(),
      orderEntity.getCustomerId(),
      orderEntity.getTotal()
    );
  }

}
