package getdressed.controllers;

import getdressed.domain.Product;
import getdressed.domain.Product;
import getdressed.dto.requests.ProductRequestDTO;
import getdressed.dto.responses.ProductResponseDTO;
import getdressed.dto.responses.ProductResponseDTO;
import getdressed.handler.response.ResponseMessage;
import getdressed.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getAll(){
        List<Product> products = productService.getAll();
        if (products.isEmpty()) return ResponseMessage.notFound("No product was found");
        else return ResponseMessage.ok("Success", products.stream().map(ProductResponseDTO::fromProduct).toList());
    }

    @GetMapping("/search/{search}")
    public ResponseEntity search(@PathVariable String search){
        List<Product> products = productService.search(search);
        if (products.isEmpty()) return ResponseMessage.notFound("No product was found");
        else return ResponseMessage.ok("Success", products.stream().map(ProductResponseDTO::fromProduct).toList());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        Product product = productService.getById(id).orElse(null);
        if (product == null) return ResponseMessage.badRequest("Product not found");
        else return ResponseMessage.ok("Success", ProductResponseDTO.fromProduct(product));
    }

    @GetMapping("/get/category/{category}")
    public ResponseEntity getByCategory(@PathVariable String category){
        List<Product> products = productService.getByCategory(category).orElse(null);
        if (products.isEmpty()) return ResponseMessage.badRequest("No product was found");
        else return ResponseMessage.ok("Success", products.stream().map(ProductResponseDTO::fromProduct).toList());
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> save(@RequestBody ProductRequestDTO productToSave){
        Product product = productService.save(productToSave.toProduct());
        if (product == null) return ResponseMessage.badRequest("Bad request");
        else return ResponseMessage.ok("Success", ProductResponseDTO.fromProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@RequestBody ProductRequestDTO requestDTO, @PathVariable Long id){
        Product product = productService.update(requestDTO.toProduct(), id);
        if (product == null) return ResponseMessage.badRequest("Bad request");
        else return ResponseMessage.ok("Success", ProductResponseDTO.fromProduct(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        productService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
