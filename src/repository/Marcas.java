package repository;

import java.util.List;

import model.Marca;

public interface Marcas {

	public List<Marca> listar();
	public Marca porCodigo(Integer codigo);
	public Marca salvar(Marca fabricante);
	public void remover(Marca fabricante);
	public void editar(Marca fabricante);
}
