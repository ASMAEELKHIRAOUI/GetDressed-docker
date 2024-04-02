package getdressed.services.impl;

import getdressed.domain.Order;
import getdressed.repositories.OrderRepository;
import getdressed.services.CartService;
import getdressed.services.OrderItemService;
import getdressed.services.ProductService;
import getdressed.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    @Test
    public void update_ExistingOrder_OrderUpdated() {
        // Arrange
        OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
        CartService cartService = Mockito.mock(CartService.class);
        OrderItemService orderItemService = Mockito.mock(OrderItemService.class);
        UserService userService = Mockito.mock(UserService.class);
        ProductService productService = Mockito.mock(ProductService.class);

        OrderServiceImpl orderService = new OrderServiceImpl(orderRepository, cartService, orderItemService, userService, productService);

        Order existingOrder = new Order();
        existingOrder.setId(1L);
        // Set other properties of the existing order

        Order updatedOrder = new Order();
        updatedOrder.setId(1L);
        updatedOrder.setFullName("Updated John Doe");
        // Set other properties of the updated order

        when(orderRepository.getOrderById(existingOrder.getId())).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(existingOrder)).thenReturn(updatedOrder);

        // Act
        Order result = orderService.update(updatedOrder, existingOrder.getId());

        // Assert
        assertEquals(updatedOrder.getId(), result.getId());
        assertEquals(updatedOrder.getFullName(), result.getFullName());
        // Assert other properties of the updated order
    }

    @Test
    public void update_NonexistentOrder_NullReturned() {
        // Arrange
        OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
        CartService cartService = Mockito.mock(CartService.class);
        OrderItemService orderItemService = Mockito.mock(OrderItemService.class);
        UserService userService = Mockito.mock(UserService.class);
        ProductService productService = Mockito.mock(ProductService.class);

        OrderServiceImpl orderService = new OrderServiceImpl(orderRepository, cartService, orderItemService, userService, productService);

        Order nonExistentOrder = new Order();
        nonExistentOrder.setId(1L);
        // Set other properties of the non-existent order

        when(orderRepository.getOrderById(nonExistentOrder.getId())).thenReturn(Optional.empty());

        // Act
        Order result = orderService.update(nonExistentOrder, nonExistentOrder.getId());

        // Assert
        assertNull(result);
    }
}