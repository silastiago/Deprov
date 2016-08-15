package repository;

import java.util.List;

import model.Cor;

/** Esta é uma Interface que possui os metodos da classe Cor,
*   
* @author silas
* @since 15-08-2016
*/

public interface Cores {

	/** Este metodo lista todas as cores cadastradas.
	*   
	* @return List<Cor>, retorna a lista de cores cadastradas.
	*   	
	*/
	public List<Cor> listar();
	
	/** Este metodo pesquisa uma cor por seu id.
	*  	
	*  @param codigo, Este codigo é o id da cor que você está procurando.
	*  @return Cor, retorna a Cor daquele id que você está pesquisando.
	*   	
	*/
	public Cor porCodigo(Integer codigo);
	
	/** Este metodo salva ou altera uma cor.
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
}
