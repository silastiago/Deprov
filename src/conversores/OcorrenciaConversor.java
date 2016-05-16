package conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import model.Cor;
import model.Ocorrencia;
import repository.Ocorrencias;
import util.Repositorios;


@FacesConverter(forClass=Ocorrencia.class)
public class OcorrenciaConversor implements Converter{

	private Repositorios repositorios = new Repositorios();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Ocorrencia retorno = null;
		Ocorrencias ocorrencias = repositorios.getocorrencia();
		if (value != null && !value.equals("")) {
			retorno = ocorrencias.porCodigo(new Integer(value));
		if (retorno == null) {
			String descricaoErro = "Ocorrencia não existe";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, descricaoErro, descricaoErro);
			throw new ConverterException(message);
		}

		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Integer codigo = ((Ocorrencia) value).getCodigo();
			return codigo == null ? "" : codigo.toString();
		}
		return null;
	}

}