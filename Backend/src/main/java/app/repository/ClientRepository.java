package app.repository;

import app.entity.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ClientRepository {

    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession(){
        return _sessionFactory.getCurrentSession();
    }

    public Client save(Client client) {
        getSession().save(client);
        return client;
    }

    public void delete(long id) {
        getSession().delete(id);
    }

    @SuppressWarnings("unchecked")
    public List<Client> getAll() {
        return getSession().createQuery("from clients").list();
    }

    public Client getByCuit(long cuit) {
        return (Client) getSession().get(Client.class,cuit);
    }

    public Client getById(long id) {
        return (Client) getSession().get(Client.class, id);
    }

    public void update(Client client) {
        getSession().update(client);
    }

    public boolean exists(long id){
        Client client = (Client) getSession().get(Client.class,id);
        return client != null;
    }
}
