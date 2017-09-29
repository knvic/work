package ru.javabegin.training.vkt7.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javabegin.training.vkt7.entities.Operation;
import ru.javabegin.training.vkt7.entities.Test;
import ru.javabegin.training.vkt7.entities.TestServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Николай on 29.09.2017.
 */


@Service("jpaOperationService")
@Repository
@Transactional
public class OperationServiceImpl implements OperationService {

    private Log log = LogFactory.getLog(OperationServiceImpl.class);

    @PersistenceContext(unitName="emf_contact")
    @Qualifier(value = "emf")

    private EntityManager em_2;


    @Transactional(readOnly=true)
    @Override
    public List<Operation> findAll() {
        List<Operation> operationList = em_2.createNamedQuery("Operation.findAll",
                Operation.class).getResultList();
        return operationList;
    }


    @Override
    public Operation save(Operation operation) {
        if (operation.getId() == null) {
            log.info("Inserting new contact");
            em_2.persist(operation);
        } else {
            em_2.merge(operation);
            log.info("Updating existing contact");
        }

        log.info("Contact saved with id: " + operation.getId());

        return operation;
    }

}
