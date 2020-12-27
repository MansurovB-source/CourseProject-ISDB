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
@Table(name = "clients_payments")
public class ClientPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_client_payment;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client id_client;

    @ManyToOne
    @JoinColumn(name = "id_provider")
    private Provider id_provider;

    private Integer sum;

    private Date dept_time;

    private Boolean paying;

    private Date payment_date;
}