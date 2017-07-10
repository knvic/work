package ru.javabegin.training.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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


    @Override
    public List<MyUsDet> getmydetails() {

        String a="";
        List<MyUsDet> list_ud=new ArrayList<MyUsDet>();
        final List<Object> principals = sessionRegistry.getAllPrincipals();



        for (Object principal : principals) {
            UserDetails authentication = (UserDetails) principal;
            //System.out.println("Candidate User: " + authentication.getUsername() + ". User for dropped " + username);


            for (GrantedAuthority auth :authentication.getAuthorities()) {
                a=a+(String)auth.getAuthority();
            }

            WebAuthenticationDetails details1 = (WebAuthenticationDetails) authentication.getAuthentication().getDetails();
            WebAuthenticationDetails details = (WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
            String userIPAddress = details.getRemoteAddress();
            System.out.println("userIPAddress=="+userIPAddress);



            MyUsDet ud =new MyUsDet(authentication.getUsername(),a,details.getRemoteAddress());
            list_ud.add(ud);
            a="";
        }
        System.out.println("Размер скписка: " + list_ud.size());

        return list_ud;
    }


    public void dropUser(String username) {
        final List<Object> principals = sessionRegistry.getAllPrincipals();


        for (Object principal : principals) {
            UserDetails authentication = (UserDetails) principal;
            System.out.println("Candidate User: " + authentication.getUsername() + ". User for dropped " + username);

            if (authentication.getUsername().equals(username)) {
                List<SessionInformation> userSessions = sessionRegistry.getAllSessions(principal, false);// получаем все сессии пользователя, кроме истекших
                for (SessionInformation sessInfo : userSessions)
                    sessInfo.expireNow();
            }
        }

        System.out.println("User: " + username + " dropped");
        return;
    }




}
