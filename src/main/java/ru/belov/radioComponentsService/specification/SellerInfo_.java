package ru.belov.radioComponentsService.specification;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import ru.belov.radioComponentsService.domain.entity.sql.MyUser;
import ru.belov.radioComponentsService.domain.entity.sql.SellerInfo;

import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SellerInfo.class)
public abstract class SellerInfo_ {

	public static volatile SingularAttribute<SellerInfo, String> zip;
	public static volatile SingularAttribute<SellerInfo, String> acc;
	public static volatile SingularAttribute<SellerInfo, String> agreement;
	public static volatile SingularAttribute<SellerInfo, Boolean> flagManufacturer;
	public static volatile SingularAttribute<SellerInfo, String> city;
	public static volatile SingularAttribute<SellerInfo, String> companyName;
	public static volatile SingularAttribute<SellerInfo, String> inn;
	public static volatile SingularAttribute<SellerInfo, Double> rating;
	public static volatile SingularAttribute<SellerInfo, Boolean> agreementFlag;
	public static volatile SingularAttribute<SellerInfo, String> bankName;
	public static volatile SingularAttribute<SellerInfo, String> kpp;
	public static volatile SingularAttribute<SellerInfo, String> responseFormat;
	public static volatile SingularAttribute<SellerInfo, String> apiAddress;
	public static volatile SingularAttribute<SellerInfo, Boolean> indFlag;
	public static volatile SingularAttribute<SellerInfo, String> phoneNumber;
	public static volatile SingularAttribute<SellerInfo, Long> id;
	public static volatile SingularAttribute<SellerInfo, String> corrAcc;
	public static volatile SingularAttribute<SellerInfo, String> businessAddress;
	public static volatile SingularAttribute<SellerInfo, MyUser> user;
	public static volatile SingularAttribute<SellerInfo, String> email;
	public static volatile SingularAttribute<SellerInfo, String> rcBic;

	public static final String ZIP = "zip";
	public static final String ACC = "acc";
	public static final String AGREEMENT = "agreement";
	public static final String FLAG_MANUFACTURER = "flagManufacturer";
	public static final String CITY = "city";
	public static final String COMPANY_NAME = "companyName";
	public static final String INN = "inn";
	public static final String RATING = "rating";
	public static final String AGREEMENT_FLAG = "agreementFlag";
	public static final String BANK_NAME = "bankName";
	public static final String KPP = "kpp";
	public static final String RESPONSE_FORMAT = "responseFormat";
	public static final String API_ADDRESS = "apiAddress";
	public static final String IND_FLAG = "indFlag";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String ID = "id";
	public static final String CORR_ACC = "corrAcc";
	public static final String BUSINESS_ADDRESS = "businessAddress";
	public static final String USER = "user";
	public static final String EMAIL = "email";
	public static final String RC_BIC = "rcBic";

}

