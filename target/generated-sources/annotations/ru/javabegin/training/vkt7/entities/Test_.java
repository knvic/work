package ru.javabegin.training.vkt7.entities;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Test.class)
public abstract class Test_ {

	public static volatile SingularAttribute<Test, Long> id;
	public static volatile SingularAttribute<Test, String> text;
	public static volatile SingularAttribute<Test, Timestamp> servetDate;
	public static volatile SingularAttribute<Test, Integer> version;

}

