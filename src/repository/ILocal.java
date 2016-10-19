package repository;

import java.util.List;

import model.Cor;
import model.Local;

/** Esta é uma Interface que possui as assinaturas dos metodos da classe Cor,
*   
* @author silas
* @since 15-08-2016
*/

public interface ILocal {

	/** Este metodo lista todos os Locais cadastrados.
	* 	@return retorna a lista de Locais cadastrados.
	*/
	public List<Local> listar();
	
	/** Este metodo pesquisa uma cor por seu id.
	*  	
	*  @param codigo, Este codigo é o id da cor que você está procurando.
	*  @return Cor, retorna a Cor daquele id que você está pesquisando.
	*   	
	*/
	public Local porCodigo(Integer codigo);
	
	/** Este metodo cria ou altera uma cor.
	*  	
	*  @param cor, Esta cor é o objeto cor que você irá criar ou modificar.
	*   	
	*/
	public void salvar(Local local);
	
	/** Este metodo Remove uma cor.
	*  	
	*  @param cor, Esta cor é o objeto cor que você irá remover.
	*   	
	*/
	public void remover(Local local);

	/** Este metodo pesquisa uma cor pelo seu nome.
	*  	
	*  @param cor, Este cor é o nome do corque você está procurando.
	*  @return retorna o cor daquele nome que você está pesquisando.
	*   	
	*/
	public Local pegaCodigo(String local);
}