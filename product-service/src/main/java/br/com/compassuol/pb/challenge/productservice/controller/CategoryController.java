package br.com.compassuol.pb.challenge.productservice.controller;

import br.com.compassuol.pb.challenge.productservice.dto.CategoryRequest;
import br.com.compassuol.pb.challenge.productservice.dto.CategoryResponse;
import br.com.compassuol.pb.challenge.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    // METHOD: POST PATH: /categories
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createCategory(@RequestBody CategoryRequest categoryRequest){
        categoryService.createCategory(categoryRequest);
        return "Categoria criada com sucesso!";
    }
    // METHOD: GET PATH: /categories
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryResponse> getAllCategories(){
        return categoryService.getAllCategories();
    }

    // METHOD: GET PATH: /categories/{id}
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse getCategoryById(@PathVariable String id){
        return categoryService.getCategoryById(id);
    }
    // METHOD: PUT PATH: /categories/{id}
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateCategory(@PathVariable String id, @RequestBody CategoryRequest categoryRequest){
        categoryService.updateCategory(id, categoryRequest);
        return "Categoria atualizada com sucesso!";
    }
    // METHOD: DELETE PATH: /categories/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCategory(@PathVariable String id){
        categoryService.deleteCategory(id);
        return "Categoria deletada com sucesso!";
    }
}
