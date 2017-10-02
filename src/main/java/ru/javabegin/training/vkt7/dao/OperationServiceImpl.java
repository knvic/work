package ru.javabegin.training.vkt7.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javabegin.training.db.Contact_;
import ru.javabegin.training.vkt7.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


    @Transactional(readOnly=true)
    @Override
    public List<Operation> findAllWithDetail() {
        List<Operation> operationList = em_2.createNamedQuery(
                "Operation.findAllWithDetail", Operation.class).getResultList();
        return operationList;
    }


    @Transactional(readOnly=true)
    @Override
    public List<Measurements> getMeasurementsByOperatoinId(Long id) {
        log.info("Finding content by first_name: " );


        CriteriaBuilder cb = em_2.getCriteriaBuilder();
        CriteriaQuery<Operation> criteriaQuery = cb.createQuery(Operation.class);
        Root<Operation> contactRoot = criteriaQuery.from(Operation.class);
        contactRoot.fetch(Operation_.measurementsSet, JoinType.LEFT);

        //Join meas = contactRoot.join(Operation_.measurementsSet);


        //ParameterExpression<Long> parametr = cb.parameter(Long.class);
        ParameterExpression<Long> parametr = cb.parameter(Long.class);
        Predicate condition = cb.equal(contactRoot.get(Operation_.id), parametr);
        //Predicate condition = cb.like(cont.get(Customer_.firstName), parametr) ;
        criteriaQuery.where(condition);
        TypedQuery<Operation> q = em_2.createQuery(criteriaQuery);
        //List<Operation> result = q.setParameter(parametr,id ).getResultList();
        List<Operation> result = q.setParameter(parametr,id ).getResultList();
        List<Measurements> result_measur=new ArrayList<>();
       // result_measur=result_measur.addAll(result.get(0).getMeasurementsSet());

        result_measur = result.get(0).getMeasurementsSet().stream().collect(Collectors.toList());


        return result_measur;
    }



    ////////Вспомогательные


    public void listOperationWithDetail(List<Operation> operationList) {
        System.out.println("");
        System.out.println("Listing operation with details:");

        for (Operation oper: operationList) {
            System.out.println(oper);
            if (oper.getMeasurementsSet() != null) {
                for (Measurements measurements:
                        oper.getMeasurementsSet()) {
                    System.out.println(measurements);
                }
            }

             System.out.println();
        }
    }






}
