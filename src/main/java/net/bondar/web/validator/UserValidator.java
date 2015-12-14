package net.bondar.web.validator;

import net.bondar.web.model.Contact;
import net.bondar.web.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator{

    @Autowired
    ContactService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return Contact.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Contact contact = (Contact) target;

        if (contact.getAge() < 18) {
            errors.rejectValue("birthDate", "contact.age.error.min");
        } else if (contact.getAge() > 110) {
            errors.rejectValue("birthDate", "contact.age.error.max");
        }

        if (service.findContactByLogin(contact.getLogin()) >0) {
            errors.rejectValue("login", "contact.username.error.unique");
        }
    }
}
