package app.dao;

import app.model.user.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User get(long id){
        return sessionFactory.getCurrentSession().get(User.class,id);
    }
}
