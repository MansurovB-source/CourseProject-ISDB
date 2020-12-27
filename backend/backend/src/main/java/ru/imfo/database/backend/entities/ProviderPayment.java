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
@Table(name = "providers_payments")
public class ProviderPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_provider_payment;

    @ManyToOne
    @JoinColumn(name = "id_provider")
    private Provider id_provider;

    @ManyToOne
    @JoinColumn(name = "id_factory")
    private Factory id_factory;

    private Integer sum;

    private Date dept_time;

    private Boolean paying;

    private Date payment_date;
}