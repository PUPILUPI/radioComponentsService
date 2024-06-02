package ru.belov.radioComponentsService.specification;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import ru.belov.radioComponentsService.domain.entity.sql.MyUser;

import java.time.OffsetDateTime;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MyUser.class)
public abstract class MyUser_ {

	public static volatile SingularAttribute<MyUser, String> firstName;
	public static volatile SingularAttribute<MyUser, String> password;
	public static volatile SingularAttribute<MyUser, String> phoneNumber;
	public static volatile SingularAttribute<MyUser, String> surname;
	public static volatile SingularAttribute<MyUser, OffsetDateTime> dateRegistration;
	public static volatile SingularAttribute<MyUser, String> middleName;
	public static volatile SingularAttribute<MyUser, Boolean> submitFlag;
	public static volatile SingularAttribute<MyUser, String> userRole;
	public static volatile SingularAttribute<MyUser, Long> userId;
	public static volatile SingularAttribute<MyUser, String> email;

	public static final String FIRST_NAME = "firstName";
	public static final String PASSWORD = "password";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String SURNAME = "surname";
	public static final String DATE_REGISTRATION = "dateRegistration";
	public static final String MIDDLE_NAME = "middleName";
	public static final String SUBMIT_FLAG = "submitFlag";
	public static final String USER_ROLE = "userRole";
	public static final String USER_ID = "userId";
	public static final String EMAIL = "email";

}

