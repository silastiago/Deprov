package repository;

import java.util.List;

import model.Seguro;

/** Esta é uma Interface que possui as assinaturas dos metodos da classe Seguro,
*   
* @author silas
* @since 15-08-2016
*/

public interface Seguros {

	/** Este metodo lista todas as assinaturas dos seguros cadastrados
	*   
	* @return retorna a lista de seguros cadastrados.
	*   	
	*/
	public List<Seguro> listar();
	
	/** Este metodo pesquisa um seguro por seu id.
	*  	
	*  @param codigo, Este codigo é o id do seguro que você está procurando.
	*  @return retorna o Seguro daquele id que você está pesquisando.
	*   	
	*/
	public Seguro porCodigo(Integer codigo);
	
	/** Este metodo cria ou altera um seguro.
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
	
	/** Este metodo pesquisa um seguro pelo seu nome.
	*  	
	*  @param seguro, Este seguro é o nome do seguro que você está procurando.
	*  @return retorna o seguro daquele nome que você está pesquisando.
	*   	
	*/
	public Seguro pegaCodigo(String seguro);
}