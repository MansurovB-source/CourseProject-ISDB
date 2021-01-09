package ru.imfo.database.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.imfo.database.backend.entities.Sausage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public interface SausageRepository extends JpaRepository<Sausage, Long> {
}
