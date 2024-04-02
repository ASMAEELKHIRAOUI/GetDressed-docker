package getdressed.dto.responses;

import getdressed.domain.Order;
import getdressed.domain.enums.Status;

public record OrderResponseDTO(
        Long id,
        String fullName,
        String email,
        String phone,
        String address,
        String zipcode,
        Status status
) {
    public static OrderResponseDTO fromOrder(Order order){
        return new OrderResponseDTO(
                order.getId(),
                order.getFullName(),
                order.getEmail(),
                order.getPhone(),
                order.getAddress(),
                order.getZipcode(),
                order.getStatus()
        );
    }
}
