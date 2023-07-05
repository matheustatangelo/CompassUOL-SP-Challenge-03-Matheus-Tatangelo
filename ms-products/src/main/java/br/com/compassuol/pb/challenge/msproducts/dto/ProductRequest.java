package br.com.compassuol.pb.challenge.msproducts.dto;

import br.com.compassuol.pb.challenge.msproducts.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

        private LocalDate date;
        private String name;
        private String description;
        private String imgUrl;
        private BigDecimal price;
        private Set<Category> categories = new HashSet<>();
}
