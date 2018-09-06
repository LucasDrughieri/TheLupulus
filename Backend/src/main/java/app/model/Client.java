package app.model;

import lombok.Data;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clients")
public @Data class Client {

    @Id
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
