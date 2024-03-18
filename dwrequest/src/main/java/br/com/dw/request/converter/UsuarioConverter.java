package br.com.dw.request.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.dw.request.entidades.Usuario;

@FacesConverter(forClass = Usuario.class,value="conversorUsuario")
public class UsuarioConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        if (value != null && !value.isEmpty()) {
            return (Usuario) uiComponent.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        if (value instanceof Usuario) {
        	Usuario entity= (Usuario) value;
            if (entity != null && entity instanceof Usuario && entity.getIdusuario() != null) {
                uiComponent.getAttributes().put( entity.getIdusuario().toString(), entity);
                return entity.getIdusuario().toString();
            }
        }
        return "";
    }
}