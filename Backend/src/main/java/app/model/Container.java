package app.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "containers")
public @Data class Container {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "container_id")
    private Long containerId;

    private String name;

    private float height;

    private float width;

    private Integer capacity;

    private String material;

    private Integer quantity;

    private Boolean visible;
}
