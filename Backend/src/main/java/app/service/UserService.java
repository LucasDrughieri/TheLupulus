package app.service;


import app.dao.UserDao;
import app.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;


    public User get(long id){
        return userDao.get(id);
    }
}
