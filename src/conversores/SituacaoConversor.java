package conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import model.Cor;
import model.Situacao;
import repository.Cores;
import repository.ISituacao;
import util.Repositorios;


@FacesConverter(forClass=Situacao.class)
public class SituacaoConversor implements Converter{

	private Repositorios repositorios = new Repositorios();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Situacao retorno = null;
		ISituacao ISituacao = repositorios.getSituacao();
		if (value != null && !value.equals("")) {
			retorno = ISituacao.porCodigo(new Integer(value));
		if (retorno == null) {
			String descricaoErro = "Estado não existe";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, descricaoErro, descricaoErro);
			throw new ConverterException(message);
		}

		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Integer codigo = ((Situacao) value).getCodigo();
			return codigo == null ? "" : codigo.toString();
		}
		return null;
	}

}