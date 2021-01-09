package ru.imfo.database.backend.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
@Data
@Entity
@Table(name = "ReturnClient")
public class ReturnClient {
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

    private Date ret_time;

}