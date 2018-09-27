package app.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "beers")
public @Data
class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "beer_id")
    private Long beerId;

    private String name;

    //TODO: Que seria esto?
    private String granos;

    private float density;

    //Se podria reemplazar por un Enum
    private String color;

    private float ibu;

    private float graduation;

    private float pricePerLitre;

    private Integer quantity;

    private Boolean visible;
}
