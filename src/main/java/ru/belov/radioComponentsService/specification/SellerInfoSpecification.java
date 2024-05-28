package ru.belov.radioComponentsService.specification;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import ru.belov.radioComponentsService.domain.entity.sql.SellerInfo;

@UtilityClass
public class SellerInfoSpecification {
    public Specification<SellerInfo> hasIndFlag(Boolean indFlag) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("indFlag"), indFlag);
    }

    public Specification<SellerInfo> hasflagManufacturer(Boolean flagManufacturer) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("flagManufacturer"), flagManufacturer);
    }

    public Specification<SellerInfo> hasRatingGreaterThanOrEqual(Double rating) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), rating);
    }

    public Specification<SellerInfo> hasCity(String city) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("city"), city);
    }
}
