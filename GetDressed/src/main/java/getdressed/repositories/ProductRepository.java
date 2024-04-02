package getdressed.repositories;

import getdressed.domain.Category;
import getdressed.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<List<Product>> findAllByName(String name);

    Optional<Product> getProductById(Long id);

    Optional<List<Product>> getProductByCategory(Category category);

    @Query("SELECT p FROM Product p WHERE " +
            "p.name LIKE %:searchTerm% " +
            "OR p.description LIKE %:searchTerm% " +
            "OR CAST(p.price AS string) LIKE %:searchTerm% " +
            "OR p.category.name LIKE %:searchTerm%")
    Optional<List<Product>> findByNameOrDescriptionOrPriceOrCategoryName(String searchTerm);

}
