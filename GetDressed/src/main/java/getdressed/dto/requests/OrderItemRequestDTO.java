package getdressed.dto.requests;

import getdressed.domain.OrderItem;
import getdressed.domain.Order;
import getdressed.domain.Product;

public record OrderItemRequestDTO(
        Double total,
        Integer quantity,
        Long product,
        Long order
) {
    public OrderItem toOrderItem(){
        OrderItem.OrderItemBuilder orderItemBuilder = new OrderItem().builder()
                .total(total)
                .quantity(quantity);
        if (product != null){
            orderItemBuilder.product(Product.builder().id(product).build());
        }
        if (order != null){
            orderItemBuilder.order(Order.builder().id(order).build());
        }
        return orderItemBuilder.build();
    }
}
