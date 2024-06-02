package ru.belov.radioComponentsService.specification;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import ru.belov.radioComponentsService.domain.entity.sql.ConsumerInfo;
import ru.belov.radioComponentsService.domain.entity.sql.MyUser;

import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ConsumerInfo.class)
public abstract class ConsumerInfo_ {

	public static volatile SingularAttribute<ConsumerInfo, String> zip;
	public static volatile SingularAttribute<ConsumerInfo, String> phoneNumber;
	public static volatile SingularAttribute<ConsumerInfo, String> city;
	public static volatile SingularAttribute<ConsumerInfo, String> companyName;
	public static volatile SingularAttribute<ConsumerInfo, String> inn;
	public static volatile SingularAttribute<ConsumerInfo, String> kpp;
	public static volatile SingularAttribute<ConsumerInfo, Long> id;
	public static volatile SingularAttribute<ConsumerInfo, String> businessAddress;
	public static volatile SingularAttribute<ConsumerInfo, MyUser> user;
	public static volatile SingularAttribute<ConsumerInfo, String> email;

	public static final String ZIP = "zip";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String CITY = "city";
	public static final String COMPANY_NAME = "companyName";
	public static final String INN = "inn";
	public static final String KPP = "kpp";
	public static final String ID = "id";
	public static final String BUSINESS_ADDRESS = "businessAddress";
	public static final String USER = "user";
	public static final String EMAIL = "email";

}

