package tn.esprit.spring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name= "Packs")

public class Packs implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    int idPacks ;
    String name;
    String description ;
    float price ;
    String category;
    String imgUrl;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Product> product_pack;
    @ManyToMany(mappedBy = "likedPackages")
    private List<User> likedByUsers;


}
