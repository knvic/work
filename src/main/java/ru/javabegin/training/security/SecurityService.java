package ru.javabegin.training.security;

import org.springframework.security.core.session.SessionRegistry;

import java.security.Principal;
import java.util.List;
import java.util.Set;

/**
 * Created by Николай on 07.07.2017.
 */
public interface SecurityService {
    List<Object> getAllPrincipals();
    List<MyUsDet> getmydetails();


}
