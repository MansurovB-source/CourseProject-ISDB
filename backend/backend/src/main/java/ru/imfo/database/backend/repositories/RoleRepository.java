package ru.imfo.database.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.imfo.database.backend.entities.Role;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
