package getdressed.services;

import getdressed.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ProductService {

    Product save(Product product);

    Product update(Product product, Long id);

    Optional<Product> getById(Long id);

    Optional<List<Product>> getByName(String name);

    Optional<List<Product>> getByCategory(String name);

    void delete(Long id);

    List<Product> getAll();

    List<Product> search(String search);

}
