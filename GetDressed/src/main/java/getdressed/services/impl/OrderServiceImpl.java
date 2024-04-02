package getdressed.services.impl;

import getdressed.domain.*;
import getdressed.domain.enums.Status;
import getdressed.repositories.OrderRepository;
import getdressed.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final OrderItemService orderItemService;
    private final UserService userService;
    private final ProductService productService;

    @Override
    public Order save(Order order) {
        order.setStatus(Status.PENDING);
        order.setUser(userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null));
        Order orderToSave = orderRepository.save(order);
        List<Cart> carts = cartService.getAllByUser(userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null).getId()).orElse(null);
        if (carts != null){
            for (Cart cart: carts) {
                OrderItem orderItem = new OrderItem();
                orderItem.setProduct(cart.getProduct());
                orderItem.setQuantity(cart.getQuantity());
                Double total = cart.getQuantity() * (cart.getProduct().getPrice() - (cart.getProduct().getPrice() * (cart.getProduct().getPromotion() / 100)));
                orderItem.setTotal(total);
                orderItem.setOrder(orderToSave);
                orderItemService.save(orderItem);
                Product product = new Product().builder()
                        .stock(cart.getProduct().getStock() - cart.getQuantity())
                        .name(cart.getProduct().getName())
                        .description(cart.getProduct().getDescription())
                        .price(cart.getProduct().getPrice())
                        .promotion(cart.getProduct().getPromotion())
                        .category(cart.getProduct().getCategory())
                        .build();
                productService.update(product, cart.getProduct().getId());
                cartService.delete(cart.getId());
            }
        }
        return orderToSave;
    }

    @Override
    public Order update(Order order, Long id) {
        Order existingOrder = orderRepository.getOrderById(id).orElse(null);
        if (existingOrder != null){
            existingOrder.setFullName(order.getFullName());
            existingOrder.setEmail(order.getEmail());
            existingOrder.setPhone(order.getPhone());
            existingOrder.setAddress(order.getAddress());
            existingOrder.setZipcode(order.getZipcode());
            existingOrder.setAddress(order.getAddress());
            existingOrder.setStatus(order.getStatus());
            return orderRepository.save(existingOrder);
        }
        return null;
    }

    @Override
    public Optional<Order> getById(Long id) {
        return orderRepository.getOrderById(id);
    }

    @Override
    public Optional<List<Order>> getByFullNameOrZipcodeOrPhoneOrEmail(String searchTerm) {
        List<Order> orders = orderRepository.findByFullNameOrZipcodeOrPhoneOrEmail(searchTerm).orElse(null);
        return Optional.ofNullable(orders);
    }

    @Override
    public Optional<List<Order>> getByStatus(Status status) {
        return orderRepository.findAllByStatus(status);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAllByUser() {
        return orderRepository.getOrderByUser(userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null)).orElse(null);
    }
}
