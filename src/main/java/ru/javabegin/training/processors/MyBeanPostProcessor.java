package ru.javabegin.training.processors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ru.javabegin.training.db.Contact;
import ru.javabegin.training.db.ContactService;
import ru.javabegin.training.db.ContactTelDetail;
import ru.javabegin.training.db.Hobby;

import java.util.List;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {


	@Override
	public Object postProcessAfterInitialization(Object object, String name) throws BeansException {
		System.err.println("postProcessAfterInitialization(): " + object);
		/*List<Contact> contact = contactService.findByCriteriaQuery("John", "Smith");
		listContactsWithDetail(contact);*/

		return object;
	}

	@Override
	public Object postProcessBeforeInitialization(Object object, String name) throws BeansException {
		return object;
	}


/*	private static void listContactsWithDetail(List<Contact> contacts) {
		System.out.println("");
		System.out.println("Listing contacts with details:");

		for (Contact contact: contacts) {
			System.out.println(contact);
			if (contact.getContactTelDetails() != null) {
				for (ContactTelDetail contactTelDetail:
						contact.getContactTelDetails()) {
					System.out.println(contactTelDetail);
				}
			}

			if (contact.getHobbies() != null) {
				for (Hobby hobby: contact.getHobbies()) {
					System.out.println(hobby);
				}
			}

			System.out.println();
		}
	}*/


}
