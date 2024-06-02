package ru.belov.radioComponentsService.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.belov.radioComponentsService.domain.dto.sql.AddSellerToListReq;
import ru.belov.radioComponentsService.domain.entity.sql.MyUser;
import ru.belov.radioComponentsService.domain.entity.sql.SellerInfo;
import ru.belov.radioComponentsService.domain.entity.sql.SellersList;
import ru.belov.radioComponentsService.domain.entity.sql.SellersListId;
import ru.belov.radioComponentsService.exceptions.GeneralException;
import ru.belov.radioComponentsService.repository.SellerInfoRepository;
import ru.belov.radioComponentsService.repository.SellersListRepository;
import ru.belov.radioComponentsService.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellersListService {
    private final SellersListRepository sellersListRepository;
    private final UserRepository userRepository;
    private final SellerInfoRepository sellerInfoRepository;

    public List<SellersList> getByConsumerIdAndTypeList(Long consumerId, Boolean favoriteFlag) {
            return sellersListRepository.findByIdConsumerIdAndFavoriteFlag(consumerId, favoriteFlag);
    }

    public SellersList add(Long consumerId, AddSellerToListReq req) {
        Optional<SellersList> sellersList = sellersListRepository.findById(new SellersListId(consumerId, req.sellerId()));
        if(sellersList.isPresent()) {
            throw new GeneralException(409, "Entry has already exist");
        }
        MyUser user = userRepository.findById(consumerId)
                .orElseThrow(() -> new GeneralException(404, "User not found"));
        SellerInfo seller = sellerInfoRepository.findById(req.sellerId())
                .orElseThrow(() -> new GeneralException(404, "Seller not found"));
        SellersList entity = new SellersList();
        entity.setId(new SellersListId());
        entity.setSeller(seller);
        entity.setConsumer(user);
        entity.setBlacklistFlag(req.blackListFlag());
        entity.setFavoriteFlag(req.favoriteFlag());
        return sellersListRepository.save(entity);
    }

    public void remove(Long consumerId, Long sellerId) {
        sellersListRepository.deleteById(new SellersListId(consumerId, sellerId));
    }

    public SellersList update(Long consumerId, Long sellerId) {
        return sellersListRepository.findById(new SellersListId(consumerId, sellerId))
                .map(oldInfo -> {
                    if (oldInfo.getFavoriteFlag()) {
                        oldInfo.setBlacklistFlag(true);
                        oldInfo.setFavoriteFlag(false);
                    } else {
                        oldInfo.setBlacklistFlag(false);
                        oldInfo.setFavoriteFlag(true);
                    }
                    return sellersListRepository.save(oldInfo);
                }).orElseThrow(() -> new GeneralException(404, "Entity not found"));
    }
}
