package pcrn.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import pcrn.interfaces.ITarefa;
import pcrn.model.Ocorrencia;
import pcrn.model.Tarefa;

public class Tarefas implements ITarefa, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	
	@Override
	public Tarefa porCodigo(int codigo) {
		return manager.find(Tarefa.class, codigo);
	}

	@Override
	public List<Tarefa> porCodigoVeiculo(int codigoVeiculo) {
		
		List<Tarefa> listaTarefas = new ArrayList<Tarefa>();
		Query query = manager.createQuery("from Tarefa where codigo_veiculo = :codigo_veiculo");
		query.setParameter("codigo_veiculo", codigoVeiculo);
		listaTarefas = query.getResultList();
		
		return listaTarefas;
	}

	@Override
	public void salvar(Tarefa tarefa) {
		manager.merge(tarefa);
	}

	@Override
	public void remover(Tarefa tarefa) {
		Tarefa tarefaTemporaria = manager.find(Tarefa.class, tarefa.getCodigo());
		manager.remove(tarefaTemporaria);		
	}
}
