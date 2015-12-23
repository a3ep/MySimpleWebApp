package net.bondar.web.validator;

import net.bondar.web.model.Contact;
import net.bondar.web.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by AzeraL on 23.12.2015.
 */
@Component
public class UserSingInValidator implements Validator {

    @Autowired
    ContactService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return Contact.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Contact contact = (Contact) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.contact.userName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.contact.password");

        Contact contactInDB = service.findContactByUserName(contact.getUserName());

        if(contactInDB==null){
            errors.rejectValue("userName", "contact.userName.error.wrongUserName");
        }else if(!contactInDB.getPassword().equals(contact.getPassword())) {
            errors.rejectValue("password", "contact.password.error.wrongPass");
        }
    }
}
