package tn.esprit.spring.DAO.Entities;

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
@Table(name= "Packs")

public class Packs implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    int idPack ;
    String name;
    String typepack ;
    String descriptionpack;
    Float price ;
    @ManyToMany(mappedBy = "packsList")
    List<Credits> creditsList;
    @ManyToMany
    List<Product> productList;


}
