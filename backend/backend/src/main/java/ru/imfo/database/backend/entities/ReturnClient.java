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
    private Long id_return;

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

    private Date ret_time;

}