package getdressed.dto.requests;

import getdressed.domain.User;

public record UserRequestDTO(
        String firstName,
        String lastName,
        String email
) {
    public User toUser(){
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .build();
    }
}
