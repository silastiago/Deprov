package pcrn.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pcrn.model.Veiculo;
import pcrn.repository.Veiculos;
import pcrn.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Veiculo.class)
public class VeiculoConversor implements Converter{

	private Veiculos veiculos;
	
	public VeiculoConversor() {
		veiculos = CDIServiceLocator.getBean(Veiculos.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Veiculo veiculo = null;
		
		if (value != null) {
			veiculo = veiculos.porCodigo(new Integer(value));
		}
		
		return veiculo;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			Veiculo veiculo = (Veiculo) value;
			return veiculo.getCodigo() == null ? null : veiculo.getCodigo().toString();

		}
		
		return null;
	}

}
