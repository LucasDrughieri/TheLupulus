package app.model;

public class BeerModel {

    private Long id;

    private String name;

    private String granos;

    private float density;

    private String color;

    private float ibu;

    private float graduation;

    private float pricePerLitre;

    private float quantity;

    private Boolean visible;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
