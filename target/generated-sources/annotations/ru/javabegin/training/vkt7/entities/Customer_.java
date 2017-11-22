package ru.javabegin.training.vkt7.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Customer.class)
public abstract class Customer_ {

	public static volatile SingularAttribute<Customer, String> firstName;
	public static volatile SingularAttribute<Customer, String> lastName;
	public static volatile SetAttribute<Customer, Operation> operationSet;
	public static volatile SingularAttribute<Customer, String> telNumber;
	public static volatile SingularAttribute<Customer, String> telModem;
	public static volatile SingularAttribute<Customer, Integer> unitNumber;
	public static volatile SingularAttribute<Customer, Long> id;
	public static volatile SingularAttribute<Customer, Integer> version;
	public static volatile SingularAttribute<Customer, String> eMail;

}

