package repository;

import java.util.List;

import model.Cor;
import model.Ocorrencia;


public interface Ocorrencias {

	public List<Ocorrencia> listar();
	public Ocorrencia porCodigo(Integer codigo);
	public List<Ocorrencia> porCodigoVeiculo(Integer codigo);
	public Ocorrencia salvar(Ocorrencia ocorrencia);
	public void remover(Ocorrencia ocorrencia);
	public void editar(Ocorrencia ocorrencia);
}
