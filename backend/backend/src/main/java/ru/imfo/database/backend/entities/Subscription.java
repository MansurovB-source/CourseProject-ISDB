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
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_subscription;

    private String name;

    private Integer price;

    private Integer discount;

    @ManyToMany
    @JoinTable(name = "subs_sausages",
            joinColumns = @JoinColumn(name = "id_subscription"),
            inverseJoinColumns = @JoinColumn(name = "id_sausage"))
    private Collection<Sausage> sausages;
}