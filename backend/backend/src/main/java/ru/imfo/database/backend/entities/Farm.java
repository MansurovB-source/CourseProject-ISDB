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
@Table(name = "farms")
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_farm;

    @ManyToOne()
    @JoinColumn(name = "id_factory")
    private Factory id_factory;

    @ManyToOne()
    @JoinColumn(name = "id_animal")
    private Animal id_animal;

    @ManyToOne
    @JoinColumn(name = "id_location")
    private Location id_location;
}