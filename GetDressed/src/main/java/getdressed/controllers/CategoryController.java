package getdressed.controllers;

import getdressed.domain.Category;
import getdressed.dto.responses.CategoryResponseDTO;
import getdressed.handler.response.ResponseMessage;
import getdressed.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/category")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getAll(){
        List<Category> categories = categoryService.getAll();
        if (categories.isEmpty()) return ResponseMessage.notFound("No category was found");
        else return ResponseMessage.ok("Success", categories.stream().map(CategoryResponseDTO::fromCategory).toList());
    }

}
