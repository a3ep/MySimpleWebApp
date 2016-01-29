package net.bondar.web.validator;

import net.bondar.web.model.Contact;
import net.bondar.web.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    ContactService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return Contact.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Contact contact = (Contact) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.contact.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.contact.lastName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.contact.userName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.contact.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.contact.confirmPassword");

        if (contact.getFirstName().length() < 2) {
            errors.rejectValue("firstName", "contact.firstName.error.minLength");
        } else if (contact.getFirstName().length() > 20) {
            errors.rejectValue("firstName", "contact.firstName.error.maxLength");
        }

        if (contact.getLastName().length() < 2) {
            errors.rejectValue("lastName", "contact.lastName.error.minLength");
        } else if (contact.getLastName().length() > 20) {
            errors.rejectValue("lastName", "contact.lastName.error.maxLength");
        }

        if (contact.getAge() < 18) {
            errors.rejectValue("birthDate", "contact.age.error.min");
        } else if (contact.getAge() > 110) {
            errors.rejectValue("birthDate", "contact.age.error.max");
        }

        if (service.count(contact.getUserName()) > 0) {
            errors.rejectValue("userName", "contact.username.error.unique");
        }

        if (contact.getUserName().length() < 6) {
            errors.rejectValue("userName", "contact.userName.error.minLength");
        } else if (contact.getUserName().length() > 16) {
            errors.rejectValue("userNameReg", "contact.userName.error.maxLength");
        }

        if (contact.getPassword().length() < 6 || contact.getPassword().length() > 16) {
            contact.setPassword("");
            contact.setConfirmPassword("");
            errors.rejectValue("password", "contact.password.error.length");
        }

        if (!contact.getPassword().equals(contact.getConfirmPassword())) {
            contact.setPassword("");
            contact.setConfirmPassword("");
            errors.rejectValue("password", "contact.password.error.equals");
        }
    }
}
