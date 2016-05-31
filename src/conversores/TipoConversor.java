package conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import model.Marca;
import model.Tipo;
import repository.Tipos;
import util.Repositorios;


@FacesConverter(forClass=Tipo.class)
public class TipoConversor implements Converter{

	private Repositorios repositorios = new Repositorios();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Tipo retorno = null;
		Tipos tipos = repositorios.getTipos();
		if (value != null && !value.equals("")) {
			retorno = tipos.porCodigo(new Integer(value));
		if (retorno == null) {
			String descricaoErro = "Marca não existe";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, descricaoErro, descricaoErro);
			throw new ConverterException(message);
		}

		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Integer codigo = ((Tipo) value).getCodigo();
			return codigo == null ? "" : codigo.toString();
		}
		return null;
	}

}