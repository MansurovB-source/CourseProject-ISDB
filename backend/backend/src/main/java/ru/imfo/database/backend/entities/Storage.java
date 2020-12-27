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
@Table(name = "storages")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_storage;

    @ManyToOne
    @JoinColumn(name = "id_factory")
    private Factory id_factory;

    @ManyToOne
    @JoinColumn(name = "id_sausage")
    private Sausage id_sausage;

    private double sausages_weigh;
}
