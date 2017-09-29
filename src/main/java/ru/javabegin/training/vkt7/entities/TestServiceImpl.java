package ru.javabegin.training.vkt7.entities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javabegin.training.db.Contact;
import ru.javabegin.training.db.ContactServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Николай on 26.09.2017.
 */

@Service("jpaTestService")
@Repository
@Transactional
public class TestServiceImpl implements TestService {

    private Log log = LogFactory.getLog(TestServiceImpl.class);

    @PersistenceContext(unitName="emf_contact")
    @Qualifier(value = "emf")

    private EntityManager em_1;

    @Transactional(readOnly=true)
    @Override
    public List<Test> findAll() {
        List<Test> test = em_1.createNamedQuery("Test.findAll",
                Test.class).getResultList();
        return test;
    }


    @Override
    public Test save(Test test) {
        if (test.getId() == null) {
            log.info("Inserting new contact");
            em_1.persist(test);
        } else {
            em_1.merge(test);
            log.info("Updating existing contact");
        }

        log.info("Contact saved with id: " + test.getId());

        return test;
    }


    @Transactional(readOnly=true)
    @Override
    public Test findById(Long id) {
        TypedQuery<Test> query = em_1.createNamedQuery(
                "Test.findById",Test.class);
        query.setParameter("id", id);

        return query.getSingleResult();
    }


}
