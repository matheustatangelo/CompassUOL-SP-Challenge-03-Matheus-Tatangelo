package br.com.compassuol.pb.challenge.msproducts.repository;

import br.com.compassuol.pb.challenge.msproducts.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
