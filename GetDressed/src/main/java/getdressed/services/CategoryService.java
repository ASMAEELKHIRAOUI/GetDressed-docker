package getdressed.services;

import getdressed.domain.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CategoryService {

    Optional<Category> getByName(String name);

    List<Category> getAll();

}
