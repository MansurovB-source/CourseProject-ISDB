package ru.imfo.database.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.imfo.database.backend.entities.DeliveryPlace;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public interface DeliveryPlaceRepository extends JpaRepository<DeliveryPlace, Long> {
}
