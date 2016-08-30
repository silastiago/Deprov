package repository;

import java.util.List;

import model.Modelo;

/** Esta é uma Interface que possui as assinaturas dos metodos da classe Modelo,
*   
* @author silas
* @since 15-08-2016
*/

public interface Modelos {

	/** Este metodo lista todos os Modelos cadastrados.
	*   
	* @return retorna a lista de modelos cadastrados.
	*   	
	*/
	public List<Modelo> listar();
	
	/** Este metodo pesquisa um modelo por seu id.
	*  	
	*  @param codigo, Este codigo é o id do modelo que você está procurando.
	*  @return retorna o modelo daquele id que você está pesquisando.
	*   	
	*/
	public Modelo porCodigo(Integer codigo);
	
	/** Este metodo cria ou altera um modelo.
	*  	
	*  @param modelo, Este modelo é o objeto Modelo que você irá criar ou modificar.
	*   	
	*/
	public void salvar(Modelo modelo);
	
	/** Este metodo Remove um modelo.
	*  	
	*  @param modelo, Este modelo é o objeto Modelo que você irá remover.
	*   	
	*/
	public void remover(Modelo modelo);

	public Modelo pegaCodigo(String modelo);
}