package ru.javabegin.training.db;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Content.class)
public abstract class Content_ {

	public static volatile SingularAttribute<Content, Contact> contact;
	public static volatile SingularAttribute<Content, Integer> id;
	public static volatile SingularAttribute<Content, byte[]> content;

}

