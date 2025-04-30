package tech.buildrun.orderms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import tech.buildrun.orderms.entity.OrderEntity;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {
    // Custom query methods can be defined here if needed
    // For example, findByCustomerId, findByStatus, etc.
    // Example: List<OrderEntity> findByCustomerId(String customerId);
    // Example: List<OrderEntity> findByStatus(String status);

}
