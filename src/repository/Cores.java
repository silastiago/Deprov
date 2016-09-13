package repository;

import java.util.List;

import model.Cor;

/** Esta é uma Interface que possui as assinaturas dos metodos da classe Cor,
*   
* @author silas
* @since 15-08-2016
*/

public interface Cores {

	/** Este metodo lista todas as cores cadastradas.
	* 	@return retorna a lista de cores cadastradas.
	*/
	public List<Cor> listar();
	
	/** Este metodo pesquisa uma cor por seu id.
	*  	
	*  @param codigo, Este codigo é o id da cor que você está procurando.
	*  @return Cor, retorna a Cor daquele id que você está pesquisando.
	*   	
	*/
	public Cor porCodigo(Integer codigo);
	
	/** Este metodo cria ou altera uma cor.
	*  	
	*  @param cor, Esta cor é o objeto cor que você irá criar ou modificar.
	*   	
	*/
	public void salvar(Cor cor);
	
	/** Este metodo Remove uma cor.
	*  	
	*  @param cor, Esta cor é o objeto cor que você irá remover.
	*   	
	*/
	public void remover(Cor cor);

	/** Este metodo pesquisa uma cor pelo seu nome.
	*  	
	*  @param cor, Este cor é o nome do corque você está procurando.
	*  @return retorna o cor daquele nome que você está pesquisando.
	*   	
	*/
	public Cor pegaCodigo(String cor);
}