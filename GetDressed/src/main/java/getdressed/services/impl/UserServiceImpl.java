package getdressed.services.impl;

import getdressed.domain.Role;
import getdressed.domain.User;
import getdressed.repositories.UserRepository;
import getdressed.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAll() {
        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        if (authorities.contains("VIEW_USERS"))return userRepository.findAll();
        return null;
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User assignRole(Long id, Role role) {
        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        if (authorities.contains("ASSIGN_ROLE_TO_USER")){
            User user = getById(id).orElse(null);
            if (user != null && role != null){
                user.setRole(role);
                return userRepository.save(user);
            }
            return null;
        }return null;
    }

    @Override
    public User update(User user) {
            User existingUser = getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
            if (existingUser != null){
                existingUser.setEmail(user.getEmail());
                existingUser.setFirstName(user.getFirstName());
                existingUser.setLastName(user.getLastName());
                return userRepository.save(existingUser);
        }return null;
    }

    @Override
    public void delete(Long id) {
        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        if (authorities.contains("DELETE_USERS") || SecurityContextHolder.getContext().getAuthentication().getName().equals(getById(id).get().getEmail()))getById(id).ifPresent(userRepository::delete);
    }
}
