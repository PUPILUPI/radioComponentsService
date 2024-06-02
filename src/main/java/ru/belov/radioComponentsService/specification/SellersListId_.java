package ru.belov.radioComponentsService.specification;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import ru.belov.radioComponentsService.domain.entity.sql.SellersListId;

import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SellersListId.class)
public abstract class SellersListId_ {

	public static volatile SingularAttribute<SellersListId, Long> sellerId;
	public static volatile SingularAttribute<SellersListId, Long> consumerId;

	public static final String SELLER_ID = "sellerId";
	public static final String CONSUMER_ID = "consumerId";

}

