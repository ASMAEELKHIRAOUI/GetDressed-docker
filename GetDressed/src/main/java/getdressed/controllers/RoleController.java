package getdressed.controllers;

import getdressed.domain.Role;
import getdressed.dto.requests.RoleRequestDTO;
import getdressed.dto.responses.RoleResponseDTO;
import getdressed.handler.response.ResponseMessage;
import getdressed.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity getAll(){
        List<Role> roles = roleService.getAll();
        if (roles.isEmpty()) return ResponseMessage.notFound("No role was found");
        else return ResponseMessage.ok("Success", roles.stream().map(RoleResponseDTO::fromRole).toList());
    }

    @PostMapping
    public ResponseEntity<RoleResponseDTO> save(@RequestBody RoleRequestDTO roleToSave){
        Role role = roleService.save(roleToSave.toRole());
        if (role == null) return ResponseMessage.badRequest("Bad request");
        else return ResponseMessage.ok("Success", RoleResponseDTO.fromRole(role));
    }

    @PutMapping("/grant_authorities/{id}")
    public ResponseEntity<RoleResponseDTO> grantAuthorities(@RequestBody RoleRequestDTO rolesAuthorities, @PathVariable Long id){
        Role role = roleService.grantAuthorities(rolesAuthorities.toRole().getAuthorities(), id);
        if (role == null) return ResponseMessage.badRequest("Bad request");
        else return ResponseMessage.ok("Success", RoleResponseDTO.fromRole(role));
    }

    @PutMapping("/revoke_authorities/{id}")
    public ResponseEntity<RoleResponseDTO> revokeAuthorities(@RequestBody RoleRequestDTO rolesAuthorities, @PathVariable Long id){
        Role role = roleService.revokeAuthorities(rolesAuthorities.toRole().getAuthorities(), id);
        if (role == null) return ResponseMessage.badRequest("Bad request");
        else return ResponseMessage.ok("Success", RoleResponseDTO.fromRole(role));
    }

}
