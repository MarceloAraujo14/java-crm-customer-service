package br.com.crmcustomer.core.domain.vo;

import br.com.crmcustomer.core.domain.enums.ContactType;

import java.io.Serializable;

import static br.com.crmcustomer.core.domain.enums.ContactType.EMAIL;
import static br.com.crmcustomer.core.domain.enums.ContactType.PHONE;
import static br.com.crmcustomer.core.util.format.Formatter.formatEmail;
import static br.com.crmcustomer.core.util.format.Formatter.formatPhone;
import static br.com.crmcustomer.core.util.validation.EmailValidator.validadeEmail;
import static br.com.crmcustomer.core.util.validation.PhoneNumberValidator.validadePhone;

public class Contact implements Serializable {

    private final ContactType contactType;
    private final String contactContent;

    public Contact(String contactContent) {
        ContactType type = defineContactType(contactContent);
        if(type.equals(PHONE)) {
            contactContent = formatPhone(contactContent); validadePhone(contactContent);
        }
        if(type.equals(EMAIL)) {
            contactContent = formatEmail(contactContent); validadeEmail(contactContent);
        }
        this.contactType = type;
        this.contactContent = contactContent;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public String getContactContent() {
        return contactContent;
    }

    @Override
    public String toString() {
        return  "contactType=" + contactType +
                ", contactContent='" + contactContent + '\'';
    }

    private ContactType defineContactType(String contactContent){
        if(contactContent.contains("@") || contactContent.contains(".com")
                || contactContent.contains(".br") || contactContent.contains(".org")) return EMAIL;
        return PHONE;
    }
}
