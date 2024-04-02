package getdressed.services.impl;

import getdressed.domain.Category;
import getdressed.domain.Product;
import getdressed.repositories.ProductRepository;
import getdressed.services.CategoryService;
import getdressed.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Override
    public Product save(Product product) {
        product.setCategory(categoryService.getByName(product.getCategory().getName()).orElse(null));
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product, Long id) {
        Product existingProduct = productRepository.getProductById(id).orElse(null);
        if (existingProduct != null){
            existingProduct.setName(product.getName());
            existingProduct.setCategory(categoryService.getByName(product.getCategory().getName()).orElse(null));
            existingProduct.setPrice(product.getPrice());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setImage(product.getImage());
            existingProduct.setPromotion(product.getPromotion());
            existingProduct.setStock(product.getStock());
            return productRepository.save(existingProduct);
        }
        return null;
    }

    @Override
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<List<Product>> getByName(String name) {
        return productRepository.findAllByName(name);
    }

    @Override
    public Optional<List<Product>> getByCategory(String name) {
        Category category = categoryService.getByName(name).orElse(null);
        if (category != null){
            return productRepository.getProductByCategory(category);
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> search(String search) {
        return productRepository.findByNameOrDescriptionOrPriceOrCategoryName(search).orElse(null);
    }
}
