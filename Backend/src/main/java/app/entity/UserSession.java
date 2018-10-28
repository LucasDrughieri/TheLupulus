package app.entity;

import app.entity.user.User;
import app.entity.user.UserRole;
import app.infraestructure.Response;
import app.repository.UserSessionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name="sessions")
public class UserSession {

    public static boolean validateAccess(UserSessionRepository _userSessionRepository, String sessionToken, Integer[] admittedRoles) {
        UserSession session = _userSessionRepository.getByToken(sessionToken);
        User user = session.getUserId();

        if (user == null) {
            return false;
        }
        else if (!Arrays.asList(admittedRoles).contains(user.getRole())) {
            return false;
        }

        return true;
    }

    public static ResponseEntity<Response> errorResponse() {
        Response response = new Response();
        response.addError("El usuario no es administrador");
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    private String token;

    private Date lastLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
