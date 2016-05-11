package repository;

import java.util.List;

import model.Fabricante;
import model.Tipo;

public interface Tipos {

	public List<Tipo> listar();
	public Tipo porCodigo(Integer codigo);
	public Tipo salvar(Tipo tipo);
	public void remover(Tipo tipo);
	public void editar(Tipo tipo);
}
