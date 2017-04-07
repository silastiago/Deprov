package pcrn.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pcrn.model.Fabricante;
import pcrn.repository.Fabricantes;
import pcrn.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Fabricante.class)
public class FabricanteConversor implements Converter{

	private Fabricantes fabricantes;
	
	public FabricanteConversor() {
		fabricantes = CDIServiceLocator.getBean(Fabricantes.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Fabricante fabricante = null;
		
		if (value != null) {
			fabricante = fabricantes.porCodigo(new Integer(value));
		}
		
		return fabricante;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			Fabricante fabricante = (Fabricante) value;
			return fabricante.getCodigo() == null ? null : fabricante.getCodigo().toString();

		}
		
		
		return null;
	}

}
