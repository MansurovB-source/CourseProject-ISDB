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
    private Long id_order;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client _from;

    @ManyToOne
    @JoinColumn(name = "id_provider")
    private Provider _to;

    @ManyToOne
    @JoinColumn(name = "id_sausage")
    private Sausage id_sausage;

    private Double sausages_weight;

    private LocalDateTime ord_time;

    private Boolean special;
}