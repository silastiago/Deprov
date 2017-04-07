package pcrn.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pcrn.model.Situacao;
import pcrn.repository.Situacoes;
import pcrn.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Situacao.class)
public class SituacaoConversor implements Converter{

	private Situacoes situacoes;
	
	public SituacaoConversor() {
		situacoes = CDIServiceLocator.getBean(Situacoes.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Situacao situacao = null;
		
		if (value != null) {
			situacao = situacoes.porCodigo(new Integer(value));
		}
		
		return situacao;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			Situacao situacao = (Situacao) value;
			return situacao.getCodigo() == null ? null : situacao.getCodigo().toString();

		}
		
		return null;
	}

}
