package ru.teamidea.servicelayer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.teamidea.servicelayer.domain.User;

/**
 * Created by Timofey Klyubin on 26.10.17
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(@Param("email") String email);
}
