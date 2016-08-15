package repository;

import java.util.List;

import model.Seguro;

/** Esta é uma Interface que possui os metodos da classe Seguro,
*   
* @author silas
* @since 15-08-2016
*/

public interface Seguros {

	/** Este metodo lista todos os seguros cadastrados
	*   
	* @return List<Seguro>, retorna a lista de seguros cadastrados.
	*   	
	*/
	public List<Seguro> listar();
	
	/** Este metodo pesquisa um seguro por seu id.
	*  	
	*  @param codigo, Este codigo é o id do seguro que você está procurando.
	*  @return Seguro, retorna a Seguro daquele id que você está pesquisando.
	*   	
	*/
	public Seguro porCodigo(Integer codigo);
	
	/** Este metodo salva ou altera um seguro.
	*  	
	*  @param seguro, Esta seguro é o objeto Seguro  que você irá criar ou modificar.
	*   	
	*/
	public void salvar(Seguro seguro);
	
	/** Este metodo Remove um seguro.
	*  	
	*  @param seguro, Esta seguro é o objeto Seguro que você irá remover.
	*   	
	*/
	public void remover(Seguro seguro);
}