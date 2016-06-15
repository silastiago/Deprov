package repository;

import java.util.List;

import model.Fabricante;

public interface Fabricantes {

	public List<Fabricante> listar();
	public Fabricante porCodigo(Integer codigo);
	public Fabricante salvar(Fabricante fabricante);
	public void remover(Fabricante fabricante);
	public void editar(Fabricante fabricante);
}
