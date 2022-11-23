package validators;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

@FacesValidator("MustacheLengthValidator")
public class MustacheLengthValidator extends AbstractValidator {
    private final float min = 0;
    private final float max = 5;
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        try {
            float legCount = (float) o;
            if (legCount < min || legCount > max)
                throw new InvalidRangeException();
        } catch (NullPointerException e) {
            throw new ValidatorException(createMessage("Длина усов - обязательное поле"));
        } catch (InvalidRangeException e) {
            throw new ValidatorException(createMessage("Длина усов должна быть от " + min + " до " + max));
        }
    }
}
