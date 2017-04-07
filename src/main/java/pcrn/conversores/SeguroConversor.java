package pcrn.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pcrn.model.Seguro;
import pcrn.repository.Seguros;
import pcrn.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Seguro.class)
public class SeguroConversor implements Converter{

	private Seguros seguros;
	
	public SeguroConversor() {
		seguros = CDIServiceLocator.getBean(Seguros.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Seguro seguro = null;
		
		if (value != null) {
			seguro = seguros.porCodigo(new Integer(value));
		}
		
		return seguro;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			Seguro seguro = (Seguro) value;
			return seguro.getCodigo() == null ? null : seguro.getCodigo().toString();

		}
		
		return null;
	}

}
