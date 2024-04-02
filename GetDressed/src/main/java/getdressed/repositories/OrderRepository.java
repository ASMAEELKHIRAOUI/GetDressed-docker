package getdressed.repositories;

import getdressed.domain.Order;
import getdressed.domain.User;
import getdressed.domain.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> getOrderById(Long id);

    @Query("SELECT o FROM Order o WHERE o.fullName LIKE concat('%', :searchTerm, '%')" +
                    " OR o.zipcode LIKE concat('%', :searchTerm, '%')" +
                    " OR o.phone LIKE concat('%', :searchTerm, '%')" +
                    " OR o.email LIKE concat('%', :searchTerm, '%')")
    Optional<List<Order>> findByFullNameOrZipcodeOrPhoneOrEmail(String searchTerm);

    Optional<List<Order>> findAllByStatus(Status status);

    Optional<List<Order>> getOrderByUser(User user);

}
