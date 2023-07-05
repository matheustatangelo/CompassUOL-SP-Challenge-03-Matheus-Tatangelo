package br.com.compassuol.pb.challenge.msproducts.rest;

import br.com.compassuol.pb.challenge.msproducts.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // listar
    // buscar
    // cadastrar
    // atualizar
    // deletar

}