package app.entity.order;

import app.entity.Beer;
import app.entity.Container;
import app.entity.order.Order;
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
    private Order orderId;

    @ManyToOne
    @JoinColumn(name = "beer_id")
    private Beer beerId;

    @ManyToOne
    @JoinColumn(name = "container_id")
    private Container containerId;

    private Long cantidad;

    public Float getPrecio() {
        return beerId.getPricePerLitre() * containerId.getQuantity() * cantidad;
    }

}
