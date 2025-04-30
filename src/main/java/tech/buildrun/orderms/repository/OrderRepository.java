package tech.buildrun.orderms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import tech.buildrun.orderms.controller.dto.OrderResponse;
import tech.buildrun.orderms.entity.OrderEntity;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {

  Page<OrderResponse> findAllByCustomerId(Long customerId, PageRequest pageRequest);
    // Custom query methods can be defined here if needed
    // For example, findByCustomerId, findByStatus, etc.
    // Example: List<OrderEntity> findByCustomerId(String customerId);
    // Example: List<OrderEntity> findByStatus(String status);

}
