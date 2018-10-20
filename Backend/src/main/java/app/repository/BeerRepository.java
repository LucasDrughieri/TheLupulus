package app.repository;

import javax.transaction.Transactional;
import app.entity.Beer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class BeerRepository {

    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession(){
        return _sessionFactory.getCurrentSession();
    }

    public Beer save(Beer beer) {
        getSession().save(beer);
        return beer;
    }

    public void delete(long id) {
        getSession().delete(getById(id));
    }

    @SuppressWarnings("unchecked")
    public List<Beer> getAll() {
        return getSession().createQuery("from beers").list();
    }

    public Beer getById(long id) {
        return (Beer) getSession().get(Beer.class, id);
    }

    public Beer getByName(String name) {
        return (Beer) getSession().get(Beer.class,name);
    }

    public void update(Beer beer) {
        getSession().update(beer);
    }

    public boolean exists(long id){
        Beer client = (Beer) getSession().get(Beer.class,id);
        return client != null;
    }
}
