package ru.imfo.database.backend.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
@Data
@Entity
@Table(name = "order_schedule")
public class OrderSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_schedule;

    @ManyToOne
    @JoinColumn(name = "id_provider")
    private Provider id_provider;

    @ManyToOne
    @JoinColumn(name = "id_sausage")
    private Sausage id_sausage;

    private Double sausages_weight;

    private Date del_time;

    @ManyToMany
    @JoinTable(name = "car_schedule",
            joinColumns = @JoinColumn(name = "id_schedule"),
            inverseJoinColumns = @JoinColumn(name = "id_car"))
    private Collection<Car> cars;
}