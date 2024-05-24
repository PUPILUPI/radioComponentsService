package ru.belov.radioComponentsService.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belov.radioComponentsService.db.entity.EntityUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<EntityUser, Long> {
    Optional<EntityUser> findByEmail(String email);
}
