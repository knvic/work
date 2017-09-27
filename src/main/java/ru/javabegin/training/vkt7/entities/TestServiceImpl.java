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
import java.util.List;

/**
 * Created by Николай on 26.09.2017.
 */

@Service("jpaTestService")
@Repository
@Transactional
public class TestServiceImpl implements TestService {

    private Log log = LogFactory.getLog(TestServiceImpl.class);

    @PersistenceContext(unitName="emf_test")
    @Qualifier(value = "emf_2")
    private EntityManager em;

    @Transactional(readOnly=true)
    @Override
    public List<Test> findAll() {
        List<Test> test = em.createNamedQuery("Test.findAll",
                Test.class).getResultList();
        return test;
    }


    @Override
    public Test save(Test test) {
        if (test.getId() == null) {
            log.info("Inserting new contact");
            em.persist(test);
        } else {
            em.merge(test);
            log.info("Updating existing contact");
        }

        log.info("Contact saved with id: " + test.getId());

        return test;
    }
}
