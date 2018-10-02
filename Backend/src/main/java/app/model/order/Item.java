package app.model.order;

import app.model.Beer;
import app.model.Container;

import javax.persistence.*;

@Entity
@Table(name = "items")
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

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public Beer getBeerId() {
        return beerId;
    }

    public void setBeerId(Beer beerId) {
        this.beerId = beerId;
    }

    public Container getContainerId() {
        return containerId;
    }

    public void setContainerId(Container containerId) {
        this.containerId = containerId;
    }
}
