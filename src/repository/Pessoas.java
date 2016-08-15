package repository;

import java.util.List;
import model.Pessoa;


/** Esta é uma Interface que possui os metodos da classe Pessoa,
*   
* @author silas
* @since 15-08-2016
*/
public interface Pessoas {
	
	
	public Pessoa login(Pessoa pessoa);
	public void logout();
	public List<Pessoa> listar();
	public Pessoa porCodigo(Integer codigo);
	public Pessoa salvar(Pessoa pessoa);
	public void remover(Pessoa pessoa);
	public void editar(Pessoa pessoa);
}
