package app.entity.user;

import app.entity.Client;
import app.model.user.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @OneToOne
    @JoinColumn(name="client_id")
    private Client client;

    private String nickname;

    @JsonIgnore
    private String password;

    private Integer role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public UserModel getModel() {
        UserModel model = new UserModel();

        model.setId(id);

        if(client != null){
            model.setClientId(client.getId());
        }
        else{
            model.setClientId(new Long(0));
        }

        model.setNickname(nickname);
        model.setPassword(password);
        model.setRole(role);

        return model;
    }
}
