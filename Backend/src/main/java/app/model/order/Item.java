package app.model.order;

import app.entity.Beer;
import app.entity.Container;
import lombok.Data;

@Data
public class Item {

    Long idContenedor;
    Long idCerveza;
    Long cantidad;

    /* private Long itemId;

    private Order order;

    private Beer beer;

    private Container container;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }*/
}
