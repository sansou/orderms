package tech.buildrun.orderms.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import tech.buildrun.orderms.controller.dto.OrderResponse;
import tech.buildrun.orderms.entity.OrderEntity;
import tech.buildrun.orderms.entity.OrderItem;
import tech.buildrun.orderms.listener.dto.OrderCreatedEvent;
import tech.buildrun.orderms.repository.OrderRepository;

@Service
public class OrderService {
  
  // não consegui fazer a injeção de dependência do MongoTemplate no construtor, então estou usando o @Autowired
  @Autowired
  private MongoTemplate mongoTemplate;

  private final OrderRepository orderRepository;

  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public void save(OrderCreatedEvent event) {
    var entity = mapToEntity(event);

    orderRepository.save(entity);

  }

  private OrderEntity mapToEntity(OrderCreatedEvent event) {
    var entity = new OrderEntity();
    entity.setOrderId(event.codigoPedido());
    entity.setCustomerId(event.codigoCliente());
    entity.setItem(event.itens().stream()
        .map(i -> new OrderItem(i.produto(), i.quantidade(), i.preco()))
        .toList());
    entity.setTotal(calculateOrderAmount(event));
    return entity;
  }

  private BigDecimal calculateOrderAmount(OrderCreatedEvent event) {
    return event.itens().stream()
        .map(i -> i.preco().multiply(new BigDecimal(i.quantidade())))
        .reduce(BigDecimal::add)
        .orElse(BigDecimal.ZERO);

  }

  public Page<OrderResponse> findAllByCustommerId(Long customerId, PageRequest pageRequest) {
    var orders = orderRepository.findAllByCustomerId(customerId, pageRequest);
    return orders.map(o -> new OrderResponse(o.orderId(), o.customerId(), o.total()));
  }

  public BigDecimal findTotalByCustomerId(Long customerId) {

    var aggregations = newAggregation(
        match(Criteria.where("customerId").is(customerId)),
        group().sum("total").as("total"));

    var result = mongoTemplate.aggregate(aggregations, "tb_orders", Document.class);
    return  new BigDecimal( result.getUniqueMappedResult().get("total").toString());
  }

}