package ru.javabegin.training.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Николай on 07.07.2017.
 */
@Service("securityService")
public class SecurityServiceImpl implements SecurityService {
@Autowired
    private SessionRegistry sessionRegistry;

    @Override
    public List<Object> getAllPrincipals() {
        final List<Object> principals = sessionRegistry.getAllPrincipals();
    return principals;
    }

}
