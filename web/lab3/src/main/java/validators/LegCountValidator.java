package validators;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

@FacesValidator("LegCountValidator")
public class LegCountValidator extends AbstractValidator {
    private final int min = 0;
    private final int max = 8;
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        try {
            float legCount = (float) o;
            if (legCount < min || legCount > max)
                throw new InvalidRangeException();
            if (legCount % 1 != 0)
                throw new IncorrectFormatException();
        } catch (NullPointerException e) {
            throw new ValidatorException(createMessage("Количество ног - обязательное поле"));
        } catch (InvalidRangeException e) {
            throw new ValidatorException(createMessage("Количество ног должно быть от " + min + " до " + max));
        } catch (IncorrectFormatException e) {
            throw new ValidatorException(createMessage("Количество ног - целое число"));
        }
    }
}
