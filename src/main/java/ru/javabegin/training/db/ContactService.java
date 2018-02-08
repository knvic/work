package ru.javabegin.training.db;

import ru.javabegin.training.db.projections.Content_p;
import ru.javabegin.training.db.projections.Projection;
import ru.javabegin.training.db.projections.Projection1;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();
    List<Contact> findAllWithDetail();
    Contact findById(Long id);
    Contact save(Contact contact);
    void delete(Contact contact);
    List<Contact> findAllByNativeQuery();
    List<Contact> findByCriteriaQuery(String firstName, String lastName);
    List<Contact> findByCriteriaQuery1(Integer version);
    List<Contact> findByCriteriaQuery2();
    List<Contact> findByCriteriaQuery3(String firstName);
    List<Contact> findByCriteriaQuery4(String str);
    List<Projection> findByCriteriaQuery5();
    List<Contact> findByCriteriaQuery6(String firstName, String lastName);


    List<Projection1> findByCriteriaQuery7(String firstName, String lastName);
    List<Hobby> findByCriteriaQuery8();
    List<Contact> findByCriteriaQuery9();
    List<Contact> findByCriteriaQuery10(Hobby hobby);
    List<Contact> findByCriteriaQuery11(String text);
    List<Contact> findByCriteriaQuery12(String text);
    List<Content> findByfirstname(String name);


    Object getContent(Long id);

}
