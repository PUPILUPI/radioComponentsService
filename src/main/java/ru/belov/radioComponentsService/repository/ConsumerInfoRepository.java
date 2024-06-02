package ru.belov.radioComponentsService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belov.radioComponentsService.domain.entity.sql.ConsumerInfo;

@Repository
public interface ConsumerInfoRepository extends JpaRepository<ConsumerInfo, Long> {
}
