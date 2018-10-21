package app.repository;

import app.entity.order.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class OrderRepository {
    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession(){
        return _sessionFactory.getCurrentSession();
    }

    public Order save(Order order) {
        getSession().save(order);
        return order;
    }


    @SuppressWarnings("unchecked")
    public List<Order> getAll() {
        return getSession().createQuery("from orders").list();
    }

    @SuppressWarnings("unchecked")
    public List<Order> getAll(Long userId) {
        return getSession().createQuery("from orders order where order.userId.id = " + userId)
                .list();
    }

    public Order getById(long id) {
        return (Order) getSession().get(Order.class, id);
    }

    public Order getByName(String name) {
        return (Order) getSession().get(Order.class,name);
    }

    public void update(Order order) {
        getSession().update(order);
    }

    public boolean exists(long id){
        Order client = (Order) getSession().get(Order.class,id);
        return client != null;
    }

    public void delete(long id) {
        getSession().delete(getById(id));
    }
}
