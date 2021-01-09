package ru.imfo.database.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.imfo.database.backend.entities.*;
import ru.imfo.database.backend.repositories.*;
import ru.imfo.database.backend.services.UserService;

import javax.persistence.EntityExistsException;
import java.security.Principal;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */

@RestController
public class ClientController {
    private UserService userService;
    private ClientRepository clientRepository;
    private HumanRepository humanRepository;
    private SubscriptionRepository subscriptionRepository;
    private DeliveryPlaceRepository deliveryPlaceRepository;
    private SausageRepository sausageRepository;
    private OrderRepository orderRepository;

    @Autowired
    public ClientController(UserService userService, ClientRepository clientRepository, HumanRepository humanRepository, SubscriptionRepository subscriptionRepository, DeliveryPlaceRepository deliveryPlaceRepository) {
        this.userService = userService;
        this.clientRepository = clientRepository;
        this.humanRepository = humanRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.deliveryPlaceRepository = deliveryPlaceRepository;
    }

    @PostMapping("/human")
    Client human(@RequestBody Human human, Principal principal) {
        Human n_human = humanRepository.save(human);
        User n_user = userService.findByUsername(principal.getName());
        Client client = new Client(n_human, deliveryPlaceRepository.getOne((long) 1), subscriptionRepository.getOne((long) 1), n_user);
        return clientRepository.save(client);
    }
    @GetMapping("/human")
    Human human(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Client client = clientRepository.findClientByUser(user);
        try {
            return humanRepository.findById(client.getHuman().getId()).orElseThrow(EntityExistsException::new);
        } catch (EntityExistsException ex) {
            return null;
        }

    }

    @GetMapping("/catalog")
    Collection<Sausage> sausages() {
        return sausageRepository.findAll();
    }

    @PostMapping("/orders")
    ResponseEntity<String> getOrder(@RequestBody Collection<Order> orders, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Client client = clientRepository.findClientByUser(user);
        for(Order order : orders) {
            order.set_from(client);
        }
        orderRepository.saveAll(orders);

        return new ResponseEntity<>("All orders saved in the database", HttpStatus.CREATED);
    }

    

//    @GetMapping("/subscriptions")
//    Collection<Subscription> getSubscription

}


