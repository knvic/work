package ru.javabegin.training.security;

import java.util.List;

/**
 * Created by Николай on 07.07.2017.
 */
public interface SecurityuserService {

    List<Object> getAllPrincipals();
    List<MyUsDet> getmydetails();


}
