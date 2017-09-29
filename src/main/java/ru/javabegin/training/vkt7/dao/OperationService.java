package ru.javabegin.training.vkt7.dao;

import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Operation;

import java.util.List;

/**
 * Created by Николай on 29.09.2017.
 */
public interface OperationService  {


    List<Operation> findAll();
    Operation save(Operation operation);
}
