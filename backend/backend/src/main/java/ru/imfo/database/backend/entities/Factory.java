package ru.imfo.database.backend.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
@Data
@Entity
@Table(name = "factories")
public class Factory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_factory;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_location")
    private Location id_location;

    private Long worker_num;

    @ManyToMany
    @JoinTable(name = "factory_sausages",
            joinColumns = @JoinColumn(name = "id_factory"),
            inverseJoinColumns = @JoinColumn(name = "id_sasage"))
    private Collection<Sausage> sausages;
}