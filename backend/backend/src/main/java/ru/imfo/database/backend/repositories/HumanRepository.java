package ru.imfo.database.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.imfo.database.backend.entities.Human;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public interface HumanRepository extends JpaRepository<Human, Long> {

}
