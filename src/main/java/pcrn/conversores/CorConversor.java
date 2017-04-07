package pcrn.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pcrn.model.Cor;
import pcrn.repository.Cores;
import pcrn.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Cor.class)
public class CorConversor implements Converter{

	private Cores cores;
	
	public CorConversor() {
		cores = CDIServiceLocator.getBean(Cores.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cor cor = null;
		
		if (value != null) {
			cor = cores.porCodigo(new Integer(value));
		}
		
		return cor;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			Cor cor = (Cor) value;
			return cor.getCodigo() == null ? null : cor.getCodigo().toString();

		}
		
		return null;
	}

}
