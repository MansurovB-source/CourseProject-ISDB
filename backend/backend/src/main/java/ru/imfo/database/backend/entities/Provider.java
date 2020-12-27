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
@Table(name = "providers")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_provider;

    @OneToOne
    @JoinColumn(name = "id_human")
    private Human id_human;

    private Double salary;

    @ManyToOne
    @JoinColumn(name = "id_factory")
    private Factory id_factory;

    @OneToOne
    @JoinColumn(name = "id_delivery_place")
    private DeliveryPlace id_delivery_place;

    @OneToOne
    @JoinColumn(name = "id")
    private User id_user;
}