package ru.belov.radioComponentsService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belov.radioComponentsService.domain.entity.sql.MyUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByEmail(String email);
    boolean existsByEmail(String email);
}
