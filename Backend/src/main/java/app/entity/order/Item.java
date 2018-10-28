package app.entity.order;

import app.entity.Beer;
import app.entity.Container;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Entity(name="items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private Long itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @ManyToOne
    @JoinColumn(name = "beer_id")
    private Beer beer;

    @ManyToOne
    @JoinColumn(name = "container_id")
    private Container container;

    private Long cantidad;

    public Float getPrecio() {
        return beer.getPricePerLitre() * container.getCapacity() * cantidad;
    }

}
