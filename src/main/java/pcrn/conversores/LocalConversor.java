package pcrn.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pcrn.model.Local;
import pcrn.repository.Locais;
import pcrn.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Local.class)
public class LocalConversor implements Converter{

	private Locais locais;
	
	public LocalConversor() {
		locais = CDIServiceLocator.getBean(Locais.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Local local= null;
		
		if (value != null) {
			local = locais.porCodigo(new Integer(value));
		}
		
		return local;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			Local local = (Local) value;
			return local.getCodigo() == null ? null : local.getCodigo().toString();

		}
		
		return null;
	}

}
