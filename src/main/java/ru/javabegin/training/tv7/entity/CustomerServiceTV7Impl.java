package ru.javabegin.training.tv7.entity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryService;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.db.CustomerServiceImpl;
import ru.javabegin.training.vkt7.entities.Customer_;
import ru.javabegin.training.vkt7.reports.DataCustomerList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;



public abstract class CustomerServiceTV7Impl implements CustomerService {

    @Autowired
    AuxiliaryService auxiliaryService;


    /*
    @Transactional(readOnly=true)
    @Override
    public List<Operationtv7> findOperationtv7ByDate(String type, Long idCustomer, LocalDateTime ldt){
       // log.info("Finding по номеру модема и времени операции: " );
        ldt=auxiliaryService.addTime(ldt,"23");

        Timestamp date=auxiliaryService.localDateTime_TimeStamp(ldt);
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

        if (date != null) {
            Predicate p = cb.equal(contactRoot.get(Operationtv7_.chronoligical),date);
            criteria = cb.and(criteria, p);
        }


        criteriaQuery.where(criteria);

        return em.createQuery(criteriaQuery).getResultList();

    }

*/
}
