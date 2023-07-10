package br.com.compassuol.pb.challenge.productservice.dto;

import br.com.compassuol.pb.challenge.productservice.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private Date date;
    private String description;
    private String name;
    private String imgUrl;
    private BigDecimal price;
    private Set<Category> categories;
}
