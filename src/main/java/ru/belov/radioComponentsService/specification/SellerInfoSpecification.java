package ru.belov.radioComponentsService.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import ru.belov.radioComponentsService.domain.entity.sql.SellerInfo;
import ru.belov.radioComponentsService.domain.entity.sql.SellerInfo_;

import java.util.List;


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
    public Specification<SellerInfo> hasCompanyNameLike(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(SellerInfo_.COMPANY_NAME), "%" + name + "%");
    }

    public Specification<SellerInfo> hasIdsIn(List<Long> ids) {
        return (root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<Long> inClause = criteriaBuilder.in(root.get(SellerInfo_.ID));
            for (Long id : ids) {
                inClause.value(id);
            }
            return inClause;
        };
    }
    public Specification<SellerInfo> hasIdsNotIn(List<Long> ids) {
        return (root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<Long> inClause = criteriaBuilder.in(root.get(SellerInfo_.ID));
            for (Long id : ids) {
                inClause.value(id);
            }
            return criteriaBuilder.not(inClause);
        };
    }

}
