package ru.javabegin.training.db;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.ProjectionList;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javabegin.training.db.projections.Content_p;
import ru.javabegin.training.db.projections.Projection;
import ru.javabegin.training.db.projections.Projection1;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Service("jpaContactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {
    final static String ALL_CONTACT_NATIVE_QUERY = "select id, first_name, last_name, birth_date, version from contact";

    private Log log = LogFactory.getLog(ContactServiceImpl.class);

    /*@PersistenceContext(unitName="emf_contact")
    @Qualifier(value = "emf")
*/
    @Qualifier(value = "emf_1")
    @PersistenceContext(unitName="emf_customer")

    private EntityManager em;




    private ProjectionList contactProjection;




    @Transactional(readOnly=true)
    @Override
    public List<Contact> findAll() {
        List<Contact> contacts = em.createNamedQuery("Contact.findAll",     
            Contact.class).getResultList();
        return contacts;
    }
    
    @Transactional(readOnly=true)
    @Override
    public List<Contact> findAllWithDetail() {
        List<Contact> contacts = em.createNamedQuery(
            "Contact.findAllWithDetail", Contact.class).getResultList();
        return contacts;
    }

    @Transactional(readOnly=true)
    @Override
    public Contact findById(Long id) {
        TypedQuery<Contact> query = em.createNamedQuery(
            "Contact.findById", Contact.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    public Contact save(Contact contact) {
        if (contact.getId() == null) {
            log.info("Inserting new contact");
            em.persist(contact);
        } else {
            em.merge(contact);
            log.info("Updating existing contact");
        }

        log.info("Contact saved with id: " + contact.getId());

        return contact;
    }

    @Override
    public void delete(Contact contact) {
        Contact mergedContact = em.merge(contact);
        em.remove(mergedContact);

        log.info("Contact with id: " + contact.getId()  + " deleted successfully");
    }

    @Transactional(readOnly=true)
    @Override
    public List<Contact> findAllByNativeQuery() {
        return em.createNativeQuery(ALL_CONTACT_NATIVE_QUERY, 
            "contactResult").getResultList();
    }   

    @Transactional(readOnly=true)
    @Override
    public List<Contact> findByCriteriaQuery(String firstName, String lastName) {
        log.info("Finding contact for firstName: " + firstName 
                  + " and lastName: " + lastName);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contact> criteriaQuery = cb.createQuery(Contact.class);
        Root<Contact> contactRoot = criteriaQuery.from(Contact.class);
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

        criteriaQuery.where(criteria);

        return em.createQuery(criteriaQuery).getResultList();
    }


    @Transactional(readOnly=true)
    @Override
    public List<Contact> findByCriteriaQuery1(Integer version) {
        log.info("Finding contact for version: " );

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contact> criteriaQuery = cb.createQuery(Contact.class);
        Root<Contact> contactRoot = criteriaQuery.from(Contact.class);
        //contactRoot.fetch(Contact_.contactTelDetails, JoinType.LEFT);
        //contactRoot.fetch(Contact_.hobbies, JoinType.LEFT);

        //criteriaQuery.select(contactRoot).distinct(true);


        ParameterExpression<Integer> ver = cb.parameter(Integer.class);
        // Predicate condition = cb.gt(contactRoot.get(Contact_.version), ver);
        Predicate condition = cb.equal(contactRoot.get(Contact_.version), ver);
        criteriaQuery.where(condition);
        TypedQuery<Contact> q = em.createQuery(criteriaQuery);
        List<Contact> result = q.setParameter(ver, version).getResultList();




        return result;
    }
    @Transactional(readOnly=true)
    @Override
    public List<Contact> findByCriteriaQuery2() {
        log.info("Finding contact for version: " );

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contact> criteriaQuery = cb.createQuery(Contact.class);
        Root<Contact> contactRoot = criteriaQuery.from(Contact.class);
        criteriaQuery.select(contactRoot).where(cb.equal(contactRoot.get("firstName"),"Марина"));

        TypedQuery<Contact> typedQuery = em.createQuery(criteriaQuery);
        List<Contact> result = typedQuery.getResultList();

       // criteriaQuery.where(cb.in(firstName).value("М").value("S").value("J"));


        //contactRoot.fetch(Contact_.contactTelDetails, JoinType.LEFT);
        //contactRoot.fetch(Contact_.hobbies, JoinType.LEFT);

        //criteriaQuery.select(contactRoot).distinct(true);


/*
        ParameterExpression<Character> ver = cb.parameter(Character.class);
        // Predicate condition = cb.gt(contactRoot.get(Contact_.version), ver);
        Predicate condition = cb.equal(contactRoot.get(Contact_.firstName), ver);
        criteriaQuery.where(condition);
        TypedQuery<Contact> q = em.createQuery(criteriaQuery);
        List<Contact> result = q.setParameter(ver, character).getResultList();
*/


       /* CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Teacher> query = cb.createQuery(Teacher.class);
        Root<Teacher> teacher = query.from(Teacher.class);
        query.select(teacher).where(cb.equal(teacher.get("firstName"),"prasad"));

        TypedQuery<Teacher> typedQuery = em.createQuery(query);
        List<Teacher> teachers = (List<Teacher>) typedQuery.getResultList();

        for (Teacher t : teachers) {
            System.out.println(t.getFirstName() + " "
                    + t.getLastName() + " " + t.getSalary());
        }
*/



        return result;


    }


    @Transactional(readOnly=true)
    @Override
    public List<Contact> findByCriteriaQuery3(String firstName) {
        log.info("Finding contact for version: " );

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contact> criteriaQuery = cb.createQuery(Contact.class);
        Root<Contact> contactRoot = criteriaQuery.from(Contact.class);
        //contactRoot.fetch(Contact_.contactTelDetails, JoinType.LEFT);
        //contactRoot.fetch(Contact_.hobbies, JoinType.LEFT);

        //criteriaQuery.select(contactRoot).distinct(true);


        ParameterExpression<String> parametr = cb.parameter(String.class);
        // Predicate condition = cb.gt(contactRoot.get(Contact_.version), ver);
        Predicate condition = cb.equal(contactRoot.get(Contact_.firstName), parametr);
        criteriaQuery.where(condition);
        TypedQuery<Contact> q = em.createQuery(criteriaQuery);
        List<Contact> result = q.setParameter(parametr, firstName).getResultList();


        return result;
    }


        /*
//////////////////////////// LIKE с параметром в Criteria API
*/

    @Transactional(readOnly=true)
    @Override
    public List<Contact> findByCriteriaQuery4(String str) {
        log.info("Finding contact for version LIKE: " );

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contact> criteriaQuery = cb.createQuery(Contact.class);
        Root<Contact> contactRoot = criteriaQuery.from(Contact.class);

        ParameterExpression<String> parametr = cb.parameter(String.class);

        criteriaQuery.select(contactRoot);
        criteriaQuery.where(cb.like(contactRoot.get(Contact_.firstName),parametr ));

        TypedQuery<Contact> typedQuery = em.createQuery(criteriaQuery);

        List<Contact> result = typedQuery.setParameter(parametr, str+"%").getResultList();




        return result;
    }


    /*
////////////////////////////Проекции в Criteria API
*/

    @Transactional(readOnly=true)
    @Override
    public List<Projection> findByCriteriaQuery5() {
        log.info("Finding item for use projection: " );

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Projection> criteriaQuery = cb.createQuery(Projection.class);
        Root<Contact> contactRoot = criteriaQuery.from(Contact.class);


        criteriaQuery.select(cb.construct(Projection.class,contactRoot.get(Contact_.firstName),contactRoot.get(Contact_.lastName)));


        Predicate condition = cb.like(contactRoot.get(Contact_.firstName),"Ма%");



        criteriaQuery.where(condition);

        //TypedQuery<Contact> typedQuery = em.createQuery(criteriaQuery);

        List<Projection> result =em.createQuery(criteriaQuery).getResultList();




        return result;
    }



    @Transactional(readOnly=true)
    @Override
    public List<Contact> findByCriteriaQuery6(String firstName, String lastName) {
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

        Predicate p = cb.equal(tRoot.get(ContactTelDetail_.telType),
                "hom");
        criteria = cb.and(criteria, p);

*//*


        criteriaQuery.where(criteria);
        */


        List<Contact> result=em.createQuery(criteriaQuery).getResultList();
        return result;
    }






    @Transactional(readOnly=true)
    @Override
    public List<Projection1> findByCriteriaQuery7(String firstName, String lastName) {
        log.info("Finding contact for firstName: " + firstName
                + " and lastName: " + lastName);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Projection1> criteriaQuery = cb.createQuery(Projection1.class);
        Root<Contact> contactRoot = criteriaQuery.from(Contact.class);
        //Join tel = contactRoot.join(Contact_.contactTelDetails);
        //criteriaQuery.where(cb.equal(tel.get(ContactTelDetail_.telNumber), "89193452346"));

        //contactRoot.fetch(Contact_.contactTelDetails, JoinType.LEFT);
        //contactRoot.fetch(Contact_.hobbies, JoinType.LEFT);
        Join tel = contactRoot.join(Contact_.contactTelDetails);
        Join hob = contactRoot.join(Contact_.hobbies);
        //criteriaQuery.select(contactRoot).distinct(true);


        criteriaQuery.select(cb.construct(Projection1.class,contactRoot.get(Contact_.firstName),contactRoot.get(Contact_.lastName),tel.get(ContactTelDetail_.telType),tel.get(ContactTelDetail_.telNumber),hob.get(Hobby_.hobbyId)));

        //criteriaQuery.where(cb.equal(tel.get(ContactTelDetail_.telNumber), "89193452346"));

        Predicate criteria = cb.conjunction();

        Predicate p= cb.like(tel.get(ContactTelDetail_.telType), "д%");
        criteria = cb.and(criteria, p);


       p= cb.like(hob.get(Hobby_.hobbyId), "к%");
        criteria = cb.and(criteria, p);


        //criteriaQuery.where(cb.like(tel.get(ContactTelDetail_.telType), "д%"));


        criteriaQuery.where(criteria);



        List<Projection1> result=em.createQuery(criteriaQuery).getResultList();
        return result;
    }



    @Transactional(readOnly=true)
    @Override
    public List<Hobby> findByCriteriaQuery8() {
        log.info("Finding all Hobbies: " );

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Hobby> criteriaQuery = cb.createQuery(Hobby.class);
        Root<Hobby> contactRoot = criteriaQuery.from(Hobby.class);
       //criteriaQuery.select(contactRoot).distinct(true);

        criteriaQuery.select(contactRoot);
        criteriaQuery.orderBy(cb.asc(contactRoot.get(Hobby_.hobbyId)));

        List<Hobby> result=em.createQuery(criteriaQuery).getResultList();
        return result;
    }


    @Transactional(readOnly=true)
    @Override
    public List<Contact> findByCriteriaQuery9() {
        log.info("Finding all Contacts: " );

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contact> criteriaQuery = cb.createQuery(Contact.class);
        Root<Contact> contactRoot = criteriaQuery.from(Contact.class);
        //criteriaQuery.select(contactRoot).distinct(true);

        criteriaQuery.select(contactRoot);
        criteriaQuery.orderBy(cb.asc(contactRoot.get(Contact_.firstName)));

        List<Contact> result=em.createQuery(criteriaQuery).getResultList();
        return result;
    }

    ///////////////////Find contact by Hobby!!!!!!!!!!!!!!!!!!!!!!!

    @Transactional(readOnly=true)
    @Override
    public List<Contact> findByCriteriaQuery10(Hobby hobby) {
        log.info("Finding contact by Hobby: " + hobby);
String par= hobby.getHobbyId();
        //String par = "Кулинария";
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contact> criteriaQuery = cb.createQuery(Contact.class);
        Root<Contact> contactRoot = criteriaQuery.from(Contact.class);
        Join hob = contactRoot.join(Contact_.hobbies);
        //criteriaQuery.select(contactRoot).distinct(true);

        ParameterExpression<String> parametr = cb.parameter(String.class);
        // Predicate condition = cb.gt(contactRoot.get(Contact_.version), ver);
        Predicate condition = cb.equal(hob.get(Hobby_.hobbyId), parametr);
        criteriaQuery.where(condition);
        TypedQuery<Contact> q = em.createQuery(criteriaQuery);
        List<Contact> result = q.setParameter(parametr, par).getResultList();


        return result;


    }
///////////////////Find contact by  lastname!!!!!!!!!!!!!!!!!!!!!!!

    @Transactional(readOnly=true)
    @Override
    public List<Contact> findByCriteriaQuery11(String text) {
        log.info("Finding contact by text: " + text);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contact> criteriaQuery = cb.createQuery(Contact.class);
        Root<Contact> contactRoot = criteriaQuery.from(Contact.class);

        //criteriaQuery.select(contactRoot).distinct(true);

        ParameterExpression<String> parametr = cb.parameter(String.class);
        // Predicate condition = cb.gt(contactRoot.get(Contact_.version), ver);
        Predicate condition = cb.like(contactRoot.get(Contact_.lastName), parametr);
        criteriaQuery.where(condition);
        TypedQuery<Contact> q = em.createQuery(criteriaQuery);
        List<Contact> result = q.setParameter(parametr, "%"+text+"%").getResultList();


        return result;


    }


    ///////////////////Find contact by  tel!!!!!!!!!!!!!!!!!!!!!!!

    @Transactional(readOnly=true)
    @Override
    public List<Contact> findByCriteriaQuery12(String tel_namer) {
        log.info("Finding contact by tel namber: " );


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contact> criteriaQuery = cb.createQuery(Contact.class);
        Root<Contact> contactRoot = criteriaQuery.from(Contact.class);
        Join tel = contactRoot.join(Contact_.contactTelDetails);
        //criteriaQuery.select(contactRoot).distinct(true);

        ParameterExpression<String> parametr = cb.parameter(String.class);
        // Predicate condition = cb.gt(contactRoot.get(Contact_.version), ver);
        Predicate condition = cb.like(tel.get(ContactTelDetail_.telNumber), parametr);
        criteriaQuery.where(condition);
        TypedQuery<Contact> q = em.createQuery(criteriaQuery);
        List<Contact> result = q.setParameter(parametr, "%"+tel_namer+"%").getResultList();


        return result;


    }



    @Transactional(readOnly=true)
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


    }




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

}
