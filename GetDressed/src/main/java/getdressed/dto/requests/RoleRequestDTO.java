package getdressed.dto.requests;

import getdressed.domain.Authority;
import getdressed.domain.Role;

import java.util.List;

public record RoleRequestDTO(
         String name,
         List<Authority> authorities,
         boolean isDefault
){
    public Role toRole(){
        return Role.builder()
                .name(name)
                .isDefault(isDefault)
                .authorities(authorities)
                .build();
    }
}
