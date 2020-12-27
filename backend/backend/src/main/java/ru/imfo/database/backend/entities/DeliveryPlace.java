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
@Table(name = "delivery_places")
public class DeliveryPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_delivery_place;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_location")
    private Location id_location;

    private String address;

    private Long client_num;
}