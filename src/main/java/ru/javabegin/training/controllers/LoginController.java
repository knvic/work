package ru.javabegin.training.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import ru.javabegin.training.db.Contact;
import ru.javabegin.training.db.ContactService;
import ru.javabegin.training.db.test.TestBean11;
import ru.javabegin.training.objects.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LoginController {

	@Autowired
	ContactService contactService;

	@Autowired
	private AccessDecisionManager accessDecisionManager;


	/*@Resource(name="sessionRegistry")
	private SessionRegistryImpl sessionRegistry;*/

	@Autowired
	private SessionRegistry sessionRegistry;
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
/*

	@RequestMapping(value = "/1", method = RequestMethod.GET)
	public ModelAndView main(HttpSession session) {
		return new ModelAndView("login", "user", new User());
	}
*/








@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		// пока русский текст без локализации, хотя так не рекомендуется!
		if (user != null) {
			model.addObject("errorMsg", user.getName() + " у вас нет доступа к этой странице!");
		} else {
			model.addObject("errorMsg", "У вас нет доступа к этой странице!");
		}

		model.setViewName("/accessDenied");
		return model;

	}

	@RequestMapping(value = "/calc", method = RequestMethod.GET)
	public String calc(HttpSession session) {
		return "calc";
	}
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error(HttpSession session) {
		return "error";
	}

	@RequestMapping(value = "/1", method = RequestMethod.GET)
	public ModelAndView main(HttpSession session) {
		return new ModelAndView("login");
	}


	@RequestMapping(value = "/2", method = RequestMethod.GET)
	public ModelAndView log1(HttpSession session) {
		System.out.println(accessDecisionManager);
		return new ModelAndView("login1","user", new User());
	}
	/*@RequestMapping(value = "/3", method = RequestMethod.POST)
	public ModelAndView secondPage(@ModelAttribute("user") User user, HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.addObject("user", user );
		model.setViewName("secondPage");

		return model;
	}*/
	@RequestMapping(value = "/3", method = RequestMethod.GET)
	public String secondPage() {


		return "redirect:secondPage";
	}


	@RequestMapping(value = "/check-user", method = RequestMethod.POST)
	public ModelAndView checkUser(@ModelAttribute("user") User user) {
		return new ModelAndView("main", "user", user);
	}

	@RequestMapping(value = "/failed", method = RequestMethod.GET)
	public ModelAndView failed() {
		return new ModelAndView("login-failed", "message", "Login failed!");
	}


	@RequestMapping(value = "/db", method = RequestMethod.GET)
	public ModelAndView ById() {
		ModelAndView model = new ModelAndView();
		List<Contact> contact = contactService.findByCriteriaQuery("John", "Smith");


		model.addObject("contact", contact );
		model.setViewName("secondPage");


		//System.out.println(TestBean11.test());
		 String uname;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			uname = ((UserDetails)principal).getUsername();
		} else {
			uname = principal.toString();
		}

		System.out.println("");
		System.out.println("Username :"+uname);


		return model;
	}





	@RequestMapping(value = "/p", method = RequestMethod.GET)
	public String allPrincipals(HttpServletRequest request, HttpServletResponse response) {

	/*	ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		SessionRegistry sReg = (SessionRegistry) appContext.getBean("sessionRegistry");

		List<Object> pr =sReg.getAllPrincipals();
		System.out.println("pr="+pr);
		for(Object principal1: sReg.getAllPrincipals())
			System.out.println("Principal: "+principal1.toString());

		//List<String> a = AllPrinc.getUsersFromSessionRegistry();*/

		//List<Object> principals =sessionRegistry.getAllPrincipals();
	/*	List<String> a= sessionRegistry.getAllPrincipals().stream()
				.filter(u -> !sessionRegistry.getAllSessions(u, false).isEmpty())
				.map(Object::toString)
				.collect(Collectors.toList());

		System.out.println("a="+a);*/


		System.out.println("Principals: "+sessionRegistry.getAllPrincipals().size());


		getActiveSessions(sessionRegistry);




		return "main";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String allPrincipals1() {


	//	System.out.println("Principals: "+sessionRegistry.getAllPrincipals().size());


		getActiveSessions(sessionRegistry);




		return "/app/main";
	}


	private List<SessionInformation> getActiveSessions(SessionRegistry sessionRegistry) {
		final List<Object> principals = sessionRegistry.getAllPrincipals();
		if (principals != null) {
			System.out.println("Principals не равен нулю");
			List<SessionInformation> sessions = new ArrayList<>();
			for (Object principal : principals) {
				sessions.addAll(sessionRegistry.getAllSessions(principal,     false));
			}
			return sessions;
		}
		return Collections.emptyList();
	}




	public ModelAndView exampleClick() {

		return new ModelAndView("secondPage");
	}

	public void printUserDetails() {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		logger.info("password = " + userDetails.getPassword());
		logger.info("username = " + userDetails.getUsername());

		for (GrantedAuthority auth : userDetails.getAuthorities()) {
			logger.info(auth.getAuthority());
		}

	}


/*
	@RequestMapping(value = "/f_all", method = RequestMethod.GET)
	public ModelAndView f_all() {


		ModelAndView model = new ModelAndView();
		Set<ContactTelDetail> tel=null;
		List<Set<ContactTelDetail>> r = new ArrayList<Set<ContactTelDetail>>();
		Map<Contact,ContactTelDetail> res = new HashMap<Contact, ContactTelDetail>();
		List<Contact> contact = contactDao.findAllWithDetail();
		for (Contact c:contact) {
			if (c.getContactTelDetails() !=null){
				tel=c.getContactTelDetails();
				r.add(tel);

			}

		}


		model.addObject("contact", contact );
		model.addObject("r", r );
		model.setViewName("db");

		return model;
	}


	@RequestMapping(value = "/db", method = RequestMethod.GET)
	public ModelAndView ById() {
		ModelAndView model = new ModelAndView();
		Contact contact = contactDao.findById(1l);
		model.addObject("contact", contact );
		model.setViewName("db");
		Set<ContactTelDetail> tel=contact.getContactTelDetails();
		model.addObject("tel", tel );
		Set<Hobby> hobbies =contact.getHobbies();
		model.addObject("hobbies", hobbies );

		return model;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addContact() {

		Contact contact = new Contact();
		contact.setFirstName("Максим");
		contact.setLastName("Соломинцев");
		contact.setBirthDate(new Date());

		ContactTelDetail  contactTelDetail = new ContactTelDetail("дом", "8928345766");
		contact.addContactTelDetail(contactTelDetail);
		contactTelDetail = new ContactTelDetail("раб", "89193452346");
		contact.addContactTelDetail(contactTelDetail);
		*//*Hobby hobby = new Hobby();
		hobby.setHobbyId("Кулинария");*//*


		contactDao.save(contact);
		Long id = contact.getId();


		ModelAndView model = new ModelAndView();

		contact = contactDao.findById(id);

		model.addObject("contact", contact );
		model.setViewName("db");
		Set<ContactTelDetail> tel=contact.getContactTelDetails();
		model.addObject("tel", tel );
		Set<Hobby> hobbies =contact.getHobbies();
		model.addObject("hobbies", hobbies );

		return model;
	}

	@RequestMapping(value = "/first", method = RequestMethod.GET)
	public ModelAndView findByFirst() {

		ModelAndView model = new ModelAndView();
		Contact contact = contactDao.findByFirst("Максим");
		model.addObject("contact", contact );
		model.setViewName("db");
		Set<ContactTelDetail> tel=contact.getContactTelDetails();
		model.addObject("tel", tel );
		Set<Hobby> hobbies =contact.getHobbies();
		model.addObject("hobbies", hobbies );

		return model;
	}


	@RequestMapping(value = "/del_user", method = RequestMethod.GET)
	public ModelAndView del(HttpSession session) {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
		return new ModelAndView("db_del", "user", new User());
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public ModelAndView del_user(@ModelAttribute("user") User user) {
		ModelAndView model = new ModelAndView();
		String name_del= user.getName();

//		Contact contact = contactDao.findByFirst("Максим");
		Contact contact = contactDao.findByFirst(name_del);
		if (contact==null)
			{ model.addObject("user", user );
			model.addObject("message", "Login failed!" );
			model.setViewName("login-failed");
			return model;
			}
		model.addObject("contact", contact );
		model.setViewName("db_del_applay");
		Set<ContactTelDetail> tel=contact.getContactTelDetails();
		model.addObject("tel", tel );
		Set<Hobby> hobbies =contact.getHobbies();
		model.addObject("hobbies", hobbies );
		model.addObject("user", user );

		return model;
	}
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView complit(@ModelAttribute("user") User user) {
		String a=user.getName();
		Contact contact = contactDao.findByFirst(a);
		contactDao.delete(contact);
		contact=null;
		List<Contact> contacts = contactDao.findAll();

		ModelAndView model = new ModelAndView();
		model.addObject("user", user );
		model.addObject("contact", contacts );
		model.setViewName("db_del_coplit");
		return model;
	}

//новое____________________________//////////////////////////////////


	@RequestMapping(value = "/1", method = RequestMethod.GET)
	public ModelAndView main_db(HttpSession session) {



		return new ModelAndView("db_main", "contact", new Contact());
	}
	*/
}
