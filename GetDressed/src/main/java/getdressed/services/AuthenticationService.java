package getdressed.services;

import getdressed.dto.requests.AuthenticationRequest;
import getdressed.dto.requests.RegisterRequest;
import getdressed.dto.responses.AuthenticationResponse;
import org.springframework.stereotype.Component;

@Component
public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest user);

    AuthenticationResponse authenticate(AuthenticationRequest user);

}
