package getdressed.services.impl;

import getdressed.domain.Category;
import getdressed.domain.Product;
import getdressed.repositories.ProductRepository;
import getdressed.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Test
    public void save_ValidProduct_ProductSaved() {
        // Arrange
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        CategoryService categoryService = Mockito.mock(CategoryService.class);

        ProductServiceImpl productService = new ProductServiceImpl(productRepository, categoryService);

        // Create a Category object
        Category category = new Category(50L, "TestCategory");

        Product product = new Product(50L, "Test Product", "Description", 10.0, 100, 0, "image.jpg", category);

        // Mock categoryService.getByName() to return the category
        when(categoryService.getByName("TestCategory")).thenReturn(Optional.of(category));

        // Mock productRepository.save() to return the saved product
        when(productRepository.save(product)).thenReturn(product);

        // Act
        Product savedProduct = productService.save(product);

        // Assert
        assertEquals(product, savedProduct);
        verify(categoryService, times(1)).getByName("TestCategory");
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void update_ExistingProduct_ProductUpdated() {
        // Arrange
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        CategoryService categoryService = Mockito.mock(CategoryService.class);

        ProductServiceImpl productService = new ProductServiceImpl(productRepository, categoryService);

        Long productId = 1L;
        Product existingProduct = new Product(1L, "Existing Product", "Existing Description", 20.0, 50, 0, "existing.jpg", new Category(1L, "ExistingCategory"));
        Product updatedProduct = new Product(1L, "Updated Product", "Updated Description", 30.0, 75, 10, "updated.jpg", new Category(1L, "UpdatedCategory"));

        // Mock productRepository.getProductById() to return the existing product
        when(productRepository.getProductById(productId)).thenReturn(Optional.of(existingProduct));

        // Mock categoryService.getByName() to return a category for the updated product
        Category updatedCategory = new Category(1L, "UpdatedCategory");
        when(categoryService.getByName("UpdatedCategory")).thenReturn(Optional.of(updatedCategory));

        // Mock productRepository.save() to return the updated product
        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

        // Act
        Product result = productService.update(updatedProduct, productId);

        // Assert
        assertEquals(updatedProduct, result);
        verify(productRepository, times(1)).getProductById(productId);
        verify(categoryService, times(1)).getByName("UpdatedCategory");
        verify(productRepository, times(1)).save(existingProduct);
    }

    @Test
    public void update_NonexistentProduct_NullReturned() {
        // Arrange
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        CategoryService categoryService = Mockito.mock(CategoryService.class);

        ProductServiceImpl productService = new ProductServiceImpl(productRepository, categoryService);

        Long productId = 1L;
        Product updatedProduct = new Product(1L, "Updated Product", "Updated Description", 30.0, 75, 10, "updated.jpg", new Category(1L, "UpdatedCategory"));

        // Mock productRepository.getProductById() to return an empty optional
        when(productRepository.getProductById(productId)).thenReturn(Optional.empty());

        // Act
        Product result = productService.update(updatedProduct, productId);

        // Assert
        assertNull(result);
        verify(productRepository, times(1)).getProductById(productId);
        verify(categoryService, never()).getByName(anyString());
        verify(productRepository, never()).save(any());
    }
}