package getdressed.dto.requests;

import getdressed.domain.Cart;
import getdressed.domain.Category;
import getdressed.domain.Product;
import getdressed.domain.User;

public record CartRequestDTO(
        Integer quantity,
        Long product
) {
    public Cart toCart(){
        Cart.CartBuilder cartBuilder = new Cart().builder().quantity(quantity);
        if (product != null){
            cartBuilder.product(Product.builder().id(product).build());
        }
        return cartBuilder.build();
    }
}
