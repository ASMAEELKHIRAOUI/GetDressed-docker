package getdressed.services;

import getdressed.domain.Cart;
import getdressed.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CartService {

    Cart save(Cart cart);

    Cart update(Cart cart, Long id);

    Optional<Cart> getById(Long id);

    Optional<List<Cart>> getAllByUser(Long user);

    void delete(Long id);

    List<Cart> getAll();

}
