package br.com.compassuol.pb.challenge.productservice.dto;

import br.com.compassuol.pb.challenge.productservice.model.Category;
import br.com.compassuol.pb.challenge.productservice.model.Product;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductResponse {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String description;
    private String name;
    private String imgUrl;
    private BigDecimal price;
    private Set<Category> categories = new HashSet<>();


    public ProductResponse(Product product) {
        this.id = product.getId();
        this.date = product.getDate();
        this.description = product.getDescription();
        this.name = product.getName();
        this.imgUrl = product.getImgUrl();
        this.price = product.getPrice();
        this.categories = new HashSet<>(product.getCategories());
    }
}
