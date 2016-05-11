package repository;

import java.util.List;

import model.Seguro;


public interface Seguros {

	public List<Seguro> listar();
	public Seguro porCodigo(Integer codigo);
	public Seguro salvar(Seguro seguro);
	public void remover(Seguro seguro);
	public void editar(Seguro seguro);
}
