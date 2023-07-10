package br.com.compassuol.pb.challenge.productservice.service;

import br.com.compassuol.pb.challenge.productservice.dto.CategoryRequest;
import br.com.compassuol.pb.challenge.productservice.dto.CategoryResponse;
import br.com.compassuol.pb.challenge.productservice.model.Category;
import br.com.compassuol.pb.challenge.productservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public void createCategory(CategoryRequest categoryRequest) {
         Category category = Category.builder()
                .name(categoryRequest.getName())
                .build();
        categoryRepository.save(category);
        log.info("Role criada com sucesso!");
    }

    public void updateCategory(String id, CategoryRequest categoryRequest) {
         Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
         category.setName(categoryRequest.getName());
        categoryRepository.save(category);
        log.info("Role atualizada com sucesso!");
    }


    public void deleteCategory(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        categoryRepository.delete(category);
        log.info("Product is delete: {}", category.getId());
    }

    public List<CategoryResponse> getAllCategories() {
        List<Category> category = categoryRepository.findAll();
        return category.stream().map(this::mapToCategoryResponse).toList();
    }

    private CategoryResponse mapToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }


    public CategoryResponse getCategoryById(String id) {
        return categoryRepository.findById(id)
                .map(this::mapToCategoryResponse)

                .orElseThrow(() -> new RuntimeException("Category not found"));
    }
}
