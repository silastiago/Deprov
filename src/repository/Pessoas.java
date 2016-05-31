package repository;

import java.util.List;

import org.hibernate.Session;

import model.Pessoa;

public interface Pessoas {
	
	public Pessoa login(Pessoa pessoa);
	public void logout();
	public List<Pessoa> listar();
	public Pessoa porCodigo(Integer codigo);
	public Pessoa salvar(Pessoa pessoa);
	public void remover(Pessoa pessoa);
	public void editar(Pessoa pessoa);
}
