package pcrn.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pcrn.model.Ocorrencia;
import pcrn.repository.Ocorrencias;
import pcrn.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Ocorrencia.class)
public class OcorrenciaConversor implements Converter{

	private Ocorrencias ocorrencias;
	
	public OcorrenciaConversor() {
		ocorrencias = CDIServiceLocator.getBean(Ocorrencias.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Ocorrencia ocorrencia = null;
		
		if (value != null) {
			ocorrencia = ocorrencias.porCodigo(new Integer(value));
		}
		
		return ocorrencia;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			Ocorrencia ocorrencia = (Ocorrencia) value;
			return ocorrencia.getCodigo() == null ? null : ocorrencia.getCodigo().toString();

		}
		return null;
	}

}
