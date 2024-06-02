package ru.belov.radioComponentsService.specification;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import ru.belov.radioComponentsService.domain.entity.sql.MyUser;
import ru.belov.radioComponentsService.domain.entity.sql.SellerInfo;
import ru.belov.radioComponentsService.domain.entity.sql.SellersList;
import ru.belov.radioComponentsService.domain.entity.sql.SellersListId;

import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SellersList.class)
public abstract class SellersList_ {

	public static volatile SingularAttribute<SellersList, SellerInfo> seller;
	public static volatile SingularAttribute<SellersList, Boolean> favoriteFlag;
	public static volatile SingularAttribute<SellersList, Boolean> blacklistFlag;
	public static volatile SingularAttribute<SellersList, SellersListId> id;
	public static volatile SingularAttribute<SellersList, MyUser> consumer;

	public static final String SELLER = "seller";
	public static final String FAVORITE_FLAG = "favoriteFlag";
	public static final String BLACKLIST_FLAG = "blacklistFlag";
	public static final String ID = "id";
	public static final String CONSUMER = "consumer";

}

