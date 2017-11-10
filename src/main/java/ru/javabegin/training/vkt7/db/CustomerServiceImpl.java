package ru.javabegin.training.vkt7.db;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javabegin.training.db.*;
import ru.javabegin.training.vkt7.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.sql.Timestamp;
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

    @Transactional(readOnly=true)
    @Override
    public List<Customer> findAllWithDetail() {
        List<Customer> customerList = em.createNamedQuery(
                "Customer.findAllWithDetail", Customer.class).getResultList();
        return customerList;
    }

    @Transactional
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
       /* em.flush();
        em.clear();*/


        return customer;
    }


    @Override
    public void delete(Customer customer) {
        Customer mergedContact = em.merge(customer);
        em.remove(mergedContact);

        log.info("Customer with id: " + customer.getId()  + " deleted successfully");
    }


    @Transactional(readOnly=true)
    @Override
    public Customer findById(Long id) {
        TypedQuery<Customer> query = em.createNamedQuery(
                "Customer.findById", Customer.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    //////////////////API Criteria//////////////////////////////////////////


/*

    @Transactional(readOnly=true)
    @Override
    public List<Customer>  findByCriteriaQuery_total_moth(Long id){
        log.info("Finding contact for firstName: " + firstName
                + " and lastName: " + lastName);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contact> criteriaQuery = cb.createQuery(Contact.class);
        Root<Contact> contactRoot = criteriaQuery.from(Contact.class);
        //Join tel = contactRoot.join(Contact_.contactTelDetails);
        //criteriaQuery.where(cb.equal(tel.get(ContactTelDetail_.telNumber), "89193452346"));

        contactRoot.fetch(Contact_.contactTelDetails, JoinType.LEFT);
        Join tel = contactRoot.join(Contact_.contactTelDetails);
        criteriaQuery.select(contactRoot).distinct(true);

        //criteriaQuery.where(cb.equal(tel.get(ContactTelDetail_.telNumber), "89193452346"));

        criteriaQuery.where(cb.like(tel.get(ContactTelDetail_.telNumber), "89%"));

        */
/*     Root<ContactTelDetail> tRoot = criteriaQuery.from(ContactTelDetail.class);
        contactRoot.fetch(Contact_.contactTelDetails, JoinType.LEFT);
        contactRoot.fetch(Contact_.hobbies, JoinType.LEFT);

        criteriaQuery.select(contactRoot).distinct(true);

        Predicate criteria = cb.conjunction();

        if (firstName != null) {
            Predicate p = cb.equal(contactRoot.get(Contact_.firstName),
                    firstName);
            criteria = cb.and(criteria, p);
        }

        if (lastName != null) {
            Predicate p = cb.equal(contactRoot.get(Contact_.lastName),
                    lastName);
            criteria = cb.and(criteria, p);
        }

*//*
*/
/*

        Predicate p = cb.equal(tRoot.get(ContactTelDetail_.telType),
                "hom");
        criteria = cb.and(criteria, p);

*//*
*/
/*


        criteriaQuery.where(criteria);
        *//*



        List<Contact> result=em.createQuery(criteriaQuery).getResultList();
        return result;
    }

*/
    @Override
    public void update_del_clone(Long id_customer, Long id_opertion){

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = cb.createQuery(Customer.class);
        Root<Customer> contactRoot = criteriaQuery.from(Customer.class);
        contactRoot.fetch(Customer_.operationSet, JoinType.LEFT);
        Join oper = contactRoot.join(Customer_.operationSet, JoinType.LEFT).join(Operation_.measurementsSet, JoinType.LEFT);


    }



    @Transactional(readOnly=true)
    @Override
    public List<Customer> findCustomerLikeFirstName(String name) {
        log.info("Finding contact for version LIKE: " );

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = cb.createQuery(Customer.class);
        Root<Customer> contactRoot = criteriaQuery.from(Customer.class);
        contactRoot.fetch(Customer_.operationSet, JoinType.LEFT);
        Join oper = contactRoot.join(Customer_.operationSet, JoinType.LEFT).join(Operation_.measurementsSet, JoinType.LEFT);


        /*Join meas = oper.join(Operation_.measurementsSet,JoinType.LEFT);
        oper.fetch(Operation_.measurementsSet,JoinType.LEFT);
*/





        criteriaQuery.select(contactRoot).distinct(true);
        ParameterExpression<String> parametr = cb.parameter(String.class);

        criteriaQuery.select(contactRoot);
      criteriaQuery.where(cb.like(contactRoot.get(Customer_.firstName),parametr ));


        TypedQuery<Customer> typedQuery = em.createQuery(criteriaQuery);

        List<Customer> result = typedQuery.setParameter(parametr, name+"%").getResultList();




        return result;
    }






    @Transactional(readOnly=true)
    @Override
    public  List<Operation> findOperationByIdCustomer(Long id){
        log.info("Finding operation by id: " );
       // id=10L;

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Operation> criteriaQuery = cb.createQuery(Operation.class);

        Root<Operation> contactRoot = criteriaQuery.from(Operation.class);
        contactRoot.fetch(Operation_.measurementsSet, JoinType.LEFT);
        Join cont = contactRoot.join(Operation_.customer,JoinType.LEFT);
        criteriaQuery.select(contactRoot).distinct(true);

        ParameterExpression<Long> parametr = cb.parameter(Long.class);
        Predicate condition = cb.gt(cont.get(Customer_.id), parametr) ;
        criteriaQuery.where(condition);
        TypedQuery<Operation> q = em.createQuery(criteriaQuery);
        List<Operation> result = q.setParameter(parametr, id).getResultList();
      return result;
    }



    @Transactional(readOnly=true)
    @Override
    public  List<Operation> findOperationByModemCustomer(String modem){
        log.info("Finding operation by id: " );
        // id=10L;

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Operation> criteriaQuery = cb.createQuery(Operation.class);

        Root<Operation> contactRoot = criteriaQuery.from(Operation.class);
        contactRoot.fetch(Operation_.measurementsSet, JoinType.LEFT);
        Join cont = contactRoot.join(Operation_.customer,JoinType.LEFT);
        criteriaQuery.select(contactRoot).distinct(true);

        ParameterExpression<String> parametr = cb.parameter(String.class);
        Predicate condition = cb.equal(cont.get(Customer_.telModem), parametr) ;
        criteriaQuery.where(condition);
        TypedQuery<Operation> q = em.createQuery(criteriaQuery);
        List<Operation> result = q.setParameter(parametr, modem).getResultList();
        return result;
    }


    @Transactional(readOnly=true)
    @Override
    public  List<Operation> findOperation_total_moth(Long id, Timestamp ts, String type, String status){
        log.info("Finding operation by id: " );

        // id=10L;


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Operation> criteriaQuery = cb.createQuery(Operation.class);

        Root<Operation> contactRoot = criteriaQuery.from(Operation.class);
        contactRoot.fetch(Operation_.measurementsSet, JoinType.LEFT);
        Join cont = contactRoot.join(Operation_.customer,JoinType.LEFT);
        criteriaQuery.select(contactRoot).distinct(true);

        Predicate criteria = cb.conjunction();

        if (id != null) {
            Predicate p = cb.equal(cont.get(Customer_.id),
                    id);
            criteria = cb.and(criteria, p);
        }

        if (ts != null) {
            Predicate p = cb.equal(contactRoot.get(Operation_.chronological),
                    ts);
            criteria = cb.and(criteria, p);
        }

        if (type != null) {
            Predicate p = cb.equal(contactRoot.get(Operation_.typeOperation),
                    type);
            criteria = cb.and(criteria, p);
        }


        if (status != null) {
            Predicate p = cb.like(contactRoot.get(Operation_.status),
                   status);
            criteria = cb.and(criteria, p);
        }


        criteriaQuery.where(criteria);

        List<Operation> result=em.createQuery(criteriaQuery).getResultList();
        return result;

    }


    @Transactional(readOnly=true)
    @Override
    public  List<Operation> findOperation_daily(Long id, Timestamp ts, String type, String status){
        log.info("Finding operation by id: " );

        // id=10L;


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Operation> criteriaQuery = cb.createQuery(Operation.class);

        Root<Operation> contactRoot = criteriaQuery.from(Operation.class);
        contactRoot.fetch(Operation_.measurementsSet, JoinType.LEFT);
        Join cont = contactRoot.join(Operation_.customer,JoinType.LEFT);
        criteriaQuery.select(contactRoot).distinct(true);

        Predicate criteria = cb.conjunction();

        if (id != null) {
            Predicate p = cb.equal(cont.get(Customer_.id),
                    id);
            criteria = cb.and(criteria, p);
        }

        if (ts != null) {
            Predicate p = cb.equal(contactRoot.get(Operation_.chronological),
                    ts);
            criteria = cb.and(criteria, p);
        }

        if (type != null) {
            Predicate p = cb.equal(contactRoot.get(Operation_.typeOperation),
                    type);
            criteria = cb.and(criteria, p);
        }


        if (status != null) {
            Predicate p = cb.like(contactRoot.get(Operation_.status),
                    status);
            criteria = cb.and(criteria, p);
        }


        criteriaQuery.where(criteria);

        List<Operation> result=em.createQuery(criteriaQuery).getResultList();
        return result;

    }


    @Transactional(readOnly=true)
    @Override
    public List<Operation> findOperation_betwen_data(Long id_customer, Timestamp day_of,Timestamp day_to, String type, String status){

        log.info("Finding operation by id: " );

        // id=10L;




        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Operation> criteriaQuery = cb.createQuery(Operation.class);

        Root<Operation> contactRoot = criteriaQuery.from(Operation.class);
        contactRoot.fetch(Operation_.measurementsSet, JoinType.LEFT);
        Join cont = contactRoot.join(Operation_.customer,JoinType.LEFT);
        criteriaQuery.select(contactRoot).distinct(true);

        Predicate criteria = cb.conjunction();

        if (id_customer != null) {
            Predicate p = cb.equal(cont.get(Customer_.id),
                    id_customer);
            criteria = cb.and(criteria, p);
        }

        if (day_of != null) {
            Predicate p = cb.greaterThanOrEqualTo(contactRoot.get(Operation_.chronological),
                    day_of);
            criteria = cb.and(criteria, p);
        }

        if (day_to != null) {
            Predicate p = cb.lessThan(contactRoot.get(Operation_.chronological),
                    day_to);
            criteria = cb.and(criteria, p);
        }


        if (type != null) {
            Predicate p = cb.equal(contactRoot.get(Operation_.typeOperation),
                    type);
            criteria = cb.and(criteria, p);
        }


        if (status != null) {
            Predicate p = cb.like(contactRoot.get(Operation_.status),
                    status);
            criteria = cb.and(criteria, p);
        }


        criteriaQuery.where(criteria);

        List<Operation> result=em.createQuery(criteriaQuery).getResultList();
        return result;


    }



    @Transactional()
    @Override
    public void delete_clone_Operation(Long id_customer, Timestamp day_of,Timestamp day_to, String type, String status){

        log.info("Finding operation by id: " );

        // id=10L;




        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Operation> criteriaQuery = cb.createQuery(Operation.class);

        Root<Operation> contactRoot = criteriaQuery.from(Operation.class);
        contactRoot.fetch(Operation_.measurementsSet, JoinType.LEFT);
        Join cont = contactRoot.join(Operation_.customer,JoinType.LEFT);
        criteriaQuery.select(contactRoot).distinct(true);

        Predicate criteria = cb.conjunction();

        if (id_customer != null) {
            Predicate p = cb.equal(cont.get(Customer_.id),
                    id_customer);
            criteria = cb.and(criteria, p);
        }

        if (day_of != null) {
            Predicate p = cb.greaterThanOrEqualTo(contactRoot.get(Operation_.chronological),
                    day_of);
            criteria = cb.and(criteria, p);
        }

        if (day_to != null) {
            Predicate p = cb.lessThan(contactRoot.get(Operation_.chronological),
                    day_to);
            criteria = cb.and(criteria, p);
        }


        if (type != null) {
            Predicate p = cb.equal(contactRoot.get(Operation_.typeOperation),
                    type);
            criteria = cb.and(criteria, p);
        }


        if (status != null) {
            Predicate p = cb.like(contactRoot.get(Operation_.status),
                    status);
            criteria = cb.and(criteria, p);
        }


        criteriaQuery.where(criteria);

        List<Operation> result=em.createQuery(criteriaQuery).getResultList();



    }




    @Transactional(readOnly=true)
    @Override
    public  List<Operation> findOperationByModemTimeCustomer(String modem, Timestamp date){
        log.info("Finding по номеру модема и времени операции: " );

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Operation> criteriaQuery = cb.createQuery(Operation.class);
        Root<Operation> contactRoot = criteriaQuery.from(Operation.class);
        contactRoot.fetch(Operation_.measurementsSet, JoinType.LEFT);
        //contactRoot.fetch(Operation_.customer, JoinType.RIGHT);
        Join cont = contactRoot.join(Operation_.customer,JoinType.LEFT);

        criteriaQuery.select(contactRoot).distinct(true);

        Predicate criteria = cb.conjunction();
        if (modem != null) {
            Predicate p =cb.equal(cont.get(Customer_.telModem), modem);
            criteria = cb.and(criteria, p);
        }

        if (date != null) {
            Predicate p = cb.equal(contactRoot.get(Operation_.chronological),date);
            criteria = cb.and(criteria, p);
        }


        criteriaQuery.where(criteria);

        return em.createQuery(criteriaQuery).getResultList();

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
