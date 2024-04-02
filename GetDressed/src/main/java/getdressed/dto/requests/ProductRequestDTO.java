package getdressed.dto.requests;

import getdressed.domain.Category;
import getdressed.domain.Product;

public record ProductRequestDTO(
        String name,
        String description,
        Double price,
        Integer stock,
        Integer promotion,
        String image,
        String category
) {
    public ProductRequestDTO {
        validateParams(name, price, stock, promotion, category);
    }

    public Product toProduct() {
        Product.ProductBuilder productBuilder = new Product().builder()
                .name(name)
                .description(description)
                .price(price)
                .stock(stock)
                .promotion(promotion);
        if(category != null) {
            productBuilder.category(Category.builder().name(category).build());
        }
        return productBuilder.build();
    }

    private void validateParams(String name, Double price, Integer stock, Integer promotion, String category) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (price == null || price <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        if (stock == null || stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        if (promotion == null || promotion < 0 || promotion >= 100) {
            throw new IllegalArgumentException("Promotion must be between 0 and 99");
        }
        if (category == null || category.isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
    }
}
