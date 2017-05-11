package pcrn.interfaces;

import java.util.Date;
import java.util.List;

import pcrn.model.Tarefa;


public interface ITarefa {

	public Tarefa porCodigo(int codigo);
	
	public List<Tarefa> porCodigoVeiculo(int codigoVeiculo);
	
	public void salvar(Tarefa tarefa);
	
	public void remover(Tarefa tarefa);

	public List<Tarefa> porCodigoVeiculoEData(Date dataTarefa);	
}