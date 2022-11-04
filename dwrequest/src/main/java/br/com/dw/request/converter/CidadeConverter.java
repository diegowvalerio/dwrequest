package br.com.dw.request.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dw.request.classe.Cidade;



@FacesConverter(forClass = Cidade.class,value="conversorCidade")
public class CidadeConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Cidade) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Cidade) {
        	Cidade entity= (Cidade) value;
            if (entity != null && entity instanceof Cidade && entity.getIdcidade() != null) {
                uiComponent.getAttributes().put( entity.getIdcidade().toString(), entity);
                return entity.getIdcidade().toString();
            }
        }
        return "";
    }
}