package ru.javabegin.training.vkt7.db;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javabegin.training.db.*;
import ru.javabegin.training.tv7.entity.Operationtv7;
import ru.javabegin.training.tv7.entity.Operationtv7T;
import ru.javabegin.training.tv7.entity.Operationtv7T_;
import ru.javabegin.training.tv7.entity.Operationtv7_;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryService;
import ru.javabegin.training.vkt7.entities.*;
import ru.javabegin.training.vkt7.reports.DataCustomer;
import ru.javabegin.training.vkt7.reports.DataCustomerList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Николай on 25.09.2017.
 */

@Service("jpaCustomerService")
@Repository
@Transactional
public class CustomerServiceImpl implements CustomerService {

@Autowired
    AuxiliaryService auxiliaryService;
@Autowired
DataCustomerList dcs;


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

    @Transactional(readOnly=true)
    @Override
    public List<Customer> findAllWithDetail_not_block() {
        List<Customer> customerList = em.createNamedQuery(
                "Customer.findAllWithDetail_not_block", Customer.class).getResultList();
        return customerList;
    }


    @Transactional(readOnly=true)
    @Override
    public List<Customer> findAllWithDetailTv7() {
        List<Customer> customerList = em.createNamedQuery(
                "Customer.findAllWithDetail_tv7", Customer.class).getResultList();
        return customerList;
    }

    @Transactional(readOnly=true)
    @Override
    public List<Customer> findAllWithDetailTv7_not_block() {
        List<Customer> customerList = em.createNamedQuery(
                "Customer.findAllWithDetail_tv7_not_block", Customer.class).getResultList();
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
    @Transactional
    @Override
    public void update(Customer customer){



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




    @Transactional(readOnly=true)
    @Override
    public  Customer findByIdTv7(Long id) {
        TypedQuery<Customer> query = em.createNamedQuery(
                "Customer.findById_tv7", Customer.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }


    @Transactional(readOnly=true)
    @Override
    public  Customer findByIdTv7T(Long id) {
        TypedQuery<Customer> query = em.createNamedQuery(
                "Customer.findById_tv7_t", Customer.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }

    //////////////////API Criteria//////////////////////////////////////////



    @Transactional(readOnly=true)
    @Override
    public Customer findByName(String name){
        TypedQuery<Customer> query = em.createNamedQuery(
                "Customer.findByName", Customer.class);
        query.setParameter("firstName", name);

        return query.getSingleResult();
    }



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
     //   log.info("Finding operation by id: " );
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
       // log.info("Finding operation by id: " );
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
       // log.info("Finding operation by id: " );

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
      //  log.info("Finding operation by id: " );

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
        criteriaQuery.orderBy(cb.asc(contactRoot.get(Operation_.chronological)));

        List<Operation> result=em.createQuery(criteriaQuery).getResultList();
        return result;

    }


    @Transactional(readOnly=true)
    @Override
    public List<Operation> findOperation_betwen_data(Long id_customer, Timestamp day_of,Timestamp day_to, String type, String status){

     //   log.info("Finding operation by id: " );

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
        criteriaQuery.orderBy(cb.asc(contactRoot.get(Operation_.chronological)));

        List<Operation> result=em.createQuery(criteriaQuery).getResultList();
        return result;


    }



    @Transactional()
    @Override
    public void delete_clone_Operation(Long id_customer, Timestamp day_of,Timestamp day_to, String type, String status){

      //  log.info("Finding operation by id: " );

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

    @Transactional
    @Override
    public void deleteOperation(Long customerID, Long operationID){

        Customer cust = findById(customerID);
        Set<Operation> operationSet = cust.getOperationSet();
        Operation toDelOperation = null;
        for (Operation operation : operationSet) {
            if (operation.getId().equals(operationID)) {
                toDelOperation = operation;

            }
        }
        operationSet.remove(toDelOperation);
        save(cust);

    }


    @Transactional
    @Override
    public List<DataCustomer> customerOperationStatus(){
        List<Object> resultList=new ArrayList<>();

        Date date = new Date();
        date=auxiliaryService.addTime(date,"23");

        Timestamp date_ts=auxiliaryService.date_TimeStamp(date);

        //// Проверяем наличие TOTAL_MOTH за предыдущий месяц
        LocalDateTime date_moth=auxiliaryService.timestamp_to_localDateTime(date_ts);
        ///Дата предыдущего месяца
        Timestamp date_prevision_moth =auxiliaryService.getLastDayPrevisionMoth(date_ts);
        List<Date> date_daily_List =auxiliaryService.from_the_beginning_of_month(date);

        List<DataCustomer> dataCustomerList=new ArrayList<>();
        List<Customer> customerList=findAllWithDetail();
        String daily="OK";
        String quality="OK";
        int d=0;
        int q=0;
        for(Customer customer:customerList){
            daily="OK";
            quality="OK";

            d=0;
            q=0;
            // Проверяем наличие всех измерений daily
            List<Operation> operationList_daily=findOperation_betwen_data(customer.getId(),auxiliaryService.date_TimeStamp(date_daily_List.get(0)),date_ts,"daily","OK");
            for(Date day:date_daily_List) {

                for (Operation operation : operationList_daily) {
                    d=0;

                    if (operation.getChronological().equals(auxiliaryService.date_TimeStamp(day))){
                        d=1;
                            List<Measurements> measurementsList=new ArrayList<>(operation.getMeasurementsSet());
                            for(Measurements measurements:measurementsList){
                                if (!measurements.getQuality().equals("C0")){
                                   q=1;
                                    System.out.println("ERROR QUALITY за дату " +day);
                                   quality="error_QUALITY";
                                   break;
                                }
                            }
                    }

                }



            }
            if(d==0){
                //System.out.println("Измерение за дату " +day+" отсутствуют!!");
                daily="Данные не полные";
                //break;
            }
            if(q==1){
                //System.out.println("Измерение за дату " +day+" отсутствуют!!");
                quality="error_QUALITY";
                //break;
            }


            System.out.println("Customer " +customer.getFirstName());
            System.out.println(" " +customer.getFirstName());


            List<Operation> operationList_total= findOperation_daily(customer.getId(),date_prevision_moth, "total_moth","OK");
            System.out.println("размер массива operationList_total "+operationList_total.size() );


            DataCustomer dataCustomer=new DataCustomer();
            dataCustomer.setCustomer(customer);
            if (operationList_total.size()>0){
                System.out.println("Измерения TOTAL присутствуют ");
                dataCustomer.setMoth("OK");
            }else
            {
                System.out.println("Измерения TOTAL НЕТ ");
                dataCustomer.setMoth("Отсутствуют");
            }
            dataCustomer.setDaily_all(daily);
            dataCustomer.setQuality(quality);
            if (dataCustomer.getMoth().contains("OK")&dataCustomer.getDaily_all().contains("OK")){
                dataCustomer.setStatus("ГОТОВО");
            }else
            {dataCustomer.setStatus("Данные не полные");}

            dataCustomerList.add(dataCustomer);
        }



        dcs.setDataCustomerList(dataCustomerList);


        return dataCustomerList;
    }









    @Transactional
    @Override
    public void test_customerOperationStatus(){
        List<Object> resultList=new ArrayList<>();
        Date date = new Date();
        date=auxiliaryService.addTime(date,"23");

        System.out.println("Дата сегодня" +date);

        Timestamp date_ts=auxiliaryService.date_TimeStamp(date);

        //// Проверяем наличие TOTAL_MOTH за предыдущий месяц
        LocalDateTime date_moth=auxiliaryService.timestamp_to_localDateTime(date_ts);
        ///Дата предыдущего месяца
        Timestamp date_prevision_moth =auxiliaryService.getLastDayPrevisionMoth(date_ts);
        System.out.println("Дата конца предыдущегг месяца" +date_moth);

        List<Date> date_daily_List =auxiliaryService.from_the_beginning_of_month(date);

        date_daily_List.forEach(p-> System.out.println(p));



        List <Customer> customerList=findAllWithDetail();

        String daily="OK";
        int d=0;
        int q=0;
        for(Customer customer:customerList){
            daily="OK";
            d=0;
            q=0;
        List<Operation> operationList_daily=findOperation_betwen_data(customer.getId(),auxiliaryService.date_TimeStamp(date_daily_List.get(0)),date_ts,"daily","OK");
            for(Date day:date_daily_List) {

                List <Operation> list = operationList_daily
                        .parallelStream()
                        .filter(p -> p.getChronological().equals(auxiliaryService.date_TimeStamp(day))||auxiliaryService.date_TimeStamp(day).before(p.getBeginDayDate())||(p.getChronological().equals(auxiliaryService.date_TimeStamp(day))&&auxiliaryService.date_TimeStamp(day).before(p.getBeginDayDate())))
                        .collect(Collectors.toList());


                        //.anyMatch(p -> p.getChronological().equals(auxiliaryService.date_TimeStamp(day)));
                         if( list.size()>0){


                             if (auxiliaryService.date_TimeStamp(day).before(list.get(0).getBeginDayDate())){
                                 System.out.println(customer.getFirstName()+ " Измерений за дату " +day+" нет. Счетчик не работал!!");
                             }else {
                             System.out.println(customer.getFirstName()+ " Измерение за дату " +day+" есть");}

                             d=1;
                             boolean a= list.get(0).getMeasurementsSet()
                                     .parallelStream()
                                     .anyMatch(p ->!p.getQuality().equals("C0"));
                             if(a){
                                 q=1;
                                 System.out.println(customer.getFirstName()+ " Quality err за " +day+" есть  ERROR");}


                         }
                         else{ System.out.println(customer.getFirstName()+ " Измерение за дату " +day+" ОТСУТСТВУЮТ");}



                          /*.filter(p->{
                              System.out.println(customer.getFirstName()+ " Измерение за дату " +day+" есть");
                              return true;
                          });*/
//                        .findFirst().get().getMeasurementsSet().stream()
//                        .filter(p ->!p.getQuality().equals("C0"))
//
//                        .filter(p->{
//                            System.out.println(customer.getFirstName()+ "Quality err за " +day+" есть  ERROR");
//
//                            return true;
//                        })  ;


                        //.anyMatch(p -> p.getChronological().equals(auxiliaryService.date_TimeStamp(day)));




            }


          /*  if(d==0){
                //System.out.println("Измерение за дату " +day+" отсутствуют!!");
                daily="Данные не полные";
                break;
            }


            System.out.println("Customer " +customer.getFirstName());
            System.out.println(" " +customer.getFirstName());


            List<Operation> operationList_total= findOperation_daily(customer.getId(),date_prevision_moth, "total_moth","OK");
            System.out.println("размер массива operationList_total "+operationList_total.size() );


            DataCustomer dataCustomer=new DataCustomer();
            dataCustomer.setCustomer(customer);
            if (operationList_total.size()>0){
                System.out.println("Измерения TOTAL присутствуют ");
                dataCustomer.setMoth("OK");
            }else
            {
                System.out.println("Измерения TOTAL НЕТ ");
                dataCustomer.setMoth("NO");
            }
            dataCustomer.setDaily_all(daily);
            if (dataCustomer.getMoth().contains("OK")&dataCustomer.getDaily_all().contains("OK")){
                dataCustomer.setStatus("ГОТОВО");
            }else
            {dataCustomer.setStatus("НЕТ");}

            dataCustomerList.add(dataCustomer);*/
        }






     //   return dataCustomerList;
    }



    @Transactional
    @Override
    public  void deleteOperationQualityErr(){
        List<Object> resultList=new ArrayList<>();
        Date date = new Date();
        date=auxiliaryService.addTime(date,"23");

        System.out.println("Дата сегодня" +date);

        Timestamp date_ts=auxiliaryService.date_TimeStamp(date);

        //// Проверяем наличие TOTAL_MOTH за предыдущий месяц
        LocalDateTime date_moth=auxiliaryService.timestamp_to_localDateTime(date_ts);
        ///Дата предыдущего месяца
        Timestamp date_prevision_moth =auxiliaryService.getLastDayPrevisionMoth(date_ts);
        System.out.println("Дата конца предыдущегг месяца" +date_moth);

        List<Date> date_daily_List =auxiliaryService.from_the_beginning_of_month(date);

        date_daily_List.forEach(p-> System.out.println(p));



        List<Customer> customerList=findAllWithDetail();


        for(Customer customer:customerList){

            // Проверяем наличие всех измерений daily
            List<Operation> operationList_daily=findOperation_betwen_data(customer.getId(),auxiliaryService.date_TimeStamp(date_daily_List.get(0)),date_ts,"daily","OK");
               for (Operation operation : operationList_daily) {
                  List<Measurements> measurementsList=new ArrayList<>(operation.getMeasurementsSet());
                        for(Measurements measurements:measurementsList){
                                if (!measurements.getQuality().equals("C0")){
                                   deleteOperation(customer.getId(),operation.getId());
                                    break;
                                }
                        }

                }

        }

        //   return dataCustomerList;
    }



    public List<Operation> getOperations(String name) {
       // log.info("Finding content by first_name: " );


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



    @Transactional(readOnly=true)
    @Override
    public List<Operationtv7> findOperationtv7ByDate(String type, Long idCustomer, LocalDateTime ldt){
        // log.info("Finding по номеру модема и времени операции: " );
        Timestamp date=null;
        Timestamp date_before=null;
        Timestamp date_after=null;
        if (type.equals("day")||type.equals("total")){ldt=auxiliaryService.addTime_h_0(ldt,"0");}
        if (type.equals("month")){
            ldt=auxiliaryService.addTime((ldt.minusMonths(1)).with(TemporalAdjusters.lastDayOfMonth()),"0");
            System.out.println("Дата для МЕСЯЧНЫЙ АРХИВ (из метода):: :: "+ ldt);
            }

        try {
            date = auxiliaryService.localDateTime_TimeStamp(ldt);
            //date_before = auxiliaryService.localDateTime_TimeStamp(ldt.minusDays(1));
            date_before = auxiliaryService.localDateTime_TimeStamp(ldt);
            date_after = auxiliaryService.localDateTime_TimeStamp(auxiliaryService.addTime_h_0(ldt,"23"));
        }
        catch (Exception e){
            System.out.println("Дата не задана и равна NULL");
        }

        System.out.println();
        System.out.println();
        System.out.println("//////////////////////////////////////////////////////////////////////");
        System.out.println("//////////////////////////////////////////////////////////////////////");
        System.out.println(idCustomer + " дата  " + date);
        System.out.println(idCustomer + "  date_before  " + date_before);
        System.out.println(idCustomer + "  date_after  " + date_after);

        System.out.println("//////////////////////////////////////////////////////////////////////");
        System.out.println("//////////////////////////////////////////////////////////////////////");
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Operationtv7> criteriaQuery = cb.createQuery(Operationtv7.class);
        Root<Operationtv7> contactRoot = criteriaQuery.from(Operationtv7.class);
        // contactRoot.fetch(Operationv7_.measurementsSet, JoinType.LEFT);
        //contactRoot.fetch(Operation_.customer, JoinType.RIGHT);
        Join cont = contactRoot.join(Operationtv7_.customer,JoinType.LEFT);

        criteriaQuery.select(contactRoot).distinct(true);

        Predicate criteria = cb.conjunction();
        if (idCustomer != null) {
            Predicate p =cb.equal(cont.get(Customer_.id), idCustomer);
            criteria = cb.and(criteria, p);
        }

        /*if (date != null) {
            Predicate p = cb.equal(contactRoot.get(Operationtv7_.chronoligical),date);
            criteria = cb.and(criteria, p);
        }*/
        if (date != null) {
            Predicate p = cb.greaterThanOrEqualTo(contactRoot.get(Operationtv7_.chronoligical),date_before);
            criteria = cb.and(criteria, p);
        }

        if (date != null) {
            Predicate p = cb.lessThanOrEqualTo(contactRoot.get(Operationtv7_.chronoligical),date_after);
            criteria = cb.and(criteria, p);
        }



        criteriaQuery.where(criteria);

        return em.createQuery(criteriaQuery).getResultList();

    }


    @Transactional(readOnly=true)
    @Override
    public List<Operationtv7T> findOperationtv7TByDate( Long idCustomer, LocalDateTime ldt){
        // log.info("Finding по номеру модема и времени операции: " );
        Timestamp date=null;
        Timestamp date_before=null;
        Timestamp date_after=null;

        ldt=auxiliaryService.addTime_h_0(ldt,"0");

        try {
            date = auxiliaryService.localDateTime_TimeStamp(ldt);
            date_before = auxiliaryService.localDateTime_TimeStamp(ldt);
            date_after = auxiliaryService.localDateTime_TimeStamp(auxiliaryService.addTime_h_0(ldt,"23"));

        }
        catch (Exception e){
            System.out.println("Дата не задана и равна NULL");
        }


            System.out.println();
            System.out.println();
            System.out.println("//////////////////////////////////////////////////////////////////////");
            System.out.println("//////////////////////////////////////////////////////////////////////");
            System.out.println(idCustomer + " дата  " + date);
            System.out.println(idCustomer + "  date_before  " + date_before);
            System.out.println(idCustomer + "  date_after  " + date_after);

            System.out.println("//////////////////////////////////////////////////////////////////////");
            System.out.println("//////////////////////////////////////////////////////////////////////");



        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Operationtv7T> criteriaQuery = cb.createQuery(Operationtv7T.class);
        Root<Operationtv7T> contactRoot = criteriaQuery.from(Operationtv7T.class);
        // contactRoot.fetch(Operationv7_.measurementsSet, JoinType.LEFT);
        //contactRoot.fetch(Operation_.customer, JoinType.RIGHT);
        Join cont = contactRoot.join(Operationtv7T_.customer,JoinType.LEFT);

        criteriaQuery.select(contactRoot).distinct(true);

        Predicate criteria = cb.conjunction();
        if (idCustomer != null) {
            Predicate p =cb.equal(cont.get(Customer_.id), idCustomer);
            criteria = cb.and(criteria, p);
        }

        /*if (date != null) {
            Predicate p = cb.equal(contactRoot.get(Operationtv7T_.chronoligical),date);
            criteria = cb.and(criteria, p);
        }*/


        if (date != null) {
            Predicate p = cb.greaterThanOrEqualTo(contactRoot.get(Operationtv7T_.chronoligical),date_before);
            criteria = cb.and(criteria, p);
        }

        if (date != null) {
            Predicate p = cb.lessThanOrEqualTo(contactRoot.get(Operationtv7T_.chronoligical),date_after);
            criteria = cb.and(criteria, p);
        }


        criteriaQuery.where(criteria);

        return em.createQuery(criteriaQuery).getResultList();

    }

    @Transactional(readOnly=true)
    @Override
    public List<Operationtv7T> findOperationtv7TByDateEquals(Long idCustomer, LocalDateTime ldt){
        // log.info("Finding по номеру модема и времени операции: " );
        Timestamp date=null;
        Timestamp date_before=null;
        Timestamp date_after=null;
       ldt=auxiliaryService.addTime_h_0(ldt,"0");






        try {
            date = auxiliaryService.localDateTime_TimeStamp(ldt);
            date_before = auxiliaryService.localDateTime_TimeStamp(ldt.minusDays(1));
            date_after = auxiliaryService.localDateTime_TimeStamp(ldt.plusDays(1));

        }
        catch (Exception e){
            System.out.println("Дата не задана и равна NULL");
        }


            System.out.println();
            System.out.println();
            System.out.println("//////////////////////////////////////////////////////////////////////");
            System.out.println("//////////////////////////////////////////////////////////////////////");
            System.out.println(idCustomer + " дата  " + date);
            System.out.println(idCustomer + "  date_before  " + date_before);
            System.out.println(idCustomer + "  date_after  " + date_after);

            System.out.println("//////////////////////////////////////////////////////////////////////");
            System.out.println("//////////////////////////////////////////////////////////////////////");


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Operationtv7T> criteriaQuery = cb.createQuery(Operationtv7T.class);
        Root<Operationtv7T> contactRoot = criteriaQuery.from(Operationtv7T.class);
        // contactRoot.fetch(Operationv7_.measurementsSet, JoinType.LEFT);
        //contactRoot.fetch(Operation_.customer, JoinType.RIGHT);
        Join cont = contactRoot.join(Operationtv7T_.customer,JoinType.LEFT);

        criteriaQuery.select(contactRoot).distinct(true);

        Predicate criteria = cb.conjunction();
        if (idCustomer != null) {
            Predicate p =cb.equal(cont.get(Customer_.id), idCustomer);
            criteria = cb.and(criteria, p);
        }

        /*if (date != null) {
            Predicate p = cb.equal(contactRoot.get(Operationtv7T_.chronoligical),date);
            criteria = cb.and(criteria, p);
        }*/


        if (date != null) {
            Predicate p = cb.equal(contactRoot.get(Operationtv7T_.chronoligical),date_before);
            criteria = cb.and(criteria, p);
        }

        if (date != null) {
            Predicate p = cb.equal(contactRoot.get(Operationtv7T_.chronoligical),date_after);
            criteria = cb.and(criteria, p);
        }


        criteriaQuery.where(criteria);

        return em.createQuery(criteriaQuery).getResultList();

    }


    @Transactional(readOnly=true)
    @Override
    public List<Customer>  findTv7Customers() {
        List<Customer> customerList = em.createNamedQuery(
                "Customer.findTv7Cusromers", Customer.class).getResultList();
        return customerList;
    }


    @Transactional(readOnly=true)
    @Override
    public List<Operationtv7> findTv7_betwen_data(Long id_customer, Timestamp day_of,Timestamp day_to, String type, String status){

        //   log.info("Finding operation by id: " );

        // id=10L;




        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Operationtv7> criteriaQuery = cb.createQuery(Operationtv7.class);

        Root<Operationtv7> contactRoot = criteriaQuery.from(Operationtv7.class);
        //contactRoot.fetch(Operationtv7_.measurementsSet, JoinType.LEFT);
        Join cont = contactRoot.join(Operationtv7_.customer,JoinType.LEFT);
        criteriaQuery.select(contactRoot).distinct(true);

        Predicate criteria = cb.conjunction();

        if (id_customer != null) {
            Predicate p = cb.equal(cont.get(Customer_.id),
                    id_customer);
            criteria = cb.and(criteria, p);
        }

        if (day_of != null) {
            Predicate p = cb.greaterThanOrEqualTo(contactRoot.get(Operationtv7_.chronoligical),
                    day_of);
            criteria = cb.and(criteria, p);
        }

        if (day_to != null) {
            Predicate p = cb.lessThan(contactRoot.get(Operationtv7_.chronoligical),
                    day_to);
            criteria = cb.and(criteria, p);
        }


        if (type != null) {
            Predicate p = cb.equal(contactRoot.get(Operationtv7_.typeOperation),
                    type);
            criteria = cb.and(criteria, p);
        }


       /* if (status != null) {
            Predicate p = cb.like(contactRoot.get(Operation_.status),
                    status);
            criteria = cb.and(criteria, p);
        }*/


        criteriaQuery.where(criteria);
        criteriaQuery.orderBy(cb.asc(contactRoot.get(Operationtv7_.chronoligical)));

        List<Operationtv7> result=em.createQuery(criteriaQuery).getResultList();
        return result;


    }

    @Transactional(readOnly=true)
    @Override
    public List<Operationtv7T> findTv7T_betwen_data(Long id_customer, Timestamp day_of,Timestamp day_to){

        //   log.info("Finding operation by id: " );

        // id=10L;




        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Operationtv7T> criteriaQuery = cb.createQuery(Operationtv7T.class);

        Root<Operationtv7T> contactRoot = criteriaQuery.from(Operationtv7T.class);
        //contactRoot.fetch(Operationtv7_.measurementsSet, JoinType.LEFT);
        Join cont = contactRoot.join(Operationtv7T_.customer,JoinType.LEFT);
        criteriaQuery.select(contactRoot).distinct(true);

        Predicate criteria = cb.conjunction();

        if (id_customer != null) {
            Predicate p = cb.equal(cont.get(Customer_.id),
                    id_customer);
            criteria = cb.and(criteria, p);
        }

        if (day_of != null) {
            Predicate p = cb.greaterThanOrEqualTo(contactRoot.get(Operationtv7T_.chronoligical),
                    day_of);
            criteria = cb.and(criteria, p);
        }

        if (day_to != null) {
            Predicate p = cb.lessThan(contactRoot.get(Operationtv7T_.chronoligical),
                    day_to);
            criteria = cb.and(criteria, p);
        }



        criteriaQuery.where(criteria);
        criteriaQuery.orderBy(cb.asc(contactRoot.get(Operationtv7T_.chronoligical)));

        List<Operationtv7T> result=em.createQuery(criteriaQuery).getResultList();
        return result;


    }



}
