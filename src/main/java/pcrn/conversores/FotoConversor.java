package pcrn.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pcrn.model.Foto;
import pcrn.repository.Fotos;
import pcrn.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Foto.class)
public class FotoConversor implements Converter{

	private Fotos fotos;
	
	public FotoConversor() {
		fotos = CDIServiceLocator.getBean(Fotos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Foto foto = null;
		
		if (value != null) {
			foto = fotos.porCodigo(new Integer(value));
		}
		
		return foto;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			Foto foto = (Foto) value;
			return foto.getCodigo() == null ? null : foto.getCodigo().toString();

		}
		
		return null;
	}

}
