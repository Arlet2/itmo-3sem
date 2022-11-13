package validators;

import javax.faces.application.FacesMessage;
import javax.faces.validator.Validator;

public abstract class AbstractValidator implements Validator {
    protected FacesMessage createMessage(String msg) {
        FacesMessage facesMessage = new FacesMessage(msg);
        facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        return facesMessage;
    }
}
