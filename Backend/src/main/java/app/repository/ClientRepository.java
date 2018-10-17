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

    public void save(Client client) {
        getSession().save(client);
    }

    public void delete(Client client) {
        getSession().delete(client);
    }

    @SuppressWarnings("unchecked")
    public List<Client> getAll() {
        return getSession().createQuery("from clients").list();
    }

    public Client getByCuit(long cuit) {
        return (Client) getSession().createQuery(
                "from clients where email = :email")
                .setParameter("cuit", cuit)
                .uniqueResult();
    }

    public Client getById(long id) {
        return (Client) getSession().load(Client.class, id);
    }

    public void update(Client client) {
        getSession().update(client);
    }

    public boolean exists(long cuit){
        Client client = this.getByCuit(cuit);
        return client != null;
    }
}
