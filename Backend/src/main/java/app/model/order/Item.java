package app.model.order;

import app.model.Beer;
import app.model.Container;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "items")
public @Data
class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
