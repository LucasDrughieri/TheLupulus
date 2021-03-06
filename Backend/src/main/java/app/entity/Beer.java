package app.entity;

import javax.persistence.*;

@Entity(name="beers")
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "beer_id")
    private Long id;

    private String name;

    //TODO: Que seria esto?
    private String granos;

    private float density;

    //Se podria reemplazar por un Enum
    private String color;

    private float ibu;

    private float graduation;

    @Column(name = "price_per_litre")
    private float pricePerLitre;

    private float quantity;

    private Boolean visible;

    public Long getBeerId() {
        return id;
    }

    public void setBeerId(Long beerId) {
        this.id = beerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGranos() {
        return granos;
    }

    public void setGranos(String granos) {
        this.granos = granos;
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getIbu() {
        return ibu;
    }

    public void setIbu(float ibu) {
        this.ibu = ibu;
    }

    public float getGraduation() {
        return graduation;
    }

    public void setGraduation(float graduation) {
        this.graduation = graduation;
    }

    public float getPricePerLitre() {
        return pricePerLitre;
    }

    public void setPricePerLitre(float pricePerLitre) {
        this.pricePerLitre = pricePerLitre;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public void addStock(float quantityToAdd) {
        quantity = quantity + quantityToAdd;
    }
}
