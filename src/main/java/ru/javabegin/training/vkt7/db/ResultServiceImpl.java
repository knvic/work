package ru.javabegin.training.vkt7.db;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javabegin.training.db.Contact;
import ru.javabegin.training.vkt7.entities.Result;
import ru.javabegin.training.vkt7.entities.Result_old;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Николай on 25.09.2017.
 */


@Service("jpaResultService")
@Repository
@Transactional
public class ResultServiceImpl implements ResultService {
    final static String ALL_RESULT_NATIVE_QUERY = "select id, first_name, last_name, tel_number, tel_modem, unit_number, e_mail, version from customer";

    private Log log = LogFactory.getLog(CustomerServiceImpl.class);

    @PersistenceContext(unitName="emf_customer")
    @Qualifier(value = "emf_1")
    private EntityManager em_1;

    //private ProjectionList contactProjection;




    @Transactional(readOnly=true)
    @Override
    public List<Result> findAll(){
        List<Result> results = em_1.createNamedQuery("Result.findAll",
                Result.class).getResultList();
        return results;

    }


    @Override
    public Result save(Result result) {
        if (result.getId() == 0) {
            log.info("Inserting new contact");
            em_1.persist(result);
        } else {
            em_1.merge(result);
            log.info("Updating existing contact");
        }

        log.info("Contact saved with id: " + result.getId());

        return result;
    }


}
