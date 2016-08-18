package repository;

import java.util.List;
import model.Tipo;

/** Esta é uma Interface que possui assinaturas dos metodos da classe Tipo,
*   
* @author silas
* @since 15-08-2016
*/

public interface Tipos {

	/** Este metodo lista todos os tipos de veiculos cadastrados.
	*   
	* @return retorna a lista de todos os tipos de veiculos cadastrados
	*   	
	*/
	public List<Tipo> listar();
	
	/** Este metodo pesquisa um tipo de veiculo por seu id.
	*  	
	*  @param codigo, Este codigo é o id do Tipo do Veiculo que você está procurando.
	*  @return retorna o Tipo do veiculo daquele id que você está pesquisando.
	*   	
	*/
	public Tipo porCodigo(Integer codigo);
	
	/** Este metodo cria ou altera um Tipo de Veiculo.
	*  	
	*  @param tipo, Este tipo é o objeto Tipo que você irá criar ou modificar.
	*   	
	*/
	public void salvar(Tipo tipo);
	
	/** Este metodo Remove um tipo.
	*  	
	*  @param tipo, Esta tipo é o objeto Tipo que você irá remover.
	*   	
	*/
	public void remover(Tipo tipo);
}