package ru.belov.radioComponentsService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.belov.radioComponentsService.domain.entity.sql.SellerInfo;

@Repository
public interface SellerInfoRepository extends JpaRepository<SellerInfo, Long>,
        JpaSpecificationExecutor<SellerInfo> {
}
