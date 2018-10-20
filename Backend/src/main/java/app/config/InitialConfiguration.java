package app.config;

import app.entity.Client;
import app.entity.user.User;
import app.repository.ClientRepository;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InitialConfiguration {

    @Autowired
    ClientRepository _clientRepository;

    @Autowired
    UserRepository _userRepository;

    @EventListener
    public void seedAll(ContextRefreshedEvent event){
        seedClients();
        seedUsers();
    }

    private void seedClients(){

        Client client = new Client();
        client.setAddress("Calle 1");
        client.setBusinessName("Cervecería San Cebada");
        client.setCuit(123L);
        client.setEmail("sancebada@gmail.com");
        client.setPhoneNumber(57894565L);

        _clientRepository.save(client);
    }

    private void seedUsers(){

        Client client = _clientRepository.getById(1);

        User user = new User();
        user.setClientId(client);
        user.setNickname("cebada");
        user.setPassword("cebada");
        user.setRole(2);

        _userRepository.save(user);
    }
}
