package ru.imfo.database.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.imfo.database.backend.entities.Client;
import ru.imfo.database.backend.entities.User;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findClientByUser(User user);
}
