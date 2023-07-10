package br.com.compassuol.pb.challenge.productservice.repository;

import br.com.compassuol.pb.challenge.productservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}

