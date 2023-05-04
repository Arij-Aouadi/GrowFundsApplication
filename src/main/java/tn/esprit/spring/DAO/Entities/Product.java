package tn.esprit.spring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name= "Product")

public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    int idProduct;
    String name;
    String description;
    float price;
    String category;
    String imgUrl;
    float interest;
    @JsonIgnore
    @ManyToMany(mappedBy="product_pack", cascade = CascadeType.ALL)
    private List<Packs> packs;
}
