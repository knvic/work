package ru.javabegin.training.vkt7.db;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.ProjectionList;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javabegin.training.db.ContactServiceImpl;
import ru.javabegin.training.vkt7.entities.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @PersistenceContext(unitName="emf_customer")
    @Qualifier(value = "emf_1")
    private EntityManager em_1;

    //private ProjectionList contactProjection;




    @Transactional(readOnly=true)

    @Override
    public List<Customer> findAll(){
        List<Customer> customers = em_1.createNamedQuery("Customer.findAll",
                Customer.class).getResultList();
        return customers;

    }
}
