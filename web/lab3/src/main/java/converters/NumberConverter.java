package converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("NumberConverter")
public class NumberConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        s = processString(s);
        try {
            return Float.parseFloat(s);
        } catch (NumberFormatException e) {
            FacesMessage msg = new FacesMessage("Поле должно быть числом");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ConverterException(msg);
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return o.toString();
    }

    private String processString(String input) {
        return input
                .replace(",", ".")
                .replace(" ", "");
    }
}
