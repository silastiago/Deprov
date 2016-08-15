package repository;

import java.util.List;

import model.Fabricante;

/** Esta é uma Interface que possui os metodos da classe Fabricante,
*   
* @author silas
* @since 15-08-2016
*/

public interface Fabricantes {

	/** Este metodo lista de todos os Fabricantes cadastrados.
	*   
	* @return List<Fabricante>, retorna a lista de todos os Fabricantes cadastrados.
	*   	
	*/
	public List<Fabricante> listar();
	
	/** Este metodo pesquisa um Fabricante por seu id.
	*  	
	*  @param codigo, Este codigo é o id do Fabricante que você está procurando.
	*  @return fabricante, retorna o Fabricante daquele id que você está pesquisando.
	*   	
	*/
	public Fabricante porCodigo(Integer codigo);
	
	/** Este metodo salva ou altera um fabricante.
	*  	
	*  @param fabricante, Esta fabricante é o objeto Fabricante que você irá criar ou modificar.
	*   	
	*/
	public void  salvar(Fabricante fabricante);
	
	/** Este metodo Remove um Fabricante.
	*  	
	*  @param fabricante, Esta fabricante é o objeto Fabricante que você irá remover.
	*   	
	*/
	public void remover(Fabricante fabricante);
}
