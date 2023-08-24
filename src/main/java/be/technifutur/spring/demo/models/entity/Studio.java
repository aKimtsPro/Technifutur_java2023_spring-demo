package be.technifutur.spring.demo.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Studio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studio_id", nullable = false)
    private Long id;

    @Column(name = "studio_name", nullable = false)
    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST})// (optional = false)
    @JoinColumn(name = "studio_address_id", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "studio")
    private List<Game> games;

}
