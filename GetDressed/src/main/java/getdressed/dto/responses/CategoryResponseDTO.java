package getdressed.dto.responses;

import getdressed.domain.Category;

public record CategoryResponseDTO(
        String name
) {
    public static CategoryResponseDTO fromCategory(Category product){
        return new CategoryResponseDTO(product.getName());
    }
}
