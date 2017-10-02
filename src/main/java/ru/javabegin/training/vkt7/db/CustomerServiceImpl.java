package ru.javabegin.training.vkt7.db;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javabegin.training.db.Contact;
import ru.javabegin.training.db.Contact_;
import ru.javabegin.training.db.Content;
import ru.javabegin.training.db.Content_;
import ru.javabegin.training.vkt7.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Николай on 25.09.2017.
 */

@Service("jpaCustomerService")
@Repository
@Transactional
public class CustomerServiceImpl implements CustomerService {


    final static String ALL_CUSTOMER_NATIVE_QUERY = "select id, first_name, last_name, tel_number, tel_modem, unit_number, e_mail, version from customer";

    private Log log = LogFactory.getLog(CustomerServiceImpl.class);

  /*  @Qualifier(value = "emf_1")
    @PersistenceContext(unitName="emf_customer")*/

    @PersistenceContext(unitName="emf_contact")
    @Qualifier(value = "emf")

    private EntityManager em;

    //private ProjectionList contactProjection;




    @Transactional(readOnly=true)

    @Override
    public List<Customer> findAll(){
        List<Customer> customers = em.createNamedQuery("Customer.findAll",Customer.class).getResultList();
        return customers;

    }


    @Override
    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            log.info("Inserting new contact");
            em.persist(customer);
        } else {
            em.merge(customer);
            log.info("Updating existing contact");
        }

        log.info("Contact saved with id: " + customer.getId());

        return customer;
    }

    @Transactional(readOnly=true)
    @Override
    public Customer findById(Long id) {
        TypedQuery<Customer> query = em.createNamedQuery(
                "Customer.findById", Customer.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }


    @Transactional(readOnly=true)
    @Override
    public  List<Operation> findOperationByIdCustomer(Long id){
        log.info("Finding operation by id: " );
        id=10L;

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Operation> criteriaQuery = cb.createQuery(Operation.class);

        Root<Operation> contactRoot = criteriaQuery.from(Operation.class);
        Join cont = contactRoot.join(Operation_.customer);

        ParameterExpression<Long> parametr = cb.parameter(Long.class);
        Predicate condition = cb.gt(cont.get(Customer_.id), parametr) ;
        criteriaQuery.where(condition);
        TypedQuery<Operation> q = em.createQuery(criteriaQuery);
        List<Operation> result = q.setParameter(parametr, id).getResultList();
      return result;
    }


    @Transactional(readOnly=true)
    @Override
    public List<Operation> getOperationsByCustomerId(Long id) {
        log.info("Finding content by first_name: " );


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Operation> criteriaQuery = cb.createQuery(Operation.class);
        Root<Operation> contactRoot = criteriaQuery.from(Operation.class);
        //Join cont = contactRoot.join(Operation_.customer);
        //criteriaQuery.select(contactRoot).distinct(true);
        ///contactRoot.fetch(Contact_.content, JoinType.LEFT);

        //ParameterExpression<Long> parametr = cb.parameter(Long.class);
        ParameterExpression<Long> parametr = cb.parameter(Long.class);
         Predicate condition = cb.equal(contactRoot.get(Operation_.idCustomer), parametr);
        //Predicate condition = cb.like(cont.get(Customer_.firstName), parametr) ;
        criteriaQuery.where(condition);
        TypedQuery<Operation> q = em.createQuery(criteriaQuery);
        List<Operation> result = q.setParameter(parametr,id ).getResultList();

        return result;
    }


    public List<Operation> getOperations(String name) {
        log.info("Finding content by first_name: " );


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Operation> criteriaQuery = cb.createQuery(Operation.class);
        Root<Operation> contactRoot = criteriaQuery.from(Operation.class);
        //Join cont = contactRoot.join(Operation_.customer);
        //criteriaQuery.select(contactRoot).distinct(true);
        ///contactRoot.fetch(Contact_.content, JoinType.LEFT);

        //ParameterExpression<Long> parametr = cb.parameter(Long.class);
        ParameterExpression<String> parametr = cb.parameter(String.class);
        Predicate condition = cb.like(contactRoot.get(Operation_.typeOperation), parametr);
        //Predicate condition = cb.like(cont.get(Customer_.firstName), parametr) ;
        criteriaQuery.where(condition);
        TypedQuery<Operation> q = em.createQuery(criteriaQuery);
        List<Operation> result = q.setParameter(parametr,name+"%" ).getResultList();
        int a=result.size();
        System.out.println(a);

        for (Operation operation: result) {
            System.out.println(operation);

        }

        return result;


    }



/*
    @Transactional(readOnly=true)
    @Override
    public Object getContent(Long id) {
        log.info("Finding content by first_name: " );


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Content> criteriaQuery = cb.createQuery(Content.class);
        Root<Content> contactRoot = criteriaQuery.from(Content.class);
        Join cont = contactRoot.join(Content_.contact);
        //criteriaQuery.select(contactRoot).distinct(true);
        ///contactRoot.fetch(Contact_.content, JoinType.LEFT);

        ParameterExpression<Long> parametr = cb.parameter(Long.class);
        // Predicate condition = cb.gt(contactRoot.get(Contact_.version), ver);
        Predicate condition = cb.equal(cont.get(Contact_.id), parametr) ;
        criteriaQuery.where(condition);
        TypedQuery<Content> q = em.createQuery(criteriaQuery);
        List<Content> result = q.setParameter(parametr, id).getResultList();
        Object r=null;
        for (Content content: result) {
            System.out.println(content);
            r=content.getContent();
        }



        return r;


    }

    */



    /* @Transactional(readOnly=true)
   @Override
    public List<Content> findByfirstname(String name) {
        log.info("Finding content by first_name: " );


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Content> criteriaQuery = cb.createQuery(Content.class);
        Root<Content> contactRoot = criteriaQuery.from(Content.class);
        Join cont = contactRoot.join(Content_.contact);
        //criteriaQuery.select(contactRoot).distinct(true);
        ///contactRoot.fetch(Contact_.content, JoinType.LEFT);

        ParameterExpression<String> parametr = cb.parameter(String.class);
        // Predicate condition = cb.gt(contactRoot.get(Contact_.version), ver);
        Predicate condition = cb.like(cont.get(Contact_.firstName), parametr) ;
        criteriaQuery.where(condition);
        TypedQuery<Content> q = em.createQuery(criteriaQuery);
        List<Content> result = q.setParameter(parametr, name+"%").getResultList();


        return result;
*/



}
