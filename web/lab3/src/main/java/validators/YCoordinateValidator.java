package validators;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

@FacesValidator("YCoordinateValidator")
public class YCoordinateValidator extends AbstractValidator {
    private final float min = -5;
    private final float max = 5;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        try {
            float y = (float) o;
            if (y < min || y > max)
                throw new InvalidRangeException();
        } catch (NullPointerException e) {
            throw new ValidatorException(createMessage("Координата Y - обязательное поле"));
        } catch (InvalidRangeException e) {
            throw new ValidatorException(createMessage("Y должна быть от " + min + " до " + max));
        }
    }
}
