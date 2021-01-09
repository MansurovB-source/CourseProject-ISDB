package ru.imfo.database.backend.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "_from", referencedColumnName = "id")
    private Client _from;

    @ManyToOne
    @JoinColumn(name = "_to", referencedColumnName = "id")
    private Provider _to;

    @ManyToOne
    @JoinColumn(name = "sausage_id", referencedColumnName = "id")
    private Sausage sausage;

    private Double sausages_weight;

    private LocalDateTime ord_time;

    private Boolean special;
}