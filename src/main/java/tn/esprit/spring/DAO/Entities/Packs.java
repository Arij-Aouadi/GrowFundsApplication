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
    int idPack ;
    String name;
    String typepack ;
    String descriptionpack;
    Float price ;
    @ManyToMany
    List<Credits> creditsList;
    @JsonIgnore

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Product> product_pack;
    @JsonIgnore

    @ManyToMany(mappedBy = "likedPackages")
    private List<User> likedByUsers;


}
