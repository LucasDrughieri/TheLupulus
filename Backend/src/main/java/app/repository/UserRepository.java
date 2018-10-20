package app.repository;

import app.entity.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepository {

    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession(){
        return _sessionFactory.getCurrentSession();
    }

    public User save(User user) {
        getSession().save(user);
        return user;
    }

    public void delete(long id) {
        getSession().delete(getById(id));
    }

    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        return getSession().createQuery("from users").list();
    }

    public User getById(long id) {
        return (User) getSession().get(User.class, id);
    }

    public User getByNickname(String nickname) {
        return (User) getSession().get(User.class, nickname);
    }

    public boolean exists(long id){
        User user = (User) getSession().get(User.class,id);
        return user != null;
    }
}
