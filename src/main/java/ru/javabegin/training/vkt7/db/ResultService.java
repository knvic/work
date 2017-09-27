package ru.javabegin.training.vkt7.db;

import ru.javabegin.training.vkt7.entities.Result;
import ru.javabegin.training.vkt7.entities.Result_old;

import java.util.List;

/**
 * Created by Николай on 25.09.2017.
 */
public interface ResultService {

    List<Result> findAll();
    //Result findById(Long id);
    Result save(Result result);
   // void delete(Result result);
}
