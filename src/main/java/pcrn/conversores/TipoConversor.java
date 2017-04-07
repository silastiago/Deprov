package pcrn.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pcrn.model.Tipo;
import pcrn.repository.Tipos;
import pcrn.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Tipo.class)
public class TipoConversor implements Converter{

	private Tipos tipos;
	
	public TipoConversor() {
		tipos = CDIServiceLocator.getBean(Tipos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Tipo tipo = null;
		
		if (value != null) {
			tipo = tipos.porCodigo(new Integer(value));
		}
		
		return tipo;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			Tipo tipo = (Tipo) value;
			return tipo.getCodigo() == null ? null : tipo.getCodigo().toString();

		}
		
		return null;
	}

}
