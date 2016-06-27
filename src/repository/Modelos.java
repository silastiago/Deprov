package repository;

import java.util.List;

import model.Modelo;

public interface Modelos {

	public List<Modelo> listar();
	public Modelo porCodigo(Integer codigo);
	public Modelo salvar(Modelo modelo);
	public void remover(Modelo modelo);
	public void editar(Modelo modelo);
	public List<Modelo> porCodigoFabricante(Integer codigo_fabricante);
}
