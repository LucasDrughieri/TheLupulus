package app.repository;

import app.entity.Container;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ContainerRepository {

    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession(){
        return _sessionFactory.getCurrentSession();
    }

    public Container save(Container container) {
        getSession().save(container);
        return container;
    }

    public void delete(long id) {
        getSession().delete(getById(id));
    }

    @SuppressWarnings("unchecked")
    public List<Container> getAll() {
        return getSession().createQuery("from containers").list();
    }

    public Container getById(long id) {
        return (Container) getSession().get(Container.class, id);
    }

    public void update(Container container) {
        getSession().update(container);
    }

    public boolean exists(long id){
        Container container = (Container) getSession().get(Container.class,id);
        return container != null;
    }
}
