package app.repository;

import javax.transaction.Transactional;
import app.entity.Beer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class BeerRepository {

    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession(){
        return _sessionFactory.getCurrentSession();
    }

    public void save(app.entity.Beer item){

        getSession().save(item);
        return;
    }

    public void delete(app.entity.Beer item) {
        getSession().delete(item);
        return;
    }

    /*
    @Override
    public void update(Beer item) {

    }

    @Override
    public void getById(Beer item) {

    }

    @Override
    public void getAll(Beer item) {

    }
    */
}
