package conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import model.Modelo;
import repository.Modelos;
import util.Repositorios;


@FacesConverter(forClass=Modelo.class)
public class ModeloConversor implements Converter{

	private Repositorios repositorios = new Repositorios();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Modelo retorno = null;
		Modelos modelos = repositorios.getModelos();
		if (value != null && !value.equals("")) {
			retorno = modelos.porCodigo(new Integer(value));
		if (retorno == null) {
			String descricaoErro = "Cidade não existe";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, descricaoErro, descricaoErro);
			throw new ConverterException(message);
		}

		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Integer codigo = ((Modelo) value).getCodigo();
			return codigo == null ? "" : codigo.toString();
		}
		return null;
	}

}