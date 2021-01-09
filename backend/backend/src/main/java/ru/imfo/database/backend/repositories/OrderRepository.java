package ru.imfo.database.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.imfo.database.backend.entities.Order;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
