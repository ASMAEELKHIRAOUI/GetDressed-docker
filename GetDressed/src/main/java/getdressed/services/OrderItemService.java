package getdressed.services;

import getdressed.domain.Order;
import getdressed.domain.OrderItem;
import getdressed.domain.enums.Status;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface OrderItemService {

    OrderItem save(OrderItem orderItem);

    OrderItem update(OrderItem orderItem, Long id);

    Optional<OrderItem> getById(Long id);

    Optional<List<OrderItem>> getByOrder(Long order);

    void delete(Long id);

    List<OrderItem> getAll();

}
