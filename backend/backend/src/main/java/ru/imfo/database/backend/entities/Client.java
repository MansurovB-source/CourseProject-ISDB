package ru.imfo.database.backend.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */

@Data
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "human_id", referencedColumnName = "id")
    private Human human;

    @OneToOne
    @JoinColumn(name = "delivery_place_id", referencedColumnName = "id")
    private DeliveryPlace delivery_place;

    @ManyToOne
    @JoinColumn(name = "subscription_id", referencedColumnName = "id")
    private Subscription subscription;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Client(Human id_human, DeliveryPlace id_delivery_place, Subscription id_subscription, User id_user) {
        this.human = id_human;
        this.delivery_place = id_delivery_place;
        this.subscription = id_subscription;
        this.user = id_user;
    }

    public Client() {
    }
}