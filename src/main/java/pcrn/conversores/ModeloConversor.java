package pcrn.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pcrn.model.Modelo;
import pcrn.repository.Modelos;
import pcrn.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Modelo.class)
public class ModeloConversor implements Converter{

	private Modelos modelos;
	
	public ModeloConversor() {
		modelos = CDIServiceLocator.getBean(Modelos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Modelo modelo = null;
		
		if (value != null) {
			modelo = modelos.porCodigo(new Integer(value));
		}
		
		return modelo;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			Modelo modelo = (Modelo) value;
			return modelo.getCodigo() == null ? null : modelo.getCodigo().toString();

		}
		return null;
	}

}
