package validators;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

@FacesValidator("RadiusValidator")
public class RadiusValidator extends AbstractValidator {
    private final float min = 1;
    private final float max = 4;
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        try {
            float r = (float) o;
            if (r < min || r > max)
                throw new InvalidRangeException();
            if (r % 0.25 != 0)
                throw new IncorrectPresentationException();
        } catch (NullPointerException e) {
            throw new ValidatorException(createMessage("Радиус - обязательное поле"));
        } catch (InvalidRangeException e) {
            throw new ValidatorException(createMessage("Радиус должен быть от "+min +" до "+max));
        } catch (IncorrectPresentationException e) {
            throw new ValidatorException(createMessage("Данное значение не может быть получено"));
        }
    }
}
