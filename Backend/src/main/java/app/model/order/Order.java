package app.model.order;

import app.entity.order.Item;
import app.entity.user.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class Order {
    Long idPedido;
    User cliente;
    Integer estado;
    Boolean pagado;
    List<Item> items;
    Float total;
}
