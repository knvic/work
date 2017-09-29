package ru.javabegin.training.vkt7.entities;

import ru.javabegin.training.db.Contact;

import java.util.List;

/**
 * Created by Николай on 26.09.2017.
 */
public interface TestService {
    List<Test> findAll();
       Test save(Test test);
    Test findById(Long id);
}
