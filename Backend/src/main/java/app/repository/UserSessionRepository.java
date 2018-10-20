package app.repository;

import app.entity.UserSession;
import app.entity.user.User;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserSessionRepository {

    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession(){
        return _sessionFactory.getCurrentSession();
    }

    public UserSession save(UserSession userSession) {
        getSession().save(userSession);
        return userSession;
    }

    public void delete(long id) {
        getSession().delete(getById(id));
    }

    public UserSession getById(long id) {
        return (UserSession) getSession().get(UserSession.class, id);
    }

    public boolean exists(long id, String token){

        Criteria criteria = getSession().createCriteria(UserSession.class);

        UserSession userSession = (UserSession) criteria.add(Restrictions.eq("token", token)).uniqueResult();

        return userSession != null;
    }
}
