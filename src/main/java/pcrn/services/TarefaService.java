package pcrn.services;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import pcrn.model.Tarefa;
import pcrn.repository.Tarefas;
import pcrn.util.jpa.Transactional;

public class TarefaService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Tarefas tarefas;
	
	@Transactional
	public Tarefa porCodigo(int codigo){
		return tarefas.porCodigo(codigo);
	}
	
	@Transactional
	public void salvar(Tarefa tarefa){
		tarefas.salvar(tarefa);
	}
	
	@Transactional
	public void remover(Tarefa tarefa){
		tarefas.remover(tarefa);
	}
	
	@Transactional
	public List<Tarefa> porCodigoVeiculo(int codigoVeiculo){
		return tarefas.porCodigoVeiculo(codigoVeiculo);
	}
	
	@Transactional
	public List<Tarefa> porCodigoVeiculoEData(Date dataAtual){
		return tarefas.porCodigoVeiculoEData(dataAtual);
	}
	
}
