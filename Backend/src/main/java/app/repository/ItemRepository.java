package app.repository;

import app.entity.order.Item;
import app.entity.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ItemRepository {
    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession(){
        return _sessionFactory.getCurrentSession();
    }

    public Item save(Item user) {
        getSession().save(user);
        return user;
    }

    public void delete(long id) {
        getSession().delete(getById(id));
    }

    @SuppressWarnings("unchecked")
    public List<Item> getAll() {
        return getSession().createQuery("from items").list();
    }

    public Item getById(long id) {
        return (Item) getSession().get(Item.class, id);
    }
}
