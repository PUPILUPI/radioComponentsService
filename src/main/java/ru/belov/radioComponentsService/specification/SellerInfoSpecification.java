package ru.belov.radioComponentsService.specification;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import ru.belov.radioComponentsService.domain.entity.sql.SellerInfo;
import ru.belov.radioComponentsService.domain.entity.sql.SellerInfo_;


@UtilityClass
public class SellerInfoSpecification {


    public Specification<SellerInfo> hasIndFlag(Boolean indFlag) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(SellerInfo_.IND_FLAG), indFlag);
    }

    public Specification<SellerInfo> hasflagManufacturer(Boolean flagManufacturer) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(SellerInfo_.FLAG_MANUFACTURER), flagManufacturer);
    }

    public Specification<SellerInfo> hasRatingGreaterThanOrEqual(Double rating) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get(SellerInfo_.RATING), rating);
    }

    public Specification<SellerInfo> hasCity(String city) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(SellerInfo_.CITY), city);
    }
}
