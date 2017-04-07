package pcrn.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pcrn.model.Pericia;
import pcrn.repository.Pericias;
import pcrn.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Pericia.class)
public class PericiaConversor implements Converter{

	private Pericias pericias;
	
	public PericiaConversor() {
		pericias = CDIServiceLocator.getBean(Pericias.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Pericia pericia = null;
		
		if (value != null) {
			pericia = pericias.porCodigo(new Integer(value));
		}
		
		return pericia;		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			Pericia pericia = (Pericia) value;
			return pericia.getCodigo() == null ? null : pericia.getCodigo().toString();

		}
		
		return null;
	}

}
