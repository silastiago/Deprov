package repository;

import java.util.List;

import model.Cor;


public interface Cores {

	public List<Cor> listar();
	public Cor porCodigo(Integer codigo);
	public Cor salvar(Cor cor);
	public void remover(Cor cor);
	public void editar(Cor cor);
}
