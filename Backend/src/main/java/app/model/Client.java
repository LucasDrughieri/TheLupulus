package app.model;

import lombok.Data;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public @Data class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "cuit")
    private Long cuit;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

}
