package getdressed.services.impl;

import getdressed.domain.Cart;
import getdressed.domain.User;
import getdressed.repositories.CartRepository;
import getdressed.services.CartService;
import getdressed.services.ProductService;
import getdressed.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductService productService;

    @Override
    public Cart save(Cart cart) {
        Cart existingCart = cartRepository.getCartByUserAndProduct(userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null),productService.getById(cart.getProduct().getId()).orElse(null)).orElse(null);
        if (existingCart != null){
            existingCart.setQuantity(existingCart.getQuantity()+cart.getQuantity());
            return cartRepository.save(existingCart);
        }
        else {
            cart.setUser(userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null));
            cart.setProduct(productService.getById(cart.getProduct().getId()).orElse(null));
            return cartRepository.save(cart);
        }
    }

    @Override
    public Cart update(Cart cart, Long id) {
        Cart existingCart = cartRepository.getCartById(id);
        if (existingCart != null){
            existingCart.setQuantity(cart.getQuantity());
            existingCart.setProduct(existingCart.getProduct());
            existingCart.setUser(existingCart.getUser());
            return cartRepository.save(existingCart);
        }
        return null;
    }

    @Override
    public Optional<Cart> getById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public Optional<List<Cart>> getAllByUser(Long user) {
        return cartRepository.findAllByUser(userService.getById(user).orElse(null));
    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }
}
