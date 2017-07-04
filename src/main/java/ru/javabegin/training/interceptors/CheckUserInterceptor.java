package ru.javabegin.training.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ru.javabegin.training.db.*;
import ru.javabegin.training.objects.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CheckUserInterceptor extends HandlerInterceptorAdapter { // implements HandlerInterceptor{


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		//System.out.println("sessionFactory = " + sessionFactory);

		if (request.getRequestURI().contains("check-user")) {

			User user = (User) modelAndView.getModel().get("user");
			if (user == null || !user.isAdmin()) {
				response.sendRedirect(request.getContextPath() + "/failed");
			}

		}
	}

}
