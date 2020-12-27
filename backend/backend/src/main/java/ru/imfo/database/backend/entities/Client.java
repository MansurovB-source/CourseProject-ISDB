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
    private Long id_provider;

    @OneToOne
    @JoinColumn(name = "id_human")
    private Human id_human;

    @OneToOne
    @JoinColumn(name = "id_delivery_place")
    private DeliveryPlace id_delivery_place;

    @ManyToOne
    @JoinColumn(name = "id_subscription")
    private Subscription id_subscription;

    @OneToOne
    @JoinColumn(name = "id")
    private User id_user;
}