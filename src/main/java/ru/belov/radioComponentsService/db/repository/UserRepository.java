package ru.belov.radioComponentsService.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belov.radioComponentsService.db.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
