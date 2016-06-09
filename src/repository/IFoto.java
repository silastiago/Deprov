package repository;

import java.util.List;

import model.Foto;


public interface IFoto {

	public List<Foto> listar();
	public Foto porCodigo(Integer codigo);
	public Foto salvar(Foto foto);
	public void remover(Foto foto);
	public void editar(Foto foto);
}
