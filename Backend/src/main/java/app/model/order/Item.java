package app.model.order;

import app.entity.Beer;
import app.entity.Container;
import lombok.Data;

@Data
public class Item {

    private Long idContenedor;
    private Long idCerveza;
    private Long cantidad;

    /* private Long itemId;

    private Order orderId;

    private Beer beerId;

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
    }*/
}
