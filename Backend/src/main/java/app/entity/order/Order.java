package app.entity.order;

import app.entity.user.User;
import app.entity.order.Item;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity(name = "orders")
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date date;

    private Integer status;

    private BigDecimal amount;

    private Boolean visible;

    private Boolean pagado;

    @OneToMany(mappedBy = "order")
    private List<Item> items;
}
