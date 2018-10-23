package app.entity.order;

import app.entity.Beer;
import app.entity.Container;
import lombok.*;

import javax.persistence.*;

@Data
@Entity(name="items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "beer_id")
    private Beer beer;

    @ManyToOne
    @JoinColumn(name = "container_id")
    private Container container;

    private Long cantidad;

    public Float getPrecio() {
        return beer.getPricePerLitre() * container.getQuantity() * cantidad;
    }

}
