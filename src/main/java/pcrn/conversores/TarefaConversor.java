package pcrn.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pcrn.model.Tarefa;
import pcrn.repository.Tarefas;
import pcrn.util.cdi.CDIServiceLocator;

@FacesConverter(forClass=Tarefa.class)
public class TarefaConversor implements Converter{

	private Tarefas tarefas;
	
	public TarefaConversor() {
		tarefas = CDIServiceLocator.getBean(Tarefas.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Tarefa tarefa = null;
		
		if (value != null) {
			tarefa = tarefas.porCodigo(new Integer(value));
		}
		
		return tarefa;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			Tarefa tarefa = (Tarefa) value;
			return tarefa.getCodigo() == null ? null : tarefa.getCodigo().toString();

		}
		return null;
	}

}
