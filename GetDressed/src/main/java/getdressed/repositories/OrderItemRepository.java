package getdressed.repositories;

import getdressed.domain.Order;
import getdressed.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    Optional<List<OrderItem>> findAllByOrder(Order order);

    Optional<OrderItem> getOrderItemById(Long id);

}
