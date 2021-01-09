package ru.imfo.database.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.imfo.database.backend.entities.Subscription;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
