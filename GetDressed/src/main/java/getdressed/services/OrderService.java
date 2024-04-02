package getdressed.services;

import getdressed.domain.Order;
import getdressed.domain.User;
import getdressed.domain.enums.Status;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface OrderService {
    Order save(Order order);

    Order update(Order order, Long id);

    Optional<Order> getById(Long id);

    Optional<List<Order>> getByFullNameOrZipcodeOrPhoneOrEmail(String searchTerm);

    Optional<List<Order>> getByStatus(Status status);

    void delete(Long id);

    List<Order> getAll();

    List<Order> getAllByUser();
}
