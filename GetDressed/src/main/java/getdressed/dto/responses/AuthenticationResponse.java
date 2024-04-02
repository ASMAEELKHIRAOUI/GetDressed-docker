package getdressed.dto.responses;

import getdressed.domain.Authority;
import getdressed.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse{

    private String token;
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private List<String> authorities;

}
