package repository;

import java.util.List;

import model.Cor;
import model.Grupo;


public interface Grupos {

	public List<Grupo> listar();
	public Grupo porCodigo(Integer codigo);
	public Grupo salvar(Grupo grupo);
	public void remover(Grupo grupo);
	public void editar(Grupo grupo);
}
