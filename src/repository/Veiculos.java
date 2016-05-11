package repository;

import java.util.List;

import model.Veiculo;

public interface Veiculos {

	public List<Veiculo> listar();
	public Veiculo porCodigo(Integer codigo);
	public Veiculo salvar(Veiculo veiculo);
	public void remover(Veiculo veiculo);
	public void editar(Veiculo veiculo);
	public List<Veiculo> listarPorPlaca(Veiculo veiculo);
}
