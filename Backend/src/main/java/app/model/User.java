package app.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "users")
public @Data class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @JoinColumn(name="client_id")
    private Client clientId;

    private String nickname;

    private String password;

    private String role;

}
