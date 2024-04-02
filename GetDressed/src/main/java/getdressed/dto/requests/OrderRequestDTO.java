package getdressed.dto.requests;

import getdressed.domain.Order;
import getdressed.domain.enums.Status;

public record OrderRequestDTO(
        String fullName,
        String email,
        String address,
        String phone,
        String zipcode,
        Status status
) {
    public OrderRequestDTO {
        validateParams(fullName, email, address, phone, zipcode, status);
    }

    public Order toOrder() {
        Order.OrderBuilder orderBuilder = new Order().builder()
                .fullName(fullName)
                .email(email)
                .phone(phone)
                .address(address)
                .status(status)
                .zipcode(zipcode);
        return orderBuilder.build();
    }

    private void validateParams(String fullName, String email, String address, String phone, String zipcode, Status status) throws IllegalArgumentException {
        if (fullName == null || fullName.isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be null or empty");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }
        if (phone == null || phone.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }
        if (zipcode == null || zipcode.isEmpty()) {
            throw new IllegalArgumentException("Zipcode cannot be null or empty");
        }
    }
}
