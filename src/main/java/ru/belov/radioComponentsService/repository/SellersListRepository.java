package ru.belov.radioComponentsService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belov.radioComponentsService.domain.entity.sql.SellersList;
import ru.belov.radioComponentsService.domain.entity.sql.SellersListId;

import java.util.List;

@Repository
public interface SellersListRepository extends JpaRepository<SellersList, SellersListId> {
    List<SellersList> findByIdConsumerIdAndFavoriteFlag(Long consumerId, Boolean favoriteFlag);
    void deleteById(SellersListId id);


}
