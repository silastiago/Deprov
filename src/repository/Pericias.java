package repository;

import java.util.List;

import model.Cor;
import model.Pericia;


public interface Pericias {

	public List<Pericia> listar();
	public Pericia porCodigo(Integer codigo);
	public Pericia salvar(Pericia pericia);
	public void remover(Pericia pericia);
	public void editar(Pericia ericia);
}
