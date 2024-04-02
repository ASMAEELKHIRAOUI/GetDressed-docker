package getdressed.controllers;

import getdressed.domain.Role;
import getdressed.domain.User;
import getdressed.dto.requests.RoleRequestDTO;
import getdressed.dto.requests.UserRequestDTO;
import getdressed.dto.responses.UserResponseDTO;
import getdressed.handler.response.ResponseMessage;
import getdressed.services.RoleService;
import getdressed.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @PutMapping("/assign_role/{id}")
    public ResponseEntity<UserResponseDTO> assignRole(@RequestBody RoleRequestDTO request, @PathVariable Long id){
        Role role = roleService.getByName(request.name()).orElse(null);
        User user = userService.assignRole(id, role);
        if (user == null && role == null) return ResponseMessage.badRequest("Bad request");
        else return ResponseMessage.ok("Success", UserResponseDTO.fromUser(user));
    }

    @PutMapping
    public ResponseEntity<UserResponseDTO> update(@RequestBody UserRequestDTO request){
        User user = userService.update(request.toUser());
        if (user == null) return ResponseMessage.badRequest("Bad request");
        else return ResponseMessage.ok("Success", UserResponseDTO.fromUser(user));
    }

}
